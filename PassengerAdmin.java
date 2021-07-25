import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PassengerAdmin extends Admin{
        private boolean firstClass = false;
        
        public PassengerAdmin(DatabaseConnection c){connect = c;}
        
        public void admin()
        {
        
                final JFrame frame = new JFrame("Admin - Passenger");
                frame.setVisible(true);
                frame.setBounds(200, 200, 600, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JLabel lblNewLabel = new JLabel("Please enter information about the passenger. Leave fields blank if you wish to view all passengers.");
                JLabel secondline = new JLabel("To add a passenger, enter complete information. To delete, enter either passenger name or pasID.");
                
                JLabel label = new JLabel("");
                
                
                
                JLabel nameLabel = new JLabel("Name");
                
                final JTextField nameField = new JTextField();
                nameField.setColumns(10);
                
                JLabel ageLabel = new JLabel("Age");
                
                final JTextField ageField = new JTextField();
                ageField.setColumns(10);
                
                JLabel flightLabel = new JLabel("Flight ID");
                
                final JTextField flightField = new JTextField();
                flightField.setColumns(10);
                
                JLabel seatLabel = new JLabel("Seat ID");
                
                final JTextField seatField = new JTextField();
                seatField.setColumns(10);
                
                final JCheckBox firstClassBox = new JCheckBox("First Class");
                
                JLabel passLabel = new JLabel("Passenger ID");
                
                final JTextField pasField = new JTextField();
                pasField.setColumns(10);
                
                JButton btnAdd = new JButton("Add");
                
                btnAdd.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                if(firstClassBox.isSelected())
                                    firstClass = true;
                                else if(!firstClassBox.isSelected())
                                    firstClass = false;
                                if(addPassenger(nameField.getText(), ageField.getText() + "", flightField.getText() + "", firstClass, seatField.getText(), pasField.getText()))
                                    JOptionPane.showMessageDialog(frame, "Passenger " + nameField.getText() + " has been added to database.");
                                else
                                    JOptionPane.showMessageDialog(frame, "Please enter complete information and make sure pasID is not already in the database.");

                        }
                });
                
                JButton btnView = new JButton("View and Edit");
                
                btnView.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                                
                                if(firstClassBox.isSelected())
                                    firstClass = true;
                                else if(!firstClassBox.isSelected())
                                    firstClass = false;
                                viewPassenger(nameField.getText(), ageField.getText() + "", flightField.getText() + "", firstClass, seatField.getText(), pasField.getText());
                                
                        }
                });
                
                JButton btnDelete = new JButton("Delete");
                
                btnDelete.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                        try {
                                if(deletePassenger(nameField.getText(), pasField.getText() + ""))
                                        JOptionPane.showMessageDialog(frame, "Passenger " + nameField.getText() + " " + pasField.getText() + " has been deleted from database.");
                                else
                                        JOptionPane.showMessageDialog(frame, "Please enter passenger name or pasID that exists in the database!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        }
                });
                
                JButton btnCloseWindow = new JButton("Close Window");
                
                btnCloseWindow.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                
                                frame.dispose();
                        }
                });
                
                GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
                groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(label)
                                                .addComponent(lblNewLabel)
                                                .addComponent(secondline)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(flightLabel)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(btnAdd)
                                                                        .addGap(34)
                                                                        .addComponent(btnView))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(nameLabel)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(ageLabel)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(33)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(passLabel)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(pasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(seatLabel)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18)
                                                                        .addComponent(firstClassBox))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(btnDelete)
                                                                        .addGap(42)
                                                                        .addComponent(btnCloseWindow)
                                                                        .addGap(46)))))
                                        .addContainerGap(78, Short.MAX_VALUE))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(secondline, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(nameLabel)
                                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(seatLabel)
                                                .addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(firstClassBox))
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(ageLabel)
                                                .addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(passLabel)
                                                .addComponent(pasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(flightLabel)
                                                .addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(55)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(btnAdd)
                                                .addComponent(btnView)
                                                .addComponent(btnDelete)
                                                .addComponent(btnCloseWindow))
                                        .addGap(51)
                                        .addComponent(label)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                frame.getContentPane().setLayout(groupLayout);
                
        }
        public boolean addPassenger(String name, String age, String flightID, boolean fc, String seatID, String pasID)
        {
                ResultSet rs;
                String sql = "SELECT pasID from Passenger";
                rs = connect.execute(sql);
                try {
                    while(rs.next())
                    {
                        String id = rs.getString("pasID");
                        if(id.equalsIgnoreCase(pasID))
                            return false;
                    }
                }
                catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                if(name.equals("") || age.equals("") || flightID.equals("") || seatID.equals("") || pasID.equals(""))
                    return false;
                sql = "INSERT INTO Passenger(name, age, flightID, firstClass, seatID, pasID) "
                                                + "VALUES(\""
                                                + name
                                                + "\", "
                                                + age
                                                + ", "
                                                + flightID
                                                + ", "
                                                + fc
                                                + ", \'"
                                                + seatID
                                                + "\', \'"
                                                + pasID + "\')";
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
        
        public void viewPassenger(String name, String age, String flightID, boolean fc, String seatID, String pasID)
        {
                ResultSet rs;
                
                //default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Passenger";
                if(fc)
                        sql = sql + " WHERE firstClass = true ";
                //if any attribute is specified, append WHERE to sql
                if(name.compareTo("") + age.compareTo("") + flightID.compareTo("") + seatID.compareTo("") + pasID.compareTo("")!= 0)
		{
                        if(fc)
                                sql = sql + " AND ";
                        else if(!fc)
                                sql = sql + " WHERE ";
			//append specified attribute to sql
			if(name.compareTo("") != 0)
				sql = sql + "name = \"" + name + "\" AND ";
            if(age.compareTo("") != 0)
				sql = sql + "age = " + age + " AND ";
            if(flightID.compareTo("") != 0)
				sql = sql + "flightID = " + flightID + " AND ";
            if(seatID.compareTo("") != 0)
				sql = sql + "seatID = \"" + seatID + "\" AND ";
           
                        
			sql = sql.substring(0, sql.length() - 4);
			
		}
                
                //CHECK, DELETE WHEN DONE
                System.out.println(sql);
                
                rs = connect.execute(sql);
                
                //Create frame
                final JFrame frame = new JFrame();
                frame.setBounds(400, 100, 700, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                JPanel panel = new JPanel();
                frame.add(panel);
                
                
                // get number of rows
                int rowNum = AirlineReservationSystem.getRowNum(rs);

                //fill Object[][] array with values
                int i = 0;
                Object[][] data = new Object[rowNum][6];
                try {
                        while (rs.next()) {

                                                                                
                                                data[i][0] = rs.getString("name");
                                                data[i][1] = rs.getInt("age");
                                                data[i][2] = rs.getInt("flightID");
                                                data[i][3] = rs.getInt("firstClass");
                                                data[i][4] = rs.getString("seatID");
                                                data[i][5] = rs.getString("pasID");
                                                i++;
                                        }
                        
                        
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                //header table
                String columnNames[] = {"name", "age", "flightID", "firstClass", "seatID", "pasID"}; 
                
                //fill table
                final JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(650, 300));
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                JLabel text = new JLabel("Edit database except for pasID");
                panel.add(text);
                panel.add(scrollPane);
                
                //tablemodellisten for editing database
                table.getModel().addTableModelListener(new TableModelListener(){
                        public void tableChanged(TableModelEvent e)
                        {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                if(column == 5)
                                        JOptionPane.showMessageDialog(frame, "Warning: Cannot change pasID");
                                else
                                {
                                        TableModel model = (TableModel)e.getSource();                                
                                        String columnName = model.getColumnName(column);
                                        Object data = model.getValueAt(row, column);
                                        String pasID = table.getValueAt(row, 5).toString();                                
                                        editPassenger(columnName, data, pasID);
                                }
                                        
                                
                        }
                });
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);
        }
        
        /*
         * Admin edit Passenger database except for pasID
         */
        public void editPassenger(String columnName, Object data, String pasID){
                String sql = "UPDATE Passenger SET " + columnName + " = \"" + data + "\" WHERE pasID = \"" + pasID + "\"";
                try {
                        connect.executeUpdate(sql);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        
        public boolean deletePassenger(String name, String id) throws SQLException
        {
                if(name.equals("") && id.equals(""))
                        return false;
                ResultSet rs;
                
                String sql = "SELECT * FROM Passenger WHERE name = \'" + name + "\'";
                rs = connect.execute(sql);
                if(!rs.next())
                {
                        sql = "SELECT * FROM Passenger WHERE pasID = \'" + id + "\'";
                        rs = connect.execute(sql);
                        if(!rs.next())
                                return false;
                }
                
                
                if(name.compareTo("") != 0)
                        sql = "DELETE FROM Passenger WHERE name = \'" + name + "\'";
                else if(id.compareTo("") != 0)
                        sql = "DELETE FROM Passenger WHERE pasID = \'" + id + "\'";
                        
                
                
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
}