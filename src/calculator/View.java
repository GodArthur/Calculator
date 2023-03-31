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
	JButton[] funcBtns = new JButton[10]; // TODO : +1 SIZE OF ARRAY IF NECESSARY
	JButton[] specialFuncBtns = new JButton[5];

	JButton addBtn, subBtn, multBtn, divBtn;
	JButton decBtn, eqBtn, negBtn, delBtn, clrBtn, nextBtn;
	JButton abxBtn, acosBtn, logbxBtn, MADBtn, sinhBtn; //TODO : ADD YOUR BUTTON HERE

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 550);
		// setBounds(100, 100, 565, 478);

		// Creating Content Pane
		contentPane = new JPanel();
		contentPane.setSize(420, 550);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creating Input Textbox
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setEditable(false);
		Font bigFont = textField.getFont().deriveFont(Font.PLAIN, 30f);
		textField.setFont(bigFont);

		// Initializing operator buttons 
		addBtn = new JButton("+");
		subBtn = new JButton("—");
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
		acosBtn = new JButton("acosX");
		logbxBtn = new JButton("logbx");
		MADBtn = new JButton("MAD");
		sinhBtn = new JButton("sinh");
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
		
		specialFuncBtns[0] = abxBtn;
		specialFuncBtns[1] = acosBtn;
		specialFuncBtns[2] = logbxBtn;
		specialFuncBtns[3] = MADBtn;
		specialFuncBtns[4] = sinhBtn;


		
		// Initializing the value of the buttons
		for (int i = 0; i < numBtns.length; i++) {

			numBtns[i] = new JButton(String.valueOf(i));
			// numBtns[i].setFocusable(false);
		}

		delBtn.setBounds(50, 430, 135, 40);
		clrBtn.setBounds(205, 430, 135, 40);

		// creating the button grid
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setBackground(Color.BLACK);
		//panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridLayout(6, 4, 10, 10));

		// Adding buttons to the calculator GUI
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
		panel.add(negBtn);
		panel.add(nextBtn);
		panel.add(abxBtn);
		panel.add(acosBtn);
		panel.add(logbxBtn);
		panel.add(MADBtn);
		panel.add(sinhBtn);
		// TODO : ADD BUTTON HERE
		
		//changing button num colour
		for (JButton num : numBtns) {
			num.setBackground(Color.WHITE);
		}

		//changing button func colour
		for (JButton func : funcBtns) {
			func.setBackground(new Color(227, 247, 181));
		}
		
		//changing button func colour
		for (JButton func : specialFuncBtns) {
			func.setBackground(new Color(247, 241, 181));
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
	void addMADBtnListener(ActionListener listen) {
		MADBtn.addActionListener(listen);
	}
	
	void addsinhBtnListener(ActionListener listen) {
		sinhBtn.addActionListener(listen);
	}
	// TODO : Create your own button listener

}
