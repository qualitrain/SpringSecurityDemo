package mx.com.qtx.servicios;

public class PersistenciaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String nombreTabla;
	private String ubicacion;
	
	public PersistenciaException(String msg) {
		super(msg);
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}