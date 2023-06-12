package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaModificacionCliente implements Initializable {
	@FXML
	private Button btActualizarDatos;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	private String clienteDni;
	private boolean actualizacionRealizada;

	@FXML
	void Actualizar(ActionEvent event) {
		String nombre = tfNombre.getText();
		String telefono = tfTelefono.getText();
		Cliente cliente = getClienteConDni(clienteDni);
		if (cliente != null) {
			cliente.setNombre(nombre);
			cliente.setTelefono(telefono);
			System.out.println(
					"Cliente actualizado: DNI=" + clienteDni + ", Nombre=" + nombre + ", Teléfono=" + telefono);

			actualizacionRealizada = true;
		} else {
			System.out.println("No se encontró el cliente con DNI=" + clienteDni);
			actualizacionRealizada = false;
		}

		Stage stage = (Stage) tfDni.getScene().getWindow();
		stage.close();
	}

	private Cliente getClienteConDni(String clienteDni) {
		return new Cliente(null, clienteDni, null);
	}

	public void setCliente(String dni) {
		clienteDni = dni;
		tfDni.setText(clienteDni);
	}

	public boolean isActualizacionRealizada() {
		return actualizacionRealizada;
	}

	@FXML
	void cancelar(ActionEvent event) {
		actualizacionRealizada = false;

		Stage stage = (Stage) tfDni.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
