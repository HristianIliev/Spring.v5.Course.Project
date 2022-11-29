package hristian.iliev.spring.course.stock.comparison.comparison;

import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.ComparisonCalculations;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Tag;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;

import java.util.List;

public interface ComparisonService {

  public List<Comparison> retrieveComparisonsByUser(long userId);

  public void addComparisonToUser(User user, String firstStockName, String secondStockName);

  public void tagComparison(Long id, String firstStockName, String secondStockName, Tag tag);

  public void deleteTagOf(Comparison comparison);

  public List<Tag> retrieveTagsOfUser(Long id);

  public void deleteComparison(User user, String firstStockName, String secondStockName);
}
