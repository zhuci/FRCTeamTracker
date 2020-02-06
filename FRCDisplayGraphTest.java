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
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;

public class FRCDisplayGraphTest extends Application
{
	private Canvas c;
	private int teamOneNum;
	private int teamTwoNum;
        private Stage primary;
        VBox team1GraphBox;
        VBox team2GraphBox;
        TextField teamOneField;
        TextField teamTwoField;
	public FRCDisplayGraphTest()
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
		primary.setScene(new Scene(bp, 500, 500));
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

		GridPane gp = new GridPane();
		teamOneField = new TextField();
		teamOneField.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					String initial = teamOneField.getText();
					System.out.println("\n"+initial);
					teamOneNum = Integer.parseInt(initial);
                                        int[] rankArray = rankArray(teamOneNum, 2010);
                                        createGraph(rankArray, 0);
				}
				catch (Exception e) {

                                    e.printStackTrace();
				}
			}
		});
		GridPane.setConstraints(teamOneField, 0, 1);
		teamTwoField = new TextField();
		teamTwoField.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					String initial = teamTwoField.getText();
					System.out.println("\n"+initial);
					teamTwoNum = Integer.parseInt(initial);
                                        int[] rankArray = rankArray(teamTwoNum, 2010);
                                        createGraph(rankArray, 1);
				}
				catch (Exception e) {
                                    e.printStackTrace();

				}
			}
		});
		GridPane.setConstraints(teamTwoField, 1, 1);
                int[] rankArray = rankArray(teamTwoNum, 2010);
                for(int i = 0; i < rankArray.length; i++)
                {
                    System.out.println(rankArray[i]);
                }
		LineChart lineChart;
                lineChart = buildLineCharts(teamTwoNum, 2010, rankArray);
		GridPane.setConstraints(lineChart, 1,2);

		//CINDY PUT CODE HERE I THINK




                HBox graphBox = new HBox();
                team1GraphBox = new VBox();
                team2GraphBox = new VBox();
                graphBox.getChildren().addAll(team1GraphBox, team2GraphBox);
                gp.addRow(2,graphBox); 
                //gp.setConstraints(graphBox, 0, 2);
                //GridPane.setConstraints(team2GraphBox, 1, 2);

		gp.getChildren().addAll(teamone, teamtwo, teamOneField, teamTwoField);
		return gp;
	}

        private void createGraph(int[] data, int location)
        {
            LineChart lineChart;
            lineChart = buildLineCharts(2010, data);
            if(location == 1)
            {
                team2GraphBox.getChildren().clear();
                team2GraphBox.getChildren().addAll(lineChart);
            }
            else{
                team1GraphBox.getChildren().clear();
                team1GraphBox.getChildren().addAll(lineChart);
            }
            teamOneField.setMaxWidth(250);
            teamTwoField.setMaxWidth(250);
        }

	public LineChart buildLineCharts(int team, int rookie, int[] rankingArray)
	{
		//defining the axes
		final NumberAxis xAxis = new NumberAxis(rookie, 2019, 1);
		final NumberAxis yAxis = new NumberAxis(0,60,1);
		xAxis.setLabel("Year");
		yAxis.setLabel("Average District Ranking");

		//creating the chart
		final LineChart<Number,Number> lineChart =
		new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Team " + String.valueOf(team)); 

		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName("Data");
		for(int i = 0; i<rankingArray.length; i++) {
			series.getData().add(new XYChart.Data(rookie + i , rankingArray[i]));
		}
		lineChart.getData().add(series);
		
                return lineChart;
	}

	private static int[] rankArray(int teamNum, int teamRookieYear) {
		int[] rankArray = new int[2020-teamRookieYear];
		try {
			for(int i = teamRookieYear; i < 2020; i++) {
				
				rankArray[i-teamRookieYear] = (int)(Math.random() * 50 + 1);
				//for(int j = 0; j < rankArray.length; j++) System.out.println(rankArray[j]);
				//rankArray[i-teamRookieYear] = getJSONRequest(new URL(String.format("https://frc-api.firstinspires.org/v2.0/%d/rankings/district/%d", i, teamNum)));
				//System.out.println(getJSONRequest(new URL(String.format("https://frc-api.firstinspires.org/v2.0/%d/rankings/district/??teamNumber=%d??", i, teamNum))));

			}
		} catch (Exception e) {}
		return rankArray;
	}	
}
