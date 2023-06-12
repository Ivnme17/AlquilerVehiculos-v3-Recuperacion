package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alquiler {
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final int PRECIO_DIA = 20;
	private static LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private static Cliente cliente;
	private Vehiculo vehiculo;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
		setCliente(cliente);
		setVehiculo(vehiculo);
		setFechaAlquiler(fechaAlquiler);
		setFechaDevolucion(fechaDevolucion);
	}

	public Alquiler(Alquiler alquiler) {
		this(new Cliente(alquiler.getCliente()), new Turismo(alquiler.getVehiculo()), alquiler.getFechaAlquiler(),
				alquiler.getFechaDevolucion());
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		LocalDate hoy = LocalDate.now();
		if (fechaAlquiler.isAfter(hoy)) {
			throw new IllegalArgumentException("La fecha de alquiler no puede ser posterior a hoy");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		LocalDate hoy = LocalDate.now();
		if (fechaDevolucion.isBefore(fechaAlquiler) || fechaDevolucion.isAfter(hoy)) {
			throw new IllegalArgumentException("La fecha de devolución no es adecuada");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	public void devolver(LocalDate fechaDevolucion) throws IllegalArgumentException {
		setFechaDevolucion(fechaDevolucion);
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public static LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public String toString() {
		return String.format(
				"Alquiler [FORMATO_FECHA=%s, PRECIO_DIA=%s, fechaAlquiler=%s, fechaDevolucion=%s, cliente=%s, turismo=%s]",
				FORMATO_FECHA, PRECIO_DIA, FORMATO_FECHA.format(fechaAlquiler), FORMATO_FECHA.format(fechaDevolucion),
				cliente, vehiculo);
	}
}