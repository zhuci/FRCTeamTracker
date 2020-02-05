import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.event.*;
public class FRCDisplay extends Application
{
	private Canvas c;
	private int teamOneNum;
	private int teamTwoNum;
	public FRCDisplay()
	{
		c = new Canvas(500,500);
	}

	@Override 
	public void init()
	{
	}
	@Override 
	public void start(Stage primary)
	{
		BorderPane bp = new BorderPane();
		bp.setTop(buildMenus());
		bp.setCenter(buildDisplay());
		primary.setScene(new Scene(bp));
		primary.show();
	}
	@Override
	public void stop()
	{
	}
	private MenuBar buildMenus()
	{
		MenuBar mbar = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu sizeMenu = new Menu("Size");
		mbar.getMenus().addAll(buildFileMenu());
		return mbar;
	}
	private Menu buildFileMenu()
	{
		Menu fileMenu = new Menu("File");
		MenuItem quitItem = new MenuItem("Quit");
		quitItem.setOnAction( e -> Platform.exit());
		fileMenu.getItems().addAll(quitItem);
		return fileMenu;
	}
	private void refresh()
	{
		
	}

	public GridPane buildDisplay() {
		Label teamone = new Label("First Team");
		GridPane.setConstraints(teamone, 0, 0);

		Label teamtwo = new Label("Second Team");
		GridPane.setConstraints(teamtwo, 1, 0);

		TextField teamOneField = new TextField();
		teamOneField.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					String initial = teamOneField.getText();
					System.out.println("\n"+initial);
					teamOneNum = Integer.parseInt(initial);
				}
				catch (Exception e) {

				}
			}
		});
		GridPane.setConstraints(teamOneField, 0, 1);
		TextField teamTwoField = new TextField();
		teamTwoField.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					String initial = teamTwoField.getText();
					System.out.println("\n"+initial);
					teamTwoNum = Integer.parseInt(initial);
				}
				catch (Exception e) {

				}
			}
		});
		GridPane.setConstraints(teamTwoField, 1, 1);

		//CINDY PUT CODE HERE I THINK






		GridPane gp = new GridPane();
		gp.getChildren().addAll(teamone, teamtwo, teamOneField, teamTwoField);
		return gp;
	}

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
