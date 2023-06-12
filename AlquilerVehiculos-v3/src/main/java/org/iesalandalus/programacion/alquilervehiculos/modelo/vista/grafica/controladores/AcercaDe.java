package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AcercaDe {

	@FXML
	private ImageView ImagenDePerfil;

	@FXML
	private Label Nombre;

	private Label lbMensajes;

	public void setLBMensajes(Label lbMensajes) {
		this.lbMensajes = lbMensajes;
	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void saluda() {
		lbMensajes.setText("Bienvenido a la ventana Acerca de...");
	}
}
