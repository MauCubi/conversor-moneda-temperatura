package modelo;

import java.util.ArrayList;
import java.util.List;

public class Moneda {
	
	private String nombre;
	private String abreviatura;
	private List<Double> cambio = new ArrayList<>();
	
	public Moneda(String nombre, String abreviatura, List<Double> cambio) {
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.cambio = cambio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public List<Double> getCambio() {
		return cambio;
	}

	public void setCambio(List<Double> cambio) {
		this.cambio = cambio;
	}

	@Override
	public String toString() {
		return nombre + " - " + abreviatura;
	}
	
	

}
