package hristian.iliev.spring.course.stock.comparison.stocks.repository;

import hristian.iliev.spring.course.stock.comparison.stocks.entity.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>, QuoteRepositoryCustom {
}
