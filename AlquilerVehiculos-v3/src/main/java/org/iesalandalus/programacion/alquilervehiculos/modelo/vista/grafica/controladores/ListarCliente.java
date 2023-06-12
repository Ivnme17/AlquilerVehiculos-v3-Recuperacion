package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.awt.Button;
import java.awt.MenuBar;
import java.awt.TextField;
import java.io.IOException;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListarCliente {

	@FXML
	private MenuBar BarraMenu;

	@FXML
	private TableView tablaClientes;
	@FXML
	private TableColumn tcNombre;

	@FXML
	private TableColumn tcDNI;

	@FXML
	private TableColumn tcTelefono;
	@FXML
	private TextField TfDni;

	@FXML
	private TextField TfNombre;

	@FXML
	private TextField TfTelefono;

	@FXML
	private Button btBorrar;

	@FXML
	private Button btBuscar;

	@FXML
	private Button btDevolver;

	@FXML
	private Button btModificar;

	@FXML
	private Button btMostrarTodo;

	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tcDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		// Cargar los clientes en la tabla
		List<Cliente> clientes = Clientes.getInstancia().get();
		tablaClientes.setItems(FXCollections.observableArrayList(clientes));
	}

	@FXML
	void Borrar(ActionEvent event) {
		Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
		if (clienteSeleccionado != null) {
			try {
				Clientes.getInstancia().borrar(clienteSeleccionado);
				Dialogos.mostrarDialogoInformacion("Borrar cliente", "Cliente borrado correctamente.", null);
				limpiarCampos();
			} catch (OperationNotSupportedException e) {
				Dialogos.mostrarDialogoError("Borrar cliente", e.getMessage(), null);
			}
		} else {
			Dialogos.mostrarDialogoAdvertencia("Borrar cliente", "Debe seleccionar un cliente para borrar.", null);
		}
	}

	@FXML
	void Buscar(ActionEvent event) {
		String dni = TfDni.getText();
		if (!dni.isBlank()) {
			Cliente cliente = Clientes.getInstancia().buscar(new Cliente(dni, null, null));
			if (cliente != null) {
				tablaClientes.getSelectionModel().select(cliente);
				tablaClientes.scrollTo(cliente);
			} else {
				Dialogos.mostrarDialogoInformacion("Buscar cliente",
						"No se encontró ningún cliente con el DNI especificado.", null);
			}
		} else {
			Dialogos.mostrarDialogoAdvertencia("Buscar cliente", "Debe introducir un DNI para realizar la búsqueda.",
					null);
		}
	}

	@FXML
	void Devolver(ActionEvent event) {
		Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
		if (clienteSeleccionado != null) {
			Clientes.getInstancia().devolver(clienteSeleccionado.getDni());
			Dialogos.mostrarDialogoInformacion("Devolver cliente", "Cliente devuelto correctamente.", null);
		} else {
			Dialogos.mostrarDialogoAdvertencia("Devolver cliente", "Debe seleccionar un cliente para devolver.", null);
		}
	}

	@FXML
	void Modificar(ActionEvent event) {
		Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
		if (clienteSeleccionado != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaModificacionCliente.fxml"));
				Parent root = loader.load();

				VentanaModificacionCliente controller = loader.getController();
				controller.setCliente(clienteSeleccionado.getDni());

				Stage stage = new Stage();
				stage.setTitle("Modificar cliente");
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.showAndWait();
				if (controller.isActualizacionRealizada()) {
					Dialogos.mostrarDialogoInformacion("Modificar cliente", "Cliente modificado correctamente.", null);
				}
			} catch (IOException e) {
				Dialogos.mostrarDialogoError("Modificar cliente",
						"No se pudo abrir la ventana de modificación de cliente.", null);
			}
		} else {
			Dialogos.mostrarDialogoAdvertencia("Modificar cliente", "Debe seleccionar un cliente para modificar.",
					null);
		}
	}

	private void limpiarCampos() {
		TfDni.setText("");
		TfNombre.setText("");
		TfTelefono.setText("");
	}

	@FXML
	void MostrarTodo(ActionEvent event) {
		List<Cliente> clientes = Clientes.getInstancia().get();
		tablaClientes.setItems(FXCollections.observableArrayList(clientes));
	}
}
