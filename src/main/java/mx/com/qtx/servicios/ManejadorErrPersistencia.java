package mx.com.qtx.servicios;

import java.sql.SQLException;
import java.util.Map;

public class ManejadorErrPersistencia {
	public static PersistenciaException crearEx(Map<String,String> detEx,
			Exception ex){
		PersistenciaException pex = new PersistenciaException(detEx.get("msg"));
		pex.setNombreTabla(detEx.get("tabla"));
		pex.setUbicacion(detEx.get("ubicacion"));
		pex.initCause(ex);
		return pex;
	}
	public static String getDescripcionDetallada(PersistenciaException pex){
		StringBuilder sb = new StringBuilder();
		Throwable causa = pex.getCause();
		sb.append(pex.getMessage() + "\n")
		  .append("Excepcion: " + pex.getClass().getName() + "\n" )
		  .append("Ubicacion: " + pex.getUbicacion() + "\n")
		  .append("Tabla: " + pex.getNombreTabla() + "\n")
		  ;
		while(causa != null){
			sb.append("\nExcepcion Causa : " + causa.getClass().getName() + "\n")
			  .append("Mensaje de excepci�n causa: " + causa.getMessage() + "\n");
			if(causa instanceof SQLException){
				SQLException sqlEx = (SQLException) causa;
				sb.append("Error code:" + sqlEx.getErrorCode() + "\n")
				  .append("SQLState: " + sqlEx.getSQLState() + "\n");
			}
			causa = causa.getCause();
		}

		return sb.toString();
	}

}
