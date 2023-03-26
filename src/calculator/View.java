package calculator;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

//	private JPanel contentPane;
	private JTextField textField;
	
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
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		contentPane.add(btn_equal);
		setContentPane(contentPane);
	}
	
	public String getTextField() {
		return textField.getText();
	}
	public void setTextField(String display) {
		textField.setText(display);
	}
	// Do this below for every button
	void addBtn_1Listener(ActionListener listen) {
		btn_1.addActionListener(listen);
	}

}
