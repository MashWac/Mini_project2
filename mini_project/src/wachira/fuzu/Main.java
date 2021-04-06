package wachira.fuzu;

import com.mysql.cj.jdbc.MysqlDataSource;

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

public class Main {
    static final String DRIVER="com.mysql.Driver";
    static final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/db_macharia_wachira_137492";
    static final String USER= "Macharia";
    static final String PASSWORD="i*HCBmyZdL]2nk5";
    public static Connection getConnection(){
        Connection cnx= null;
        Statement statement =null;
        ResultSet resultSet= null;
        try{
            cnx= DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
    public static void main(String args[]) {
        Homepage hFrame = new Homepage();
        hFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hFrame.setSize(300, 400);
        hFrame.setVisible(true);
    }

}
