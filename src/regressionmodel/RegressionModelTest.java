/**
*This is the main to test the Regression model
 * @author Jose Rodriguez
 * @version 1.0
 */
package regressionmodel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegressionModelTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //Tests the REgression Model 
        GridPane pane = new GridPane();
        RegressionModel rm = new RegressionModel(pane);
        Scene scene = new Scene(pane, 200,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lineal Regression");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
