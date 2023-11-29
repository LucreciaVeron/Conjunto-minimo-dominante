package Inteligencia;

import java.util.HashSet;
import java.util.Set;

public class Grafo {
	private double [][] matrizDePesos;
	private boolean [][] matrizDeAdyacencia;
	private static int vertices;
	private static int cantAristas;

	
	public Grafo (int n){
		if(n<0) {
			throw new IllegalArgumentException("La cantidad de vertices no puede ser negativa");
		}
		vertices=n;
		matrizDePesos = new double [n][n];
		matrizDeAdyacencia = new boolean [n][n];
		cantAristas = 0;
		
		for(int i=0; i<vertices; i++) {
			for(int j=0; j<vertices; j++) {
				matrizDePesos [i][j]=0;
				matrizDeAdyacencia [i][j]=false;
				}
			}
		
	}
	
	public int tamaño() {
		return vertices;
	}
	
	//Agregar arista----------------------------------------------------------------
	public void agregarArista(int i, int j, double x) {	
		if(i!=j) {
			existeArista(i,j);
			matrizDeAdyacencia[i][j]=true;
			matrizDeAdyacencia[j][i]=true;	
			
			matrizDePesos[i][j]= x;
			matrizDePesos[j][i]= x;	
			
			cantAristas++;
		}
	}
	
	//Eliminar arista----------------------------------------------------------------
	
	public void eliminarArista(int i, int j) {
			existeArista(i,j);
			matrizDeAdyacencia[i][j]=false;
			matrizDeAdyacencia[j][i]=false;	
			
			matrizDePesos[i][j]= 0;
			matrizDePesos[j][i]= 0;
			
			cantAristas--;
	}	
	
	//Getters-----------------------------------------------------------------
	public double getPesoDeArista(int i, int j) { 
		existeArista(i,j);
		return matrizDePesos[i][j];
	}
	
	public int getVertices() {
		return vertices;
	}
	
	public int getCantidadAristas() {
		return cantAristas;
	}

	//--------------------------------------------------------------------------------
	public Set<Integer> vecinos (int i){
		verificarVertice(i);
		Set<Integer> ret = new HashSet<Integer>();
		for(int j=0; j<tamaño(); j++) {
			if(existeArista(i,j)) {
				ret.add(j);
				}
			}
		Set<Integer> copy = Set.copyOf(ret);
		return copy;
	}
	
	public int grado (int i)
	{ 
		return vecinos(i).size();
	}
	
	//------------------------------------------------------------------------------------------
	public boolean existeArista(int i, int j) {
		//codigo defencivo
		 verificarVertice(i);
		 verificarVertice(j);
		
		//codigo de negocio
		return matrizDeAdyacencia[i][j];
	}

	
	//-------------------------------------------------------------------------------------------
	
	private void verificarVertice(int i) {
		if(i<0) {
			throw new IllegalArgumentException("El vertice no puede ser negativo");
		}
		if(i>=tamaño()) {
			throw new IllegalArgumentException("El vertice tiene que ser mayor a 0 y menor a la longitud de la matriz de adyacencia");
		}
	}
	
}


