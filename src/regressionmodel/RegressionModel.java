/**
 * This program will help solve linear regression problems
 * found within asm 210 or other classes
 * More features will be added in the future
 * so far the main functionality works 
 * @author Jose Rodriguez
 * @version 1.0
 */
package regressionmodel;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class RegressionModel extends Application {

    private double sizeN, x, y, xSquared,xy;
    private double q, r;
    private int matrix[][];
    private Scanner scan;
    private final int START = 3;
    RegressionModel test;
    TextField[][] tf;
    TextField textfield;
    TextField sizetf;
    Button setSize;
    Button calculate;

    @Override
    public void start(Stage primaryStage) {
        sizetf = new TextField("size");

        setSize = new Button("Set Size");
        GridPane pane = new GridPane();
        setArray(START, pane);//we start with 3
        setSize.setOnMouseClicked(e -> {
            setArray(Integer.parseInt(sizetf.getText()), pane);
        });
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lineal regression");
        primaryStage.show();
    }

    /**
     * This fills up the arrays and displays to the screen the 
     * text boxes
     * @param size preferred size of the array
     * @param pane gridpane to display and adds things to
     */
    public void setArray(int size, GridPane pane) {
        sizeN = size;
        matrix = new int[size][size];
        calculate = new Button("Calculate");
        pane.getChildren().clear();//clear each turn
        pane.add(calculate, 0, size + 1);//add calculate button
        tf = new TextField[size][size];//set the sizetf of the TextField

        for (int i = 0; i < size; i++) {
            tf[1][i] = new TextField("1");
            pane.add(tf[1][i], 1, i);//This adds it to the pane

//            matrix[i][0] = Integer.parseInt(tf[i][0].getText());
//            System.out.println(tf[i][0].getText());
            for (int j = 0; j < size; j++) {
                tf[0][j] = new TextField("2");
                pane.add(tf[0][j], 0, j);//This adds it to the pane
            }
        }
        if (!pane.getChildren().contains(this.sizetf)//This checks if it 
                //This checks if it 
                //already contains it and doesnt add it
                && !pane.getChildren().contains(setSize)) {
            pane.add(this.sizetf, 4, 2);//This is one more from each 
            pane.add(setSize, 4, 3);//This is one more from each 
        }
        calculate.setOnMouseClicked(e -> {
            calculate(tf,size);
            display();

        });
    }

    /**
     * This sets the x values for the matrix
     *
     * @param tf textBox to retrieve values from
     * @param size size of the array to iterate
     */
    public void setXYvalues(TextField[][] tf, int size) {
        for (int i = 0; i < size; i++) {
            matrix[1][i] = Integer.parseInt(tf[1][i].getText());
            matrix[0][i] = Integer.parseInt(tf[0][i].getText());
        }
    }
    /**
     * Displays the array to console
     */
    public void display() {
        Stage graphStage = new Stage();
        BorderPane pane = new BorderPane();
        AnchorPane graph = new AnchorPane();
        GridPane labels = new GridPane();
        labelsToDisplay(labels);
        pane.setLeft(labels);
        graph.setPrefSize(250, 200);
        graph.setStyle("-fx-background-color: red");
        System.out.println("The matrix is: ");
        System.out.println("X\tY");
        for (int j = 0; j < sizeN; j++) {
            System.out.print(matrix[0][j]);
            System.out.println("\t" + matrix[1][j]);
        }
        pane.setRight(graph);
        System.out.println("q: "+getQ());
        System.out.println("r: "+getR());       
        Scene scene = new Scene(pane,400,200);
        graphStage.setScene(scene);
        graphStage.show();
    }
    /**
     * Sets the labels nicely in the second stage
     * @param pane to put labels on
     */
    public void labelsToDisplay(GridPane pane){
        Label labelX = new Label("Σx = " + getSummationX());
        Label labelY = new Label("Σy = " + getSummationY());
        Label labelXY = new Label("Σxy = "+ getSummationXY());
        Label labelXX = new Label("Σx² = "+ getSummationXSquared());
        Label n = new Label("n = " + sizeN);
        double qRounded = Math.round(getQ());//here we round the numbers
        double rRounded = Math.round(getR());//here we round the numbers
        Label answer = new Label("ŷ = " + qRounded+" x + "+rRounded);
        
        pane.add(n, 0, 0);
        pane.add(labelX, 0, 1);
        pane.add(labelY, 0, 2);
        pane.add(labelXY, 0, 3);
        pane.add(labelXX, 0, 4);
        pane.add(answer, 0, 5);
        
    }
    /**
     * Calculates the summation of x times y
     */
    public void summationXY() {
        xy = 0;
        for (int j = 0; j < sizeN; j++) {
            xy += matrix[0][j] * matrix[1][j];
        }
    }
    
    /**
     * Returns the value of xy
     * @return value of summation xy
     */
    public double getSummationXY(){
        return xy;
    }

    /**
     * calculates the summation of all x
     */
    public void summationX() {
        x = 0;
        for (int j = 0; j < sizeN; j++) {
            x += matrix[0][j];
        }
    }
    /**
     * The summation of x value
     * @return the value of x 
     */
    public double getSummationX(){
        return x;
    }

    /**
     * calculates the summation of all y
     */
    public void summationY() {
        y = 0;
        for (int j = 0; j < sizeN; j++) {
            y += matrix[1][j];
        }        
    }
    
    /**
     * the summation of y value
     * @return the summation of Y
     */
    public double getSummationY(){
        return y;
    }
    
    /**
     * Calculates the summation for x^2
     */
    public void summationXsquared() {
        xSquared = 0;
            for (int j = 0; j < sizeN; j++) {
            xSquared += matrix[0][j]*matrix[0][j];
        }       
    }
    public double getSummationXSquared(){
        return xSquared;
    }
    /**
     * calculates q
     */
    private void calculateQ() {
        q = (((sizeN)*(getSummationXY()))-((getSummationX())*getSummationY()))
                /(((sizeN)*getSummationXSquared())-((getSummationX()*getSummationX())));
    }
    public double getQ(){
        return q;
    }
    /**
     * Calculates r
     */
    private void calculateR() {
        r = (((getSummationY())*(getSummationXSquared()))-((getSummationX())*(getSummationXY())))
                /((sizeN)*(getSummationXSquared())-((getSummationX())*(getSummationX())));
    }
    public double getR(){
        return r;
    }
    public void calculate(TextField[][] tf, int size){
        setXYvalues(tf,size);
        summationXY();
        summationX();
        summationY();
        summationXsquared();
        calculateQ();
        calculateR();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
