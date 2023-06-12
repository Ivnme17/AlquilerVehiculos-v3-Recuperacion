package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
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
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {
	private File FICHERO_CLIENTES;
	private String RAIZ;
	private String NOMBRE;
	private String DNI;
	private String TELEFONO;

	public static List<Cliente> coleccionClientes;

	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	public class XMLException extends Exception {
		public XMLException(String mensaje) {
			super(mensaje);
		}
	}

	public void comenzar() throws XMLException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document;

		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(FICHERO_CLIENTES);

			Element raiz = document.getDocumentElement();
			NodeList listaClientes = raiz.getElementsByTagName("cliente");

			for (int i = 0; i < listaClientes.getLength(); i++) {
				Element elementoCliente = (Element) listaClientes.item(i);

				String dni = elementoCliente.getAttribute("dni");
				String nombre = elementoCliente.getAttribute("nombre");
				String telefono = elementoCliente.getAttribute("telefono");

				Cliente cliente = new Cliente(dni, nombre, telefono);
				coleccionClientes.add(cliente);
			}
		} catch (Exception e) {
			throw new XMLException("No se pudo leer el archivo XML de clientes.");
		}
	}

	private void leerDom() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documento = builder.parse(FICHERO_CLIENTES);
			NodeList listaClientes = documento.getElementsByTagName("cliente");

			for (int i = 0; i < listaClientes.getLength(); i++) {
				Element elementoCliente = (Element) listaClientes.item(i);
				String dni = elementoCliente.getAttribute("dni");
				String nombre = elementoCliente.getAttribute("nombre");
				String telefono = elementoCliente.getAttribute("telefono");
				Cliente cliente = new Cliente(dni, nombre, telefono);
				coleccionClientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
		}
	}

	private Cliente getCliente(String dni) {
		for (Cliente cliente : coleccionClientes) {
			if (cliente.getDni().equals(dni)) {
				return cliente;
			}
		}
		return null;
	}

	public void terminar() throws XMLException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document;

		try {
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();

			Element raiz = document.createElement(RAIZ);
			document.appendChild(raiz);

			for (Cliente cliente : coleccionClientes) {
				Element elementoCliente = document.createElement(NOMBRE);
				elementoCliente.setAttribute(DNI, cliente.getDni());
				elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
				elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
				raiz.appendChild(elementoCliente);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(FICHERO_CLIENTES);
			transformer.transform(source, result);
		} catch (Exception e) {
			throw new XMLException("No se pudo escribir en el archivo XML");
		}
	}

	private void crearDom() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documento = builder.newDocument();

			Element raiz = documento.createElement(RAIZ);
			documento.appendChild(raiz);

			for (Cliente cliente : coleccionClientes) {
				Element elementoCliente = documento.createElement("cliente");
				elementoCliente.setAttribute("dni", cliente.getDni());
				elementoCliente.setAttribute("nombre", cliente.getNombre());
				elementoCliente.setAttribute("telefono", cliente.getTelefono());
				raiz.appendChild(elementoCliente);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(documento);
			StreamResult result = new StreamResult(FICHERO_CLIENTES);
			transformer.transform(source, result);
		} catch (Exception e) {
			System.out.println("Error al crear el archivo de clientes: " + e.getMessage());
		}
	}

	public static Element getElemento(Document documento, String etiqueta, String valor) {
		NodeList lista = documento.getElementsByTagName(etiqueta);
		for (int i = 0; i < lista.getLength(); i++) {
			Element elemento = (Element) lista.item(i);
			if (elemento.getAttribute("dni").equals(valor)) {
				return elemento;
			}
		}
		return null;
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if (cliente != null && !coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		int indice = coleccionClientes.indexOf(cliente);
		if (indice != -1) {
			return coleccionClientes.get(indice);
		}
		return null;
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (!coleccionClientes.remove(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		if (cliente != null && coleccionClientes.contains(cliente)) {
			if (nombre != null && !nombre.isBlank()) {
				cliente.setNombre(nombre);
			}
			if (telefono != null && !telefono.isBlank()) {
				cliente.setTelefono(telefono);
			}
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

	}

	public static Clientes getInstancia() {
		return new Clientes();
	}

	@Override
	public List<Cliente> get() {
		// TODO Auto-generated method stub
		return null;
	}

	public void devolver(String dni) {
	    Cliente cliente = getCliente(dni);
	    if (cliente != null) {
	        cliente.setVehiculoAlquilado(null);
	    }
	}

	public void actualizar(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		
	}
}