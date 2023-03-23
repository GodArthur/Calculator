package calculator;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import java.util.ArrayList;




public class Main extends Application {
	
	private TextField txtfield = new TextField();
	private ArrayList<String> operands = new ArrayList<String>(); 
	private ArrayList<String> operators = new ArrayList<String>(); 

	StringBuilder currentNumber =  new StringBuilder("");
	private StringBuilder expression = new StringBuilder("");
	private boolean start = true;
	
	@Override
	public void start(Stage primaryStage) {
		txtfield.setFont(Font.font(20));
		txtfield.setPrefHeight(50);
		txtfield.setAlignment(Pos.CENTER_RIGHT);
		txtfield.setEditable(false);
		txtfield.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ;");

		primaryStage.setTitle("creating buttons");
  
  
        StackPane stackpane = new StackPane();
        stackpane.setPadding(new Insets(10));
        stackpane.getChildren().add(txtfield);
        
        TilePane tile = new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.TOP_CENTER);
        tile.getChildren().addAll(
        		createOperandsBtn("7"),
        		createOperandsBtn("8"),
        		createOperandsBtn("9"),
        		createOperatorsBtn("/"),
        		
        		createOperandsBtn("4"),
        		createOperandsBtn("5"),
        		createOperandsBtn("6"),
        		createOperatorsBtn("x"),
        		
        		createOperandsBtn("1"),
        		createOperandsBtn("2"),
        		createOperandsBtn("3"),
        		createOperatorsBtn("-"),
        		
        		createOperandsBtn("0"),
        		createClearBtn("C"),
        		createOperatorsBtn("="),
        		createOperatorsBtn("+"));
        
        BorderPane root = new BorderPane();
        root.setTop(stackpane);
        root.setCenter(tile);
  
        //stackpane.getChildren().add(b);
  
        Scene scene = new Scene(root, 400, 350);
  
        primaryStage.setScene(scene);
        primaryStage.setTitle("ETERNITY");
  
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Button createOperandsBtn(String ch) {
		Button btn = new Button(ch);
		btn.setPrefSize(40, 40);
		btn.setOnAction(this::processNumbers);
		return btn;
	}
	
	private Button createOperatorsBtn(String ch) {
		Button btn = new Button(ch);
		btn.setPrefSize(40, 40);
		btn.setOnAction(this::processOperators);

		return btn;
	}
	
	private Button createClearBtn(String ch) {
		Button btn = new Button(ch);
		btn.setPrefSize(40, 40);
		btn.setOnAction(e->{
			txtfield.setText("");
			operators.clear();
			expression.setLength(0);
			operands.clear();
			operators.clear();
			start=true;
		});
		return btn;
	}
	
	private void processNumbers(ActionEvent e) {
		if(start) {
			txtfield.setText("");
			start=false;
		}
		String value = ((Button)e.getSource()).getText();
        System.out.println("APPEND "+value); 		

		currentNumber.append(value);
		expression.append(value+" ");
		txtfield.setText(expression.toString());
	}
	
	private void processOperators(ActionEvent e) {
		String value = ((Button)e.getSource()).getText();
		if(!value.equals("=")) {
		
			operands.add(currentNumber.toString());
			operators.add(value);
			expression.append(value+" ");
			txtfield.setText(expression.toString());
			currentNumber.setLength(0);

		} else {
			operands.add(currentNumber.toString());

	          System.out.println("OPERANDS"); 		

			 for (int i = 0; i < operands.size();i++) 
		      { 		      
		          System.out.println(operands.get(i)); 		
		      } 
	          System.out.println("OPERATORS"); 		

			 for (int i = 0; i < operators.size();i++) 
		      { 		      
		          System.out.println(operators.get(i)); 		
		      } 
			if(operators.isEmpty())
				return;
			
			//-------------------------TO CHANGE!!--------------------------
			float fresult = 0;
			int operatorsCount = 0;
			for(int i = 0; i < operands.size()-1; i++) {
		          System.out.println("OPERAND 1: "+Long.parseLong(operands.get(i)));
		          System.out.println("OPERAND 2: "+Long.parseLong(operands.get(i+1))); 
		          System.out.println("OPERATOR: "+operators.get(operatorsCount)); 		

				float result = calculate(Long.parseLong(operands.get(i)), Long.parseLong(operands.get(i+1)), operators.get(operatorsCount));
				fresult += result;
				operatorsCount++;
			}
			txtfield.setText(String.valueOf(fresult));
			start=true;
			operators.clear();
			operands.clear();
			expression.setLength(0);
			currentNumber.setLength(0);
		}
	}
	
	private float calculate(long operand1, long operand2, String operator) {
		switch(operator) {
		case "+": return operand1+operand2;
		case "-": return operand1-operand2;
		case "x": return operand1*operand2;
		case "/": 
			if(operand2==0)
				return 0;
			return operand1/operand2;
		default: return 0;
		}
	}
}
