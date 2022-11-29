package hristian.iliev.spring.course.stock.comparison.users.repository;

import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}
