import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.mysql.jdbc.MysqlDataTruncation;


public class FlightAdmin extends Admin{

        
        
        public FlightAdmin(DatabaseConnection c)
        {
                connect = c;
        }
        
        public void admin()
        {
        	final JFrame frame = new JFrame("Admin - Flight");
        	JPanel contentPane;
        	final JTextField flightField;
        	final JTextField planeField;
        	final JTextField gateField;
        	final JTextField pilotField;
        	final JTextField destField;
        	final JTextField depDateField;
        	final JTextField depTimeField;
        	final JTextField archiveField;
        	
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setBounds(100, 100, 600, 300);
    		frame.setVisible(true);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		frame.setContentPane(contentPane);
    		
    		JLabel lblPleaseEnterInformation = new JLabel("Please enter information about the Flight. Leave fields blank if you wish to view all flights");
    		
    		JLabel lblToDeleteA = new JLabel("To delete a flight, only the flightID is necessary");
    		
    		JLabel lblFlightId = new JLabel("Flight ID");
    		
    		flightField = new JTextField();
    		flightField.setColumns(10);
    		
    		JLabel lblPlaneId = new JLabel("Plane ID");
    		
    		planeField = new JTextField();
    		planeField.setColumns(10);
    		
    		JLabel lblGateId = new JLabel("Gate ID");
    		
    		gateField = new JTextField();
    		gateField.setColumns(10);
    		
    		JLabel lblPilotId = new JLabel("Pilot ID");
    		
    		pilotField = new JTextField();
    		pilotField.setColumns(10);
    		
    		JLabel lblDestination = new JLabel("Destination");
    		
    		destField = new JTextField();
    		destField.setColumns(10);
    		
    		JLabel lblDepartureDate = new JLabel("Departure Date");
    		
    		depDateField = new JTextField();
    		depDateField.setColumns(10);
    		
    		final JCheckBox datebxBefore = new JCheckBox("Before");
    		
    		final JCheckBox datebxAfter = new JCheckBox("After");
    		
    		JLabel lblDepartureTime = new JLabel("Departure Time");
    		
    		depTimeField = new JTextField();
    		depTimeField.setColumns(10);
    		
    		final JCheckBox timebxBefore_1 = new JCheckBox("Before");
    		
    		final JCheckBox timebxAfter_1 = new JCheckBox("After");
    		
    		JButton btnAdd = new JButton("Add");
    		
    		JButton btnViewAndEdit = new JButton("View and Edit");
    		
    		JButton btnDelete = new JButton("Delete");
    		
    		
    		
    		JButton btnCloseWindow = new JButton("Close Window");
    		
    		btnCloseWindow.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent ae)
    			{
    				frame.dispose();
    			}
    		});
    		
    		JButton btnArchive = new JButton("Archive");
    		                
    		JLabel lblToArchiveData = new JLabel("To archive data, enter a cut off date and click Archive");
    		
    		JLabel lblCutoffDateFor = new JLabel("Cutoff date for Archiving");
    		    		
    		archiveField = new JTextField();
    		archiveField.setColumns(10);
    		    		

    		
    		btnDelete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        try {
                                if(deleteFlight(flightField.getText()))
                                        JOptionPane.showMessageDialog(frame, "Flight " + flightField.getText() +" has been deleted from the database.");
                                else
                                        JOptionPane.showMessageDialog(frame, "Please enter a Flight ID that exists in the database!");
                        } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }
                }
        });
    		
    		 btnAdd.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                         if(!addFlight(flightField.getText(), destField.getText(), depDateField.getText(), depTimeField.getText(), planeField.getText(),
                                         pilotField.getText(), gateField.getText()))
                                         JOptionPane.showMessageDialog(frame, "Please enter an unused flightID, make sure your date field is in the form YYYY-MM-DD and "
                                                         +"your time is in the form HH:MM:SS!");
                         else
                                 JOptionPane.showMessageDialog(frame, "Flight " + flightField.getText() + " has been added to database.");
                 }
         });
         
         btnViewAndEdit.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                         if(!depDateField.getText().equals("") && datebxBefore.isSelected() && datebxAfter.isSelected())
                                 JOptionPane.showMessageDialog(frame, "Please only pick either greater or less than!");
                         else if(!depTimeField.getText().equals("") && timebxBefore_1.isSelected() && timebxAfter_1.isSelected())
                                 JOptionPane.showMessageDialog(frame, "Please only pick either greater or less than!");
                         else
                         {
                                 int compareDate;
                                 int compareTime;
                                 if(datebxBefore.isSelected())
                                         compareDate = -1;
                                 else if(datebxAfter.isSelected())
                                         compareDate = 1;
                                 else
                                         compareDate = 0;
                                 if(timebxBefore_1.isSelected())
                                         compareTime = -1;
                                 else if(timebxAfter_1.isSelected())
                                         compareTime = 1;
                                 else
                                         compareTime = 0;
                                 viewFlights(flightField.getText(), destField.getText(), depDateField.getText(), depTimeField.getText(), planeField.getText(),
                                                 pilotField.getText(), gateField.getText(), compareDate, compareTime);
                 
                         }
                 }
         });
         
		 
		 btnArchive.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent ae)
        	 {
        		 String s = archiveField.getText();
        		 try {
					connect.executeArchive(s);
					JOptionPane.showMessageDialog(frame, "Archived");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Incorrect Date format, must be in form YYYY-MM-DD HH:MM:SS");
				}
        	 }
         });
    		GroupLayout gl_contentPane = new GroupLayout(contentPane);
    		gl_contentPane.setHorizontalGroup(
    			gl_contentPane.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_contentPane.createSequentialGroup()
    					.addContainerGap()
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
    						.addComponent(lblPleaseEnterInformation)
    						.addComponent(lblToDeleteA)
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(lblFlightId)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(lblPlaneId)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(planeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(lblGateId)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(gateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(lblPilotId)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(lblDestination)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(destField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(lblDepartureDate)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(depDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(datebxBefore)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(datebxAfter))
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(lblDepartureTime)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(depTimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(timebxBefore_1)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(timebxAfter_1))
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(btnAdd)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(btnViewAndEdit)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(btnDelete)
    							.addPreferredGap(ComponentPlacement.UNRELATED)
    							.addComponent(btnCloseWindow)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(btnArchive))
    						.addComponent(lblToArchiveData)
    						.addGroup(gl_contentPane.createSequentialGroup()
    							.addComponent(lblCutoffDateFor)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(archiveField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
    					.addContainerGap(27, Short.MAX_VALUE))
    		);
    		gl_contentPane.setVerticalGroup(
    			gl_contentPane.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_contentPane.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(lblPleaseEnterInformation)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(lblToDeleteA)
    					.addGap(3)
    					.addComponent(lblToArchiveData)
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblFlightId)
    						.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblPlaneId)
    						.addComponent(planeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblGateId)
    						.addComponent(gateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblPilotId)
    						.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblDestination)
    						.addComponent(destField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblDepartureDate)
    						.addComponent(depDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(datebxBefore)
    						.addComponent(datebxAfter))
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblDepartureTime)
    						.addComponent(depTimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(timebxBefore_1)
    						.addComponent(timebxAfter_1))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblCutoffDateFor)
    						.addComponent(archiveField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
    						.addComponent(btnAdd)
    						.addComponent(btnViewAndEdit)
    						.addComponent(btnDelete)
    						.addComponent(btnCloseWindow)
    						.addComponent(btnArchive))
    					.addContainerGap())
    		);
    		contentPane.setLayout(gl_contentPane);
        }
        
        public boolean deleteFlight(String id) throws SQLException {
                if (id.equals(""))
                        return false;
                ResultSet rs;

                // default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Flight WHERE flightID = " + id + "";
                rs = connect.execute(sql);
                
                if (!rs.next()) {
                        return false;
                }

                if (id.compareTo("") != 0)
                        sql = "DELETE FROM Flight WHERE flightID = \'" + id + "\'";

                try {
                        System.out.println(sql);
                        connect.executeUpdate(sql);
                        return true;
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return false;
                }
        }

        public boolean addFlight(String flightID, String destination, String depDate, String depTime, String planeID
                        , String pilotID, String gateID) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String sql = "INSERT INTO Flight VALUES(" + flightID + ", \"" + destination + "\", \'" + depDate + "\', \'" + depTime + "\', " + planeID + ", " +
                                pilotID + ", \"" + gateID + "\", \'" + dateFormat.format(cal.getTime()) + "\')";
                System.out.println(sql);
                if (flightID.equals("") || destination.equals("") || depDate.equals("") || depTime.equals("") || planeID.equals("") 
                                || pilotID.equals("") || gateID.equals(""))
                        return false;
                 
                 sql = "INSERT INTO Flight VALUES(" + flightID + ", \"" + destination + "\", \'" + depDate + "\', \'" + depTime + "\', " + planeID + ", " +
                                                pilotID + ", \"" + gateID + "\", \'" + dateFormat.format(cal.getTime()) + "\')";
                
                String sqlCheck = "SELECT * FROM Flight WHERE flightID = " + flightID;
                System.out.println(sqlCheck);
                ResultSet rs;
                rs = connect.execute("SELECT * FROM Flight WHERE flightID = " + flightID);
                
                try {
                        if(AirlineReservationSystem.getRowNum(rs) == 0)
                        {
                                
                                        connect.executeUpdate(sql);
                                        return true;
                                
                                        
                                
                        }
                } 
                catch(MysqlDataTruncation e)
                {
                	JOptionPane.showMessageDialog(new JFrame(), "Incorrect format. Date(YYYY-MM-DD), Time(HH:MM:SS)");
                }catch (SQLException e) {
                        // TODO Auto-generated catch block
                	JOptionPane.showMessageDialog(new JFrame(), "Incorrect format. Date(YYYY-MM-DD), Time(HH:MM:SS)");
                }
                
                return false;
        }// addPilot

        public void viewFlights(String flightID, String destination, String depDate, String depTime, String planeID
                        , String pilotID, String gateID, int compareDate, int compareTime) {

                ResultSet rs;

                // default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Flight";

                // if any attribute is specified, append WHERE to sql
                if (flightID.compareTo("") + destination.compareTo("") + depDate.compareTo("") + depTime.compareTo("") + 
				planeID.compareTo("") + pilotID.compareTo("") + gateID.compareTo("") != 0) {
			sql = sql + " WHERE ";
			
			if(flightID.compareTo("") != 0)
				sql = sql + "flightID = " + flightID + " AND ";
			
			if(destination.compareTo("") != 0)
				sql += "destination = \"" + destination + "\" AND ";
			
			if(depDate.compareTo("") != 0 && compareDate == 0)
				sql += "depDate = \'" + depDate + "\' AND ";
			else if(compareDate > 0)
				sql += "depDate >" + "\'" + depDate + "\' AND ";
			else if(compareDate<0)
				sql+= "depDate <" + "\'" + depDate + "\' AND ";
			
			if(depTime.compareTo("") != 0 && compareTime == 0)
				sql += "depTime = \'" + depTime + "\' AND ";
			else if(compareTime>0)
				sql += "depTime > \'" + depTime + "\' AND ";
			else if(compareTime<0)
				sql += "depTime < \'" + depTime + "\' AND ";
			
			if(planeID.compareTo("") != 0)
				sql += "planeID = " + planeID + " AND ";
			
			if(pilotID.compareTo("") != 0)
				sql += "pilotID = " + pilotID + " AND ";
			
			if(gateID.compareTo("") != 0)
				sql += "gateID = \"" + gateID + "\" AND ";
			
			sql = sql.substring(0, sql.length()-4);

		}


                // CHECK, DELETE WHEN DONE
                System.out.println(sql);
                rs = connect.execute(sql);
                
                // Create frame
                final JFrame frame = new JFrame();
                frame.setBounds(400, 100, 800, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                JPanel panel = new JPanel();
                frame.add(panel);

                // get number of rows
                int rowNum = AirlineReservationSystem.getRowNum(rs);

                // fill Object[][] array with values
                int i = 0;
                Object[][] data = new Object[rowNum][8];
                try {
                        while (rs.next()) {

                                data[i][0] = rs.getString("flightID");
                                data[i][1] = rs.getString("destination");
                                data[i][2] = rs.getDate("depDate");
                                data[i][3] = rs.getTime("depTime");
                                data[i][4] = rs.getInt("planeID");
                                data[i][5] = rs.getInt("pilotID");
                                data[i][6] = rs.getString("gateID");                                
                                data[i][7] = rs.getTimestamp("updatedAt");
                                

                                i++;
                        }

                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                // header table
                String columnNames[] = { "flightID", "destination", "depDate", "depTime", "planeID", "pilotID", "gateID" , "updateAt"};

                // fill table
                final JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(750, 300));
                table.setFillsViewportHeight(true);
                table.getColumnModel().getColumn(1).setPreferredWidth(120);
                table.getColumnModel().getColumn(7).setPreferredWidth(150);
                JScrollPane scrollPane = new JScrollPane(table);
                JLabel text = new JLabel("Edit database except for FlightID");
                panel.add(text);
                panel.add(scrollPane);

                // tablemodellisten for editing database
                table.getModel().addTableModelListener(new TableModelListener() {
                        public void tableChanged(TableModelEvent e) {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                TableModel model = (TableModel) e.getSource();
                                String columnName = model.getColumnName(column);
                                Object data = model.getValueAt(row, column);
                                String flightID = (String) table.getValueAt(row, 0);
                                if (column != 1)
                                	JOptionPane.showMessageDialog(new JFrame(), "Cannot change flightID");
                                if(column < 2 || column == 6)
                                        editFlight(columnName, data, flightID, 0);
                                else if(column == 0 || (column > 3 && column < 5))
                                        editFlight(columnName, data, flightID, 1);
                                else
                                        editFlight(columnName, data, flightID, 2);

                        }
                });
                
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);

        }// viewPilots

        public void editFlight(String columnName, Object data, String flightID, int type) {
                String sql = "";
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                if(type == 0)
                sql = "UPDATE Flight SET " + columnName + " = \"" + data + "\", updatedAt = \'" + dateFormat.format(cal.getTime())  
                                + "\' WHERE flightID = " + flightID;
                
                else if(type == 1)
                        sql = "UPDATE Flight SET " + columnName + " = " + data + ", updatedAt = \'" + dateFormat.format(cal.getTime())
                        + "\' WHERE flightID = " + flightID;
                else
                        sql = "UPDATE Flight SET " + columnName + " = \'" + data + "\', updatedAt = \'" + dateFormat.format(cal.getTime())
                        + "\' WHERE flightID = " + flightID;
                System.out.println(data.getClass());
                System.out.println(sql);
                try {
                        connect.executeUpdate(sql);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        
}
