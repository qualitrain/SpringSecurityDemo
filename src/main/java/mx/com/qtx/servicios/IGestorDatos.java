package mx.com.qtx.servicios;


import java.util.Set;

import mx.com.qtx.entidades.CatValorSimple;
import mx.com.qtx.entidades.Persona;


public interface IGestorDatos {
	Set<CatValorSimple> leerValoresCatSimple(String tipoValor);
	Set<CatValorSimple> leerValoresCatSimpleConInicio(String tipoValor, String inicioValAlfanumerico);
	Set<Persona> leerPersonas();
	Persona leerPersonaXID(long id);
}
