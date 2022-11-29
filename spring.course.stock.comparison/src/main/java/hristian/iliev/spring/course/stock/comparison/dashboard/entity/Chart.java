package hristian.iliev.spring.course.stock.comparison.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chart {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private Long id;

  private int periods;

  @ManyToOne
  @JsonIgnore
  private Dashboard dashboard;

  private String firstStockName;
  private String secondStockName;

  private String type;

}
