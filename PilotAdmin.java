import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PilotAdmin extends Admin{

        public PilotAdmin(DatabaseConnection c)
        {
                connect = c;
        }
        
        
        /**                        adminPilots
         * This is the admin menu for the pilot relation.
         * Admin can add, edit, delete, or view pilots.
         */
        public void admin(){
                
                final JFrame frame = new JFrame("Admin - Pilot");
                frame.setVisible(true);
                frame.setBounds(200, 200, 578, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JLabel lblNewLabel = new JLabel("Enter information to filter and view pilots. To view all pilots, leave all the fields blank.");
                
                JLabel label = new JLabel("To add a pilot, enter name and years of experience. To delete a pilot, enter pilot name or pilotID.");
                
                JLabel nameLabel = new JLabel("Name");
                
                final JTextField nameField = new JTextField();
                nameField.setColumns(10);
                
                final JLabel pilotLabel = new JLabel("Pilot ID");
                
                final JTextField pilotField = new JTextField();
                pilotField.setColumns(10);
                
                JLabel expLabel = new JLabel("Years of Experience");
                
                final JTextField expField = new JTextField();
                expField.setColumns(10);
                final JCheckBox greaterBox = new JCheckBox("Greater than");
                
                final JCheckBox lessBox = new JCheckBox("Less Than");
                
                
                JButton btnAdd = new JButton("Add");
                //call addPilot if view button is clicked and fields are not empty
                btnAdd.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                if(addPilot(nameField.getText(), expField.getText() + ""))
                                    JOptionPane.showMessageDialog(frame, "Pilot " + nameField.getText() + " has been added to database.");
                                else
                                    JOptionPane.showMessageDialog(frame, "Please enter pilot name and years of experience!");

                        }
                });
                JButton btnView = new JButton("View and Edit");
                
                //call viewPilots if view button is clicked
                btnView.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                                if(!expField.getText().equals("") && greaterBox.isSelected() && lessBox.isSelected())
                                        JOptionPane.showMessageDialog(frame, "Please only pick either greater or less than!");
                                else
                                {
                                        int compareExp;
                                        
                                        if(greaterBox.isSelected())
                                            compareExp = 1;
                                        else if(lessBox.isSelected())
                                            compareExp = -1;
                                        else
                                            compareExp = 0;
                                        viewPilots(nameField.getText(), pilotField.getText() +"", expField.getText() + "", compareExp);
                                }
                        }
                });
                
                JButton btnDelete = new JButton("Delete");
                btnDelete.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                        try {
                                if(deletePilot(nameField.getText(), pilotField.getText() + ""))
                                        JOptionPane.showMessageDialog(frame, "Pilot " + nameField.getText() + " " + pilotField.getText() + " has been deleted database.");
                                else
                                        JOptionPane.showMessageDialog(frame, "Please enter pilot name or pilot ID that exists in the database!");
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
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(pilotLabel))
                                                                        .addGap(80)
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(expLabel)
                                                                        .addGap(18)
                                                                        .addComponent(expField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(18)
                                                        .addComponent(greaterBox)
                                                        .addGap(18)
                                                        .addComponent(lessBox))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(btnAdd)
                                                        .addGap(36)
                                                        .addGap(39)
                                                        .addComponent(btnView)
                                                        .addGap(41)
                                                        .addComponent(btnDelete)
                                                        .addGap(49)
                                                        .addComponent(btnCloseWindow)))
                                        .addContainerGap(78, Short.MAX_VALUE))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addGap(26)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(nameLabel)
                                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(pilotLabel))
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(expLabel)
                                                .addComponent(expField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(greaterBox)
                                                .addComponent(lessBox))
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(btnAdd)
                                                .addComponent(btnView)
                                                .addComponent(btnDelete)
                                                .addComponent(btnCloseWindow))
                                        .addGap(22)
                                        .addComponent(label)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                frame.getContentPane().setLayout(groupLayout);

        }
        
        public boolean addPilot(String name, String exp)
        {
                ResultSet rs;
                int maxID = 0;
                //default sql statement(if no attributes are specified
                String sql = "SELECT max(pilotID) as max FROM Pilot";
                rs = connect.execute(sql);
                
                try {
                    while(rs.next())
                    {
                        maxID = rs.getInt("max");
                        System.out.println(maxID);
                    }
                }
                catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                int newID = maxID + 1;
                if(name.equals("") || exp.equals(""))
                    return false;
                sql = "INSERT INTO Pilot VALUES(\"" + name +"\", " + newID + ", " + exp + ")";
                try {
                        connect.executeUpdate(sql);
                        return true;
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return false;
        }//addPilot
        
        /**                                viewPilots
         * This method creates a window for the user to view pilots.
         * Attributes are given by the adminPilots method
         * 
         * @param name - name of pilot to find
         * @param pID - id of pilot to find
         * @param exp - exp of pilot to find
         */
        public void viewPilots(String name, String pID, String exp, int compareExp){
                
                ResultSet rs;
                
                //default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Pilot";
                
                //if any attribute is specified, append WHERE to sql
                if(name.compareTo("") + pID.compareTo("") + exp.compareTo("") != 0)
		{
			sql = sql + " WHERE ";
			
			//append specified attribute to sql
			if(name.compareTo("") != 0)
				sql = sql + "pName = \"" + name + "\" AND ";
            if(pID.compareTo("") != 0)
				sql = sql + "pilotID = " + pID + " AND ";
            if(exp.compareTo("") != 0 && compareExp == 0)
				sql = sql + "yrExp = " + exp + " AND ";
            else if(exp.compareTo("") != 0 && compareExp > 0)
				sql = sql + "yrExp > " + exp + " AND ";
            else if(exp.compareTo("") != 0 && compareExp < 0)
				sql = sql + "yrExp < " + exp + " AND ";
			
			sql = sql.substring(0, sql.length()- 4);
			
		}
                
                //CHECK, DELETE WHEN DONE
                System.out.println(sql);
                
                rs = connect.execute(sql);
                
                //Create frame
                final JFrame frame = new JFrame();
                frame.setBounds(400, 100, 500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                JPanel panel = new JPanel();
                frame.add(panel);
                
                
                // get number of rows
                int rowNum = AirlineReservationSystem.getRowNum(rs);

                //fill Object[][] array with values
                int i = 0;
                Object[][] data = new Object[rowNum][3];
                try {
                        while (rs.next()) {

                                                                                
                                                data[i][0] = rs.getString("pName");
                                                data[i][1] = rs.getInt("pilotID");
                                                data[i][2] = rs.getInt("yrExp");
                                                
                                                i++;
                                        }
                        
                        
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                //header table
                String columnNames[] = {"pName", "Pilot ID", "yrExp"}; 
                
                //fill table
                final JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(450, 300));
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                JLabel text = new JLabel("Edit database except for PilotID");
                panel.add(text);
                panel.add(scrollPane);
                
                //tablemodellisten for editing database
                table.getModel().addTableModelListener(new TableModelListener(){
                        public void tableChanged(TableModelEvent e)
                        {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                if(column == 1)
                                        JOptionPane.showMessageDialog(frame, "Cannot change pilotID");
                                else
                                {
                                        TableModel model = (TableModel)e.getSource();                                
                                        String columnName = model.getColumnName(column);
                                        Object data = model.getValueAt(row, column);
                                        int pilotID = (int) table.getValueAt(row, 1);                                
                                        editPilot(columnName, data, pilotID);
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
                
        }//viewPilots
        
        /*
         * Admin edit Pilot database except for pilotID
         */
        public void editPilot(String columnName, Object data, int pilotID){
                String sql = "UPDATE Pilot SET " + columnName + " = \"" + data + "\" WHERE pilotID = " + pilotID;
                try {
                        connect.executeUpdate(sql);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        
        public boolean deletePilot(String name, String id) throws SQLException
        {
                if(name.equals("") && id.equals(""))
                        return false;
                ResultSet rs;
                
                //default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Pilot WHERE pName = \'" + name + "\'";
                rs = connect.execute(sql);
                if(!rs.next())
                {
                        sql = "SELECT * FROM Pilot WHERE pilotID = \'" + id + "\'";
                        rs = connect.execute(sql);
                        if(!rs.next())
                                return false;
                }
                
                
                if(name.compareTo("") != 0)
                        sql = "DELETE FROM pilot WHERE pName = \'" + name + "\'";
                else if(id.compareTo("") != 0)
                        sql = "DELETE FROM pilot WHERE pilotID = \'" + id + "\'";
                        
                
                
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