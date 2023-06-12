package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.awt.event.ActionEvent;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeerVehiculo {
	@FXML
	private Label lbMarca;

	@FXML
	private Label lbMatricula;

	@FXML
	private Label lbModelo;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfModelo;

	@FXML
	void Aceptar(ActionEvent event) {
	    String marca = tfMarca.getText();
	    String matricula = tfMatricula.getText();
	    String modelo = tfModelo.getText();

	    try {
	        Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(matricula);
	        vehiculo.setMarca(marca);
	        vehiculo.setModelo(modelo);
	        System.out.println("Vehículo aceptado: " + vehiculo);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error al crear el vehículo: " + e.getMessage());
	    }
	}

	@FXML
	void Cancelar(ActionEvent event) {
	    tfMarca.clear();
	    tfMatricula.clear();
	    tfModelo.clear();
	}
}
