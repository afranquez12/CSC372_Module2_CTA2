package cta2;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class BankAccountTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField balanceField;
	private JTextField amountField;
	private double balance = 0.0;  // Bank account balance

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankAccountTest frame = new BankAccountTest();
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
	public BankAccountTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JLabel for the Bank Balance
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(30, 30, 100, 25);
		contentPane.add(lblBalance);

		// JTextField to display the balance
		balanceField = new JTextField();
		balanceField.setBounds(150, 30, 200, 25);
		balanceField.setEditable(false);
		contentPane.add(balanceField);
		balanceField.setColumns(10);
		balanceField.setText(String.format("$%.2f", balance)); // Show initial balance

		// JLabel for Amount Input
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(30, 80, 100, 25);
		contentPane.add(lblAmount);

		// JTextField to input amount
		amountField = new JTextField();
		amountField.setBounds(150, 80, 200, 25);
		contentPane.add(amountField);
		amountField.setColumns(10);

		// JButton to deposit amount
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(30, 130, 150, 30);
		contentPane.add(btnDeposit);

		// JButton to withdraw amount
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(200, 130, 150, 30);
		contentPane.add(btnWithdraw);

		// ActionListener for Deposit Button
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = Double.parseDouble(amountField.getText());
					if (amount > 0) {
						balance += amount;
						updateBalanceDisplay();
					} else {
						JOptionPane.showMessageDialog(null, "Enter a positive amount.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number.");
				}
				amountField.setText(""); // Clear input field after operation
			}
		});

		// ActionListener for Withdraw Button
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = Double.parseDouble(amountField.getText());
					if (amount > 0 && amount <= balance) {
						balance -= amount;
						updateBalanceDisplay();
					} else if (amount > balance) {
						JOptionPane.showMessageDialog(null, "Insufficient funds.");
					} else {
						JOptionPane.showMessageDialog(null, "Enter a positive amount.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number.");
				}
				amountField.setText(""); // Clear input field after operation
			}
		});
	}

	// Method to update the balance display
	private void updateBalanceDisplay() {
		balanceField.setText(String.format("$%.2f", balance));
	}
}

