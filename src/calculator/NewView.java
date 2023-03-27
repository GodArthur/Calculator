package calculator;

import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class NewView extends JFrame {

	private JPanel panel;
	private JPanel contentPane;
	JTextField textField;
	JButton[] numBtns = new JButton[10];
	JButton[] funcBtns = new JButton[8];
	
	JButton addBtn, subBtn, multBtn, divBtn;
	JButton decBtn, eqBtn, delBtn, clrBtn;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NewView frame = new NewView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 */
	public NewView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 550);
		//setBounds(100, 100, 565, 478);
		
		//Creating Content Pane
		contentPane = new JPanel();
		contentPane.setSize(420, 550);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creating Input Textbox
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setEditable(false);
		
		//Initializing operator buttons
		addBtn = new JButton("+");
		subBtn = new JButton("-");
		multBtn = new JButton("x");
		divBtn = new JButton("/");
		decBtn = new JButton(".");
		eqBtn = new JButton("=");
		delBtn = new JButton("DEL");
		clrBtn = new JButton("C");
		
		funcBtns[0] = addBtn;
		funcBtns[1] = subBtn;
		funcBtns[2] = divBtn;
		funcBtns[3] = multBtn;
		funcBtns[4] = decBtn;
		funcBtns[5] = eqBtn;;
		funcBtns[6] = delBtn;;
		funcBtns[7] = clrBtn;
		
		//Setting the value of the buttons
		for(int i = 0; i < numBtns.length; i++) {
			
			numBtns[i] = new JButton(String.valueOf(i));
			//numBtns[i].setFocusable(false);
		}
		
		delBtn.setBounds(50, 430, 135, 40);
		clrBtn.setBounds(205, 430, 135, 40);
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		
		panel.add(numBtns[1]);
		panel.add(numBtns[2]);
		panel.add(numBtns[3]);
		panel.add(addBtn);
		panel.add(numBtns[4]);
		panel.add(numBtns[5]);
		panel.add(numBtns[6]);
		panel.add(subBtn);
		panel.add(numBtns[7]);
		panel.add(numBtns[8]);
		panel.add(numBtns[9]);
		panel.add(multBtn);
		panel.add(decBtn);
		panel.add(numBtns[0]);
		panel.add(eqBtn);
		panel.add(divBtn);
		
		for(JButton num : numBtns) {
			num.setBackground(Color.WHITE);
		
			
		}
		
		for(JButton func : funcBtns) {
			
			func.setBackground(Color.GRAY);
			//panel.add(func);
		}
		
		
		
		add(panel);
		add(delBtn);
		add(clrBtn);
		add(textField);
			

		
	}
	
	
	
	public String getExpressionInput() {
		return textField.getText();
	}
	
	public void setExpressionInput(String display) {
		textField.setText(display);
	}
	
	void addOperandsAndOperatorsBtnListener(ActionListener listen) {
		
		for(JButton num : numBtns) {
			num.addActionListener(listen);
		}
		
		for(JButton func : funcBtns) {
			func.addActionListener(listen);
		}
		
			//funcBtns[i].setFocusable(false);

	}
	
	void addCalculateBtnListener(ActionListener listen) {
		eqBtn.addActionListener(listen);
	}
	}
