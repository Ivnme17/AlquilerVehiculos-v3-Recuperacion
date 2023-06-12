package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	public void comenzar() {
		Cliente cliente1 = new Cliente("Bob Esponja", "11223344B", "950112233");
		insertarCliente(cliente1);

		Vehiculo vehiculo1 = new Vehiculo("Seat", "León", "1234BCD");
		insertarvehiculo(vehiculo1);

		LocalDate fechaAlquiler1 = LocalDate.now();
		LocalDate fechaDevolucion1 = fechaAlquiler1.plus(5, ChronoUnit.DAYS);
		Alquiler alquiler1 = new Alquiler(cliente1, vehiculo1, fechaAlquiler1, fechaDevolucion1);
		abrirAlquiler(alquiler1);

		System.out.println("El modelo ha comenzado.");
	}

	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}

	public void insertarCliente(Cliente cliente) {
		clientes.add(new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono()));
	}

	public void insertarvehiculo(Vehiculo vehiculo) {
		vehiculos.add(new Vehiculo(vehiculo.getMarca(), vehiculo.getModelo(), Vehiculo.getMatricula()));
	}

	public void abrirAlquiler(Alquiler alquiler) {
		Cliente cliente = buscarCliente(alquiler.getCliente().getDni());
		Vehiculo vehiculo = buscarVehiculo(alquiler.getVehiculo().getMatricula());
		if (cliente != null && vehiculo != null) {
			alquileres.add(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler(), null));
		}
	}

	public void cerrarAlquiler(Alquiler alquiler) {
		for (Alquiler a : alquileres) {
			if (a.getCliente().equals(alquiler.getCliente()) && a.getVehiculo().equals(alquiler.getVehiculo())
					&& a.getFechaAlquiler().equals(alquiler.getFechaAlquiler()) && a.getFechaDevolucion() == null) {
				a.setFechaDevolucion(LocalDate.now());
				break;
			}
		}
	}

	public Cliente buscarCliente(String dni) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				return new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono());
			}
		}
		return null;
	}

	public Vehiculo buscarVehiculo(String matricula) {
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getMatricula().equals(matricula)) {
				return new Vehiculo(vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getMatricula());
			}
		}
		return null;
	}

	public void modificarCliente(String dni, String nuevoNombre, String nuevoTelefono) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				cliente.setNombre(nuevoNombre);
				cliente.setTelefono(nuevoTelefono);
				break;
			}
		}
	}

	public void borrarCliente(String dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni().equals(dni)) {
				clientes.remove(i);
				borrarAlquileresPorCliente(dni);
				break;
			}
		}
	}

	private void borrarAlquileresPorCliente(String dni) {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getCliente().getDni().equals(dni)) {
				alquileresCliente.add(alquiler);
			}
		}
		alquileres.removeAll(alquileresCliente);
	}

	public List<Cliente> getClientes() {
		List<Cliente> copiaClientes = new ArrayList<>();
		for (Cliente cliente : clientes) {
			copiaClientes.add(new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono()));
		}
		return copiaClientes;
	}

	public List<Vehiculo> getvehiculos() {
		List<Vehiculo> copiavehiculos = new ArrayList<>();
		for (Vehiculo vehiculo : vehiculos) {
			copiavehiculos.add(new Vehiculo(vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getMatricula()));
		}
		return copiavehiculos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> copiaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			copiaAlquileres.add(new Alquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler(),
					alquiler.getFechaDevolucion()));
		}
		return copiaAlquileres;
	}
}