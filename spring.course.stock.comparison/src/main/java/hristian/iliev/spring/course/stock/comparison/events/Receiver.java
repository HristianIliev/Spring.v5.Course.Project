package hristian.iliev.spring.course.stock.comparison.events;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
  }

}
