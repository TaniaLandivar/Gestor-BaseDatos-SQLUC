/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.Tabla;

import gestorbd.GestorBD;
import static gestorbd.GestorBD.tablas;
import static gestorbd.GestorBD.ventanaPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author iJona
 */
public class VentanaCrear extends javax.swing.JFrame {

    /**
     * Creates new form VentanaCrear
     */
    public int numCampos = 0;
    public ArrayList<JTextField> entryCampos;
    public  ArrayList<JComboBox> combox;
    public VentanaCrear(String opcion) {
        
        initComponents();
        this.setLocationRelativeTo(null);
        entryCampos = new ArrayList<>();
        combox = new ArrayList<>();
        if (opcion.equals("Nuevo")){
            agregarCampo("Id");
            colocarEntryCampos();
        }
        
    }
    public void agregarCampo(String name){
        String items [] = {"String","Int","Double"};
        
        JTextField entryCampo = new JTextField(name);
        JComboBox comboCampo = new JComboBox(items);
         entryCampos.add(entryCampo);
         combox.add(comboCampo);
         numCampos++;
    }
    public void colocarEntryCampos(){
        //recorrer los campos y posicionarlos
        if(numCampos>1) botonRemove.setEnabled(true);
        else botonRemove.setEnabled(false);
        panelCampos.removeAll();
        int saltoCampos =30;
        for (int i = 0; i < numCampos;i++){
             panelCampos.add(entryCampos.get(i),new org.netbeans.lib.awtextra.AbsoluteConstraints(20, saltoCampos, 190, 35));
             panelCampos.add(combox.get(i),new org.netbeans.lib.awtextra.AbsoluteConstraints(230, saltoCampos, 75, 35));
             saltoCampos += 45;
                    
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

        entryNombreTabla = new javax.swing.JTextField();
        botonAddCampo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCampos = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        botonRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        entryNombreTabla.setText("jTextField1");
        entryNombreTabla.setBorder(null);
        entryNombreTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryNombreTablaActionPerformed(evt);
            }
        });
        getContentPane().add(entryNombreTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 170, 20));

        botonAddCampo.setText("+");
        botonAddCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddCampoActionPerformed(evt);
            }
        });
        getContentPane().add(botonAddCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 50, 40));

        panelCampos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField4.setText("Id");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        panelCampos.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelCampos.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jScrollPane1.setViewportView(panelCampos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 340, 210));

        botonCancelar.setText("CANCELAR");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(botonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        botonAceptar.setText("ACEPTAR");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(botonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 110, 30));

        botonRemove.setText("-");
        botonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(botonRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 50, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crear.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAddCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddCampoActionPerformed
        String texto = "Campo "+String.valueOf(numCampos);
         agregarCampo(texto);
         
        colocarEntryCampos();
    }//GEN-LAST:event_botonAddCampoActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        if (TablaNoExiste()){
            String campos[]= new String[numCampos];
            String linea = "";
            for (int i = 0; i < numCampos; i++){

                campos[i] =(entryCampos.get(i).getText());
                System.out.println("   "+campos[i]);
                linea=linea.concat(campos[i]);
                if(i!=numCampos-1)
                    linea = linea+",";
                
            }
            System.out.println("linea: "+linea);
            String ruta =entryNombreTabla.getText()+".csv";
            PrintWriter archivo;
            try {
                
                archivo = new PrintWriter(ruta);
                archivo.println(linea);
                archivo.close();
                /////////////
                
                File file =  new File(ruta);
                Tabla tabla = new Tabla(file);
                System.out.println("absPath: "+file.getAbsolutePath());
                 if (GestorBD.tablaNoExiste(tabla))
                    tablas.add(tabla);
                else
                    System.out.println("Muestro un mensaje de tabla ya existe");
                    //Muestro un mensaje de tabla ya existe
                System.out.println("entro antes de act,.. "+tablas.size());
                this.setVisible(false);
                ventanaPrincipal.actualizarInterfazPrincipal(tabla.getUrl());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaCrear.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(VentanaCrear.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
        }
        else System.out.println("msj la tabla existe...");
        
        
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void botonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRemoveActionPerformed
        
        numCampos--;
        combox.remove(numCampos);
         entryCampos.remove(numCampos);
        colocarEntryCampos();
        this.repaint();
    }//GEN-LAST:event_botonRemoveActionPerformed

    private void entryNombreTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryNombreTablaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entryNombreTablaActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCrear("Nuevo").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonAddCampo;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonRemove;
    public javax.swing.JTextField entryNombreTabla;
    public javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JPanel panelCampos;
    // End of variables declaration//GEN-END:variables

    private boolean TablaNoExiste() {
    
         return true;
    }
}