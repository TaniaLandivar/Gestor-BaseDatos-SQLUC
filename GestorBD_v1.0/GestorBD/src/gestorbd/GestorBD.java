/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorbd;

import Logica.*;
import Presentacion.InterfazPrincipal;
import java.util.ArrayList;

/**
 *
 * @author iJona
 */
public class GestorBD {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Tabla> tablas ;
    public static InterfazPrincipal ventanaPrincipal;
    
    public static void main(String[] args) {
        tablas = new ArrayList<>();
        ventanaPrincipal = new InterfazPrincipal();
        ventanaPrincipal.setVisible(true);
    }
    public static boolean tablaNoExiste(Tabla tabla1) {
        if(tablas.size()>0){
            for (int i =0; i <tablas.size();i++){
                if (tabla1.getUrl().equals(tablas.get(i).getUrl()))
                    return false;
            }
            return true;
        }
        return true;
    }
    
}
