package wachira.fuzu;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

public class Parent_registration extends JFrame implements ActionListener{
    private FlowLayout layout;
    private JTextField parentFName, parentLName, pRelationship, parentUserName,parentPhoneNo,parentEmail,childFName,childUserName,childLName,parentPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JLabel lblparentFName,headerLabel,lblparentLName,lblchildFName,lblchildLName,lblpRelationship,lblParentUserName, lblparentPhoneNo, lblparentEmail, lblchilduserName,lblparentPassword;
    private Container container;
    private JPanel headerPanel, parentFNamePanel,parentLNamePanel, childFNamePanel,parentuserNamePanel,parentEmailPanel,childLNamePanel,
    pRelationshipPanel,parentPhonePanel,childuserNamePanel, parentbtnPanel, parentPasswordPanel;
    private ImageIcon logoImage;


    public Parent_registration(){
        super("Parent Registration Form");
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

        parentFNamePanel=new JPanel();
        parentFNamePanel.setPreferredSize(new Dimension(300,50));
        parentFNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentFName= new JLabel("First Name: ");
        parentFNamePanel.add(lblparentFName);
        parentFName= new JTextField(30);
        parentFNamePanel.add(parentFName);
        container.add(parentFNamePanel);

        parentLNamePanel=new JPanel();
        parentLNamePanel.setPreferredSize(new Dimension(300,50));
        parentLNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentLName= new JLabel("Last Name: ");
        parentLNamePanel.add(lblparentLName);
        parentLName= new JTextField(30);
        parentLNamePanel.add(parentLName);
        container.add(parentLNamePanel);

        parentuserNamePanel=new JPanel();
        parentuserNamePanel.setPreferredSize(new Dimension(300,50));
        parentuserNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblParentUserName= new JLabel("Parent's Username: ");
        parentuserNamePanel.add(lblParentUserName);
        parentUserName= new JTextField(30);
        parentuserNamePanel.add(parentUserName);
        container.add(parentuserNamePanel);

        parentPhonePanel=new JPanel();
        parentPhonePanel.setPreferredSize(new Dimension(300,50));
        parentPhonePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentPhoneNo= new JLabel("Phone Number: ");
        parentPhonePanel.add(lblparentPhoneNo);
        parentPhoneNo= new JTextField(30);
        parentPhonePanel.add(parentPhoneNo);
        container.add(parentPhonePanel);

        parentEmailPanel=new JPanel();
        parentEmailPanel.setPreferredSize(new Dimension(300,50));
        parentEmailPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentEmail= new JLabel("Email: ");
        parentEmailPanel.add(lblparentEmail);
        parentEmail= new JTextField(30);
        parentEmailPanel.add(parentEmail);
        container.add(parentEmailPanel);

        pRelationshipPanel=new JPanel();
        pRelationshipPanel.setPreferredSize(new Dimension(300,50));
        pRelationshipPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblpRelationship= new JLabel("Relationship to child: ");
        pRelationshipPanel.add(lblpRelationship);
        pRelationship= new JTextField(30);
        pRelationshipPanel.add(pRelationship);
        container.add(pRelationshipPanel);

        childFNamePanel=new JPanel();
        childFNamePanel.setPreferredSize(new Dimension(300,50));
        childFNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildFName= new JLabel("Child First Name: ");
        childFNamePanel.add(lblchildFName);
        childFName= new JTextField(30);
        childFNamePanel.add(childFName);
        container.add(childFNamePanel);

        childLNamePanel=new JPanel();
        childLNamePanel.setPreferredSize(new Dimension(300,50));
        childLNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildLName= new JLabel("Child Last Name: ");
        childLNamePanel.add(lblchildLName);
        childLName= new JTextField(30);
        childLNamePanel.add(childLName);
        container.add(childLNamePanel);

        childuserNamePanel=new JPanel();
        childuserNamePanel.setPreferredSize(new Dimension(300,50));
        childuserNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchilduserName= new JLabel("Child's Username: ");
        childuserNamePanel.add(lblchilduserName);
        childUserName= new JTextField(30);
        childuserNamePanel.add(childUserName);
        container.add(childuserNamePanel);

        parentPasswordPanel=new JPanel();
        parentPasswordPanel.setPreferredSize(new Dimension(300,50));
        parentPasswordPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentPassword= new JLabel("Password: ");
        parentPasswordPanel.add(lblparentPassword);
        parentPassword= new JTextField(30);
        parentPasswordPanel.add(parentPassword);
        container.add(parentPasswordPanel);

        parentbtnPanel=new JPanel();
        parentbtnPanel.setPreferredSize(new Dimension(300,50));
        parentbtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        parentbtnPanel.setBackground(Color.GRAY);
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this::btnRegisterActionPerformed);
        btnCancel =new JButton("Cancel");
        btnCancel.addActionListener(this);
        parentbtnPanel.add(btnRegister);
        parentbtnPanel.add(btnCancel);
        container.add(parentbtnPanel);

    }
    public boolean verifyFields(){
        String str_parentfname=parentFName.getText();
        String str_parentlname=parentLName.getText();
        String str_parentusername=parentUserName.getText();
        String str_email=parentEmail.getText();
        String str_relationship=pRelationship.getText();
        String str_phone=parentPhoneNo.getText();
        String str_childfname=childFName.getText();
        String str_childlname=childLName.getText();
        String str_childusername=childUserName.getText();
        String str_pass=parentPassword.getText();

        if(str_parentfname.trim().equals("")||str_parentlname.trim().equals("")||str_parentusername.trim().equals("")||str_email.trim().equals("")
                ||str_phone.trim().equals("")||str_relationship.trim().equals("")||str_pass.trim().equals("")||str_childfname.trim().equals("")
                ||str_childlname.trim().equals("")||str_childusername.trim().equals("")){
            JOptionPane.showMessageDialog(null, "One Or More Of The Fields Are Empty", "Empty Fields", 2);
            return false;
        }else {
            return true;
        }
    }
    public boolean checkPUsername(String str_Pusername){
        PreparedStatement statement;
        ResultSet rs;
        boolean pusername_exists= false;
        String query="SELECT * FROM `tbl_parentdetails` WHERE `parentUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_Pusername);
            rs = statement.executeQuery();
            if (rs.next()) {
                pusername_exists = true;
                JOptionPane.showMessageDialog(null, "This Username is Already in use", "Username in use", 2);
            }
        }catch (SQLException ex){
            Logger.getLogger(Parent_registration.class.getName()).log(Level.SEVERE,null,ex);
        }
        return pusername_exists;

    }
    public boolean checkCUsername(String str_Cusername){
        PreparedStatement statement;
        ResultSet rs;
        boolean cusername_exists= false;
        String query="SELECT * FROM `tbl_childdetails` WHERE `childUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_Cusername);
            rs = statement.executeQuery();
            if (rs.next()) {
                cusername_exists = true;
                JOptionPane.showMessageDialog(null, "This Username is Already in use", "Username in use", 2);
            }
        }catch (SQLException ex){
            Logger.getLogger(Parent_registration.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cusername_exists;

    }
    private void btnRegisterActionPerformed(ActionEvent evt){
        String str_parentfname=parentFName.getText();
        String str_parentlname=parentLName.getText();
        String str_parentusername=parentUserName.getText();
        String str_email=parentEmail.getText();
        String str_relationship=pRelationship.getText();
        String str_phone=parentPhoneNo.getText();
        String str_childfname=childFName.getText();
        String str_childlname=childLName.getText();
        String str_childusername=childUserName.getText();
        String str_pass=parentPassword.getText();
        if (verifyFields()){
            if(!checkPUsername(str_parentusername)&&!checkCUsername(str_childusername)){
                PreparedStatement ps,ps2;
                String reg_ParentQuery="INSERT INTO `tbl_parentdetails`(`parentfName`, `parentlName`, `parentUsername`, `parentPhone`, `parentEmail`, `relationship`, `parentPassword`) VALUES (?,?,?,?,?,?,?)";
                String reg_ChildQuery="INSERT INTO `tbl_childdetails`(`childfName`, `childlName`, `childUsername`, `parentUsername`) VALUES (?,?,?,?)";
                try{
                    ps=Main.getConnection().prepareStatement(reg_ParentQuery);
                    ps.setString(1,str_parentfname);
                    ps.setString(2,str_parentlname);
                    ps.setString(3,str_parentusername);
                    ps.setString(4,str_phone);
                    ps.setString(5,str_email);
                    ps.setString(6,str_relationship);
                    ps.setString(7,str_pass);


                    ps2=Main.getConnection().prepareStatement(reg_ChildQuery);
                    ps2.setString(1,str_childfname);
                    ps2.setString(2,str_childlname);
                    ps2.setString(3,str_childusername);
                    ps2.setString(4,str_parentusername);

                    if (ps.executeUpdate() != 0 && ps2.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Your Have been registered");
                        this.dispose();
                        Parent_dashboard Parentdash_Form = new Parent_dashboard();
                        Parentdash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Parentdash_Form.setVisible(true);
                        Parentdash_Form.setSize(500,500);
                        Parentdash_Form.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration was unsuccessful");
                    }
                }catch (SQLException ex){
                    Logger.getLogger(Parent_registration.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnCancel) {
            this.dispose();
            Login L_Form = new Login();
            L_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            L_Form.setVisible(true);
            L_Form.setSize(500,500);
            L_Form.setLocationRelativeTo(null);
        }
    }
}
