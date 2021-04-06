package wachira.fuzu;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

class Teacher_registration extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JTextField teacherFName, teacherLName, teacherUserName,teacherPhoneNo,teacherEmail,teacherClass,teacherPassword;
    private JButton btnTRegister;
    private JButton btnTCancel;
    private JLabel lblteacherFName,headerLabel,lblteacherLName,lblTeacherUserName, lblteacherPhoneNo, lblteacherEmail, lblteacherClass,lblteacherPassword;
    private Container container2;
    private JPanel headerPanel, teacherFNamePanel,teacherLNamePanel, teacheruserNamePanel,teacherEmailPanel,teacherPhonePanel,teacherClassPanel, teacherbtnPanel,teacherPasswordPanel;
    private ImageIcon logoImage;


    public Teacher_registration(){
        super("Teacher Registration Form");
        container2=getContentPane();
        layout= new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage= new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel= new JLabel();
        headerLabel.setBounds(10,11,200,200);
        headerPanel=new JPanel();
        headerPanel.setPreferredSize(new Dimension(300,200));
        headerPanel.add(headerLabel);
        container2.add(headerPanel);
        Image img=logoImage.getImage();
        Image newImage=img.getScaledInstance(headerLabel.getWidth(),headerLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalLogo= new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);

        teacherFNamePanel=new JPanel();
        teacherFNamePanel.setPreferredSize(new Dimension(300,50));
        teacherFNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherFName= new JLabel("First Name: ");
        teacherFNamePanel.add(lblteacherFName);
        teacherFName= new JTextField(30);
        teacherFNamePanel.add(teacherFName);
        container2.add(teacherFNamePanel);

        teacherLNamePanel=new JPanel();
        teacherLNamePanel.setPreferredSize(new Dimension(300,50));
        teacherLNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherLName= new JLabel("Last Name: ");
        teacherLNamePanel.add(lblteacherLName);
        teacherLName= new JTextField(30);
        teacherLNamePanel.add(teacherLName);
        container2.add(teacherLNamePanel);

        teacheruserNamePanel=new JPanel();
        teacheruserNamePanel.setPreferredSize(new Dimension(300,50));
        teacheruserNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblTeacherUserName= new JLabel("Username: ");
        teacheruserNamePanel.add(lblTeacherUserName);
        teacherUserName= new JTextField(30);
        teacheruserNamePanel.add(teacherUserName);
        container2.add(teacheruserNamePanel);

        teacherPhonePanel=new JPanel();
        teacherPhonePanel.setPreferredSize(new Dimension(300,50));
        teacherPhonePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherPhoneNo= new JLabel("Phone number: ");
        teacherPhonePanel.add(lblteacherPhoneNo);
        teacherPhoneNo= new JTextField(30);
        teacherPhonePanel.add(teacherPhoneNo);
        container2.add(teacherPhonePanel);

        teacherEmailPanel=new JPanel();
        teacherEmailPanel.setPreferredSize(new Dimension(300,50));
        teacherEmailPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherEmail= new JLabel("Email: ");
        teacherEmailPanel.add(lblteacherEmail);
        teacherEmail= new JTextField(30);
        teacherEmailPanel.add(teacherEmail);
        container2.add(teacherEmailPanel);


        teacherClassPanel=new JPanel();
        teacherClassPanel.setPreferredSize(new Dimension(300,50));
        teacherClassPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherClass= new JLabel("Class: ");
        teacherClassPanel.add(lblteacherClass);
        teacherClass= new JTextField(30);
        teacherClassPanel.add(teacherClass);
        container2.add(teacherClassPanel);


        teacherPasswordPanel=new JPanel();
        teacherPasswordPanel.setPreferredSize(new Dimension(300,50));
        teacherPasswordPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherPassword= new JLabel("Password: ");
        teacherPasswordPanel.add(lblteacherPassword);
        teacherPassword= new JTextField(30);
        teacherPasswordPanel.add(teacherPassword);
        container2.add(teacherPasswordPanel);

        teacherbtnPanel=new JPanel();
        teacherbtnPanel.setPreferredSize(new Dimension(300,50));
        teacherbtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        teacherbtnPanel.setBackground(Color.GRAY);
        btnTRegister = new JButton("Register");
        btnTRegister.addActionListener(this::btnRegisterActionPerformed);
        btnTCancel =new JButton("Cancel");
        btnTCancel.addActionListener(this);
        teacherbtnPanel.add(btnTRegister);
        teacherbtnPanel.add(btnTCancel);
        container2.add(teacherbtnPanel);

    }
    public boolean verifyFields(){
        String str_fname=teacherFName.getText();
        String str_lname=teacherLName.getText();
        String str_username=teacherUserName.getText();
        String str_email=teacherEmail.getText();
        String str_phone=teacherPhoneNo.getText();
        String str_class=teacherClass.getText();
        String str_pass=teacherPassword.getText();

        if(str_fname.trim().equals("")||str_lname.trim().equals("")||str_username.trim().equals("")||str_email.trim().equals("")
                ||str_phone.trim().equals("")||str_class.trim().equals("")||str_pass.trim().equals("")){
            JOptionPane.showMessageDialog(null, "One Or More Of The Fields Are Empty", "Empty Fields", 2);
            return false;
        }else {
            return true;
        }
    }
    public boolean checkUsername(String str_username){
        PreparedStatement statement;
        ResultSet rs;
        boolean username_exists= false;
        String query="SELECT * FROM `tbl_teacherdetails` WHERE `teacherUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_username);
            rs = statement.executeQuery();
            if (rs.next()) {
                username_exists = true;
                JOptionPane.showMessageDialog(null, "This Username is Already in use", "Username in use", 2);
            }
        }catch (SQLException ex){
            Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE,null,ex);
        }
        return username_exists;

    }
    private void btnRegisterActionPerformed(ActionEvent evt){
        String str_teacherfname=teacherFName.getText();
        String str_teacherlname=teacherLName.getText();
        String str_teacherusername=teacherUserName.getText();
        String str_pass=teacherPassword.getText();
        String str_teacherphone=teacherPhoneNo.getText();
        String str_teacheremail=teacherEmail.getText();
        String str_teacherclass=teacherClass.getText();
        System.out.println(str_teacherclass);
        int classTaught = Integer.parseInt(str_teacherclass);
        System.out.println(classTaught);
        if (verifyFields()){
            if(!checkUsername(str_teacherusername)){
                PreparedStatement ps;
                ResultSet rs;
                String reg_TeachQuery="INSERT INTO `tbl_teacherdetails`(`teacherfName`, `teacherlName`, `teacherUsername`, `teacherPhone`, `teacherEmail`, `classTaught`, `teacherPassword`) VALUES (?,?,?,?,?,?,?)";
                try{
                    ps=Main.getConnection().prepareStatement(reg_TeachQuery);
                    ps.setString(1,str_teacherfname);
                    ps.setString(2,str_teacherlname);
                    ps.setString(3,str_teacherusername);
                    ps.setString(4,str_teacherphone);
                    ps.setString(5,str_teacheremail);
                    ps.setInt(6,classTaught);
                    ps.setString(7,str_pass);
                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Your Have been registered");
                        this.dispose();
                        Teacher_dashboard TeachDash_Form = new Teacher_dashboard();
                        TeachDash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        TeachDash_Form.setVisible(true);
                        TeachDash_Form.setSize(500,500);
                        TeachDash_Form.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration was unsuccessful");
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "The error is here");
                    Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnTCancel) {
            this.dispose();
            Login L_Form = new Login();
            L_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            L_Form.setVisible(true);
            L_Form.setSize(500,500);
            L_Form.setLocationRelativeTo(null);
        }

    }
}
