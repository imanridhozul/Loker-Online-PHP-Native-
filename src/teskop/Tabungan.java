package teskop;


import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.swing.JRViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class Tabungan extends javax.swing.JFrame {

    /**
     * Creates new form Tabungan
     */
    static String idpinjam,idAnggota,jmlhPinjam;
    public Tabungan() {
        initComponents();
        getData();
    }
    
    int jumlahtabungan=0,jumlahpinjaman = 0;
    String id,nama,alamat,hp,jk,jumlah,jp;
    public void getTabel(){
        try {
            int row=tabel.getSelectedRow();
            String tabel_kiri=(tabel.getModel().getValueAt(row, 0).toString());
            String hpp=(tabel.getModel().getValueAt(row, 3).toString());
            java.sql.Connection conn= (java.sql.Connection)Koneksi.koneksiDB();
            java.sql.Statement stm= conn.createStatement();
            java.sql.ResultSet sql= stm.executeQuery("select * from anggota where nama='"+tabel_kiri+"' and hp = '"+hpp+"'" );
            if (sql.next()){
                idAnggota = id = sql.getString("id");
                nama=sql.getString("nama");
                alamat=sql.getString("alamat");
                hp=sql.getString("jk");
                jk=sql.getString("hp");
            }
            
        }catch(Exception e){
        
        }
    }
    public void getidP(){
        try {
            int row=tabelp.getSelectedRow();
           // String tabel_kiri=(tabel.getModel().getValueAt(row, 0).toString());
            String tgl=(tabelp.getModel().getValueAt(row, 2).toString());
            java.sql.Connection conn= (java.sql.Connection)Koneksi.koneksiDB();
            java.sql.Statement stm= conn.createStatement();
            java.sql.ResultSet sql= stm.executeQuery("select * from pinjam where ida='"+idAnggota+"' and tanggalp = '"+tgl+"'" );
            System.out.println("tggl "+tgl);
            if (sql.next()){
                idpinjam = sql.getString("id"); 
                jmlhPinjam = sql.getString("jumlahp"); 
            }
            
        }catch(Exception e){
        
        }
    }
    public void getData(){
        DefaultTableModel tabel1= new DefaultTableModel();
        tabel1.addColumn("Nama");
        tabel1.addColumn("Alamat");
        tabel1.addColumn("Jenis Kelamin");
        tabel1.addColumn(" No Hp");
        try {
           
           Connection conn = Koneksi.koneksiDB();
           Statement stm = conn.createStatement();
           ResultSet sql = stm.executeQuery("select * from anggota");
           while(sql.next()){
                tabel1.addRow(new Object[]{
                    sql.getString(2),
                    sql.getString(3),
                    sql.getString(4),
                    sql.getString(5),
                });
            }
            tabel.setModel(tabel1);
        }catch (SQLException e) {
        }
    }
    boolean status;
    Connection conn = Koneksi.koneksiDB();
    String x;
    public void getTabungan()
    {
        
        
                   DefaultTableModel tabel2= new DefaultTableModel();
                    tabel2.addColumn("Nama");
                    tabel2.addColumn("Jumlah (Rp)");
                    tabel2.addColumn("Tanggal");

                    try {
                       
                       Statement stm = conn.createStatement();
                       ResultSet sql = stm.executeQuery("select nama,jumlah,tanggal from tabungan, anggota where anggota.id=ida and ida = '"+id+"'");
                       while(sql.next()){
                            tabel2.addRow(new Object[]{
                                sql.getString(1),
                                sql.getString(2),
                                sql.getString(3)

                            });
                        }
                        t.setModel(tabel2);
                        Statement stt = conn.createStatement();
                        ResultSet rss = stt.executeQuery("select count(*) as j from tabungan,anggota where ida = anggota.id and ida ='"+id+"'");
                        while (rss.next())
                        {
                            x = rss.getString("j");
                        }
                        System.out.print(x);
                        if(x.equals("0"))
                        {
                            System.out.print("Tidak ada Tabungan");
                            jumlah = "0";
                            to.setText(jumlah);
                            status = true;
                        }
                        else
                        {
                            Statement st = conn.createStatement();
                            ResultSet rs = st.executeQuery("select sum(jumlah) as j from tabungan where ida ='"+id+"'");
                            while (rs.next()){
                            status = false;        
                            jumlah = rs.getString("j").toString();
                            jumlahtabungan = rs.getInt("j");
                            }
                            to.setText(jumlah);
                        }
                        System.out.println(jumlah);
                       /* 
                        //pilih jumlah data
                        */

                    }catch (SQLException e) {
                    }
    }
    public void insertTabungan()
    {
         try 
        {   
            String jm,n;
            jm = nominals.getText();
            n = tanggals.getText();
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            if("YYYY-MM-DD".equals(n))
            {
                
                Date ys=new Date(); // membuat oject ys dari class Date 
                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd"); //membuat object
                String query = "insert into tabungan values (NULL,'"+id+"','"+jm+"','"+s.format(ys) +"')";
                System.out.print("Kosong "+query+" "+s.format(ys));               
                st.execute(query);
            }
            else
            {
                String query = "insert into tabungan values (NULL,'"+id+"','"+jm+"','"+n+"')";
                System.out.print(query);          
                st.execute(query);
            }
            
         //   Statement st = con.createStatement();
            
            
                JOptionPane.showMessageDialog(null, "Data Update");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void insertPinjaman()
    {
         try 
        {   
            String jm,n;
            jm = nominals.getText();
            n = tanggals.getText();
            Connection con = Koneksi.koneksiDB();
            Statement st = con.createStatement();
            if("YYYY-MM-DD".equals(n))
            {
                
                Date ys=new Date(); // membuat oject ys dari class Date 
                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd"); //membuat object
                String query = "insert into pinjam values (NULL,'"+id+"','"+jm+"','"+s.format(ys) +"')";
                System.out.print("Kosong "+query+" "+s.format(ys));               
                st.execute(query);
            }
            else
            {
                String query = "insert into pinjam values (NULL,'"+id+"','"+jm+"','"+n+"')";
                System.out.print(query);               
                st.execute(query);
            }
                JOptionPane.showMessageDialog(null, "Data Update");
                       
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
  
    public void getPinjaman()
    {
        
                   String jpinjam;
                   DefaultTableModel tabel3= new DefaultTableModel();
                    tabel3.addColumn("Nama");
                    tabel3.addColumn("Jumlah (Rp)");
                    tabel3.addColumn("Tanggal");

                    try {
                       
                       Statement stm = conn.createStatement();
                       ResultSet sql = stm.executeQuery("select nama,jumlahp,tanggalp from pinjam, anggota where ida = anggota.id and ida = '"+id+"'");
                       while(sql.next()){
                            tabel3.addRow(new Object[]{
                                sql.getString(1),
                                sql.getString(2),
                                sql.getString(3)

                            });
                        }
                        tabelp.setModel(tabel3);
                        Statement stt = conn.createStatement();
                        ResultSet rss = stt.executeQuery("select count(*) as j from pinjam,anggota where ida = anggota.id and ida ='"+id+"'");
                        while (rss.next())
                        {
                            x = rss.getString("j");
                        }
                        System.out.print(x);
                        if(x.equals("0"))
                        {
                            System.out.print("Tidak ada Tabungan");
                            jp = "0";
                            tp.setText(jp);
                        }
                        else
                        {
                            Statement st = conn.createStatement();
                            ResultSet rst = st.executeQuery("select sum(jumlahp) as j from pinjam where ida ='"+id+"'");
                            while (rst.next()){
                                jp = rst.getString("j").toString();
                                System.out.println("p :" + jp);
                                jumlahpinjaman = rst.getInt("j");
                            }
                            tp.setText(jp);
                            /*int zs = Integer.parseInt(jp);
                            System.out.println("124sqwqeqwrq" + zs);
                            zs = zs - 10;
                            System.out.println("qwqeqwrq" + zs);*/
                        }
                       /* System.out.println(jp);
                            Statement stb = conn.createStatement();
                            ResultSet rstt = stb.executeQuery("select sum(jumlahp) as jp,sum(jumlah) as jt from pinjam,anggota,tabungan where ida = id and id ='"+id+"'");
                            while (rstt.next()){

                            jp = rstt.getString("j").toString();
                            }
                        
                        //pilih jumlah data
                        */
                        
                        //System.out.print("kurangan" + (jumlah - jp));
                       
                        
                    }catch (SQLException e) {
                 }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        t = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        to = new javax.swing.JLabel();
        rp = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelp = new javax.swing.JTable();
        namas = new javax.swing.JTextField();
        nominals = new javax.swing.JTextField();
        tanggals = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cr = new javax.swing.JComboBox();
        cari = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        key = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        PrinPin = new javax.swing.JButton();
        LapPin = new javax.swing.JButton();
        printTab = new javax.swing.JButton();
        LapTab = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tp = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        t.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        jLabel1.setText("Total Tabungan     ");

        to.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        to.setText("0");

        rp.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        rp.setText("Rp.");

        tabelp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelp);

        namas.setText("Nama");

        nominals.setText("Nominal");
        nominals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nominalsActionPerformed(evt);
            }
        });

        tanggals.setText("YYYY-MM-DD");
        tanggals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalsActionPerformed(evt);
            }
        });

        jButton1.setText("Tabung");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pinjam");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nama", "Alamat", "No Hp" }));
        cr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                crMouseReleased(evt);
            }
        });

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/cari.png"))); // NOI18N
        cari.setContentAreaFilled(false);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/clear.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        key.setText("Pencarian");
        key.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keyKeyReleased(evt);
            }
        });

        PrinPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/print data pinjaman.png"))); // NOI18N
        PrinPin.setContentAreaFilled(false);
        PrinPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrinPinActionPerformed(evt);
            }
        });

        LapPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/data pinjaman pdf.png"))); // NOI18N
        LapPin.setContentAreaFilled(false);
        LapPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPinActionPerformed(evt);
            }
        });

        printTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/print data tabungan.png"))); // NOI18N
        printTab.setContentAreaFilled(false);
        printTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTabActionPerformed(evt);
            }
        });

        LapTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/data tabungan pdf.png"))); // NOI18N
        LapTab.setContentAreaFilled(false);
        LapTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapTabActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/Main Menu.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/New folder/Lihat Laporan.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LapPin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(printTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(LapTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(PrinPin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LapTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LapPin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrinPin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(58, 58, 58))
        );

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        jLabel5.setText("Total Pinjaman ");

        tp.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        tp.setText("0");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
        jLabel4.setText("Rp.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teskop/Button/text/MANAJEMEN-KOPERASI(1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(649, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cr, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(namas, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nominals, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tanggals, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(2, 2, 2)
                            .addComponent(rp)
                            .addGap(23, 23, 23)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cr, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(nominals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tanggals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(namas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(rp)
                                    .addComponent(to)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_tMouseClicked

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        getTabel();
        namas.setText(nama);
        getTabungan();
        getPinjaman();       
    
    }//GEN-LAST:event_tabelMouseClicked

    private void nominalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nominalsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominalsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            // TODO add your handling code here:
        insertTabungan();
        Tabungan te = new Tabungan();
        te.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tanggalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        insertPinjaman();
        Tabungan te = new Tabungan();
        te.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
Menu x = new Menu();
x.setVisible(true);
this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

private void LapTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapTabActionPerformed
        try {
            Date ys=new Date(); // membuat oject ys dari class Date 
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd"); //membuat object
            String sql = "SELECT nama,jumlah,tanggal FROM `tabungan`,anggota where anggota.id = tabungan.ida and ida ='"+id+"'";
            Connection conn = (Connection) Koneksi.koneksiDB();
            Statement stm = (Statement) conn.createStatement();
           ResultSet rs = stm.executeQuery(sql);
           //Map<String, Object> parameters = new HashMap<>(); 
           JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);            
           JasperReport jasperReport = JasperCompileManager.compileReport("./Laporan/tabung.jrxml");          
           JasperPrint print = JasperFillManager.fillReport(jasperReport, null, jrRS); 
           JRExporter exporter = new JRPdfExporter();
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("./lap/Tabungan/Laporan Tabungan "+nama+"-"+s.format(ys)+".pdf")); // your output goes here
           exporter.exportReport();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        }
 
}//GEN-LAST:event_LapTabActionPerformed

private void printTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTabActionPerformed
  try {
             
           String sql = "SELECT nama,jumlah,tanggal FROM `tabungan`,anggota where anggota.id = tabungan.ida and ida ='"+id+"'";
           Connection conn = (Connection) Koneksi.koneksiDB();
           Statement stm = (Statement) conn.createStatement();
           
                    ResultSet rs = stm.executeQuery(sql);
                    JasperPrint jasperPrint;       
                    JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);            
                    JasperReport jasperReport = JasperCompileManager.compileReport("./Laporan/tabung.jrxml");          
                    jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrRS);
                    JRViewer aViewer = new JRViewer(jasperPrint);                  
                    JDialog viewer = new JDialog(); 	
                    viewer.setTitle(".: Jasper Report :.");             
                    viewer.setAlwaysOnTop(true);
                    viewer.getContentPane().add(aViewer);                  
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
                    viewer.setBounds(0,0,screenSize.width, screenSize.height);
                    viewer.setVisible(true);              

                    stm.close();
                
        } catch (JRException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
}//GEN-LAST:event_printTabActionPerformed

private void LapPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPinActionPerformed
        try {
            Date ys=new Date(); // membuat oject ys dari class Date 
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd"); //membuat object
            String sql = "SELECT nama,jumlahp,tanggalp FROM `pinjam`,anggota where anggota.id = pinjam.ida and ida ='"+id+"'";
            Connection conn = (Connection) Koneksi.koneksiDB();
            Statement stm = (Statement) conn.createStatement();
           ResultSet rs = stm.executeQuery(sql);
           Map<String, Object> parameters = new HashMap<String, Object>(); 
           JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);            
           JasperReport jasperReport = JasperCompileManager.compileReport("./Laporan/Peminjaman.jrxml");          
           JasperPrint print = JasperFillManager.fillReport(jasperReport, null, jrRS); 
           JRExporter exporter = new JRPdfExporter();
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("./lap/Pinjaman/Laporan Peminjaman "+nama+"-"+s.format(ys)+".pdf")); // your output goes here
           exporter.exportReport();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}//GEN-LAST:event_LapPinActionPerformed

private void PrinPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrinPinActionPerformed
try {
             
           String sql = "SELECT nama,jumlahp,tanggalp FROM `pinjam`,anggota where anggota.id = pinjam.ida and ida ='"+id+"'";
            Connection conn = (Connection) Koneksi.koneksiDB();
           Statement stm = (Statement) conn.createStatement();
             
                    ResultSet rs = stm.executeQuery(sql);
                    JasperPrint jasperPrint;       
                    JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);            
                    JasperReport jasperReport = JasperCompileManager.compileReport("./Laporan/Peminjaman.jrxml");          
                    jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrRS);
                    JRViewer aViewer = new JRViewer(jasperPrint);                  
                    JDialog viewer = new JDialog(); 	
                    viewer.setTitle(".: Jasper Report :.");             
                    viewer.setAlwaysOnTop(true);
                    viewer.getContentPane().add(aViewer);                  
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
                    viewer.setBounds(0,0,screenSize.width, screenSize.height);
                    viewer.setVisible(true);              

                    stm.close();
              
        } catch (JRException ex) {
            Logger.getLogger(Tabungan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
}//GEN-LAST:event_PrinPinActionPerformed

private void tabelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpMouseClicked
    getidP();
   System.out.print(idAnggota +"xxxxxxxxxxxxxxxxxxx"+ jmlhPinjam);
    Bunga bung = new Bunga();
    bung.setVisible(true);
    this.dispose();
}//GEN-LAST:event_tabelpMouseClicked

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    try{
        Desktop.getDesktop().open(new File("lap"));
    }
    catch(IOException ex)
    {
        JOptionPane.showMessageDialog(rootPane, "Not Found");
    }
}//GEN-LAST:event_jButton4ActionPerformed
String kataKunci;int dasarCari;
private void crMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crMouseClicked
    dasarCari = cr.getSelectedIndex();
    cari.setEnabled(true);
    System.out.print(dasarCari);
}//GEN-LAST:event_crMouseClicked

private void crMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crMouseReleased
    dasarCari = cr.getSelectedIndex();
    cari.setEnabled(true);System.out.print(dasarCari);
}//GEN-LAST:event_crMouseReleased

private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        try {
            kataKunci = key.getText();
            DefaultTableModel tabel1= new DefaultTableModel();
                tabel1.addColumn("Nama");
                tabel1.addColumn("Alamat");
                tabel1.addColumn("Jenis Kelamin");
                tabel1.addColumn("No Hp");           
                   Connection conn = Koneksi.koneksiDB();
                   Statement stm = conn.createStatement();
                   ResultSet sql;
                   if(dasarCari==0){System.out.print("cari nama + "+kataKunci);
                    sql = stm.executeQuery("select * from anggota where nama like '%"+kataKunci+"%';");}
                   else if(dasarCari==1){
                    sql = stm.executeQuery("select * from anggota where alamat like '%"+kataKunci+"%';");}
                   else{
                    sql = stm.executeQuery("select * from anggota where hp like '%"+kataKunci+"%';");    }
                   while(sql.next()){
                        tabel1.addRow(new Object[]{
                            sql.getString(2),
                            sql.getString(3),
                            sql.getString(4),
                            sql.getString(5)
                        });
                    }
                    tabel.setModel(tabel1);
        } catch (SQLException ex) {
            Logger.getLogger(Pengurus.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_cariActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    getData();
}//GEN-LAST:event_jButton5ActionPerformed

private void keyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyKeyReleased
     dasarCari = cr.getSelectedIndex();
}//GEN-LAST:event_keyKeyReleased

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
            java.util.logging.Logger.getLogger(Tabungan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tabungan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tabungan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tabungan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tabungan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LapPin;
    private javax.swing.JButton LapTab;
    private javax.swing.JButton PrinPin;
    private javax.swing.JButton cari;
    private javax.swing.JComboBox cr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField key;
    private javax.swing.JTextField namas;
    private javax.swing.JTextField nominals;
    private javax.swing.JButton printTab;
    private javax.swing.JLabel rp;
    private javax.swing.JTable t;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabelp;
    private javax.swing.JTextField tanggals;
    private javax.swing.JLabel to;
    private javax.swing.JLabel tp;
    // End of variables declaration//GEN-END:variables
}
