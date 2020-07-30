package mx.com.qtx.sec.rest;

public interface IResultadoOperacion {
	public Object getObjResultadoOk();
	public void setObjResultadoOk(Object objResultadoOk);
	public boolean todoOk();
	public void agregarError(int codError, String adicion);
	public String getResumenErrores();
}
