package teskop;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Bunga.java
 *
 * Created on Oct 24, 2016, 7:21:27 AM
 */
/**
 *
 * @author Windows 8.1 Pro
 */
public class Bunga extends javax.swing.JFrame {
    private String id;
    static double tmp = 0; 
    public Bunga() {
        initComponents();
        tmp=0;
        getData();
    }
    public void getData(){
        DefaultTableModel tabel1= new DefaultTableModel();
        tabel1.addColumn("jumlah Bunga");
        tabel1.addColumn("Tanggal Bayar");
        try {
           
            java.sql.Connection conn = Koneksi.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            System.out.println("id pinjam "+Tabungan.idpinjam);
           ResultSet sql = stm.executeQuery("select * from bunga where idpinjam ='"+Tabungan.idpinjam+"'");
           while(sql.next()){
                tabel1.addRow(new Object[]{
                    sql.getString(3),
                    sql.getString(4),
                });
            }
            tabel.setModel(tabel1);
        }catch (SQLException e) {
        }
        tmp = Double.valueOf(Tabungan.jmlhPinjam);
        double bungaPinjam = tmp*0.01;
        bung.setText(String.valueOf(bungaPinjam));
        sisa.setText(Tabungan.jmlhPinjam);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bung = new javax.swing.JLabel();
        nominals = new javax.swing.JTextField();
        tanggals = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sisa = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        jLabel1.setText("Bunga Untuk pinjaman ini : Rp.");

        bung.setText("0");

        tanggals.setText("YYYY-MM-DD");

        jLabel2.setText("Jumlah");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Sisa Pinjaman : Rp. ");

        sisa.setText("0");

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bung))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tanggals, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nominals, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sisa)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nominals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(212, 212, 212))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try 
        {   
            String jm,n;
            jm = nominals.getText();
            n = tanggals.getText();
            double xd = Double.valueOf(jm) - (tmp*0.01);
            double sisaSetor = tmp-xd;
            System.out.println("tmp = "+tmp+" - "+xd+" = "+sisaSetor);
            java.sql.Connection con = Koneksi.koneksiDB();
            java.sql.Statement st = con.createStatement();
            
            if("YYYY-MM-DD".equals(n))
            {
                
                Date ys=new Date(); // membuat oject ys dari class Date 
                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd"); //membuat object
                String query = "insert into bunga values (NULL,'"+Tabungan.idpinjam+"','"+jm+"','"+s.format(ys) +"')";
                System.out.print("Kosong "+query+" "+s.format(ys));               
                st.execute(query);
            }
            else
            {
                String query = "insert into pinjam values (NULL,'"+Tabungan.idpinjam+"','"+jm+"','"+n+"')";
                System.out.print(query);               
                st.execute(query);
            }
            String query = "update pinjam set jumlahp ='"+sisaSetor+"' where id = '"+Tabungan.idpinjam+"'";
            System.out.print(query);               
            st.execute(query);
                JOptionPane.showMessageDialog(null, "Data Update");
            Tabungan x = new Tabungan();
            x.setVisible(true);
            this.dispose();
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
             Tabungan x = new Tabungan();
            x.setVisible(true);
            this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bunga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bunga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bunga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bunga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Bunga().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bung;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nominals;
    private javax.swing.JLabel sisa;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tanggals;
    // End of variables declaration//GEN-END:variables
}
