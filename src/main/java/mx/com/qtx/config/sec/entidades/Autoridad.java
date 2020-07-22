package mx.com.qtx.config.sec.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Autoridad {
	@Id
	@Column(name = "aut_id")
	private int id;
	@Column(name = "aut_nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="aut_nombre_usr")
	private Usuario usuario;
	
	public Autoridad() {
		super();
	}

	public Autoridad(int id, String nombre, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Autoridad [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + "]";
	}

}
