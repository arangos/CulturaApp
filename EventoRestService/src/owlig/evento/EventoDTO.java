package owlig.evento;

import org.joda.time.DateTime;

public class EventoDTO {
	
	private int idEvento;
	private String NombreEvento;
	private String TipoEvento;
	private String lugarEvento;
	private int cuposEvento;
	private String descripcionEvento;
	private int calificacionEvento;
	private int numeroBusquedasEvento;
	private String fotoEvento;
	private DateTime fechaEvento;
	
	public EventoDTO(String nombreEvento, String tipoEvento, String lugarEvento, int cuposEvento,String descripcionEvento, int calificacionEvento, int numeroBusquedasEvento, String fotoEvento,DateTime fechaEvento) {
		super();
		NombreEvento = nombreEvento;
		TipoEvento = tipoEvento;
		this.lugarEvento = lugarEvento;
		this.cuposEvento = cuposEvento;
		this.descripcionEvento = descripcionEvento;
		this.calificacionEvento = calificacionEvento;
		this.numeroBusquedasEvento = numeroBusquedasEvento;
		this.fotoEvento = fotoEvento;
		this.fechaEvento = fechaEvento;
	}
	
	public EventoDTO(){
		
	}
	
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getNombreEvento() {
		return NombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		NombreEvento = nombreEvento;
	}
	public String getTipoEvento() {
		return TipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		TipoEvento = tipoEvento;
	}
	public String getLugarEvento() {
		return lugarEvento;
	}
	public void setLugarEvento(String lugarEvento) {
		this.lugarEvento = lugarEvento;
	}
	public int getCuposEvento() {
		return cuposEvento;
	}
	public void setCuposEvento(int cuposEvento) {
		this.cuposEvento = cuposEvento;
	}
	public String getDescrpcionEvento() {
		return descripcionEvento;
		
		
	}
	public void setDescrpcionEvento(String descrpcionEvento) {
		this.descripcionEvento = descrpcionEvento;
	}
	public int getCalificacionEvento() {
		return calificacionEvento;
	}
	public void setCalificacionEvento(int calificacionEvento) {
		this.calificacionEvento = calificacionEvento;
	}
	public int getNumeroBusquedasEvento() {
		return numeroBusquedasEvento;
	}
	public void setNumeroBusquedasEvento(int numeroBusquedasEvento) {
		this.numeroBusquedasEvento = numeroBusquedasEvento;
	}
	public String getFotoEvento() {
		return fotoEvento;
	}
	public void setFotoEvento(String fotoEvento) {
		this.fotoEvento = fotoEvento;
	}
	public DateTime getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(DateTime fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	
	

}
