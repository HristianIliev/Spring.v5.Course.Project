package hristian.iliev.spring.course.stock.comparison.comparison.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiagramData {

  private ComparisonCalculations calculations;
  private List<DataPoint> dataPoints;

}
