/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmocentralizado;

import java.util.ArrayList;

/**
 *
 * @author Tx
 */
public class Administrador implements Runnable{
    private Recurso recurso;

    public Administrador(ArrayList<Integer> rec) {
        recurso=new Recurso(rec);
    }

    @Override
    public void run() {
       
    }
    
}
