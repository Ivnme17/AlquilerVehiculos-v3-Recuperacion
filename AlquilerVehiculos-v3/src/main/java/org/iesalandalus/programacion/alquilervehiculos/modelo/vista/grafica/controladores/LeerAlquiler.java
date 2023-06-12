package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeerAlquiler {
	@FXML
	private TextField TfCliente;

	@FXML
	private TextField TfFechaAlquiler;

	@FXML
	private TextField TfFechaDevolucion;

	@FXML
	private TextField TfVehiculo;

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private Label lbCliente;

	@FXML
	private Label lbFechaAlquiler;

	@FXML
	private Label lbFechaDevolucion;

	@FXML
	private Label lbVehiculo;

	private boolean cancelado;

	public void initialize() {
		cancelado = false;
	}

	@FXML
	void Aceptar(ActionEvent event) {
		String cliente = TfCliente.getText();
		String fechaAlquiler = TfFechaAlquiler.getText();
		String fechaDevolucion = TfFechaDevolucion.getText();
		String vehiculo = TfVehiculo.getText();

		boolean clienteValido = validarCliente(cliente);
		boolean fechaAlquilerValida = validarFechaAlquiler(fechaAlquiler);
		boolean fechaDevolucionValida = validarFechaDevolucion(fechaDevolucion);
		boolean vehiculoValido = validarVehiculo(vehiculo);

		if (clienteValido && fechaAlquilerValida && fechaDevolucionValida && vehiculoValido) {
			cancelado = false;
			TfCliente.getScene().getWindow().hide();
		} else {
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		cancelado = true;
		TfCliente.getScene().getWindow().hide();
	}

	public boolean isCancelado() {
		return cancelado;
	}

	private boolean validarCliente(String cliente) {
		return cliente.matches("[A-Z][a-zñ]*( [A-Z][a-zñ]*)*");
	}

	private boolean validarFechaAlquiler(String fechaAlquiler) {
		return true;
	}

	private boolean validarFechaDevolucion(String fechaDevolucion) {
		return true;
	}

	private boolean validarVehiculo(String vehiculo) {
		return vehiculo.matches("[A-Z0-9]{7}");
	}
}
