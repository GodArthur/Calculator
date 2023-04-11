package calculator;

import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

	private JPanel panel;
	private JPanel contentPane;
	JTextField textField;
	JButton[] numBtns = new JButton[10];
	JButton[] funcBtns = new JButton[11]; // TODO : +1 SIZE OF ARRAY IF NECESSARY
	JButton[] specialFuncBtns = new JButton[7];

	JButton addBtn, subBtn, multBtn, divBtn;

	JButton decBtn, eqBtn, negBtn, delBtn, clrBtn, nextBtn,lastAnsBtn;
	JButton abxBtn, acosBtn, logbxBtn, MADBtn, sinhBtn, stDevBtn, xYBtn; //TODO : ADD YOUR BUTTON HERE

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("ETERNITY Calculator by Team C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 900);
		// setBounds(100, 100, 565, 478);

		// Creating Content Pane
		contentPane = new JPanel();
		contentPane.setSize(420, 550);
		contentPane.setBackground(Color.decode("#1B1212"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creating Input Textbox
		textField = new JTextField();
		textField.setBounds(50, 50, 475, 120);
		textField.setBackground(Color.decode("#1B1212"));
		textField.setForeground(Color.WHITE);
		//textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setEditable(false);
		Font font = new Font("HELVETICA", Font.PLAIN, 40);
		textField.setFont(font);

		// Initializing operator buttons 
		addBtn = new JButton("+");
		subBtn = new JButton("\u2014");
		multBtn = new JButton("*");
		divBtn = new JButton("/");
		
		decBtn = new JButton(".");
		eqBtn = new JButton("=");
		negBtn = new JButton("(-)");
		delBtn = new JButton("DEL");
		clrBtn = new JButton("C");
		abxBtn = new JButton("ab\u02e3");
		clrBtn = new JButton("Clr");
		nextBtn = new JButton("next");
		acosBtn = new JButton("acos");
		logbxBtn = new JButton("logbx");
		xYBtn = new JButton("x^y");
		MADBtn = new JButton("MAD");
		sinhBtn = new JButton("sinh");
		stDevBtn = new JButton("stDev");
		lastAnsBtn = new JButton("Ans");
		// TODO : ADD BUTTON HERE
		
		
		funcBtns[0] = addBtn;
		funcBtns[1] = subBtn;
		funcBtns[2] = divBtn;
		funcBtns[3] = multBtn;
		funcBtns[4] = decBtn;
		funcBtns[5] = negBtn;
		funcBtns[6] = eqBtn;
		funcBtns[7] = delBtn;
		funcBtns[8] = nextBtn;
		funcBtns[9] = clrBtn;
		funcBtns[10] = lastAnsBtn;
		
		specialFuncBtns[0] = abxBtn;
		specialFuncBtns[1] = acosBtn;
		specialFuncBtns[2] = logbxBtn;
		specialFuncBtns[3] = MADBtn;
		specialFuncBtns[4] = sinhBtn;
		specialFuncBtns[5] = stDevBtn;
		specialFuncBtns[6] = xYBtn;

		// Initializing the value of the numbered buttons
		for (int i = 0; i < numBtns.length; i++) {

			numBtns[i] = new JButton(String.valueOf(i));
			// numBtns[i].setFocusable(false);
		}

		// creating the button grid
		panel = new JPanel();
		panel.setBounds(50, 220, 475, 580);
		panel.setBackground(Color.decode("#1B1212"));
		//panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridLayout(7, 4,10,10));

		// Adding buttons to the calculator GUI
		panel.add(abxBtn);
		panel.add(acosBtn);
		panel.add(logbxBtn);
		panel.add(xYBtn);
		panel.add(MADBtn);
		panel.add(sinhBtn);
		panel.add(stDevBtn);
		panel.add(clrBtn);
		panel.add(numBtns[7]);
		panel.add(numBtns[8]);
		panel.add(numBtns[9]);
		panel.add(addBtn);
		panel.add(numBtns[4]);
		panel.add(numBtns[5]);
		panel.add(numBtns[6]);
		panel.add(subBtn);
		panel.add(numBtns[1]);
		panel.add(numBtns[2]);
		panel.add(numBtns[3]);
		panel.add(multBtn);
		panel.add(decBtn);
		panel.add(numBtns[0]);
		panel.add(eqBtn);
		panel.add(divBtn);
		panel.add(negBtn);
		panel.add(nextBtn);
		panel.add(lastAnsBtn);
		panel.add(delBtn);
		// TODO : ADD BUTTON HERE 
		
		Font buttonFont = new Font("Arial", Font.BOLD, 28);
		//changing button num colour
		for (JButton num : numBtns) {
			num.setBorder(new EmptyBorder(0, 0, 0, 0));
			num.setBackground(Color.decode("#28282B"));
			num.setForeground(Color.WHITE);
			num.setFont(buttonFont);
		}

		//changing button func colour
		for (JButton func : funcBtns) {
			func.setBorder(new EmptyBorder(0, 0, 0, 0));
			func.setBackground(Color.decode("#CECBC5"));
			func.setFont(buttonFont);
		}
		
		eqBtn.setBackground(Color.decode("#0BDA51"));
		
		//changing button func colour
		for (JButton func : specialFuncBtns) {
			func.setBorder(new EmptyBorder(0, 0, 0, 0));
			func.setBackground(Color.decode("#FF8700"));
			func.setFont(buttonFont);
		}

		add(panel);
		add(textField);

	}

	public String getExpressionInput() {
		return textField.getText();
	}

	public void setExpressionInput(String display) {
		textField.setText(display);
	}


	/*
	 * Function adds event listeners to the operators and operands
	 */
	void addOperandsAndOperatorsBtnListener(ActionListener listen) {

		for (JButton num : numBtns) {
			num.addActionListener(listen);
		}

		for (JButton func : funcBtns) {

			if (!(func.getText().equals("="))) {
				func.addActionListener(listen);
			}
		}

		// funcBtns[i].setFocusable(false);

	}

	/*
	 * Function adds an event listener to the equal button
	 */
	
	void addLastAnswerBtnListener(ActionListener listen) {
		lastAnsBtn.addActionListener(listen);
	}
	
	void addCalculateBtnListener(ActionListener listen) {
		eqBtn.addActionListener(listen);
	}
	
	void addABtoXBtnListener(ActionListener listen) {
		abxBtn.addActionListener(listen);
	}
	
	void addNextBtnListener(ActionListener listen) {
		nextBtn.addActionListener(listen);
	}
	void addlogBXBtnListener(ActionListener listen) {
		logbxBtn.addActionListener(listen);
	}
	void addArccosBtnListener(ActionListener listen) {
		acosBtn.addActionListener(listen);
	}
	
	void addXYBtnListener(ActionListener listen) {
		xYBtn.addActionListener(listen);
	}
		
	void addMADBtnListener(ActionListener listen) {
		MADBtn.addActionListener(listen);
	}
	
	void addsinhBtnListener(ActionListener listen) {
		sinhBtn.addActionListener(listen);
	}
	
	void addstDevBtnListener(ActionListener listen) {
		stDevBtn.addActionListener(listen);
	}
	// TODO : Create your own button listener

}
