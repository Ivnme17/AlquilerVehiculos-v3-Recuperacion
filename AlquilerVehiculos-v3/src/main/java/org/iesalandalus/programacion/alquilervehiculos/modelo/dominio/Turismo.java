package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo {
	private int cilindrada;
	private static final int FACTOR_CILINDRADA = 10;

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
	}

	public Turismo(Vehiculo vehiculo) { // Turismo copia
		super(vehiculo);
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede copiar un turismo nulo");
		}
		setCilindrada(cilindrada);
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double getFactorPrecio() {
		return cilindrada / FACTOR_CILINDRADA;
	}

	@Override
	public String toString() {
		return String.format("Turismo [cilindrada=%s, FACTOR_CILINDRADA=%s]", cilindrada, FACTOR_CILINDRADA);
	}

	@Override
	public int getPlazas() {
		return 0;
		// TODO Auto-generated method stub

	}
}
