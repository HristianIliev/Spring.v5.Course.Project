package hristian.iliev.spring.course.stock.comparison.stocks;

import hristian.iliev.spring.course.stock.comparison.comparison.entity.Comparison;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.ComparisonCalculations;
import hristian.iliev.spring.course.stock.comparison.comparison.entity.DiagramData;

import java.io.IOException;
import java.time.LocalDateTime;

public interface StockQuoteService {

  public void updateStockQuotes() throws IOException, InterruptedException;

  public ComparisonCalculations calculateComparisonData(Comparison comparison, int periods);

  public boolean quotesForStockWithNameDoNotExist(String stockName);

  public DiagramData calculateComparisonDiagramData(Comparison comparison, int periods);

}
