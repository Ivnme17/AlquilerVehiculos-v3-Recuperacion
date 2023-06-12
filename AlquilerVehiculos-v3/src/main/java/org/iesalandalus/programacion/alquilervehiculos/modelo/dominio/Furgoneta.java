package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Furgoneta extends Vehiculo {
	private int pma;
	private int plazas;
	private static final int FACTOR_PMA = 100;
	private static final int FACTOR_PLAZAS = 1;

	public Furgoneta(String marca, String modelo, String matricula, int pma, int plazas) {
		super(marca, modelo, matricula);
		setPma(pma);
		setPlazas(plazas);
	}

	public Furgoneta(Furgoneta furgoneta) { // Constructor copia
		super(furgoneta);
		if (furgoneta == null) {
			throw new NullPointerException("ERROR: El valor de furgoneta no puede ser nulo.");
		}
		setPma(pma);
		setPlazas(plazas);
	}

	public int getPma() {
		return pma;
	}

	public int getPlazas() {
		return plazas;
	}

	private void setPma(int pma) {
		this.pma = pma;
	}

	private void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public double getFactorPrecio() {
		return pma / FACTOR_PMA + plazas * FACTOR_PLAZAS;
	}

	@Override
	public String toString() {
		return "Furgoneta [pma=" + pma + ", plazas=" + plazas + "]";
	}

	@Override
	public int getCilindrada() {
		// TODO Auto-generated method stub
		return 0;
	}

}
