package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.fichero.FuenteDatosFicheros;

public enum FactoriaFuenteDatos {
MEMORIA{
		@Override
		public IFuenteDatos crear(){
			return new FuenteDatosFicheros();
		}
}, FICHEROS {
	@Override
	public IFuenteDatos crear() {
		// TODO Auto-generated method stub
		return null;
	}
};

public abstract IFuenteDatos crear();
}
