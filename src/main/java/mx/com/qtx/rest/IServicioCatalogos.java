package mx.com.qtx.rest;

import java.util.List;

public interface IServicioCatalogos {
	public List<String> getNombresSugeridos(String inicioNombre);
	public List<String> getApellidosSugeridos(String inicioNombre);
}
