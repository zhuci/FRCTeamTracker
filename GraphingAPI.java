import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphingAPI extends Application {
	
 @Override
  public void start(Stage stage) {
  public LineChart buildLineCharts(int rookie, int[] rankingArray)
  {
  	//defining the axes
	final NumberAxis xAxis = new NumberAxis();
	final NumberAxis yAxis = new NumberAxis();
	xAxis.setLabel("Year");
	yAxis.setLabel("Average District Ranking")
	
	//creating the chart
	final LineChart<Number,Number> lineChart = 
		new LineChart<Number,Number>(xAxis,yAxis);
	//lineChart.setTitle("") need team name for this? 
	
	//defining a series
	XYChart.Series series = new XYChart.Series();
	series.setName("Data");
	for(int i = 0; i<rankingArray.length; i++) {
    		series.getData().add(new XYChart.Data(rookie + i , rankingArray[i]);
	}
	lineChart.getData().add(series);

  }


 }
		
