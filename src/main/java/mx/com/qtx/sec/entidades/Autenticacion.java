package mx.com.qtx.sec.entidades;

public class Autenticacion {
	private String nombreUsuario;
	private String password;

	public Autenticacion(String nombreUsuario, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	public Autenticacion() {
		super();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Autenticacion [nombreUsuario=" + nombreUsuario + ", password=" + password + "]";
	}
	
}
