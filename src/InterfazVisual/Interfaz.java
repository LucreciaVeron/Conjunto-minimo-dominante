package InterfazVisual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Inteligencia.ConjuntoMinimoDominante;
import Inteligencia.Grafo;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;

public class Interfaz {

	private JFrame frmTrabajoPractico;
	private JTextField cantidadVertices;
	static Grafo grafo;
	static String listaDeVertices [];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frmTrabajoPractico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrabajoPractico = new JFrame();
		frmTrabajoPractico.setTitle("Trabajo practico 3");
		frmTrabajoPractico.setBounds(100, 100, 450, 300);
		frmTrabajoPractico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrabajoPractico.getContentPane().setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Trabajo practico 3");
		internalFrame.setClosable(true);
		internalFrame.setBounds(150, 106, 276, 147);
		frmTrabajoPractico.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		@SuppressWarnings("rawtypes")
		JComboBox verticeI = new JComboBox();
		verticeI.setBounds(25, 25, 85, 21);
		internalFrame.getContentPane().add(verticeI);
		
		@SuppressWarnings("rawtypes")
		JComboBox verticeJ = new JComboBox();
		verticeJ.setBounds(156, 25, 85, 21);
		internalFrame.getContentPane().add(verticeJ);
		
		JButton añadir = new JButton("a\u00F1adir");
		añadir.setBounds(89, 69, 85, 21);
		internalFrame.getContentPane().add(añadir);
		internalFrame.setVisible(false);
		
		JLabel pedirTamaño = new JLabel("Ingrese el tama\u00F1o del grafo:");
		pedirTamaño.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pedirTamaño.setBounds(132, 10, 203, 21);
		frmTrabajoPractico.getContentPane().add(pedirTamaño);
		
		cantidadVertices = new JTextField();
		cantidadVertices.setHorizontalAlignment(SwingConstants.CENTER);
		cantidadVertices.setBounds(156, 41, 119, 19);
		frmTrabajoPractico.getContentPane().add(cantidadVertices);
		cantidadVertices.setColumns(10);
		
		JButton siguiente = new JButton("Siguiente");
		siguiente.setBounds(156, 70, 119, 21);
		frmTrabajoPractico.getContentPane().add(siguiente);
		
		JButton generarConjunto = new JButton("Generar conjunto dominante minimo");
		generarConjunto.setEnabled(true);		//CAMBIAR TAL VEZ
		generarConjunto.setBounds(115, 160, 203, 21);
		frmTrabajoPractico.getContentPane().add(generarConjunto);
		
		JLabel darConjunto = new JLabel("");
		darConjunto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		darConjunto.setBounds(113, 129, 268, 21);
		frmTrabajoPractico.getContentPane().add(darConjunto);
		darConjunto.setVisible(false);
		
		
		
		siguiente.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
				int vertices = Integer.parseInt(cantidadVertices.getText());
				grafo = new Grafo (vertices);

				siguiente.setEnabled(false);
				cantidadVertices.setText("");
				cantidadVertices.setEnabled(false);
				internalFrame.setVisible(true);
				
				listaDeVertices= new String[vertices + 1];
			 	listaDeVertices[0] = "Seleccione vertice";
			 	
			 	for (int i =1; i<listaDeVertices.length; i++) {
			 		String numero = Integer.toString(i);
			 		listaDeVertices[i] = numero ;
			 	}
			 	
			 	verticeI.setModel(new DefaultComboBoxModel (listaDeVertices));
				verticeJ.setModel(new DefaultComboBoxModel (listaDeVertices));
				
			}
		});
		
		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aristaPeso = 1;
				
				//se le resta -1 ya que el vertice x se ecuentra en la posicion x-1 de la matriz del grafo
				int primerVertice = Integer.parseInt((String) verticeI.getSelectedItem()) - 1;
				int segundoVertice = Integer.parseInt((String) verticeJ.getSelectedItem()) - 1;
				
				grafo.agregarArista(primerVertice, segundoVertice, aristaPeso);
			}
		});
		generarConjunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BUSCAR CONJUNTO
				ConjuntoMinimoDominante.dominante(grafo);
				String conjunto = ConjuntoMinimoDominante.getConjunto();
				darConjunto.setText("El conjunto obtenido es: " + conjunto);
				darConjunto.setVisible(true);
			}
		});
		
	}
}
