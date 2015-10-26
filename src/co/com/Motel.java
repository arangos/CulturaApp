
package co.com;

public class Motel {
	
	private String NombreMotel;
	private String Habitacion;
	private double Precio;
	private int capacidad;
	private String descripcion;
	
	public Motel(String nombreMotel, String habitacion, double precio,int capacidad, String descripcion) {
		super();
		NombreMotel = nombreMotel;
		Habitacion = habitacion;
		Precio = precio;
		this.capacidad = capacidad;
		this.descripcion = descripcion;
	}

	public String getNombreMotel() {
		return NombreMotel;
	}

	public String getHabitacion() {
		return Habitacion;
	}

	public double getPrecio() {
		return Precio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	

}
