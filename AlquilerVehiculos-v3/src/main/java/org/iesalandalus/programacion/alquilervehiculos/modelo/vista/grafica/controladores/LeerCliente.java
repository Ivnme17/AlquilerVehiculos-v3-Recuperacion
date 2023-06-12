package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeerCliente extends Controlador {
	@FXML
	private Label lbDni;

	@FXML
	private Label lbNombre;

	@FXML
	private Label lbTelefono;

	@FXML
	private static TextField tfDni;

	@FXML
	private static TextField tfNombre;

	@FXML
	private static TextField tfTelefono;

	private static boolean cancelado;

	@FXML
	void Aceptar(ActionEvent event) {
		String nombre = tfNombre.getText();
		String dni = tfDni.getText();
		String telefono = tfTelefono.getText();

		if (validarDatos(nombre, dni, telefono)) {
			Cliente cliente = new Cliente(nombre, dni, telefono);
			cancelado = false;
			getEscenario().close();
		} else {
			Dialogos.mostrarDialogoError("Los datos ingresados no son validos, nombre no valido", nombre, escenario);
			Dialogos.mostrarDialogoError("Los datos ingresados no son validos, dni no valido", dni, escenario);
			Dialogos.mostrarDialogoError("Los datos ingresados no son validos, teléfono no valido", telefono,
					escenario);
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		cancelado = true;
		getEscenario().close();
	}

	private boolean validarDatos(String nombre, String dni, String telefono) {
		return validarNombre(nombre) && validarDNI(dni) && validarTelefono(telefono);
	}

	private boolean validarNombre(String nombre) {
		return Pattern.matches("[A-Z][a-zñ]*( [A-Z][a-zñ]*)*", nombre);
	}

	private boolean validarDNI(String dni) {
		return Pattern.matches("[0-9]\\d{8}[A-HJ-NP-TV-Z]", dni);
	}

	private boolean validarTelefono(String telefono) {
		return Pattern.matches("[6-9][0-9]{8}", telefono);
	}

	public void limpiar() {
		tfNombre.setText("");
		tfDni.setText("");
		tfTelefono.setText("");
	}
}