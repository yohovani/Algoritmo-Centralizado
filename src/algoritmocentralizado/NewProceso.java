/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmocentralizado;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yohovani
 */
public class NewProceso extends Thread{
	private int Id;
	private ArrayList<Integer> cola;
	private int acceso[];
	private int n;
	private boolean libre[];
	private int contador;
	private int coordinador[];
	private boolean inC=false;
	ArrayList<Integer> recurso;

	public NewProceso(int Id, ArrayList<Integer> cola, int[] acceso, int n, boolean[] libre,int[] coord,ArrayList<Integer> recurso) {
		this.Id = Id;
		this.cola = cola;
		this.acceso = acceso;
		this.n = n;
		this.libre = libre;
		contador = 0;
		this.coordinador = coord;
		this.recurso = recurso;
	}
	
	public void coordinador(){
		while(true){
			System.out.println("Acceso: "+acceso[0]);
			if(cola.size() > 1  && acceso[0] == -1){
				try {
					acceso[0]=cola.get(0);
					libre[0] = false;
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					Logger.getLogger(NewProceso.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
	
	public void proceso(){
		while(true){
			enviarPeticion();
			
			if(obtenerAcceso()){
				utilizarRecurso();
			}
		}
	}

	private synchronized void enviarPeticion() {
		if(!cola.contains(Id) && cola.size() < n && !this.inC){
			cola.add(Id);
			this.inC = true;
			System.out.println("Se agrego a la Cola: "+Id);
			System.out.println("Cola: "+mostrarCola());	
		}
	}

	private void newCoordinador() {
		Random r = new Random();
		this.coordinador[0]=r.nextInt(n);
	}

	private boolean obtenerAcceso() {
		if(!libre[0]){	
	//		System.out.println("Acceso: "+acceso[0]+" Id: "+this.Id);			
			if(acceso[0] == this.Id){
				return true;
			}
		}
		return false;
	}
	
	public String mostrarCola(){
		String aux = "";
		for(int i=1;i<cola.size();i++){
			aux+=cola.get(i)+" | ";
		}
		return aux;
	}
	
	public synchronized void utilizarRecurso(){
		try {
			System.out.println("Acceso a: "+acceso[0]);
			recurso.add(Id);
			cola.remove(0);
			System.out.println("Cola de Peticiones: "+mostrarCola());
			acceso[0]=-1;
			libre[0] = true;
			this.inC = false;
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Logger.getLogger(NewProceso.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		if(coordinador[0] == Id){
			System.out.println("Coordinador: "+Id);
			coordinador();
			
		}else{
			System.out.println("Proceso: "+Id);
			proceso();
		}
	}

	public String mostrarRecurso(){
		String aux="";
		for(int i=0;i<recurso.size();i++){
			aux+=recurso.get(i)+"\n";
		}
		return aux;
	}
	


	public void setId(int Id) {
		this.Id = Id;
	}

	public ArrayList<Integer> getCola() {
		return cola;
	}

	public void setCola(ArrayList<Integer> cola) {
		this.cola = cola;
	}

	public String getAcceso() {
		return ""+acceso[0];
	}

	public void setAcceso(int[] acceso) {
		this.acceso = acceso;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean[] getLibre() {
		return libre;
	}

	public void setLibre(boolean[] libre) {
		this.libre = libre;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public int[] getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(int[] coordinador) {
		this.coordinador = coordinador;
	}
	
	
	
}
