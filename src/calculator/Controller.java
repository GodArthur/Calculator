package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

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
		this.view.addMADBtnListener(new MADBtnListener());
		this.view.addsinhBtnListener(new sinhBtnListener());
		this.view.addstDevBtnListener(new standardDevBtnListener());
		// TODO ADD to view
	}
	
	private void setModel(Calculator model) {
		this.model = model;
	}

	private void setView(View view) {
		this.view = view;
	}
	
	private Calculator getModel() {
		return this.model;
	}

	private View getView() {
		return this.view;
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
			if(model.getError().equalsIgnoreCase(""))
			view.setExpressionInput(model.getExpression());
			else
				view.setExpressionInput(model.getError());
//			System.out.println("expression: "+model.getExpression());
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
	
	class MADBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			model.appendMADToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
	}
	
	class sinhBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			model.appendSinhToExpression(buttonText);
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
	
		
	class standardDevBtnListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			String buttonText = ((JButton) e.getSource()).getText();
			model.appendStandardDevExpression(buttonText);
			System.out.println("from listener: "+model.getExpression());
			view.setExpressionInput(buttonText);

		}
	}
	
	//TODO : ADD YOUR BtnListener 
	

}
