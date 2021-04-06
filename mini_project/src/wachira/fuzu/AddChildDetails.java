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

public class AddChildDetails extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JTextField childUsername, childFName, childLName, childClass,parentUsername;
    private JButton btnUpload,btnCancel;
    private JLabel lblchildFName,headerLabel,lblchildLName, lblchildClass,lblchildUsername,lblparentUsername;
    private Container container;
    private JPanel headerPanel, childFNamePanel,childLNamePanel, childClassPanel, btnPanel,childUsernamePanel,parentUsernamePanel;
    private ImageIcon logoImage;


    public AddChildDetails(){
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

        childFNamePanel=new JPanel();
        childFNamePanel.setPreferredSize(new Dimension(300,50));
        childFNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildFName= new JLabel("First Name: ");
        childFNamePanel.add(lblchildFName);
        childFName= new JTextField(30);
        childFNamePanel.add(childFName);
        container.add(childFNamePanel);

        childLNamePanel=new JPanel();
        childLNamePanel.setPreferredSize(new Dimension(300,50));
        childLNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildLName= new JLabel("Last Name: ");
        childLNamePanel.add(lblchildLName);
        childLName= new JTextField(30);
        childLNamePanel.add(childLName);
        container.add(childLNamePanel);

        childClassPanel=new JPanel();
        childClassPanel.setPreferredSize(new Dimension(300,50));
        childClassPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblchildClass= new JLabel("Class: ");
        childClassPanel.add(lblchildClass);
        childClass= new JTextField(30);
        childClassPanel.add(childClass);
        container.add(childClassPanel);

        parentUsernamePanel=new JPanel();
        parentUsernamePanel.setPreferredSize(new Dimension(300,50));
        parentUsernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblparentUsername= new JLabel("Parent Username: ");
        parentUsernamePanel.add(lblparentUsername);
        parentUsername= new JTextField(30);
        parentUsernamePanel.add(parentUsername);
        container.add(parentUsernamePanel);

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
        String str_fName=childFName.getText();
        String str_lName=childLName.getText();
        String str_childClass=childClass.getText();
        String str_parentusername=parentUsername.getText();


        if(str_username.trim().equals("")||str_fName.trim().equals("") ||str_lName.trim().equals("")||str_childClass.trim().equals("")||str_parentusername.trim().equals("")){
            JOptionPane.showMessageDialog(null, "One Or More Of The Fields Are Empty", "Empty Fields", 2);
            return false;
        }else {
            return true;
        }
    }
    public boolean checkChildUsername(String str_username){
        PreparedStatement statement;
        ResultSet rs;
        boolean username_exists= false;
        String query="SELECT * FROM `tbl_childdetails` WHERE `childUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_username);
            rs = statement.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,"Child Username Exist/Please try another","Username error",2);

            }else {
                username_exists = true;
            }
        }catch (SQLException ex){
            Logger.getLogger(AddChildDetails.class.getName()).log(Level.SEVERE,null,ex);
        }
        return username_exists;

    }
    private boolean checkParentUsername(String str_Pusername){
        PreparedStatement statement;
        ResultSet rs;
        boolean username_exists= false;
        String query="SELECT * FROM `tbl_parentdetails` WHERE `parentUsername`=?";

        try {
            statement = Main.getConnection().prepareStatement(query);
            statement.setString(1, str_Pusername);
            rs = statement.executeQuery();
            if (rs.next()) {
                username_exists = true;
            }else{
                JOptionPane.showMessageDialog(null, "Parent username not found", "Username Error", 2);

            }
        }catch (SQLException ex){
            Logger.getLogger(AddChildDetails.class.getName()).log(Level.SEVERE,null,ex);
        }
        return username_exists;

    }
    private void btnUploadGradesActionPerformed(ActionEvent evt){
        String str_username=childUsername.getText();
        String str_fName=childFName.getText();
        String str_lName=childLName.getText();
        String str_childClass=childClass.getText();
        Integer int_class= Integer.parseInt(str_childClass);
        String str_parentusername=parentUsername.getText();
        if (verifyFields()){
            if(checkChildUsername(str_username)&&checkParentUsername(str_parentusername)){
                PreparedStatement ps;
                ResultSet rs;
                String reg_ChildQuery="INSERT INTO `tbl_childdetails`(`childfName`, `childlName`, `childUsername`, `currentClass`, `parentUsername`) VALUES (?,?,?,?,?)";
                try{
                    ps=Main.getConnection().prepareStatement(reg_ChildQuery);
                    ps.setString(1,str_fName);
                    ps.setString(2,str_lName);
                    ps.setString(3,str_username);
                    ps.setInt(4,int_class);
                    ps.setString(5,str_parentusername);

                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Details have been updated");
                        this.dispose();
                        Parent_dashboard Parentdash_Form = new Parent_dashboard();
                        Parentdash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Parentdash_Form.setVisible(true);
                        Parentdash_Form.setSize(500,500);
                        Parentdash_Form.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Upload was unsuccessful");
                    }
                }catch (SQLException ex){
                    Logger.getLogger(AddChildDetails.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==btnCancel)
        this.dispose();
        Parent_dashboard form = new Parent_dashboard();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
        form.setSize(500,500);
        form.setLocationRelativeTo(null);
    }
}
