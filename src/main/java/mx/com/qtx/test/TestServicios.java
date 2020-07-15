package mx.com.qtx.test;

import mx.com.qtx.servicios.ServicioCatalogos;
import mx.com.qtx.servicios.ServicioPersonas;

public class TestServicios {

	public static void main(String[] args) {
		testServicioCatalogos();
		testServicioPersonas();
	}

	private static void testServicioPersonas() {
		ServicioPersonas servicio = new ServicioPersonas();
		servicio.getPersonasTodas()
		        .forEach(p -> System.out.println(p.toStringFancy()) );
	}

	private static void testServicioCatalogos() {
		ServicioCatalogos servicio = new ServicioCatalogos();
		
		servicio.getApellidosSugeridos("Ba").forEach( x -> System.out.println(x) );
		
		servicio.getNombresSugeridos("An").forEach( x -> System.out.println(x) );
	}

}
