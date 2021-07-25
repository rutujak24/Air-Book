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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PlaneAdmin extends Admin{

        public PlaneAdmin(DatabaseConnection c)
        {
                connect = c;
        }
        
public void admin(){
                
                final JFrame frame = new JFrame("Admin - Plane");
                frame.setVisible(true);
                frame.setBounds(200, 200, 578, 256);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JLabel lblNewLabel = new JLabel("Please enter information about the pilot. Leave fields blank if you wish to view all pilots.");
                
                JLabel label = new JLabel("");
                
                JButton btnAdd = new JButton("Add");
                
                
                JButton btnView = new JButton("View and Edit");
                
                
                
                JButton btnDelete = new JButton("Delete");
                
                JButton btnCloseWindow = new JButton("Close Window");
                
                JLabel idLabel = new JLabel("Plane ID");
                
                final JTextField idField = new JTextField();
                idField.setColumns(10);
                
                JLabel ageLabel = new JLabel("Plane Age");
                
                final JTextField  ageField = new JTextField();
                ageField.setColumns(10);
                
                final JCheckBox olderBox = new JCheckBox("Older Than");
                
                final JCheckBox youngerBox = new JCheckBox("Younger Than");
                
                
                
                btnDelete.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                        try {
                                if(deletePlane(idField.getText()))
                                        JOptionPane.showMessageDialog(frame, "Plane " + idField.getText() +" has been deleted from the database.");
                                else
                                        JOptionPane.showMessageDialog(frame, "Please enter a Plane ID that exists in the database!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        }
                });
                
                
                btnAdd.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                if(addPlane(ageField.getText(), idField.getText()))
                                    JOptionPane.showMessageDialog(frame, "Plane " + idField.getText() + " has been added to database.");
                                else
                                    JOptionPane.showMessageDialog(frame, "Please enter unused planeID and an age!");

                        }
                });
                
                
                btnView.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                                if(!ageField.getText().equals("") && olderBox.isSelected() && youngerBox.isSelected())
                                        JOptionPane.showMessageDialog(frame, "Please only pick either greater or less than!");
                                else
                                {
                                        int compareExp;
                                        
                                        if(olderBox.isSelected())
                                            compareExp = 1;
                                        else if(youngerBox.isSelected())
                                            compareExp = -1;
                                        else
                                            compareExp = 0;
                                        viewPlanes(idField.getText(), ageField.getText() +"", compareExp);
                                }
                        }
                });
                
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
                                                        .addComponent(idLabel)
                                                        .addGap(18)
                                                        .addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(ageLabel)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(btnAdd)
                                                                        .addGap(34)
                                                                        .addComponent(btnView)))
                                                        .addGap(33)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(olderBox)
                                                                .addComponent(btnDelete))
                                                        .addGap(18)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(btnCloseWindow)
                                                                        .addGap(46))
                                                                .addComponent(youngerBox))))
                                        .addContainerGap(68, Short.MAX_VALUE))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(idLabel)
                                                .addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(ageLabel)
                                                .addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(olderBox)
                                                .addComponent(youngerBox))
                                        .addGap(50)
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

        public boolean deletePlane(String id) throws SQLException {
                if (id.equals(""))
                        return false;
                ResultSet rs;

                // default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Plane WHERE planeID = " + id + "";
                rs = connect.execute(sql);
                
                if (!rs.next()) {
                        return false;
                }

                if (id.compareTo("") != 0)
                        sql = "DELETE FROM Plane WHERE planeID = \'" + id + "\'";

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

        public boolean addPlane(String age, String id) {
                
                
                if (age.equals("") || id.equals(""))
                        return false;
                
                String sql = "INSERT INTO Plane VALUES(" + id + ", " + age + ")";
                
                String sqlCheck = "SELECT * FROM PLANE WHERE planeID = " + id;
                System.out.println(sqlCheck);
                ResultSet rs;
                rs = connect.execute("SELECT * FROM PLANE WHERE planeID = " + id);
                
                try {
                        if(AirlineReservationSystem.getRowNum(rs) == 0)
                        {
                                
                                        connect.executeUpdate(sql);
                                        return true;
                                
                                        
                                
                        }
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return false;
        }// addPilot

        public void viewPlanes(String pID, String age, int compareExp) {

                ResultSet rs;

                // default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Plane";

                // if any attribute is specified, append WHERE to sql
                if (pID.compareTo("") + age.compareTo("") != 0) {
			sql = sql + " WHERE ";

			// append specified attribute to sql
			if (pID.compareTo("") != 0)
				sql = sql + "planeID = " + pID + " AND ";
			
			if (age.compareTo("") != 0 && compareExp == 0)
				sql = sql + "age = " + age + " AND ";
			else if (age.compareTo("") != 0 && compareExp > 0)
				sql = sql + "age > " + age +" AND ";
			else if (age.compareTo("") != 0 && compareExp < 0)
				sql = sql + "age < " + age + " AND ";
			
			sql = sql.substring(0, sql.length() - 4);

		}

                // CHECK, DELETE WHEN DONE
                System.out.println(sql);
                rs = connect.execute(sql);

                // Create frame
                final JFrame frame = new JFrame();
                frame.setBounds(400, 100, 500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                JPanel panel = new JPanel();
                frame.add(panel);

                // get number of rows
                int rowNum = AirlineReservationSystem.getRowNum(rs);

                // fill Object[][] array with values
                int i = 0;
                Object[][] data = new Object[rowNum][2];
                try {
                        while (rs.next()) {

                                data[i][0] = rs.getString("planeID");
                                data[i][1] = rs.getInt("age");

                                i++;
                        }

                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                // header table
                String columnNames[] = { "Plane ID", "Age" };

                // fill table
                final JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(450, 300));
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                JLabel text = new JLabel("Edit database except for PlaneID");
                panel.add(text);
                panel.add(scrollPane);

                // tablemodellisten for editing database
                table.getModel().addTableModelListener(new TableModelListener() {
                        public void tableChanged(TableModelEvent e) {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                if(column == 0)
                                        JOptionPane.showMessageDialog(frame, "Cannot change planeID");
                                else
                                {    
                                        TableModel model = (TableModel) e.getSource();
                                        String columnName = model.getColumnName(column);
                                        Object data = model.getValueAt(row, column);
                                        String planeID = (String) table.getValueAt(row, 0);
                                        editPlane(columnName, data, planeID);
                                }
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

        public void editPlane(String columnName, Object data, String planeID) {
                String sql = "UPDATE Plane SET " + columnName + " = " + data
                                + " WHERE planeID = " + planeID;
                System.out.println(sql);
                try {
                        connect.executeUpdate(sql);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
}