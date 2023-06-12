package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import java.awt.Button;
import java.awt.MenuBar;
import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Vehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VentanaPrincipal<Parent> extends Controlador {

	  @FXML
	    private Menu MenuItemAcercaDe;

	    @FXML
	    private Button btAñadirAlquiler;

	    @FXML
	    private Button btAñadirCliente;

	    @FXML
	    private Button btAñadirVehiculo;

	    @FXML
	    private Button btGestionarAlquiler;

	    @FXML
	    private Button btGestionarCliente;

	    @FXML
	    private Button btGestionarVehiculo;

	private Stage escenarioGestionarAlquileres;
	private Stage escenariogestionarClientes;
	private Stage escenariogestionarVehiculos;
	private Stage escenarioinsertarCliente;
	private Stage escenarioInsertarAlquileres;
	private Stage escenarioInsertarVehiculos;

	private Stage escenarioAcercaDe;

	@FXML
	private void GestionarAlquileres(ActionEvent event) {
		if (escenarioGestionarAlquileres == null) {
			escenarioGestionarAlquileres = new Stage();
			FXMLLoader cargadorGestionarAlquileres = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/ListarAlquiler.fxml"));
			VBox raizGestionarAlquileres = cargadorGestionarAlquileres.getController();
			Alquileres controladorGestionarAlquileres = cargadorGestionarAlquileres.getController();
			Scene ListarAlquiler = new Scene(raizGestionarAlquileres);
			escenarioGestionarAlquileres.setTitle("GestionarAlquileres");
			escenarioGestionarAlquileres.initModality(Modality.APPLICATION_MODAL);
			escenarioGestionarAlquileres.setScene(ListarAlquiler);
		}

	}

	@FXML
	private void gestionarCliente(ActionEvent event) {
		if (escenariogestionarClientes == null) {
			escenariogestionarClientes = new Stage();
			FXMLLoader cargadorgestionarClientes = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/ListarCliente.fxml"));
			VBox raizgestionarClientes = cargadorgestionarClientes.getController();
			Clientes controladorgestionarClientes = cargadorgestionarClientes.getController();
			Scene ListarClientes = new Scene(raizgestionarClientes);
			escenariogestionarClientes.setTitle("GestionarClientes");
			escenariogestionarClientes.initModality(Modality.APPLICATION_MODAL);
			escenariogestionarClientes.setScene(ListarClientes);
		}

	}

	@FXML
	private void gestionarVehiculos(ActionEvent event) {
		if (escenariogestionarVehiculos == null) {
			escenariogestionarVehiculos = new Stage();
			FXMLLoader cargadorgestionarVehiculos = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/ListarVehiculo.fxml"));
			VBox raizgestionarVehiculos = cargadorgestionarVehiculos.getController();
			Vehiculos controladorgestionarVehiculos = cargadorgestionarVehiculos.getController();
			Scene ListarVehiculo = new Scene(raizgestionarVehiculos);
			escenariogestionarVehiculos.setTitle("GestionarVehiculos");
			escenariogestionarVehiculos.initModality(Modality.APPLICATION_MODAL);
			escenariogestionarVehiculos.setScene(ListarVehiculo);
		}

	}

	@FXML
	private void insertarCliente(ActionEvent event) {
		if (escenarioinsertarCliente == null) {
			escenarioinsertarCliente = new Stage();
			FXMLLoader cargadorinsertarCliente = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/LeerCliente.fxml"));
			VBox raizinsertarCliente = cargadorinsertarCliente.getController();
			Clientes controladorinsertarCliente = cargadorinsertarCliente.getController();
			Scene insertarCliente = new Scene(raizinsertarCliente);
			escenarioinsertarCliente.setTitle("InsertarCliente");
			escenarioinsertarCliente.initModality(Modality.APPLICATION_MODAL);
			escenarioinsertarCliente.setScene(insertarCliente);
		}

	}

	@FXML
	private void insertarAlquileres(ActionEvent event) {
		if (escenarioInsertarAlquileres == null) {
			escenarioInsertarAlquileres = new Stage();
			FXMLLoader cargadorInsertarAlquileres = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/LeerAlquiler.fxml"));
			VBox raizInsertarAlquileres = cargadorInsertarAlquileres.getController();
			Alquileres controladorGestionarAlquileres = cargadorInsertarAlquileres.getController();
			Scene insertarAlquileres = new Scene(raizInsertarAlquileres);
			escenarioInsertarAlquileres.setTitle("LeerAlquileres");
			escenarioInsertarAlquileres.initModality(Modality.APPLICATION_MODAL);
			escenarioInsertarAlquileres.setScene(insertarAlquileres);
		}

	}

	@FXML
	private void insertarVehiculos(ActionEvent event) {
		if (escenarioInsertarVehiculos == null) {
			escenarioInsertarVehiculos = new Stage();
			FXMLLoader cargadorInsertarVehiculos = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/ListarAlquiler"));
			VBox raizInsertarVehiculos = cargadorInsertarVehiculos.getController();
			Vehiculos controladorInsertarVehiculos = cargadorInsertarVehiculos.getController();
			Scene insertarVehiculos = new Scene(raizInsertarVehiculos);
			escenarioInsertarVehiculos.setTitle("InsertarVehiculos");
			escenarioInsertarVehiculos.initModality(Modality.APPLICATION_MODAL);
			escenarioInsertarVehiculos.setScene(insertarVehiculos);
		}

	}

	@FXML
	private void AcercaDe(ActionEvent event) {
		if (escenarioAcercaDe == null) {
			escenarioAcercaDe = new Stage();
			FXMLLoader cargadorAcercaDe = new FXMLLoader(
					LocalizadorRecursos.class.getResource("src/main/java/vistas/AcercaDe.fxml"));
			VBox raizAcercaDe = cargadorAcercaDe.getController();
			AcercaDe controladorGestionarAlquileres = cargadorAcercaDe.getController();
			Scene AcercaDe = new Scene(raizAcercaDe);
			escenarioGestionarAlquileres.setTitle("AcercaDe");
			escenarioGestionarAlquileres.initModality(Modality.APPLICATION_MODAL);
			escenarioGestionarAlquileres.setScene(AcercaDe);
		}

	}

	@FXML
	private void abrirGestionarAlquileres() throws IOException {
		abrirGestionarAlquileres();
		escenarioGestionarAlquileres.showAndWait();
	}

	@FXML
	private void abrirGestionarClientes() throws IOException {
		abrirGestionarClientes();
		escenariogestionarClientes.showAndWait();
	}

	@FXML
	private void abrirGestionarVehiculos() throws IOException {
		abrirGestionarVehiculos();
		escenariogestionarVehiculos.showAndWait();
	}

	@FXML
	private void abririnsertarCliente() throws IOException {
		abririnsertarCliente();
		escenarioinsertarCliente.showAndWait();
	}

	@FXML
	private void abririnsertarVehiculos() throws IOException {
		abririnsertarVehiculos();
		escenarioInsertarVehiculos.showAndWait();
	}

	@FXML
	private void abririnsertarAlquileres() throws IOException {
		abririnsertarAlquileres();
		escenarioInsertarAlquileres.showAndWait();
	}
	@FXML
	private void abrirAcercaDe() throws IOException {
		abrirAcercaDe();
		escenarioAcercaDe.showAndWait();
	}
}
