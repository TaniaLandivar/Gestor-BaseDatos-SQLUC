/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author iJona
 */
public class Registro {
    public  int numCampos;
    public  ArrayList<String> campos;

    public Registro(int numCampos) {
        this.numCampos = numCampos;
        this.campos = new ArrayList<>();
    }
    public void agregarCampo(String campo){
        campos.add(campo);
    }
    
    
    
    
}
