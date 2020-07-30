package mx.com.qtx.sec.servicios;

import java.util.HashMap;
import java.util.Map;

import mx.com.qtx.sec.rest.IResultadoOperacion;

public class ResultadoAutenticacion implements IResultadoOperacion{
	public static String descError[] = {
			"Ok",
			"Las Credenciales usadas son equivocadas: ",
			"El Usuario está Inhabilitado ",
			"La cuenta ha sido bloqueda ",
			"Error subyacente: "
	};
	public static final int OPERACION_EXITOSA             = 0;
	public static final int ERR_CREDENCIALES_EQUIVOCADAS  = 1;
	public static final int ERR_USUARIO_INHABILITADO	  = 2;
	public static final int ERR_CTA_BLOQUEDA	  		  = 3;
	public static final int ERR_GENERICO	  		      = 4;
	
	private boolean todoOk = true;
	private Map<Integer, String> errores;
	private Object objResultadoOk;

	public ResultadoAutenticacion() {
		super();
		this.errores = new HashMap<>();
	}
	public Object getObjResultadoOk() {
		return objResultadoOk;
	}
	public void setObjResultadoOk(Object objResultadoOk) {
		this.objResultadoOk = objResultadoOk;
	}
	public boolean todoOk() {
		return this.todoOk;
	}
	public void agregarError(int codError, String adicion) {
		this.todoOk = false;
		String descripcionErr = this.errores.getOrDefault(codError, descError[codError]);
		this.errores.put(codError, descripcionErr + adicion + " ");
	}
	public String getResumenErrores() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Integer llave : this.errores.keySet()) {
			sb.append(llave)
			  .append(" ->")
			  .append(this.errores.get(llave))
			  .append(", ");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}
