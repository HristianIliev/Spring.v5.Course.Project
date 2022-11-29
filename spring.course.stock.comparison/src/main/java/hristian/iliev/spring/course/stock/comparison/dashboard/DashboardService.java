package hristian.iliev.spring.course.stock.comparison.dashboard;

import hristian.iliev.spring.course.stock.comparison.dashboard.entity.Chart;
import hristian.iliev.spring.course.stock.comparison.dashboard.entity.Dashboard;

import java.util.List;

public interface DashboardService {

  public void addChartToDashboard(String username, String dashboardName, Chart chart);

  public void saveDashboard(Dashboard dashboard);

  public void deleteDashboard(Long dashboardId);

  public Dashboard retrieveById(Long dashboardId);
}
