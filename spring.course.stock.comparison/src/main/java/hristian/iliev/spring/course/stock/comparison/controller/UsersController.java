package hristian.iliev.spring.course.stock.comparison.controller;

import hristian.iliev.spring.course.stock.comparison.Application;
import hristian.iliev.spring.course.stock.comparison.events.Event;
import hristian.iliev.spring.course.stock.comparison.users.UsersService;
import hristian.iliev.spring.course.stock.comparison.users.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UsersController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private UsersService usersService;

  @PostMapping("/api/register")
  public ResponseEntity register(@RequestBody User user) {
    if (usersService.loadUserByUsername(user.getUsername()) != null) {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }

    if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    usersService.save(user);

    Event event = new Event.Builder()
        .withAction(Event.EventAction.CREATE)
        .withEntityClass(User.class.getName())
        .withUsername(user.getUsername())
        .withMessage("New user with " + user.getUsername() + " has been created.")
        .build();

    rabbitTemplate.convertAndSend(Application.topicExchangeName, "analytics.users", event.toJson());

    return new ResponseEntity(HttpStatus.OK);
  }

}
