package owlig.reserva;

import org.joda.time.DateTime;

public class ReservaDTO {
	
	private int idReserva;
	private String lugarReserva;
	private DateTime fechareserva;
	private String eventoReserva;
	private String Usuario;
	//3 esatdos : 1.activa 2.cancelada 3.redimida
	private String Estado;
	
	public ReservaDTO(){			
	}
	
	public ReservaDTO(int idReserva, String lugarReserva, DateTime fechareserva, String eventoReserva, String usuario,
			String estado) {
		super();
		this.idReserva = idReserva;
		this.lugarReserva = lugarReserva;
		this.fechareserva = fechareserva;
		this.eventoReserva = eventoReserva;
		Usuario = usuario;
		Estado = estado;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getLugarReserva() {
		return lugarReserva;
	}
	public void setLugarReserva(String lugarReserva) {
		this.lugarReserva = lugarReserva;
	}
	public DateTime getFechareserva() {
		return fechareserva;
	}
	public void setFechareserva(DateTime fechareserva) {
		this.fechareserva = fechareserva;
	}
	public String getEventoReserva() {
		return eventoReserva;
	}
	public void setEventoReserva(String eventoReserva) {
		this.eventoReserva = eventoReserva;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}	
	
}
