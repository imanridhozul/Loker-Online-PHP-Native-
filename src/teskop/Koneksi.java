package teskop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Koneksi {
    //public static String pathReport= System.getProperty("user.dir")+ "/src/Template/";
    public static Connection con;
    public static Connection koneksiDB(){
        if(con==null){
            try {
               
            con = DriverManager.getConnection("jdbc:mysql://localhost/koperasi", "root", null);
            System.out.println("Connection successfully created !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"gagal koneksi");
            }
        }
        return con;
    }
}
