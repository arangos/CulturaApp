package owlig.lugar;

public class LugarDTO {
	
	private int idLugar;
	private String nombre;
	private String direccion;
	private String coordenadas;
	private String foto;
	private int calificacion;
	private int cupos;
	
	public LugarDTO(){
	}
	
	public LugarDTO(String nombre, String direccion, String coordenadas, String foto, int calificacion, int cupos) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.coordenadas = coordenadas;
		this.foto = foto;
		this.calificacion = calificacion;
		this.cupos = cupos;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public int getCupos() {
		return cupos;
	}
	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	public int getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

}
