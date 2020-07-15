package mx.com.qtx.test;

import java.util.Date;

import mx.com.qtx.entidades.Persona;
import mx.com.qtx.persistencia.GestorDatosMemoria;
import mx.com.qtx.servicios.IGestorDatos;
import mx.com.qtx.util.Util;

public class TestPersistencia {
	
	public static void main(String[] args) {
		testIGestorDatos();
//		testUtil();
	}

	private static void testUtil() {
		Date fecha = Util.crearFecha(11,2,1968);
		System.out.println(Util.getFechaNacAAAAMMDD(fecha));
	}

	private static void testIGestorDatos() {
		IGestorDatos gestorDatos = new GestorDatosMemoria();
		test_LeerValoresCatSimple( gestorDatos );
		test_leerValoresCatSimpleConInicio( gestorDatos );
		test_LeerPersonas( gestorDatos );
		test_leerPersonaXID( gestorDatos );
	}

	private static void test_leerValoresCatSimpleConInicio(IGestorDatos gestorDatos) {
		System.out.println("========== test_leerValoresCatSimpleConInicio ==============");
		String cadInicio = "An";
		System.out.println("Los nombres que inician con " + cadInicio + " son:");
		gestorDatos.leerValoresCatSimpleConInicio("nombre",cadInicio)
		           .forEach( vcs -> System.out.println(vcs.getValorAlfa()));
		System.out.println("Los apellidos que inician con " + cadInicio + " son:");
		gestorDatos.leerValoresCatSimpleConInicio("apellido",cadInicio)
		           .forEach( vcs -> System.out.println(vcs.getValorAlfa()));

		
	}

	private static void test_leerPersonaXID(IGestorDatos gestorDatos) {
		System.out.println("========== test_leerPersonaXID ==============");		
		Persona p = gestorDatos.leerPersonaXID(5);
		System.out.println(p.toStringFancy());
		System.out.println(p);
	}

	private static void test_LeerPersonas(IGestorDatos gestorDatos) {
		System.out.println("========== test_LeerPersonas ==============");		
		gestorDatos.leerPersonas()
		           .stream()		           
		           .sorted( (p1, p2) -> p1.getIdPersona() < p2.getIdPersona() ? -1 : 1 )
		           .forEach( p -> System.out.println(p.toStringFancy()) );
	}

	private static void test_LeerValoresCatSimple(IGestorDatos gestorDatos) {
		System.out.println("========== Nombres ==============");
		gestorDatos.leerValoresCatSimple("nombre")
		           .stream()
		           .map( x-> x.getValorAlfa())
		           .sorted()
		           .forEach( x-> System.out.println(x) );
		
		System.out.println("========== Apellidos ==============");
		gestorDatos.leerValoresCatSimple("apellido")
		           .stream()
		           .map( x-> x.getValorAlfa())
		           .sorted()
		           .forEach( x-> System.out.println(x) );
	}
	
}
