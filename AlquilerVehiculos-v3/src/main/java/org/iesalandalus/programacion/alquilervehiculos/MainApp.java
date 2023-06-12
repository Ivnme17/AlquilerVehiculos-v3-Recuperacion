package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.modelo.vista.recursos.LocalizadorRecursos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class MainApp extends Application {


    	@Override
    	public void start(Stage escenarioPrincipal) {
    		try {
    			VBox raiz = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
    			Scene escena = new Scene(raiz);
    			escenarioPrincipal.setTitle("Ventana Principal");
    			escenarioPrincipal.setScene(escena);
    			escenarioPrincipal.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}

    public static void main(String[] args) {
        launch(args);
    }

	

}
