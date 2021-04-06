package wachira.fuzu;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewDetails extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JButton btnshowEntire, btnClose, btnSearchStudent;
    private JTextField txtStdUsername;
    private JTable tblDetails;
    private JLabel  lblSearchStudent,headerLabel,lbltablecontents;
    private Container container;
    private JPanel headerPanel, searchPanel, tableheaderPanel, tablePanel;
    private ImageIcon logoImage;
    private JScrollPane pane;
    private DefaultTableModel model= new DefaultTableModel();

    public ViewDetails(){
        super("Fuzu Primary");
        container=getContentPane();
        layout= new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage= new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel= new JLabel();
        headerLabel.setBounds(10,11,200,200);
        headerPanel=new JPanel();
        headerPanel.setPreferredSize(new Dimension(300,200));
        headerPanel.add(headerLabel);
        container.add(headerPanel);
        Image img=logoImage.getImage();
        Image newImage=img.getScaledInstance(headerLabel.getWidth(),headerLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalLogo= new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);

        searchPanel=new JPanel();
        searchPanel.setPreferredSize(new Dimension(300,100));
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblSearchStudent= new JLabel("Student Username: ");
        searchPanel.add(lblSearchStudent);
        txtStdUsername= new JTextField(30);
        searchPanel.add(txtStdUsername);
        btnSearchStudent = new JButton("Search");
        btnSearchStudent.addActionListener(this::btnSearchActionPerformed);
        btnshowEntire =new JButton("Entire table");
        btnshowEntire.addActionListener(this::btnshowEntirActionPerformed);
        btnClose =new JButton("Exit");
        btnClose.addActionListener(this);
        searchPanel.add(btnSearchStudent);
        searchPanel.add(btnshowEntire);
        searchPanel.add(btnClose);
        container.add(searchPanel);

        tableheaderPanel=new JPanel();
        tableheaderPanel.setPreferredSize(new Dimension(350,50));
        tableheaderPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lbltablecontents= new JLabel("Table details");
        lbltablecontents.setFont(new Font("Arial",Font.ITALIC,12));
        tableheaderPanel.add(lbltablecontents);
        container.add(tableheaderPanel);


        tablePanel=new JPanel();
        tablePanel.setPreferredSize(new Dimension(300,60));
        tablePanel.setLayout(new BorderLayout());

        tblDetails=new JTable(model);
        pane= new JScrollPane(tblDetails);
        pane.setVisible(true);
        tablePanel.add(pane,BorderLayout.CENTER);
        tablePanel.add(tblDetails);
        model.addColumn("childUsername");
        model.addColumn("mathematics");
        model.addColumn("english");
        model.addColumn("Kiswahili");
        PreparedStatement st;
        ResultSet rs;
        String query="SELECT `childUsername`,`mathematics`,`english`,`kiswahili` FROM `tbl_gradedetails`";
        try{
            st = Main.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()){

                model.addRow(new Object[]{rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4)});
            }
        }catch (SQLException ex){
            Logger.getLogger(ViewDetails.class.getName()).log(Level.SEVERE,null,ex);
        }


        container.add(tablePanel);
    }
    public boolean verifyFields(){
        String str_findUser=txtStdUsername.getText();

        if(str_findUser.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Field is Empty", "Empty Field", 2);
            return false;
        }else {
            return true;
        }
    }
    public boolean findUsername(String str_username){
        PreparedStatement statement;
        ResultSet rs;
        boolean username_exists= false;
        String query="SELECT `childUsername` FROM `tbl_gradedetails` WHERE `childUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_username);
            rs = statement.executeQuery();
            if (rs.next()) {
                username_exists = true;

            }else{
                JOptionPane.showMessageDialog(null, "This Username is Already in use", "Username in use", 2);
            }
        }catch (SQLException ex){
            Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE,null,ex);
        }
        return username_exists;

    }
    private void btnSearchActionPerformed(ActionEvent evt){
        String findUser=txtStdUsername.getText();
        if (verifyFields()) {
            if (findUsername(findUser)) {
                model.setRowCount(0);
                tblDetails.setModel(model);
                model.addColumn("childUsername");
                model.addColumn("mathematics");
                model.addColumn("english");
                model.addColumn("Kiswahili");

                PreparedStatement ps;
                ResultSet rs = null;
                String query = "SELECT * FROM `tbl_gradedetails` WHERE `childUsername`=?";
                try {
                    ps = Main.getConnection().prepareStatement(query);
                    ps.setString(1, findUser);
                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Showing Details of: " + findUser);
                        while (rs.next()) {
                            model.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)});
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration was unsuccessful");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    private void btnshowEntirActionPerformed(ActionEvent evt){
        DefaultTableModel model3= new DefaultTableModel();
        tblDetails=new JTable(model3);
        model3.addColumn("childUsername");
        model3.addColumn("mathematics");
        model3.addColumn("english");
        model3.addColumn("Kiswahili");
        PreparedStatement st;
        ResultSet rs;
        String query="SELECT `childUsername`,`mathematics`,`english`,`kiswahili` FROM `tbl_gradedetails`";
        try{
            st = Main.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()){
                model3.addRow(new Object[]{rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4)});
            }
        }catch (SQLException ex){
            Logger.getLogger(ViewDetails.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnClose) {
            if (Homepage.getPortalSelected() == "teacher") {
                this.dispose();
                Teacher_dashboard TeachDash_Form = new Teacher_dashboard();
                TeachDash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TeachDash_Form.setVisible(true);
                TeachDash_Form.setSize(500,500);
                TeachDash_Form.setLocationRelativeTo(null);
            } else {
                this.dispose();
                Parent_dashboard Parentdash_Form = new Parent_dashboard();
                Parentdash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Parentdash_Form.setVisible(true);
                Parentdash_Form.setSize(500,500);
                Parentdash_Form.setLocationRelativeTo(null);
            }
        }
    }
}

