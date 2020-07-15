package mx.com.qtx.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
	public static Date crearFecha(int dia, int mes, int anio) {
		GregorianCalendar gc = new GregorianCalendar(anio, mes - 1, dia);
		return gc.getTime();
	}
	
	public static String getFechaNacAAAAMMDD(Date fecha) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		return String.format("%4d-%02d-%02d", gc.get(GregorianCalendar.YEAR), 
				                              gc.get(GregorianCalendar.MONTH) + 1, 
				                              gc.get(GregorianCalendar.DAY_OF_MONTH) );
	}

}
