package mx.com.qtx.sec.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@Column(name = "usr_nombre")
	private String nombre;
	@Column(name = "usr_paswd")
	private String password;
	@Column(name = "usr_habilitado")
	private boolean estaHabilitado;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)	
	private Set<Autoridad> autoridades;
	
	public Usuario() {
	}
	public Usuario(String nombre, String password, boolean estaHabilitado) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.estaHabilitado = estaHabilitado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean estaHabilitado() {
		return estaHabilitado;
	}
	public void setEstaHabilitado(boolean estaHabilitado) {
		this.estaHabilitado = estaHabilitado;
	}
	public Set<Autoridad> getAutoridades() {
		return autoridades;
	}
	public void setAutoridades(Set<Autoridad> autoridades) {
		this.autoridades = autoridades;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + ", estaHabilitado=" + estaHabilitado + "]";
	}
	
	
}
