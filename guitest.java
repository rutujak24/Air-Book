import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class JDBCExample{
	public static void main (String [] args)
	{
		create();
		
	}
	
	public static void create()
	{
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Book Flight");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				book();
			}
		});
		
		JButton button1 = new JButton("Cancel Flight");
		panel.add(button);
		panel.add(button1);
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		Container con = frame.getContentPane();
		con.add(panel);
		
		frame.setVisible(true);
	}
	
	
	public static void book(){
JPanel panel = new JPanel();
		
		final JTextField tf = new JTextField();
		tf.setColumns(4);
		
		JButton button1 = new JButton("OK");
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				System.out.println(tf.getText());
			}
		});
		panel.add(button1);
		panel.add(tf);
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		Container con = frame.getContentPane();
		con.add(panel);
		
		frame.setVisible(true);
	}
}
