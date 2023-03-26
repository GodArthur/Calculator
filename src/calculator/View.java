package calculator;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

//	private JPanel contentPane;
	private JTextField expressionInput = new JTextField("");
	
	// Number buttons
	private JButton btn_1 = new JButton("1");
	private JButton btn_2 = new JButton("2");
	private JButton btn_3 = new JButton("3");
	private JButton btn_4 = new JButton("4");
	private JButton btn_5 = new JButton("5");
	private JButton btn_6 = new JButton("6");
	private JButton btn_7 = new JButton("7");
	private JButton btn_8 = new JButton("8");
	private JButton btn_9 = new JButton("9");
	private JButton btn_0 = new JButton("0");
	// Operators
	private JButton btn_plus = new JButton("+");
	private JButton btn_minus = new JButton("-");
	private JButton btn_mult = new JButton("*");
	private JButton btn_div = new JButton("/");
	private JButton btn_equal = new JButton("=");
	// TODO: TO BE ADDED
//	private JButton btn_opbracket = new JButton("(");
//	private JButton btn_closebracket = new JButton(")");
//	private JButton btn_decimal = new JButton(".");
	// clear
	// backspace
	// a,b,c and d buttons
	// upload data button
	
	// ADD ADVANCED FUNCTION BUTTONS HERE
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		expressionInput.setPreferredSize(new Dimension(250,50));
		expressionInput.setEditable(false);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.add(expressionInput);
		contentPane.add(btn_1);
		contentPane.add(btn_2);
		contentPane.add(btn_3);
		contentPane.add(btn_4);
		contentPane.add(btn_5);
		contentPane.add(btn_6);
		contentPane.add(btn_7);
		contentPane.add(btn_8);
		contentPane.add(btn_9);
		contentPane.add(btn_0);
		contentPane.add(btn_plus);
		contentPane.add(btn_minus);
		contentPane.add(btn_mult);
		contentPane.add(btn_div);
		contentPane.add(btn_equal);
		
		//TODO: ADD BUTTONS ON PANE HERE
		setContentPane(contentPane);
	}
	
	public String getExpressionInput() {
		return expressionInput.getText();
	}
	
	public void setExpressionInput(String display) {
		expressionInput.setText(display);
	}
	
	void addOperandsAndOperatorsBtnListener(ActionListener listen) {
		btn_1.addActionListener(listen);
		btn_2.addActionListener(listen);
		btn_3.addActionListener(listen);
		btn_4.addActionListener(listen);
		btn_5.addActionListener(listen);
		btn_6.addActionListener(listen);
		btn_7.addActionListener(listen);
		btn_8.addActionListener(listen);
		btn_9.addActionListener(listen);
		btn_0.addActionListener(listen);
		btn_plus.addActionListener(listen);
		btn_minus.addActionListener(listen);
		btn_mult.addActionListener(listen);
		btn_div.addActionListener(listen);
	}
	
	void addCalculateBtnListener(ActionListener listen) {
		btn_equal.addActionListener(listen);
	}


}
