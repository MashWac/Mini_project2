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

public class Login extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JTextField userName;
    private JPasswordField field_passWord;
    private JButton btnLogin,btnCancel, btnRegister;
    private JLabel lblenterDetails,headerLabel,lblUsername,lblPassword;
    private Container container;
    private JPanel headerPanel, enterdetailsPanel,UsernamePanel, passwordPanel, btnPanel,btnRegisterPanel;
    private ImageIcon logoImage;


    public Login(){
        super("FUZU PRIMARY");
        container=getContentPane();
        layout= new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage= new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel= new JLabel();
        headerLabel.setBounds(10,11,200,200);
        headerPanel=new JPanel();
        headerPanel.setPreferredSize(new Dimension(500,200));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(headerLabel);
        container.add(headerPanel);
        Image img=logoImage.getImage();
        Image newImage=img.getScaledInstance(headerLabel.getWidth(),headerLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalLogo= new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);

        enterdetailsPanel=new JPanel();
        enterdetailsPanel.setPreferredSize(new Dimension(300,20));
        enterdetailsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblenterDetails= new JLabel("Please Enter your details");
        lblenterDetails.setFont(new Font("Arial",Font.ITALIC,11));
        enterdetailsPanel.add(lblenterDetails);
        container.add(enterdetailsPanel);

        UsernamePanel=new JPanel();
        UsernamePanel.setPreferredSize(new Dimension(300,50));
        UsernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblUsername= new JLabel("Username: ");
        UsernamePanel.add(lblUsername);
        userName= new JTextField(30);
        UsernamePanel.add(userName);
        container.add(UsernamePanel);

        passwordPanel=new JPanel();
        passwordPanel.setPreferredSize(new Dimension(300,50));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblPassword= new JLabel("Password: ");
        passwordPanel.add(lblPassword);
        field_passWord= new JPasswordField(30);
        passwordPanel.add(field_passWord);
        container.add(passwordPanel);

        btnPanel=new JPanel();
        btnPanel.setPreferredSize(new Dimension(300,50));
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        btnCancel =new JButton("Cancel");
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        btnPanel.add(btnLogin);
        btnPanel.add(btnCancel);
        container.add(btnPanel);

        btnRegisterPanel=new JPanel();
        btnRegisterPanel.setPreferredSize(new Dimension(300,50));
        btnRegisterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this::btnRegisterActionPerformed);
        btnRegisterPanel.add(btnRegister);
        container.add(btnRegisterPanel);

    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnLogin) {
            PreparedStatement st;
            ResultSet rs;
            String str_username = userName.getText();
            String str_password = String.valueOf(field_passWord.getPassword());

            String query1 = "SELECT * FROM `tbl_teacherdetails` WHERE `teacherUsername`=? AND `teacherPassword`=?;";
            String query2 = "SELECT * FROM `tbl_parentdetails` WHERE `parentUsername`=? AND `parentPassword`=?";
            if (Homepage.getPortalSelected() == "parent") {
                try {
                    st = Main.getConnection().prepareStatement(query2);
                    st.setString(1, str_username);
                    st.setString(2, str_password);
                    rs = st.executeQuery();
                    if (rs.next()) {
                    Parent_dashboard form = new Parent_dashboard();
                    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    form.setVisible(true);
                    form.setSize(500,500);
                    form.setLocationRelativeTo(null);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username/Password", "Login error", 2);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    st = Main.getConnection().prepareStatement(query1);
                    st.setString(1, str_username);
                    st.setString(2, str_password);
                    rs = st.executeQuery();
                    if (rs.next()) {
                    Teacher_dashboard form = new Teacher_dashboard();
                    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    form.setVisible(true);
                    form.setSize(500,500);
                    form.setLocationRelativeTo(null);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username/Password", "Login error", 2);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    private void btnCancelActionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnCancel) {
            this.dispose();
            Homepage HForm = new Homepage();
            HForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            HForm.setVisible(true);
            HForm.setSize(300,400);
            HForm.setLocationRelativeTo(null);
        }
    }
    private void btnRegisterActionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnRegister) {
            if (Homepage.getPortalSelected() == "teacher") {
                this.dispose();
            Teacher_registration T_Reg_Form = new Teacher_registration();
            T_Reg_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            T_Reg_Form.setVisible(true);
            T_Reg_Form.setSize(500,800);
            T_Reg_Form.setLocationRelativeTo(null);
            } else {
                this.dispose();
            Parent_registration P_Reg_Form = new Parent_registration();
            P_Reg_Form.setVisible(true);
            P_Reg_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            P_Reg_Form.setSize(500,800);
            P_Reg_Form.setLocationRelativeTo(null);
            }
        }
    }
}
