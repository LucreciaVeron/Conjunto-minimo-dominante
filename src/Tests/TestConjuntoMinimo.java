package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Inteligencia.Grafo;
import Inteligencia.ConjuntoMinimoDominante;

class TestConjuntoMinimo {

	Grafo grafo;
	
	void crearGrafo() {
		grafo = new Grafo (3);		
		grafo.agregarArista(0, 1, 1);		
		grafo.agregarArista(0, 2, 1);			
	}

	@Test
	void testGrafoNull() {
		try {
			ConjuntoMinimoDominante.dominante(grafo);
			fail("debia lanzar una excepcion");
		}
		
		catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testGrafoSinVertices() {
		grafo = new Grafo (3);
		ConjuntoMinimoDominante.dominante(grafo);
		assertEquals("{1, 2, 3}", ConjuntoMinimoDominante.getConjunto());
	}
	
	@Test
	void testResultados() {
		crearGrafo();
		ConjuntoMinimoDominante.dominante(grafo);
		assertEquals("{2, 3}", ConjuntoMinimoDominante.getConjunto());
		assertEquals("{1}", ConjuntoMinimoDominante.getConjunto());
		assertEquals("{3, 2}", ConjuntoMinimoDominante.getConjunto());
	}


	

}
