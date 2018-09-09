
package Interface;

import Conexion.Conexion;
import Conexion.SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JFramePrincipal extends javax.swing.JFrame {
    
    private Connection conexion;
    private Conexion miConexion;
    private DefaultTableModel modelo;
    public JFramePrincipal() {
        initComponents();
        
        miConexion = new Conexion();
        conexion = new Conexion().conectar();
        
        
        mostrarProductos();
    }
    
    private void mostrarProductos()
    {
        modelo = (DefaultTableModel)tablaProducto.getModel();
        try {
            SQL sql = new SQL(conexion);
            ResultSet resultado = sql.getProducto();
            
            while(resultado.next())
            {
                modelo.addRow(new Object[]{resultado.getString(1),
                resultado.getString(2),resultado.getString(3),resultado.getString(4),
                resultado.getString(5),resultado.getString(6)});
            }
            resultado.close();
        } catch (Exception e) {
            System.err.println("Error SQL de mostrarProductos");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAgregar = new javax.swing.JMenu();
        menuReporte = new javax.swing.JMenu();
        menuReporteDos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCION", "PRECIO DE COMPRA", "PRECIO DE VENTA", "CANTIDAD"
            }
        ));
        jScrollPane1.setViewportView(tablaProducto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );

        menuAgregar.setText("Agregar");
        jMenuBar1.add(menuAgregar);

        menuReporte.setText("Generar Reporte de Productos");
        menuReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuReporteMousePressed(evt);
            }
        });
        menuReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReporteActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuReporte);

        menuReporteDos.setText("Reporte de Productos Insuficientes");
        menuReporteDos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuReporteDosMousePressed(evt);
            }
        });
        jMenuBar1.add(menuReporteDos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuReporteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReporteMousePressed
        try {
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,conexion);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            
            jasperViewer.setVisible(true);
            jasperViewer.setTitle("Reporte de Productos");
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuReporteMousePressed

    private void menuReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReporteActionPerformed
      
    }//GEN-LAST:event_menuReporteActionPerformed

    private void menuReporteDosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReporteDosMousePressed
        try {
            //parametro
            Map parametro = new HashMap();
            parametro.put("cantidad",5);
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/ReporteBlanco.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametro,conexion);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            
            jasperViewer.setVisible(true);
            jasperViewer.setTitle("Reporte de Productos");
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuReporteDosMousePressed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAgregar;
    private javax.swing.JMenu menuReporte;
    private javax.swing.JMenu menuReporteDos;
    private javax.swing.JTable tablaProducto;
    // End of variables declaration//GEN-END:variables
}
