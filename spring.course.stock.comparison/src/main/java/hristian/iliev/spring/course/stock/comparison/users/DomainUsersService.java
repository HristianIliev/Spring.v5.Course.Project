package hristian.iliev.spring.course.stock.comparison.users;

import com.google.common.hash.Hashing;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import hristian.iliev.spring.course.stock.comparison.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service
public class DomainUsersService implements UsersService {

  @Autowired
  private UsersRepository repository;

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    Iterable<User> users = repository.findAll();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }

    return null;
  }

  @Override
  @Transactional
  public void save(User user) {

    String sha256EncodedString = Hashing.sha256()
        .hashString(user.getPassword(), StandardCharsets.UTF_8)
        .toString();

    user.setPassword(sha256EncodedString);

    repository.save(user);
  }

  @Override
  public User retrieveUserByUsername(String username) {
    return loadUserByUsername(username);
  }

}
