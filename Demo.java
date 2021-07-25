package test;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Demo {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "1234";
   static int currPas = 11;
  
   
   public static void main(String[] args) {
	   
   create();
   System.out.println("Goodbye!");
}//end main
   
   public static void create()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("                                       WELCOME TO  AIRLINE RESERVATION SYSTEM                                     ");
		JButton button = new JButton("Book Flight");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				book();
			}
		});
		
		JButton cancelRevButton = new JButton("Cancel Reservation");
               
               JButton selectFlightButton = new JButton("Book Flight");
               selectFlightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

					selectFlight();
			
			}
		});
               
               cancelRevButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cancelRev();
			}
		});
               
        JButton viewButton = new JButton("View Flights");
        
        viewButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		selectFlightView();
        	}
        });
               panel.add(label);
               panel.add(selectFlightButton);
               panel.add(viewButton);
		//panel.add(button);
		panel.add(cancelRevButton);
               
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 500, 200);
		Container con = frame.getContentPane();
		con.add(panel);
		
		frame.setVisible(true);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   
   public static void selectFlightView()
   {
       final JFrame frame = new JFrame();
       frame.setBounds(300, 100, 800, 300);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       JPanel panel = new JPanel();
       frame.add(panel);
       JLabel label = new JLabel("Please select a flight");
       panel.add(label);
       String[] columnNames = {"Flight ID",
                           "Destination",
                           "Departure Date",
                           "Departure Time", "Gate"};
       
       
       
       Object[][] data = getFlight();
       /*Object[][] data = {
           {101, "Tokyo, Japan", "2013-12-25" , "1:20:00","A001"},
           {102, "Manilla, Philippines", "2013-11-02" , "13:00:00","A002"},
           {1, "aplace", "adate" , "atime","agate"},
           {1, "aplace", "adate" , "atime","agate"},
           {1, "aplace", "adate" , "atime","agate"},
           {1, "aplace", "adate" , "atime","agate"},
           {1, "aplace", "adate" , "atime","agate"},
       };*/
       
       final JTable table = new JTable(data, columnNames);
       table.setPreferredScrollableViewportSize(new Dimension(700, 200));
       table.setFillsViewportHeight(true);
       JScrollPane scrollPane = new JScrollPane(table);
       panel.add(scrollPane);
       
       //mouse click listen to select the flight. it will return flightID
       table.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               int column = table.getColumnModel().getColumnIndexAtX(e.getX());
               int row    = e.getY()/table.getRowHeight();
               Object value = "";
               if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount())
                   value = table.getValueAt(row, 4);
               System.out.println(value);
               String planeID = value.toString();
               value = table.getValueAt(row,  0);
               String flightID = value.toString();
               printSeats(planeID, flightID);
               frame.dispose();
           }
       });
       
   }
   
   public static void printSeats(String planeID, final String flightID)
   {
       final JFrame frame = new JFrame();
       frame.setBounds(300, 100, 500, 500);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       JPanel panel = new JPanel();
       frame.add(panel);
      
       final JTextField nameField = new JTextField(20);
       final JTextField ageField = new JTextField(5);
       
       //final String chosen = "";
       
       String[] columnNames = {"Seat ID","Row","Seat Number",};
       //sample data
       Object[][] data = getSeat(planeID);
       
       final JTable table = new JTable(data, columnNames);
       table.setPreferredScrollableViewportSize(new Dimension(500, 300));
       table.setFillsViewportHeight(true);
       JScrollPane scrollPane = new JScrollPane(table);
       panel.add(scrollPane);
       
           
       
       
      
      
	
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
       
       public static void selectFlight()
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 800, 300);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           JLabel label = new JLabel("Please select a flight");
           panel.add(label);
           String[] columnNames = {"Flight ID",
                               "Destination",
                               "Departure Date",
                               "Departure Time", "Gate"};
           
           
           
           Object[][] data = getFlight();
           /*Object[][] data = {
               {101, "Tokyo, Japan", "2013-12-25" , "1:20:00","A001"},
               {102, "Manilla, Philippines", "2013-11-02" , "13:00:00","A002"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
           };*/
           
           final JTable table = new JTable(data, columnNames);
           table.setPreferredScrollableViewportSize(new Dimension(700, 200));
           table.setFillsViewportHeight(true);
           JScrollPane scrollPane = new JScrollPane(table);
           panel.add(scrollPane);
           
           //mouse click listen to select the flight. it will return flightID
           table.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e) {
                   int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                   int row    = e.getY()/table.getRowHeight();
                   Object value = "";
                   if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount())
                       value = table.getValueAt(row, 4);
                   System.out.println(value);
                   String planeID = value.toString();
                   value = table.getValueAt(row,  0);
                   String flightID = value.toString();
                   printAvailableSeats(planeID, flightID);
                   frame.dispose();
               }
           });
           
       }
       
       
       
       public static void cancelRev()
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 400, 200);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           final JLabel text = new JLabel("Please enter your passenger ID");
           panel.add(text);
   
           final JTextField seat = new JTextField("<<passenger id>>",10);
           //final JTextField flight = new JTextField("<<flight ID>>",10);
   
           panel.add(seat);
          // panel.add(flight);
           JButton submitButton = new JButton("Submit");
           panel.add(submitButton);
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
                           cancelSeat(seat.getText());
                           frame.dispose();
			}
		});
           
       }
       
       public static void cancelSeat(String pasID)
       {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   ResultSet rs = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating database...");
    	      stmt = conn.createStatement();
    	      
    	    String queryDrop = "USE cs157a";
    	    Statement stmtDrop = conn.createStatement();
    	    stmtDrop.execute(queryDrop);
    	    boolean firstClass = false;
    	    int range = (999 - 111) + 1;
  
    	 

    	      String sql = "DELETE FROM Passenger WHERE pasID = \'" + pasID + "\'";
    	      System.out.println(sql);
    	      stmt.executeUpdate(sql);
    	      
    	      
    	      

    	      //System.out.println("Database created successfully...");
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            stmt.close();
    	      }catch(SQLException se2){
    	      }// nothing we can do
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
       }
       
       //print available seats for a flight
       public static void printAvailableSeats(String planeID, final String flightID)
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 500, 500);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           JLabel label = new JLabel("Please select a seat and enter your information. 'F' seats  = First class.");
           final JLabel nameLabel = new JLabel("Name: ");
           final JLabel ageLabel = new JLabel("Age: ");
           final JTextField nameField = new JTextField(20);
           final JTextField ageField = new JTextField(5);
           panel.add(label);
           //final String chosen = "";
           
           String[] columnNames = {"Seat ID","Row","Seat Number",};
           //sample data
           Object[][] data = getSeat(planeID);
           
           final JTable table = new JTable(data, columnNames);
           table.setPreferredScrollableViewportSize(new Dimension(500, 300));
           table.setFillsViewportHeight(true);
           JScrollPane scrollPane = new JScrollPane(table);
           panel.add(scrollPane);
           table.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e) {
                   int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                   int row    = e.getY()/table.getRowHeight();
                   String value = "";
                   if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount())
                       value = (String) table.getValueAt(row, 0);
                   
                   String age = ageField.getText();
                   String name = nameField.getText();
                   
                   System.out.println(name);
                   addSeatQuery(age, name, value.toString(), flightID);
                  
               }
           });
           
           panel.add(nameLabel);
           panel.add(nameField);
           panel.add(ageLabel);
           panel.add(ageField);
		
       }
       
       
       
       public static void addSeatQuery(String age, String name, String seatID, String flightID)
       {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   ResultSet rs = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating database...");
    	      stmt = conn.createStatement();
    	      
    	    String queryDrop = "USE cs157a";
    	    Statement stmtDrop = conn.createStatement();
    	    stmtDrop.execute(queryDrop);
    	    boolean firstClass = false;
    	    int range = (999 - 111) + 1;
  
    	    if(seatID.charAt(2) == '7' || seatID.charAt(2) == '8')
    	    	firstClass = true;

    	      String sql = "INSERT INTO Passenger(name, age, flightID, firstClass, seatID, pasID) " +
    	      		"VALUES(\"" + name + "\", " + age +", " + flightID + ", " + firstClass + ", \'" + seatID + "\', \'" + (int)(Math.random()*100) + 1 + "C\')";
    	      System.out.println(sql);
    	      stmt.executeUpdate(sql);
    	      
    	      
    	      

    	      //System.out.println("Database created successfully...");
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            stmt.close();
    	      }catch(SQLException se2){
    	      }// nothing we can do
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
       }
       
       public static Object[][] getSeat(String planeID)
       {
    	   Object[][]data = new Object[8][8];
    	   
    	   Connection conn = null;
    	   Statement stmt = null;
    	   ResultSet rs = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating database...");
    	      stmt = conn.createStatement();
    	      
    	    String queryDrop = "USE cs157a";
    	    Statement stmtDrop = conn.createStatement();
    	    stmtDrop.execute(queryDrop);
  


    	      String sql = "SELECT * FROM Seat WHERE Seat.planeID =" + planeID +" AND taken = false";
    	      rs = stmt.executeQuery(sql);
    	      
    	      int i = 0;
    	      while(rs.next())
    	      {

    	    	  data[i][0] = rs.getString("sID");
    	    	  data[i][1] = rs.getString("Row");
    	    	  data[i][2] = rs.getInt("seatNo");
    	    	  data[i][3] = rs.getBoolean("taken");
    	    	  data[i][4] = rs.getInt("planeID");
 
    	    
    	    	  i++;
    	      }
    	      
    	      return data;
    	      //System.out.println("Database created successfully...");
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            stmt.close();
    	      }catch(SQLException se2){
    	      }// nothing we can do
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
    	   return data;
       }
       
       public static Object[][] getFlight()
       {
    	   Connection conn = null;
    	   Statement stmt = null;
    	   ResultSet rs = null;
    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName("com.mysql.jdbc.Driver");

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating database...");
    	      stmt = conn.createStatement();
    	      
    	    String queryDrop = "USE cs157a";
    	    Statement stmtDrop = conn.createStatement();
    	    stmtDrop.execute(queryDrop);
  


    	      String sql = "SELECT * FROM FLIGHT";
    	      rs = stmt.executeQuery(sql);
    	      
    	      int i = 0;
    	      Object[][] data = new Object[10][10];
    	      while(rs.next())
    	      {

    	    	  data[i][0] = rs.getInt("flightID");
    	    	  data[i][1] = rs.getString("destination");
    	    	  data[i][2] = rs.getDate("depDate");
    	    	  data[i][3] = rs.getTime("depTime");
    	    	  data[i][4] = rs.getInt("planeID");
    	    	  data[i][5] = rs.getInt("pilotID");
    	    	  data[i][6] = rs.getString("gateID");
    	    	  
    	    	  i++;
    	      }
    	      
    	      return data;
    	      //System.out.println("Database created successfully...");
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }finally{
    	      //finally block used to close resources
    	      try{
    	         if(stmt!=null)
    	            stmt.close();
    	      }catch(SQLException se2){
    	      }// nothing we can do
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }//end finally try
    	   }//end try
		return null;
       }
}
//end JDBCExample

