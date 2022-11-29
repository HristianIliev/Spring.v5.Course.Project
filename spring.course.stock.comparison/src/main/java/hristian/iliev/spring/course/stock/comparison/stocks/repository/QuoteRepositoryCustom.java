package hristian.iliev.spring.course.stock.comparison.stocks.repository;

import hristian.iliev.spring.course.stock.comparison.stocks.entity.Quote;

import java.util.List;

public interface QuoteRepositoryCustom {

  public List<Quote> findAllByStockName(String stockName);

}
