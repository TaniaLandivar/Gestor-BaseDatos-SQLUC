/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.Tabla;
import p.cedenaResponsabilidad.Aprobador;
import p.cedenaResponsabilidad.AprobadorInstruccion;
import com.csvreader.CsvReader;
import gestorbd.GestorBD;
import static gestorbd.GestorBD.tablas;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iJona
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form InterfazPrincipal
     */
    AcercaDe ventanaAcercaDe;
    public String textoDeSalida="";
    public InterfazPrincipal(){
        initComponents();
        this.setLocationRelativeTo(null);
        entryComando.select(0, 18);
         
        botonCerrar.setContentAreaFilled(false);
        botonExc.setContentAreaFilled(false);
        botonMinimizar.setContentAreaFilled(false);
        //botonSalida.setContentAreaFilled(false);
        
        textoSalida.setVisible(false);
        fondoSalida.setVisible(false);
        scrollSalida.setVisible(false);
        separador.setVisible(false);
        
        recuperarTablasDelMeta();
        actualizarInterfazPrincipal(null);
        
        //      Elimina el contenido del entry al hacer click
        entryComando.addFocusListener(
                new FocusListener() {
                        public void focusGained(FocusEvent e) {
                            if(entryComando.getText().equals("Ingrese el comando"))
                                entryComando.setText("");
                        }
                                //  cuando no haya contenido muestra msj
                        public void focusLost(FocusEvent e) {
                            // nothing
                            if(entryComando.getText().equals(""))
                                entryComando.setText("Ingrese el comando");
                        }
        });
        
        ListaTablas.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {

            // Double-click detected
            int index = list.locationToIndex(evt.getPoint());
            actualizarTabla(tablas.get(index));
            
        } else if (evt.getClickCount() == 3) {

            // Triple-click detected
            int index = list.locationToIndex(evt.getPoint());
        }
    }
});
        System.out.println("  >>> Fin de la Actualizacion....\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        botonExc = new javax.swing.JButton();
        botonSalida = new javax.swing.JToggleButton();
        entryComando = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaTablas = new javax.swing.JList<>();
        botonCerrar = new javax.swing.JButton();
        botonMinimizar = new javax.swing.JButton();
        nombreTabla = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        scrollSalida = new javax.swing.JScrollPane();
        textoSalida = new javax.swing.JTextPane();
        fondoSalida = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        botonAcercaDe = new javax.swing.JToggleButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonExc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonEjecutar.png"))); // NOI18N
        botonExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExcActionPerformed(evt);
            }
        });
        panelPrincipal.add(botonExc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 120, 40));

        botonSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonSalida.png"))); // NOI18N
        botonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalidaActionPerformed(evt);
            }
        });
        panelPrincipal.add(botonSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 150, 70, 25));

        entryComando.setBackground(new java.awt.Color(255, 255, 204));
        entryComando.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        entryComando.setText("Ingrese el comando");
        entryComando.setBorder(null);
        entryComando.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        entryComando.setSelectedTextColor(new java.awt.Color(204, 204, 255));
        entryComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryComandoActionPerformed(evt);
            }
        });
        panelPrincipal.add(entryComando, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 87, 410, -1));

        ListaTablas.setBackground(new java.awt.Color(102, 102, 102));
        ListaTablas.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        ListaTablas.setForeground(new java.awt.Color(255, 255, 255));
        ListaTablas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ListaTablas.setSelectionBackground(new java.awt.Color(0, 51, 51));
        ListaTablas.setSelectionForeground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportView(ListaTablas);

        panelPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 260));

        botonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonCerrar.png"))); // NOI18N
        botonCerrar.setToolTipText("Cerrar");
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });
        panelPrincipal.add(botonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 40, 30));

        botonMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonMinimizar.png"))); // NOI18N
        botonMinimizar.setToolTipText("Minimizar");
        botonMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMinimizarActionPerformed(evt);
            }
        });
        panelPrincipal.add(botonMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 40, 10));

        nombreTabla.setBackground(new java.awt.Color(0, 102, 204));
        nombreTabla.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        nombreTabla.setForeground(new java.awt.Color(0, 102, 102));
        nombreTabla.setText("NombreTabla");
        panelPrincipal.add(nombreTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 270, 20));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "nombre", "apellido"
            }
        ));
        scrollTable.setViewportView(table);

        panelPrincipal.add(scrollTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 222, 554, 250));

        textoSalida.setEditable(false);
        textoSalida.setBackground(new java.awt.Color(255, 204, 204));
        textoSalida.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        textoSalida.setForeground(new java.awt.Color(51, 51, 51));
        textoSalida.setFocusCycleRoot(false);
        scrollSalida.setViewportView(textoSalida);

        panelPrincipal.add(scrollSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 65, 280, 80));

        fondoSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoSalida.png"))); // NOI18N
        panelPrincipal.add(fondoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 295, 92));

        separador.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelPrincipal.add(separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 60, 10, 90));

        botonAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonAcercaDe.png"))); // NOI18N
        botonAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAcercaDeActionPerformed(evt);
            }
        });
        panelPrincipal.add(botonAcercaDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 40));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inicio.png"))); // NOI18N
        panelPrincipal.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExcActionPerformed
        // TODO add your handling code here:
        String comando = entryComando.getText();
        textoDeSalida =" Ejecucion:\n";
        AprobadorInstruccion aprobador =new Aprobador();
        try{
            aprobador.analizarInstruccion(comando);
           
        }catch(Exception ex){
            char e='►';
            textoDeSalida = textoDeSalida.concat(String.valueOf(e)+"  la instruccion no entro en la cadena\n");
       
        }
        if(!botonSalida.isSelected()){
           
            textoSalida.setVisible(true);
            fondoSalida.setVisible(true);

            scrollSalida.setVisible(true);
            separador.setVisible(true);        
            botonSalida.doClick();
        }
        textoSalida.setText(textoDeSalida);        
        
       
        
        
    }//GEN-LAST:event_botonExcActionPerformed

    private void entryComandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryComandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entryComandoActionPerformed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ventanaAcercaDe.setVisible(false);

    }//GEN-LAST:event_botonCerrarActionPerformed

    private void botonMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMinimizarActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_botonMinimizarActionPerformed

    private void botonSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalidaActionPerformed
        // TODO add your handling code here:
        
        textoSalida.setVisible(botonSalida.isSelected());
        scrollSalida.setVisible(botonSalida.isSelected());
        fondoSalida.setVisible(botonSalida.isSelected());
        separador.setVisible(botonSalida.isSelected());
        
    }//GEN-LAST:event_botonSalidaActionPerformed

    private void botonAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAcercaDeActionPerformed
        // TODO add your handling code here:
        if(botonAcercaDe.isSelected()){
            this.setLocation(382,150);
            ventanaAcercaDe = new AcercaDe();
            ventanaAcercaDe.setVisible(true);
            ventanaAcercaDe.setLocation(80, 150);
        
        }
        else{
            this.setLocationRelativeTo(null);
            ventanaAcercaDe.setVisible(false);
        }
    }//GEN-LAST:event_botonAcercaDeActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList<String> ListaTablas;
    private javax.swing.JToggleButton botonAcercaDe;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonExc;
    private javax.swing.JButton botonMinimizar;
    private javax.swing.JToggleButton botonSalida;
    public javax.swing.JTextField entryComando;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondoSalida;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel nombreTabla;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollSalida;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JSeparator separador;
    public javax.swing.JTable table;
    private javax.swing.JTextPane textoSalida;
    // End of variables declaration//GEN-END:variables
   
    public   void actualizarInterfazPrincipal(String rutaTabla) {
        //*  ACTUALIZO LA LISTA DE TABLAS JUNTO CON LA TABLA A MOSTRAR */ //
        
        if(tablas.size()>0){
        
            ListaTablas.removeAll();
            System.out.println("  >> Actualizando la Interfaz Principal.\n    NumeroTablas: "+tablas.size());
            String[] moldeListaTablas = new String[tablas.size()];
            for (int i =0; i <tablas.size();i++){
                moldeListaTablas[i]=tablas.get(i).getNombre();            
            }
            int posTablaAMostrar=0;
            int i =0;
            if(rutaTabla!=null){
                while(i<tablas.size()){
                    if(tablas.get(i).getNombre().equals(rutaTabla)){
                        posTablaAMostrar=tablas.indexOf(tablas.get(i));
                        break;
                    }
                    i++;
                }
            }
            else posTablaAMostrar=tablas.size()-1;

            ListaTablas.setListData(moldeListaTablas);   
            ListaTablas.setSelectedIndex(posTablaAMostrar);//   dejar seleccionado la ultima tabla. creada o abierta

            actualizarTabla(tablas.get(posTablaAMostrar));
            

            scrollTable.setVisible(true);
            nombreTabla.setVisible(true);
           
        }
        else{
            DefaultListModel modelo = new DefaultListModel(); 
            ListaTablas.setModel(modelo);
            scrollTable.setVisible(false);
            nombreTabla.setVisible(false);

        }
         this.repaint();
    }
    
    
    public void actualizarTabla(Logica.Tabla tabla) {
        table.removeAll();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(tabla.getCampos());
        CsvReader archivo;
        try{
            
            archivo = new CsvReader(tabla.getUrl());
            archivo.readRecord();
            //
            while(archivo.readRecord()){
                Object[] registro = new String [ tabla.getNumeroCampos()];
                for (int i = 0 ; i< tabla.getNumeroCampos();i++){
                    registro[i]= archivo.get(i);

                }
                modeloTabla.addRow(registro);
            }
            table.setSelectionBackground(Color.MAGENTA);
            table.setSelectionForeground(Color.DARK_GRAY);
            table.setModel(modeloTabla);
            table.setSelectionMode(1);
            archivo.close();
            
            nombreTabla.setText(tabla.getNombre());
        }
        catch(IOException e){
            System.out.println("error al actualizar la tabla... "+e);
        }
        
       
        
        
        
    }

    private void recuperarTablasDelMeta() {
        String url="METADATA.csv";
        File file =new File(url);
        if(file.exists()){
            CsvReader metaDB=null;
            try{
                metaDB = new CsvReader(url);
                metaDB.readRecord();
                
                while (metaDB.readRecord()){
                     if (metaDB.get(0).equals("1")) {   // si esta habilitado!
                         Tabla tabla = new Tabla(new File(metaDB.get(1)+".csv"));
                         tabla.setClave(metaDB.get(4)); //  agregaClave
                         tabla.setLongitud(Integer.parseInt(metaDB.get(5)));    //  agregaLongitudDeLosCampos
                         if (GestorBD.tablaNoExiste(tabla))
                            tablas.add(tabla);
                        else
                            System.out.println("La tabla ya existe  en memoria");

                     }  
                }
                 metaDB.close();
            }
            catch(IOException e){
                System.out.println("error leyendo metaDB "+e);
            }
        }
        else {
           try{
               PrintWriter meta = new PrintWriter(file);
               meta.println("bandera,nombre,nReg,campos,clave,longitud");
               meta.close();
               recuperarTablasDelMeta();
           }catch(IOException e){
               System.out.println("error en crear meta: "+e);
           }
            
        }
    }
    public void salidaPrintln(String texto){
        textoDeSalida = textoDeSalida.concat("-  "+texto+"\n");
            
        
    }
}