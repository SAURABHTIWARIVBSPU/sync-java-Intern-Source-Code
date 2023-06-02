import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Loginpage{
	
	int n;
	
	public <MainPage> void Login() {
		JFrame f1 = new JFrame();
		f1.setSize(450, 450);
		f1.setLayout(null);
		f1.setLocationRelativeTo(null);
		
		JLabel heading = new JLabel("SURVEY SYSTEM");
		heading.setBounds(0, 50, 350, 50);
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setFont(new Font("BOOKMAN OLD STYLE", Font.BOLD, 30));
		f1.add(heading);
		
		JLabel uname = new JLabel("USERNAME : ");
		uname.setBounds(50, 130, 150, 50);
		f1.add(uname);
		
		JTextField name = new JTextField();
		name.setBounds(50, 170, 350, 30);
		f1.add(name);
		
		JLabel upass = new JLabel("PASSWORD: ");
		upass.setBounds(50, 200, 150, 50);
		f1.add(upass);
		
		JPasswordField pass = new JPasswordField();
		pass.setBounds(50, 240, 350, 30);
		f1.add(pass);
		
		JButton login = new JButton("LOGIN");
		login.setBounds(100, 300, 50, 40);
		f1.add(login);
		JButton reset = new JButton("RESET");
		login.setBounds(150 , 300, 50, 40);
        f1.add(reset);
       
        JButton guest = new JButton("             TRY GUEST MODE            ");
		login.setBounds(200,300, 50, 40);
        f1.add(guest);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = name.getText();
				String password = pass.actionPerformed(null);
				if(username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(f1, "Please Enter All Details!", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						sql_managa manage= new sql_managa();
						n = manage.authUser(username, password);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (n == -1) {
						JOptionPane.showMessageDialog(f1, "User Not Found!", "Warning Message", JOptionPane.WARNING_MESSAGE);
					}
					else if(n == 0) {
						JOptionPane.showMessageDialog(f1, "Incorrect Password!", "Warning Message", JOptionPane.WARNING_MESSAGE);
					}
					else {
						MainPage mainPage = new MainPage();
						try {
							mainPage.mainPageView(n);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						f1.dispose();
					}
				}
			}
		});
		
		JButton signUp = new JButton("SIGNUP");
		signUp.setBounds(250, 300, 100, 40);
		f1.add(signUp);
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp signup = new SignUp();
				signup.signUpView();
			}
		});
		
		JButton attend = new JButton("TRY GUEST MODE");
		attend.setBounds(100, 350, 250, 40);
		f1.add(attend);
		attend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String surveyCode = JOptionPane.showInputDialog("Enter Survey Code : ");
				try {
					if(!surveyCode.isEmpty() && surveyCode.length() == 5) {
						if(manage.check(surveyCode)) {
							Guest guest = new Guest();
							guest.guestView(surveyCode);
						}
						else {
							JOptionPane.showMessageDialog(f1, "Survey Not Available!", "Warning Message", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch(Exception e2) {
					
				}
			}
		});
		
		f1.setVisible(true);
	}
}