package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }

        if (vista == null) {
            throw new NullPointerException("ERROR: La vista no puede ser nula.");
        }
        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        System.out.println("");
        modelo.terminar();
        vista.terminar();
        System.out.printf("%nHasta la próxima!!!!");
    }

    public void insertarCliente(Cliente cliente) throws OperationNotSupportedException {
        modelo.insertarCliente(cliente);
    }

    public void insertarVehiculo(Vehiculo vehiculo) throws OperationNotSupportedException {
        modelo.insertarVehiculo(vehiculo);
    }

    public void abrirAlquiler(Alquiler alquiler) throws OperationNotSupportedException {
        modelo.abrirAlquiler(alquiler);
    }

    public Cliente buscarCliente(String dni) {
        return modelo.buscarCliente(dni);
    }

    public Vehiculo buscarVehiculo(String matricula) {
        return modelo.buscarVehiculo(matricula);
    }

    public Alquiler buscarAlquiler(Cliente cliente, Vehiculo vehiculo) {
        return modelo.buscarAlquiler(cliente, vehiculo);
    }

    public void modificarCliente(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Cliente clienteEncontrado = buscarCliente(cliente.getDni());
        if (clienteEncontrado != null) {
            clienteEncontrado.setNombre(nombre);
            clienteEncontrado.setTelefono(telefono);
            modelo.modificarCliente(clienteEncontrado, nombre, telefono);
            System.out.println("Cliente modificado correctamente.");
        } else {
            System.out.println("No se encontró ningún cliente con el DNI: " + cliente.getDni());
        }
    }

    public void borrarCliente(Cliente cliente) throws OperationNotSupportedException {
        modelo.borrarCliente(cliente.getDni());
    }

    public void borrarVehiculo(Vehiculo vehiculo) throws OperationNotSupportedException {
        modelo.borrarVehiculo(vehiculo.getMatricula());
    }

    public void borrarAlquiler(Alquiler alquiler) throws OperationNotSupportedException {
        modelo.borrarAlquiler(alquiler.getCliente(), alquiler.getVehiculo());
    }

    public void devolverCliente(String dni) throws OperationNotSupportedException {
        Cliente cliente = buscarCliente(dni);
        if (cliente != null) {
            modelo.devolverVehiculos(cliente);
            System.out.println("Vehículos devueltos correctamente para el cliente: " + cliente.getNombre());
        } else {
            System.out.println("No se encontró ningún cliente con el DNI: " + dni);
        }
    }

    public void devolverVehiculo(String matricula) throws OperationNotSupportedException {
        Vehiculo vehiculo = buscarVehiculo(matricula);
        if (vehiculo != null) {
            modelo.devolverVehiculos(vehiculo);
            System.out.println("Vehículo devuelto correctamente: " + vehiculo.getMatricula());
        } else {
            System.out.println("No se encontró ningún vehículo con la matrícula: " + matricula);
        }
    }

    public List<Cliente> getClientes() {
        return modelo.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modelo.getVehiculos();
    }

    public List<Alquiler> getAlquileres() {
        return modelo.getAlquileres();
    }

    public List<Alquiler> getAlquileres(Cliente cliente) {
        return modelo.getAlquileres(cliente);
    }
}


	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
		return modelo.getAlquileres(vehiculo);
	}

	public List<Alquiler> getAlquileres(Cliente cliente, Vehiculo vehiculo) {
		return modelo.getAlquileres(cliente, vehiculo);
	}

	public void registrarCliente(String nombre, String dni, String telefono, Vehiculo vehiculo) {
		try {
			Cliente cliente = new Cliente(nombre, dni, telefono);
			modelo.insertarCliente(cliente);

			modelo.insertarVehiculo(vehiculo);

			System.out.println("Cliente registrado correctamente.");
		} catch (Exception e) {
			System.out.println("Error al registrar el cliente: " + e.getMessage());
		}
	}
}
