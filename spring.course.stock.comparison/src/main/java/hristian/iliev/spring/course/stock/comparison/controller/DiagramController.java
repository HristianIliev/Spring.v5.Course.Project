package hristian.iliev.spring.course.stock.comparison.controller;

import hristian.iliev.spring.course.stock.comparison.Application;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.DiagramData;
import hristian.iliev.spring.course.stock.comparison.dashboard.entity.Chart;
import hristian.iliev.spring.course.stock.comparison.dashboard.entity.Dashboard;
import hristian.iliev.spring.course.stock.comparison.events.Event;
import hristian.iliev.spring.course.stock.comparison.stocks.StockQuoteService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiagramController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private StockQuoteService quoteService;

  @GetMapping("/api/diagrams")
  @ResponseBody
  public DiagramData generateDiagram(@RequestParam("firstStockName") String firstStockName, @RequestParam("secondStockName") String secondStockName, @RequestParam(value = "periods", required = false, defaultValue = "200") int periods) {
    Comparison comparison = new Comparison();
    comparison.setFirstStockName(firstStockName);
    comparison.setSecondStockName(secondStockName);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Event event = new Event.Builder()
        .withAction(Event.EventAction.RETRIEVE)
        .withEntityClass(Chart.class.getName())
        .withUsername(username)
        .withMessage("User with " + username + " generated a diagram for stocks: " + firstStockName + ":" + secondStockName)
        .build();

    rabbitTemplate.convertAndSend(Application.topicExchangeName, "analytics.diagrams", event.toJson());

    return quoteService.calculateComparisonDiagramData(comparison, periods);
  }

}
