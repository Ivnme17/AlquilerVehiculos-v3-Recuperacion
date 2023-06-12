package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class FuenteDatosFicheros implements IFuenteDatos {
@Override
public Clientes crearClientes(Clientes IClientes) {
	return IClientes;
}
@Override
public IVehiculos crearTurismos(IVehiculos ITurismos) {
	return ITurismos;
}
@Override
public Alquileres creaAlquiler(Alquileres IAlquileres) {
	return IAlquileres;
}
}
