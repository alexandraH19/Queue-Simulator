package view;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{
    private JLabel l1 = new JLabel("Number of clients:");
    private JLabel l2 = new JLabel("Number of queues:");
    private JLabel l3 = new JLabel("Simulation interval:");
    private JLabel l4 = new JLabel("Minimum arrival time:");
    private JLabel l5 = new JLabel("Maximum arrival time:");
    private JLabel l6 = new JLabel("Minimum service time:");
    private JLabel l7 = new JLabel("Maximum service time:");
    private JTextField f1 = new JTextField();
    private JTextField f2 = new JTextField();
    private JTextField f3 = new JTextField();
    private JTextField f4 = new JTextField();
    private JTextField f5 = new JTextField();
    private JTextField f6 = new JTextField();
    private JTextField f7 = new JTextField();
    private JButton b1=new JButton("Start");


    // ======constructor=======
    public View(){
        this.setTitle("Queue Simulator");

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        p1.add(l1);
        p1.add(f1);
        p1.add(l2);
        p1.add(f2);
        p1.add(l3);
        p1.add(f3);
        p1.add(l4);
        p1.add(f4);
        p1.add(l5);
        p1.add(f5);
        p1.add(l6);
        p1.add(f6);
        p1.add(l7);
        p1.add(f7);
        p1.add(b1);
        this.setVisible(true);
        this.setContentPane(p1);
    }

    public String getNrClients() {
        return f1.getText();
    }

    public String getNrQueues() {
        return f2.getText();
    }

    public String getInterval() {
        return f3.getText();
    }

    public String getMinArrival() {
        return f4.getText();
    }

    public String getMaxArrival() {
        return f5.getText();
    }

    public String getMinService() {
        return f6.getText();
    }

    public String getMaxService() {
        return f7.getText();
    }

    public void addListener(ActionListener list1) {
        b1.addActionListener(list1);
    }
}

