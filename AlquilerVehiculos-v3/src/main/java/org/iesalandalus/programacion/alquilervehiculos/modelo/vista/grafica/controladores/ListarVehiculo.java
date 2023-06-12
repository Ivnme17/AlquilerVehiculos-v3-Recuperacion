package org.iesalandalus.programacion.alquilervehiculos.modelo.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.Vehiculos;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListarVehiculo {
	@FXML
	private MenuBar BarraMenu;

	@FXML
	private Button Buscar;

	@FXML
	private Button BtMostrarTodo;

	@FXML
	private Button Devolver;

	@FXML
	private TableView<Vehiculo> ListarVehiculo;

	@FXML
	private Button btborrar;

	@FXML
	private TextField TFMarca;

	@FXML
	private TextField TFMatricula;

	@FXML
	private TextField TFModelo;

	@FXML
	private TableColumn<Vehiculo, String> tcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcModelo;

	private Vehiculos vehiculos;

	public void setVehiculos(Vehiculos vehiculos) {
		this.vehiculos = vehiculos;
	}

	@FXML
	void Borrar(ActionEvent event) {
		borrarTextFields();
	}

	@SuppressWarnings("unchecked")
	@FXML
	void Buscar(ActionEvent event) {
		String marca = TFMarca.getText();
		String matricula = TFMatricula.getText();
		String modelo = TFModelo.getText();

		if (!marca.isEmpty()) {
			ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculos.buscarPorMarca(marca)));
		} else if (!matricula.isEmpty()) {
			Vehiculo vehiculo = vehiculos.buscar(matricula);
			if (vehiculo != null) {
				ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculo));
			} else {
				ListarVehiculo.setItems(FXCollections.emptyObservableList());
			}
		} else if (!modelo.isEmpty()) {
			ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculos.buscarPorModelo(modelo)));
		} else {
			ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculos.get()));
		}
	}

	@FXML
	void Devolver(ActionEvent event) {
		Vehiculo vehiculoSeleccionado = ListarVehiculo.getSelectionModel().getSelectedItem();
		if (vehiculoSeleccionado != null) {
			try {
				vehiculos.borrar(vehiculoSeleccionado);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
			ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculos.get()));
			borrarTextFields();
		}
	}

	@FXML
	void Mostrar(ActionEvent event) {
		ListarVehiculo.setItems(FXCollections.observableArrayList(vehiculos.get()));
		borrarTextFields();
	}

	private void borrarTextFields() {
		TFMarca.setText("");
		TFModelo.setText("");
		TFMatricula.setText("");
	}
}
