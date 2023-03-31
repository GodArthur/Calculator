package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


import java.util.ArrayList;

public class Controller {
	private Calculator model;
	private View view;

	public Controller(Calculator model, View view) {
		this.model = model;
		this.view = view;

		this.view.addOperandsAndOperatorsBtnListener(new OperandsAndOperatorsBtnListener());
		this.view.addCalculateBtnListener(new CalculateBtnListener());
		this.view.addNextBtnListener(new NextBtnListener());
		//Transcendental Functions
		this.view.addABtoXBtnListener(new ABtoXBtnListener());
		this.view.addlogBXBtnListener(new LogBXBtnListener());
		this.view.addArccosBtnListener(new ArccosBtnListener());
		this.view.addXYBtnListener(new XYBtnListener());
		// TODO ADD to view
	}
	
	public void setModel(Calculator model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	
//-------------------------------- BtnListener Class -----------------------------------------------//	
	class OperandsAndOperatorsBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			model.appendToExpression(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	class CalculateBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String expressionInputText = model.getExpression();
			model.calculateExpression(expressionInputText);
			if(model.getError()=="")
			view.setExpressionInput(model.getExpression());
			else
				view.setExpressionInput(model.getError());
			System.out.println("expression: "+model.getExpression());
			model.clear();
		}
		
	}

	class NextBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.updateNextFunctionValue();
		}
		
	}
	
//-------------------------------- Transcendental functions Btn Classes -----------------------------------------------//	
	
	class ABtoXBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();

			model.appendAbxToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	class LogBXBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			model.appendLogbXToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	class ArccosBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			model.appendArccosToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	
	class XYBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			model.appendXYToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	//TODO : ADD YOUR BtnListener 
	

}
