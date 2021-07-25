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
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class SeatAdmin extends Admin{

        private boolean taken = false;
        public SeatAdmin(DatabaseConnection c){connect = c;}
        
        public void admin(){
                
                final JFrame frame = new JFrame("Admin - Seat");
                frame.setVisible(true);
                frame.setBounds(200, 200, 600, 260);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JLabel lblNewLabel = new JLabel("Please enter information about the seat. Leave fields blank if you wish to view all seatss.");
                
                JLabel label = new JLabel("");
                
                JLabel lblSeatId = new JLabel("Seat ID (3)");
                
                final JTextField seatField = new JTextField();
                
                
                
                seatField.setColumns(10);
                
                JLabel rowLabel = new JLabel("Row (1)");
                
                final JTextField rowField = new JTextField();
                rowField.setColumns(10);
                
                JLabel seatNoLabel = new JLabel("Seat Number");
                
                final JTextField seatNoField = new JTextField();
                seatNoField.setColumns(10);
                
                JLabel planeIDLabel = new JLabel("Plane ID");
                
                final JTextField planeIDField = new JTextField();
                planeIDField.setColumns(10);
                
                final JCheckBox takenBox = new JCheckBox("Taken");
                
                JButton btnAdd = new JButton("Add");
                
                btnAdd.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                if(takenBox.isSelected())
                                    taken = true;
                                else if(!takenBox.isSelected())
                                    taken = false;
                                if(addSeat(seatField.getText(), rowField.getText(), seatNoField.getText() + "", taken, planeIDField.getText()+""))
                                    JOptionPane.showMessageDialog(frame, "Seat " + seatField.getText() + " has been added to database.");
                                else
                                    JOptionPane.showMessageDialog(frame, "Please enter complete information and make sure seatID and row is not too long and seatID is not already in the database.");

                        }
                });
                                
                JButton btnView = new JButton("View and Edit");
                
                btnView.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                                
                                if(takenBox.isSelected())
                                    taken = true;
                                else if(!takenBox.isSelected())
                                    taken = false;
                                viewSeat(seatField.getText(), rowField.getText(), seatNoField.getText() + "", taken, planeIDField.getText()+"");
                                
                        }
                });                
                
                JButton btnDelete = new JButton("Delete");
                
                btnDelete.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                        try {
                                if(deleteSeat(seatField.getText(), planeIDField.getText()))
                                        JOptionPane.showMessageDialog(frame, "Seat(s) " + seatField.getText() + " of plane" + planeIDField.getText() + " got deleted from database.");
                                else
                                        JOptionPane.showMessageDialog(frame, "Please enter seatID  or planeID that exists in the database!");
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
                                                .addComponent(lblSeatId)
                                                .addComponent(rowLabel))
                                        .addContainerGap(516, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(label)
                                                                .addComponent(lblNewLabel))
                                                        .addGap(66))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(seatNoLabel)
                                                                .addComponent(planeIDLabel))
                                                        .addGap(18)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(planeIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(seatNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18)
                                                                        .addComponent(takenBox))
                                                                .addComponent(rowField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(btnAdd)
                                                                .addComponent(btnView)
                                                                .addComponent(btnDelete)
                                                                .addComponent(btnCloseWindow))
                                                        .addContainerGap(58, Short.MAX_VALUE))))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addGap(26)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblSeatId)
                                                .addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnAdd))
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(rowLabel)
                                                                .addComponent(rowField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(seatNoLabel)
                                                                .addComponent(seatNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(takenBox))
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(planeIDLabel)
                                                                .addComponent(planeIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGap(19)
                                                        .addComponent(label))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGap(6)
                                                        .addComponent(btnView)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(btnDelete)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(btnCloseWindow)))
                                        .addContainerGap(11, Short.MAX_VALUE))
                );
                frame.getContentPane().setLayout(groupLayout);
        }
        
        public boolean addSeat(String seatID, String row, String seatNo, boolean t, String planeID)
        {
                ResultSet rs;
                String sql = "SELECT sID from Seat";
                rs = connect.execute(sql);
                try {
                    while(rs.next())
                    {
                        String id = rs.getString("sID");
                        if(id.equalsIgnoreCase(seatID))
                            return false;
                    }
                }
                catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                if(seatID.equals("") || row.equals("") || seatNo.equals("") || planeID.equals(""))
                    return false;
                if(seatID.length()>3 || row.length()>1)
                    return false;
                sql = "INSERT INTO Seat(sID, row, seatNo, taken, planeID) "
                                                + "VALUES(\""
                                                + seatID
                                                + "\", \""
                                                + row
                                                + "\", "
                                                + seatNo
                                                + ", "
                                                + t
                                                + ", "
                                                + planeID + ")";
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
        
        public void viewSeat(String seatID, String row, String seatNo, boolean t, String planeID)
        {
                ResultSet rs;
                
                //default sql statement(if no attributes are specified
                String sql = "SELECT * FROM Seat";
                if(t)
                        sql = sql + " WHERE taken = true ";
                //if any attribute is specified, append WHERE to sql
                if(seatID.compareTo("") + row.compareTo("") + seatNo.compareTo("") + planeID.compareTo("")!= 0)
		{
                        if(t)
                                sql = sql + " AND ";
                        else if(!t)
                                sql = sql + " WHERE ";
			//append specified attribute to sql
			if(seatID.compareTo("") != 0)
				sql = sql + "sID = \"" + seatID + "\" AND ";
            if(row.compareTo("") != 0)
				sql = sql + "row = \"" + row + "\" AND ";
            if(seatNo.compareTo("") != 0)
				sql = sql + "seatNo = " + seatNo + " AND ";
            if(planeID.compareTo("") != 0)
				sql = sql + "planeID = \"" + planeID + "\" AND ";
			
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
                Object[][] data = new Object[rowNum][5];
                try {
                        while (rs.next()) {

                                                                                
                                                data[i][0] = rs.getString("sID");
                                                data[i][1] = rs.getString("row");
                                                data[i][2] = rs.getInt("seatNo");
                                                data[i][3] = rs.getInt("taken");
                                                data[i][4] = rs.getInt("planeID");
                                                i++;
                                        }
                        
                        
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                //header table
                String columnNames[] = {"sID", "row", "seatNo", "taken", "planeID"}; 
                
                //fill table
                final JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(650, 300));
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                JLabel text = new JLabel("Edit database except for seat ID");
                panel.add(text);
                panel.add(scrollPane);
                
                //tablemodellisten for editing database
                table.getModel().addTableModelListener(new TableModelListener(){
                        public void tableChanged(TableModelEvent e)
                        {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                if(column == 0)
                                        JOptionPane.showMessageDialog(frame, "Warning: Cannot change sID");
                                else
                                {
                                        TableModel model = (TableModel)e.getSource();                                
                                        String columnName = model.getColumnName(column);
                                        Object data = model.getValueAt(row, column);
                                        if(column == 1 && data.toString().length() > 1)
                                                JOptionPane.showMessageDialog(frame, "Warning: Row is only 1 character long.");
                                        else
                                        {
                                                String sID = table.getValueAt(row, 0).toString();                                
                                                editSeat(columnName, data, sID);
                                        }
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
        public void editSeat(String columnName, Object data, String sID){
                String sql = "UPDATE Seat SET " + columnName + " = \"" + data + "\" WHERE sID = \"" + sID + "\"";
                try {
                        connect.executeUpdate(sql);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        
        public boolean deleteSeat(String sID, String planeID) throws SQLException
        {
                if(sID.equals("") && planeID.equals(""))
                        return false;
                ResultSet rs;
                
                String sql = "SELECT * FROM Seat WHERE sID = \'" + sID + "\'";
                rs = connect.execute(sql);
                if(!rs.next())
                {
                        sql = "SELECT * FROM Seat WHERE planeID = \'" + planeID + "\'";
                        rs = connect.execute(sql);
                        if(!rs.next())
                                return false;
                }
                
                
                if(sID.compareTo("") != 0)
                        sql = "DELETE FROM Seat WHERE sID = \'" + sID + "\'";
                else if(planeID.compareTo("") != 0)
                        sql = "DELETE FROM Seat WHERE planeID = \'" + planeID + "\'";
                        
                
                
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