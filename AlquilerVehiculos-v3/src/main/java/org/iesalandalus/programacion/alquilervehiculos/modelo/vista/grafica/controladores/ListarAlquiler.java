package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListarAlquiler {
	public static List<Alquiler> Coleccionalquileres;
	private static List<Cliente> coleccionClientes;
	private static List<Vehiculo> coleccionVehiculos;
	@FXML
	private Button BtBorrar;

	@FXML
	private Button BtBuscar;

	@FXML
	private Button BtDevolver;

	@FXML
	private Button BtEstadistica;

	@FXML
	private Button BtMostrarTodo;

	@FXML
	private TextField TFCliente;

	@FXML
	private TextField TFFechaAlquiler;

	@FXML
	private TextField TFFechaDevolucion;

	@FXML
	private TextField TFVehiculo;
	@FXML
	private Button BtAlquilerPorCliente;

	@FXML
	private Button BtAlquilerPorVehiculo;
	@FXML
	private TableView<?> TablaBuscarAlquiler;

	@FXML
	private TableColumn<?, ?> tcClienteAlquiler;

	@FXML
	private TableColumn<?, ?> tcFechaAlquiler;

	@FXML
	private TableColumn<?, ?> tcFechaDevolucion;

	@FXML
	private TableColumn<?, ?> tcVehiculoAlquiler;

	private void limpiarTextFields() { // Sirve para que cuando una accion se ejecute se elimine el contenido de los
										// textfield.
		TFCliente.setText("");
		TFFechaAlquiler.setText("");
		TFFechaDevolucion.setText("");
		TFVehiculo.setText("");
	}

	@FXML
	void Borrar(ActionEvent event) {
		try {
			Alquiler alquiler = obtenerAlquilerSeleccionado();
			borrar(alquiler);
			limpiarTextFields();
		} catch (NullPointerException e) {
			Dialogos.mostrarDialogoError("No se puede borrar un alquiler nulo.", null, null);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("No existe ningún alquiler igual.", null, null);
		}
	}

	@FXML
	void Devolver(ActionEvent event) {
		try {
			Alquiler alquiler = obtenerAlquilerSeleccionado();
			LocalDate fechaDevolucion = LocalDate.now();
			devolver(alquiler, fechaDevolucion);
			limpiarTextFields();
		} catch (NullPointerException e) {
			Dialogos.mostrarDialogoError("No se puede devolver un alquiler nulo.", null, null);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("No existe ningún alquiler igual.", null, null);
		}
	}

	@FXML
	void Buscar(ActionEvent event) {
		try {
			Alquiler alquiler = obtenerAlquilerSeleccionado();
			alquiler = buscar(alquiler);
			mostrarAlquiler(alquiler);
			limpiarTextFields();
		} catch (NullPointerException e) {
			Dialogos.mostrarDialogoError("No se puede buscar un alquiler nulo.", null, null);
		}
	}

	private Alquiler obtenerAlquilerSeleccionado() {
		int indiceSeleccionado = TablaBuscarAlquiler.getSelectionModel().getSelectedIndex();

		if (indiceSeleccionado != -1) {
			Alquiler alquiler = (Alquiler) TablaBuscarAlquiler.getItems().get(indiceSeleccionado);
			return alquiler;
		} else {
			return null;
		}
	}

	private void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (Alquileres.Coleccionalquileres.contains(alquiler)) {
			Alquileres.Coleccionalquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	private void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		if (Alquileres.Coleccionalquileres.contains(alquiler)) {
			alquiler.devolver(fechaDevolucion);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	private Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		if (Alquileres.Coleccionalquileres.indexOf(alquiler) == -1) {
			alquiler = null;
		} else {
			alquiler = Alquileres.Coleccionalquileres.get(Alquileres.Coleccionalquileres.indexOf(alquiler));
		}

		return alquiler;
	}

	private void mostrarAlquiler(Alquiler alquiler) {
		if (alquiler != null) {
			Cliente cliente = alquiler.getCliente();
			LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
			LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
			Vehiculo vehiculo = alquiler.getVehiculo();

			TFCliente.setText(cliente.toString());
			TFFechaAlquiler.setText(fechaAlquiler.toString());
			TFFechaDevolucion.setText(fechaDevolucion.toString());
			TFVehiculo.setText(vehiculo.toString());
		}
	}

	@FXML
	void Estadistica(ActionEvent event) {
		limpiarTextFields();

		String mesSeleccionado = obtenerMesSeleccionado();

		EstadisticasVehiculos estadisticas = calcularEstadisticasPorVehiculos(mesSeleccionado);

		mostrarEstadisticas(estadisticas);
	}

	private String obtenerMesSeleccionado() {

		String mesSeleccionado = "";
		return mesSeleccionado;
	}

	private EstadisticasVehiculos calcularEstadisticasPorVehiculos(String mes) {

		EstadisticasVehiculos estadisticas = new EstadisticasVehiculos();

		return estadisticas;
	}

	private void mostrarEstadisticas(EstadisticasVehiculos estadisticas) {
		TFCliente.setText(Integer.toString(estadisticas.getClientes()));
		TFFechaAlquiler.setText(Integer.toString(estadisticas.getAlquileres()));
		TFFechaDevolucion.setText(Integer.toString(estadisticas.getDevoluciones()));
		TFVehiculo.setText(Integer.toString(estadisticas.getVehiculos()));
	}

	@FXML
	void AlquilerRealizadoPorCliente(ActionEvent event) {
		try {
			String dniCliente = TFCliente.getText();
			mostrarAlquileresPorCliente(dniCliente);
			limpiarTextFields();
		} catch (NullPointerException e) {
			Dialogos.mostrarDialogoError("No se puede mostrar los alquileres de un cliente nulo.", null, null);
		}
	}

	@FXML
	void Alquilerrealizadoporvehiculo(ActionEvent event) {
		try {
			String matriculaVehiculo = TFVehiculo.getText();
			mostrarAlquileresPorVehiculo(matriculaVehiculo);
			limpiarTextFields();
		} catch (NullPointerException e) {
			Dialogos.mostrarDialogoError("No se puede mostrar los alquileres de un vehículo nulo.", null, null);
		}
	}

	public void mostrarAlquileresPorCliente(String dniCliente) {
		Cliente cliente = buscarClientePorDni(dniCliente);

		if (cliente == null) {
			System.out.println("No se encontró el cliente con DNI: " + dniCliente);
		} else {
			List<Alquiler> alquileresCliente = getAlquileresPorCliente(cliente);

			if (alquileresCliente.isEmpty()) {
				System.out.println("No se encontraron alquileres para el cliente: " + cliente.getNombre());
			} else {
				System.out.println("Alquileres del cliente: " + cliente.getNombre());
				for (Alquiler alquiler : alquileresCliente) {
					System.out.println(alquiler);
				}
			}
		}
	}

	public void mostrarAlquileresPorVehiculo(String matriculaVehiculo) {
		Vehiculo vehiculo = buscarVehiculoPorMatricula(matriculaVehiculo);

		if (vehiculo == null) {
			System.out.println("No se encontró el vehículo con matrícula: " + matriculaVehiculo);
		} else {
			List<Alquiler> alquileresVehiculo = getAlquileresPorVehiculo(vehiculo);

			if (alquileresVehiculo.isEmpty()) {
				System.out.println("No se encontraron alquileres para el vehículo: " + vehiculo.getMatricula());
			} else {
				System.out.println("Alquileres del vehículo: " + vehiculo.getMatricula());
				for (Alquiler alquiler : alquileresVehiculo) {
					System.out.println(alquiler);
				}
			}
		}
	}

	private List<Alquiler> getAlquileresPorCliente(Cliente cliente) {
		List<Alquiler> alquileresCliente = new ArrayList<>();

		for (Alquiler alquiler : Coleccionalquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquileresCliente.add(alquiler);
			}
		}

		return alquileresCliente;
	}

	private List<Alquiler> getAlquileresPorVehiculo(Vehiculo vehiculo) {
		List<Alquiler> alquileresVehiculo = new ArrayList<>();

		for (Alquiler alquiler : Coleccionalquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquileresVehiculo.add(alquiler);
			}
		}

		return alquileresVehiculo;
	}

	private Cliente buscarClientePorDni(String dniCliente) {
		for (Cliente cliente : coleccionClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				return cliente;
			}
		}
		return null;
	}

	private Vehiculo buscarVehiculoPorMatricula(String matriculaVehiculo) {
	    for (Vehiculo vehiculo : coleccionVehiculos) {
	        if (vehiculo.getMatricula().equalsIgnoreCase(matriculaVehiculo)) {
	            return vehiculo;
	        }
	    }
	    return null; // Si no se encuentra el vehículo, devuelve null
	}

	private List<Alquiler> get(Vehiculo vehiculo) {
		List<Alquiler> alquileresVehiculo = new ArrayList<>();

		for (Alquiler alquiler : Coleccionalquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquileresVehiculo.add(alquiler);
			}
		}

		return alquileresVehiculo;
	}

	@FXML
	void Mostrar(ActionEvent event) {

		limpiarTextFields();
	}

}
