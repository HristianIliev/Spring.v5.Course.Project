package hristian.iliev.spring.course.stock.comparison.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping("/login")
  public String serveLogin(Model model) {
    return "login";
  }

  @GetMapping("/register")
  public String serveRegister(Model model) {
    return "register";
  }

}
