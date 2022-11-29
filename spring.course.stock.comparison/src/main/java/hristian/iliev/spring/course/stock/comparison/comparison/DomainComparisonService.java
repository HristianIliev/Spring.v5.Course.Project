package hristian.iliev.spring.course.stock.comparison.comparison;

import hristian.iliev.spring.course.stock.comparison.Application;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Note;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Tag;
import hristian.iliev.spring.course.stock.comparison.comparison.repository.ComparisonRepository;
import hristian.iliev.spring.course.stock.comparison.comparison.repository.NoteRepository;
import hristian.iliev.spring.course.stock.comparison.comparison.repository.TagRepository;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainComparisonService implements ComparisonService {

  @Autowired
  private ComparisonRepository comparisonRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private NoteRepository noteRepository;

  @Override
  public List<Comparison> retrieveComparisonsByUser(long userId) {
    Iterable<Comparison> comparisons = comparisonRepository.findAll();

    List<Comparison> result = new ArrayList<>();
    for (Comparison comparison : comparisons) {
      if (comparison.getUser().getId() == userId) {
        result.add(comparison);
      }
    }

    System.out.println(result.get(0).getFirstStockName());
    return result;
  }

  @Override
  public void addComparisonToUser(User user, String firstStockName, String secondStockName) {
    Comparison comparison = new Comparison();
    comparison.setFirstStockName(firstStockName);
    comparison.setSecondStockName(secondStockName);

    comparison.setUser(user);

    comparisonRepository.save(comparison);
  }

  @Override
  public void tagComparison(Long id, String firstStockName, String secondStockName, Tag tag) {
    Iterable<Comparison> comparisons = comparisonRepository.findAll();
    for (Comparison comparison : comparisons) {
      if (comparison.getUser().getId().equals(id) && comparison.getFirstStockName().equals(firstStockName) && comparison.getSecondStockName().equals(secondStockName)) {
        tag.setUserId(id);

        tagRepository.save(tag);

        comparison.setTag(tag);

        comparisonRepository.save(comparison);

        break;
      }
    }
  }

  @Override
  public void deleteTagOf(Comparison comparison) {
    if (comparison.getTag() != null) {
      Long toDelete = comparison.getTag().getId();

      comparison.setTag(null);

      comparisonRepository.save(comparison);

      tagRepository.deleteById(toDelete);
    }
  }

  @Override
  public List<Tag> retrieveTagsOfUser(Long id) {
    List<Tag> result = new ArrayList<>();

    List<Tag> tags = (List<Tag>) tagRepository.findAll();
    for (Tag tag : tags) {
      if (tag.getUserId().equals(id)) {
        result.add(tag);
      }
    }

    return result;
  }

  @Override
  public void deleteComparison(User user, String firstStockName, String secondStockName) {
    List<Comparison> comparisons = this.retrieveComparisonsByUser(user.getId());

    for (Comparison comparison : comparisons) {
      if (comparison.getFirstStockName().equals(firstStockName) && comparison.getSecondStockName().equals(secondStockName)) {
        for (Note note : comparison.getNotes()) {
          noteRepository.delete(note);
        }

        comparisonRepository.delete(comparison);

        break;
      }
    }
  }

}
