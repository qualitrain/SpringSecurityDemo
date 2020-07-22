package mx.com.qtx.config.sec.prov.ud;

import mx.com.qtx.config.sec.entidades.Usuario;

public interface IGestorDatosSec {
	public Usuario getUsuarioXID(String nombreUsuario);

}
