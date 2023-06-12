package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Alquileres implements IAlquileres {
	private File FICHERO_ALQUILERES;
	private DateTimeFormatter FORMATO_FECHA;
	private String RAIZ;
	private String ALQUILER;
	private String CLIENTE;
	private String VEHICULO;
	private String FECHA_ALQUILER;
	private String FECHA_DEVOLUCION;

	public static List<Alquiler> Coleccionalquileres;

	public Alquileres() {
		Coleccionalquileres = new ArrayList<>();
	}

	public void comenzar() {
		FICHERO_ALQUILERES = new File("alquileres.xml");
		leerDom();
	}

	private void leerDom() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(FICHERO_ALQUILERES);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(ALQUILER);
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String dni = eElement.getAttribute(CLIENTE);
					LocalDate fechaAlquiler = LocalDate.parse(eElement.getAttribute(FECHA_ALQUILER), FORMATO_FECHA);
					Vehiculo vehiculo = new Vehiculo(eElement.getAttribute(VEHICULO));
					Alquiler alquiler = new Alquiler(getCliente(dni), vehiculo, fechaAlquiler);
					if (eElement.hasAttribute(FECHA_DEVOLUCION)) {
						LocalDate fechaDevolucion = LocalDate.parse(eElement.getAttribute(FECHA_DEVOLUCION),
								FORMATO_FECHA);
						alquiler.devolver(fechaDevolucion);
					}
					Coleccionalquileres.add(alquiler);
				}
			}
		} catch (Exception e) {
			System.err.println("Error al leer el archivo XML de alquileres: " + e.getMessage());
		}
	}

	public void getAlquiler() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(FICHERO_ALQUILERES);

			NodeList listaAlquileres = doc.getElementsByTagName(ALQUILER);

			for (int i = 0; i < listaAlquileres.getLength(); i++) {
				Node nodo = listaAlquileres.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					String cliente = elemento.getAttribute(CLIENTE);
					LocalDate fechaAlquiler = LocalDate.parse(elemento.getAttribute(FECHA_ALQUILER), FORMATO_FECHA);
					LocalDate fechaDevolucion = LocalDate.parse(elemento.getAttribute(FECHA_DEVOLUCION), FORMATO_FECHA);
					String vehiculo = elemento.getAttribute(VEHICULO);

					Alquiler alquiler = new Alquiler(Vehiculos.buscar(vehiculo), Clientes.buscar(cliente),
							fechaAlquiler, fechaDevolucion);
					Coleccionalquileres.add(alquiler);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) { // NO ENCUENTRO OTRA SOLUCION AL
																				// SAXException
			System.out.println("No se pudo leer el archivo de alquileres.");
		}
	}

	public void terminar() {
		crearDom();
	}

	private void crearDom() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement(ALQUILER);
			doc.appendChild(root);

			for (Alquiler alquiler : Coleccionalquileres) {
				Element elementoAlquiler = doc.createElement(ALQUILER);
				elementoAlquiler.setAttribute(CLIENTE, alquiler.getCliente().getDni());
				elementoAlquiler.setAttribute(VEHICULO, alquiler.getVehiculo().getMatricula());
				elementoAlquiler.setAttribute(FECHA_ALQUILER, alquiler.getFechaAlquiler().format(FORMATO_FECHA));
				if (alquiler.getFechaDevolucion() != null) {
					elementoAlquiler.setAttribute(FECHA_DEVOLUCION,
							alquiler.getFechaDevolucion().format(FORMATO_FECHA));
				}
				root.appendChild(elementoAlquiler);
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(FICHERO_ALQUILERES.getAbsolutePath()));
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException e) {
			System.out.println("No se pudo crear el archivo de alquileres.");
		}
	}

	private void getElemento() {
		RAIZ = ALQUILER;
		ALQUILER = "alquiler";
		CLIENTE = "cliente";
		VEHICULO = "vehiculo";
		FECHA_ALQUILER = "fechaAlquiler";
		FECHA_DEVOLUCION = "fechaDevolucion";
		FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	public List<Alquiler> get(Vehiculos vehiculo) {
		ArrayList<Alquiler> arrayAlquileresCliente = new ArrayList<>();

		for (Alquiler alquilerLista : Coleccionalquileres) {
			if (alquilerLista.getVehiculo().equals(vehiculo)) {
				arrayAlquileresCliente.add(alquilerLista);
			}
		}
		return arrayAlquileresCliente;

	}

	@Override
	public List<Alquiler> get(Cliente cliente) {
		ArrayList<Alquiler> arrayAlquileresCliente = new ArrayList<>();

		for (Alquiler alquilerLista2 : Coleccionalquileres) {
			if (alquilerLista2.getCliente().equals(cliente)) {
				arrayAlquileresCliente.add(alquilerLista2);
			}
		}

		return arrayAlquileresCliente;
	}

	@Override
	public boolean comprobarAlquiler(Alquiler alquiler) {
		if (alquiler == null || Coleccionalquileres.contains(alquiler)) {
			return false;
		}
		for (Alquiler a : Coleccionalquileres) {
			if (a.getCliente().equals(alquiler.getCliente()) && a.getFechaDevolucion() == null) {
				return false;
			}
			if (a.getVehiculo().equals(alquiler.getVehiculo()) && a.getFechaDevolucion() == null) {
				return false;
			}
			if (a.getCliente().equals(alquiler.getCliente()) && a.getVehiculo().equals(alquiler.getVehiculo())
					&& a.getFechaAlquiler().isBefore(alquiler.getFechaDevolucion()) && (a.getFechaDevolucion() == null
							|| a.getFechaDevolucion().isAfter(alquiler.getFechaAlquiler()))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		if (!Coleccionalquileres.contains(alquiler)) {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
			Coleccionalquileres.add(alquiler);
		}

	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : Coleccionalquileres) {

			if (alquiler.getFechaDevolucion() == null) {

				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getVehiculo().equals(vehiculo)) {

					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}

			} else {

				if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getVehiculo().equals(vehiculo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		if (Coleccionalquileres.contains(alquiler)) {

			alquiler.devolver(fechaDevolucion);

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		if (Coleccionalquileres.indexOf(alquiler) == -1) {

			alquiler = null;
		} else {
			Coleccionalquileres.get(Coleccionalquileres.indexOf(alquiler));
		}

		return alquiler;

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (Coleccionalquileres.contains(alquiler)) {
			Coleccionalquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	@Override
	public Alquiler[] get() {
		// TODO Auto-generated method stub
		return null;
	}

}