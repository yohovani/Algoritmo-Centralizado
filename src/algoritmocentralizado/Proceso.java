/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmocentralizado;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yohov
 */
public class Proceso extends Thread{
	private int Id;
	private boolean tipo;
	private ArrayList<Integer> recurso;
	private int acceso[];
	private ArrayList<Integer> colaPeticiones;
	private int procesos;

	public Proceso() {
	}

	
	public Proceso(int Id, boolean tipo, ArrayList<Integer> recurso, int[] acceso) {
		this.Id = Id;
		this.tipo = tipo;
		this.recurso = recurso;
		this.acceso = acceso;
	}

	public Proceso(int Id, boolean tipo, ArrayList<Integer> recurso, int[] acceso, ArrayList<Integer> colaPeticiones,int procesos) {
		this.Id = Id;
		this.tipo = tipo;
		this.recurso = recurso;
		this.acceso = acceso;
		this.colaPeticiones = colaPeticiones;
		this.procesos = procesos;
	}
	
	
	
	public void utilizarRecurso(){
		if(this.recurso.size() > 0){
			System.out.println("Se utiliza el recurso por: "+this.Id);
			System.out.println("Cola de Peticiones");
			for(int i=0;i<this.colaPeticiones.size();i++)
				System.out.print(" "+this.colaPeticiones.get(i));
			System.out.println(" ");
			this.colaPeticiones.remove(0);
			this.recurso.remove(0);
			this.acceso[0] = -1;
		}
	}
	
	public void coordinador(){
		while(this.recurso.size() > 0){
			System.out.println("P: "+(this.procesos-1)+" cp: "+this.colaPeticiones.size());
			if(this.acceso[0] == -1){
				if(this.colaPeticiones.size() > 0 && this.colaPeticiones.size() == (this.procesos-1))
					this.acceso[0] = this.colaPeticiones.get(0);
			}
		}
	}
	
	public void procesoComun(){
		while(this.recurso.size() > 0){
			System.out.println("Acceso: "+this.acceso[0]+" Tamanno: "+this.recurso.size());
			if(this.acceso[0] == this.Id){
				utilizarRecurso();
			}
			if(!this.colaPeticiones.contains(this.Id) || this.colaPeticiones.size() == 0){
				this.colaPeticiones.add(this.Id);
			}
		}
	}

	@Override
	public void run() {
		if(tipo){
			try {
				System.out.println("Se ejecuta Coordinador por: "+this.Id);
				Thread.sleep(1000);
				coordinador();
				
			} catch (InterruptedException ex) {
				Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
			}
		}else{
			try {
				System.out.println("Se ejecuta Proceso Comun por: "+this.Id);
				Thread.sleep(1000);
				procesoComun();
			} catch (InterruptedException ex) {
				Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	
}
