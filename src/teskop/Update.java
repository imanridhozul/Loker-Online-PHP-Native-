package teskop;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class Update {
   
   /*public void up(String n,String al,String jk,String hp, String id) throws SQLException{
       Connection con = Koneksi.koneksiDB();
       Statement st = con.createStatement();
       String query = "update anggota set nama = '"+n+"', alamat = '"+al+"', jk = '"+jk+"', hp = '"+hp+"' where id = '"+id+"'";
       if(!st.execute(query)){
       JOptionPane.showMessageDialog(null, "Update Sukses "+query);
       }
    }*/
    public void up(String n,String al,String jk,String hp, String id) {
        try 
        {   
            String query = "update anggota set nama = '"+n+"', alamat = '"+al+"', jk = '"+jk+"', hp = '"+hp+"' where id = '"+id+"'";
            System.out.print(query);
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            st.execute(query);
         //   Statement st = con.createStatement();
            
            
                JOptionPane.showMessageDialog(null, "Data Update");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
    public void upPengurus(String n,String al,String jabatan,String hp, String id) {
        try 
        {   
            String query = "update pengurus set nama = '"+n+"', alamat = '"+al+"', jabatan = '"+jabatan+"', hp = '"+hp+"' where idp = '"+id+"'";
            System.out.print(query);
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            st.execute(query);
         //   Statement st = con.createStatement();
            
            
                JOptionPane.showMessageDialog(null, "Data Update");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
    ResultSet rs;
    public void tambahPengurus(String n,String al,String jabatan,String hp) {
        try 
        {   
            String query = "insert into pengurus values (NULL,'"+n+"','"+al+"','"+jabatan+"','"+hp+"')";
            System.out.print(query);
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            st.execute(query);
         //   Statement st = con.createStatement();
            
            
                JOptionPane.showMessageDialog(null, "Data Insert");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
    public void tambahAnggota(String n,String al,String jk,String hp) {
        try 
        {   
            String query = "insert into anggota values (NULL,'"+n+"','"+al+"','"+jk+"','"+hp+"')";
            System.out.print(query);
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            st.execute(query);
         //   Statement st = con.createStatement();
            
            
                JOptionPane.showMessageDialog(null, "Data Insert");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
     public void deleteAnggota(String id) {
        try 
        {   
            String query3 = "DELETE FROM bunga WHERE idpinjam in(select id from pinjam where ida='"+id+"')";
             String query = "DELETE FROM tabungan WHERE ida ='"+id+"'";
            String query2 = "DELETE FROM pinjam WHERE ida ='"+id+"'";
            String query1 = "DELETE FROM anggota WHERE id ='"+id+"'";           
            System.out.print(query +query2);
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            Statement st2= con.createStatement();
            Statement st3= con.createStatement();
            st1.execute(query);
            st3.execute(query3);
            st.execute(query2);
            st2.execute(query1);
        
                JOptionPane.showMessageDialog(null, "Data Delete");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
    public void deletePengurus(String id) {
        try 
        {   
            String query = "DELETE FROM pengurus WHERE idp ='"+id+"'";               
            System.out.print(query);
            Connection con = Koneksi.koneksiDB();
            Statement st1 = con.createStatement();
          
            st1.execute(query);
          
                JOptionPane.showMessageDialog(null, "Data Delete");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
       
    }
}
