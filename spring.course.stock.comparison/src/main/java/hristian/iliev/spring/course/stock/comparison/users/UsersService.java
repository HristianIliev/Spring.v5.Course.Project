package hristian.iliev.spring.course.stock.comparison.users;

import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

  public void save(User user);

  public User retrieveUserByUsername(String username);

}
