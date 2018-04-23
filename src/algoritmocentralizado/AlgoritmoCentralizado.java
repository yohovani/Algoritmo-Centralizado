/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmocentralizado;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yohov
 */
public class AlgoritmoCentralizado {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Random r = new Random();
		ArrayList<Integer> recurso = new ArrayList();
		ArrayList<Integer> CP = new ArrayList();
		for(int i=0;i<100;i++)
			recurso.add(r.nextInt());
		
		ArrayList<Proceso> procesos = new ArrayList();
		
		
		int x = r.nextInt(5);
		int acceso[] = {-1};
		for(int i=0;i<5;i++){
			if(x == i)
				procesos.add(new Proceso(i,true,recurso,acceso,CP,5));
			else
				procesos.add(new Proceso(i,false,recurso,acceso,CP,5));
		}
		
		for(int i=0;i<procesos.size();i++){
			procesos.get(i).start();
		}
		
		
	}
	
}
