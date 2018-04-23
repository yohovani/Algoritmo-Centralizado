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
public class Recurso {
    private ArrayList<Integer> recurso;

    Recurso(ArrayList<Integer> recurso) {
        this.recurso = recurso;
    }

    public synchronized int getRecurso() {
        int temp=recurso.get(0);
        recurso.remove(0);
        return temp;
    }
    
    
    
}
