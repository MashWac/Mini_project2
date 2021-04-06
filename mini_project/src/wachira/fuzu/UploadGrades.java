package wachira.fuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UploadGrades extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JTextField childUsername, txtMath, txtEng, txtSwa,teacheruserName;
    private JButton btnUpload,btnCancel;
    private JLabel lblMath,headerLabel,lblEng, lblSwa,lblchildUsername,lblteacherUsername;
    private Container container;
    private JPanel headerPanel, childMathPanel,childEngPanel, childSwaPanel, btnPanel,childUsernamePanel,teacherusernamePanel;
    private ImageIcon logoImage;


    public UploadGrades(){
        super("Welcome to Fuzu Primary");
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

        childUsernamePanel=new JPanel();
        childUsernamePanel.setPreferredSize(new Dimension(300,50));
        childUsernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildUsername= new JLabel("Child Username: ");
        childUsernamePanel.add(lblchildUsername);
        childUsername= new JTextField(30);
        childUsernamePanel.add(childUsername);
        container.add(childUsernamePanel);

        childMathPanel=new JPanel();
        childMathPanel.setPreferredSize(new Dimension(300,50));
        childMathPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblMath= new JLabel("Mathematics: ");
        childMathPanel.add(lblMath);
        txtMath= new JTextField(30);
        childMathPanel.add(txtMath);
        container.add(childMathPanel);

        childEngPanel=new JPanel();
        childEngPanel.setPreferredSize(new Dimension(300,50));
        childEngPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblEng= new JLabel("English: ");
        childEngPanel.add(lblEng);
        txtEng= new JTextField(30);
        childEngPanel.add(txtEng);
        container.add(childEngPanel);

        childSwaPanel=new JPanel();
        childSwaPanel.setPreferredSize(new Dimension(300,50));
        childSwaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblSwa= new JLabel("Kiswahili:");
        childSwaPanel.add(lblSwa);
        txtSwa= new JTextField(30);
        childSwaPanel.add(txtSwa);
        container.add(childSwaPanel);

        teacherusernamePanel=new JPanel();
        teacherusernamePanel.setPreferredSize(new Dimension(300,50));
        teacherusernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblteacherUsername= new JLabel("Teacher Username: ");
        teacherusernamePanel.add(lblteacherUsername);
        teacheruserName= new JTextField(30);
        teacherusernamePanel.add(teacheruserName);
        container.add(teacherusernamePanel);


        btnPanel=new JPanel();
        btnPanel.setPreferredSize(new Dimension(300,50));
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.GRAY);
        btnUpload = new JButton("Upload");
        btnUpload.addActionListener(this::btnUploadGradesActionPerformed);
        btnCancel =new JButton("Cancel");
        btnCancel.addActionListener(this);
        btnPanel.add(btnUpload);
        btnPanel.add(btnCancel);
        container.add(btnPanel);



    }
    public boolean verifyFields(){
        String str_username=childUsername.getText();
        String str_math=txtMath.getText();
        String str_eng=txtEng.getText();
        String str_swa=txtSwa.getText();
        String str_teachusername=teacheruserName.getText();


        if(str_username.trim().equals("")||str_math.trim().equals("") ||str_eng.trim().equals("")||str_swa.trim().equals("")||str_teachusername.trim().equals("")){
            JOptionPane.showMessageDialog(null, "One Or More Of The Fields Are Empty", "Empty Fields", 2);
            return false;
        }else {
            return true;
        }
    }
    public boolean checkChildUsername(String str_Cusername){
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
            }else {
                JOptionPane.showMessageDialog(null,"Child Username does not exist","Username error",2);
            }
        }catch (SQLException ex){
            Logger.getLogger(UploadGrades.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cusername_exists;

    }
    private boolean checkTUsername(String str_username){
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
            }else{
                JOptionPane.showMessageDialog(null, "Teacher username not found", "Username Error", 2);

            }
        }catch (SQLException ex){
            Logger.getLogger(UploadGrades.class.getName()).log(Level.SEVERE,null,ex);
        }
        return username_exists;

    }
    private void btnUploadGradesActionPerformed(ActionEvent evt){
        String str_username=childUsername.getText();
        String str_math=txtMath.getText();
        int int_math=Integer.parseInt(str_math);
        String str_eng=txtEng.getText();
        int int_eng=Integer.parseInt(str_eng);
        String str_swa=txtSwa.getText();
        int int_swa=Integer.parseInt(str_swa);
        String str_teachusername=teacheruserName.getText();
        if (verifyFields()){
            if(checkChildUsername(str_username)&&checkTUsername(str_teachusername)){
                PreparedStatement ps,ps2;
                ResultSet rs;
                String reg_ChildQuery="INSERT INTO `tbl_gradedetails`(`childUsername`, `mathematics`, `english`, `kiswahili`, `teacherGrading`) VALUES (?,?,?,?,?)";
                String reg_ChildQuery2="INSERT INTO `tbl_childdetails`(`teacherUsername`) VALUES (?) WHERE `childUsername`=?";

                try{
                    ps=Main.getConnection().prepareStatement(reg_ChildQuery);
                    ps.setString(1,str_username);
                    ps.setInt(2,int_math);
                    ps.setInt(3,int_eng);
                    ps.setInt(4,int_swa);
                    ps.setString(5,str_teachusername);

                    ps2=Main.getConnection().prepareStatement(reg_ChildQuery2);
                    ps2.setString(1,str_teachusername);
                    ps2.setString(2,str_username);

                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Grades have been updated");
                        this.dispose();
                        Teacher_dashboard TeachDash_Form = new Teacher_dashboard();
                        TeachDash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        TeachDash_Form.setVisible(true);
                        TeachDash_Form.setSize(500,500);
                        TeachDash_Form.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Upload was unsuccessful");
                    }
                }catch (SQLException ex){
                    Logger.getLogger(UploadGrades.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnCancel) {
            this.dispose();
            Teacher_dashboard TeachDash_Form = new Teacher_dashboard();
            TeachDash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            TeachDash_Form.setVisible(true);
            TeachDash_Form.setSize(500,500);
            TeachDash_Form.setLocationRelativeTo(null);
        }
    }
}