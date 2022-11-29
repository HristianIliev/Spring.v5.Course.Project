package hristian.iliev.spring.course.stock.comparison.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Dashboard {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  private Long id;

  @OneToMany(mappedBy="dashboard")
  private List<Chart> charts;

  @ManyToOne
  @JsonIgnore
  private User user;

  private String name;

  private String description;

}
