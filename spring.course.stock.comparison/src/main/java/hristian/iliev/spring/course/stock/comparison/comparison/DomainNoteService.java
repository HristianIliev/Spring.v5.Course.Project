package hristian.iliev.spring.course.stock.comparison.comparison;

import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Note;
import hristian.iliev.spring.course.stock.comparison.comparison.repository.ComparisonRepository;
import hristian.iliev.spring.course.stock.comparison.comparison.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DomainNoteService implements NoteService {

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private ComparisonRepository comparisonRepository;

  @Override
  public void deleteNote(int noteId) {
    noteRepository.deleteById(new Long(noteId));
  }

  @Override
  public void createNote(Note note) {
    System.out.println(note);
    List<Comparison> comparisons = (List<Comparison>) comparisonRepository.findAll();
    for (Comparison comparison : comparisons) {
      if (comparison.getFirstStockName().equals(note.getComparison().getFirstStockName()) && comparison.getSecondStockName().equals(note.getComparison().getSecondStockName())) {
        note.setComparison(comparison);

        break;
      }
    }

    note.setCreatedAt(LocalDate.now());

    noteRepository.save(note);
  }

}
