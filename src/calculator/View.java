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
	JButton[] funcBtns = new JButton[12];

	JButton addBtn, subBtn, multBtn, divBtn;
	JButton decBtn, eqBtn, delBtn, clrBtn, abxBtn, acosBtn,commaBtn,stDevBtn;

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
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creating Input Textbox
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setEditable(false);

		// Initializing operator buttons
		addBtn = new JButton("+");
		subBtn = new JButton("-");
		multBtn = new JButton("*");
		divBtn = new JButton("/");
		decBtn = new JButton(".");
		eqBtn = new JButton("=");
		delBtn = new JButton("DEL");
		clrBtn = new JButton("C");
		abxBtn = new JButton("abx");
		acosBtn = new JButton("acos");
		commaBtn = new JButton(",");
		stDevBtn = new JButton("stdev");

		funcBtns[0] = addBtn;
		funcBtns[1] = subBtn;
		funcBtns[2] = divBtn;
		funcBtns[3] = multBtn;
		funcBtns[4] = decBtn;
		funcBtns[5] = eqBtn;
		funcBtns[6] = delBtn;
		funcBtns[7] = clrBtn;
		funcBtns[8] = abxBtn;
		funcBtns[9] = acosBtn;
		funcBtns[10] = commaBtn;
		funcBtns[11] = stDevBtn;

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
		panel.setLayout(new GridLayout(5, 4, 10, 10));

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
		panel.add(abxBtn);
		panel.add(acosBtn);
		panel.add(commaBtn);
		panel.add(stDevBtn);

		//changing button num colour
		for (JButton num : numBtns) {
			num.setBackground(Color.WHITE);
		}

		//changing button func colour
		for (JButton func : funcBtns) {

			func.setBackground(Color.GRAY);
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
}
