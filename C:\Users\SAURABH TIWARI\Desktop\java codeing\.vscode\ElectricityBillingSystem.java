import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ElectricityBillingSystem extends JFrame implements ActionListener {

    private JLabel label1, label2, label3, label4;
    private JTextField tf1, tf2, tf3, tf4;
    private JButton button1, button2;
    
    public ElectricityBillingSystem() {
        setTitle("Electricity Billing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));
        
        label1 = new JLabel("Customer Name:");
        add(label1);
        tf1 = new JTextField();
        add(tf1);
        
        label2 = new JLabel("Customer Address:");
        add(label2);
        tf2 = new JTextField();
        add(tf2);
        
        label3 = new JLabel("Units Consumed:");
        add(label3);
        tf3 = new JTextField();
        add(tf3);
        
        button1 = new JButton("Calculate Bill");
        add(button1);
        button1.addActionListener(this);
        
        label4 = new JLabel("Bill Amount:");
        add(label4);
        tf4 = new JTextField();
        add(tf4);
        
        button2 = new JButton("Clear");
        add(button2);
        button2.addActionListener(this);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            String name = tf1.getText();
            String address = tf2.getText();
            double units = Double.parseDouble(tf3.getText());
            double bill = calculateBill(units);
            tf4.setText(Double.toString(bill));
            JOptionPane.showMessageDialog(null, "Bill generated successfully!");
        } else if (e.getSource() == button2) {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        }
    }
    
    public double calculateBill(double units) {
        double rate = 7.50;
        double bill = units * rate;
        return bill;
    }
    
    public static void main(String[] args) {
        new ElectricityBillingSystem();
    }
}
