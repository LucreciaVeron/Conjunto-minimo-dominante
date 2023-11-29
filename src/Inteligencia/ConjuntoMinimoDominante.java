package Inteligencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ConjuntoMinimoDominante {
	private static int vertices;
	private static boolean [] verticesMarcados;
	private static Set<Integer> vecinos;
	private static int verticeAleatoreo;
	private static List<Integer> conjuntoMinimo;
	
	public static List <Integer> dominante(Grafo grafo) {
		///grafo nulo
		if (grafo == null) {
			throw new IllegalArgumentException("Se intento consultar un grafo que no existe");
		}
		
		vertices = grafo.getVertices();
		conjuntoMinimo = new ArrayList<Integer>();
		
		//grafo sin aristas, solo vertices aislados
		if(grafo.getCantidadAristas()==0) {
			for (int i = 0; i<vertices; i++) {
				conjuntoMinimo.add(i+1);
			}
			return conjuntoMinimo;
		}
		
		//busca el conjunto minimo desde un vertice aleatoreo
		Random random = new Random();
		verticeAleatoreo = random.nextInt(vertices);		//da entre 0 y vertices-1
		verticesMarcados = new boolean[vertices];
		vecinos = new HashSet <Integer>();	
		
		//si por el cual va a empezar a buscar:
		//empieza desde el ultimo vertice-----------------------------------
		if (verticeAleatoreo == vertices-1){
		  	for (int i = verticeAleatoreo ; i>=0; i--){
		  		buscarDominante(i, conjuntoMinimo, verticesMarcados, grafo);
		  	} 
		  	return conjuntoMinimo;
		} 
		 
		//empieza desde el primer vertice----------------------------------
		 if (verticeAleatoreo == 0){
		 	for(int i =0; i < vertices ; i++) {
		 		buscarDominante(i, conjuntoMinimo, verticesMarcados, grafo);
		 	}
		 	return conjuntoMinimo;
		 } 
		 
		 ///empieza desde un vertice entre el primero y ultimo--------------
		 for(int i =verticeAleatoreo; i < vertices ; i++) {
			 buscarDominante(i, conjuntoMinimo, verticesMarcados, grafo);
		 }
		 
		 for (int i = verticeAleatoreo-1; i>=0 ; i --){
		 		buscarDominante(i, conjuntoMinimo, verticesMarcados, grafo);

		 }
		 	return conjuntoMinimo;
	}
	
	
	public static void buscarDominante (int i, List<Integer> conjuntoMinimo, boolean verticesMarcados[], Grafo grafo ) {
		vecinos = new HashSet <Integer>();	
		
		if (!verticesMarcados[i]) {
			conjuntoMinimo.add(i+1);
			verticesMarcados [i] = true;
			
			vecinos = Set.copyOf(grafo.vecinos(i));
			for(Integer v: vecinos) { 
				if(!verticesMarcados[v]) {
					verticesMarcados[v] = true;
				}
			}		
		}
	}
	
	public static String getConjunto() {
		StringBuilder conjunto = new StringBuilder();
		conjunto.append("{");
		for(int i =0; i<conjuntoMinimo.size(); i++) {
			if(i == conjuntoMinimo.size()-1) {
				conjunto.append(conjuntoMinimo.get(i));
				conjunto.append("}");
			}
			else {
				conjunto.append(conjuntoMinimo.get(i));
				conjunto.append(", ");
			}
		}
		
		return conjunto.toString();
	}

}
