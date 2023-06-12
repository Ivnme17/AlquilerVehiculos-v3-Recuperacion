package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo {
private int FACTOR_PLAZAS = 2;
private int plazas;
	public Autobus(String marca, String modelo, String matricula, int plazas) {
		super(marca, modelo,matricula);
		setPlazas(plazas);
	}

	public Vehiculo Autobus (Autobus autobus) {//Constructor copia
		if(autobus == null) {
			throw new NullPointerException("ERROR: El valor de autobus no puede ser nulo.");
		}
		return autobus; 
		
	}
	private void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public int getPlazas() {
		return plazas;
	}

	public double getFactorPrecio() {
		return plazas * FACTOR_PLAZAS;
	}
	
	public Autobus copiar() {
        return new Autobus(getMatricula(), getMarca(), getModelo(), getPlazas());
    }

    @Override
    public String toString() {
        return "Autobus{" +
                "plazas=" + plazas +
                ", " + super.toString() +
                '}';
    }

	@Override
	public int getCilindrada() {
		// TODO Auto-generated method stub
		return 0;
	}
}
