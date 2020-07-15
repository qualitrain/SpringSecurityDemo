package mx.com.qtx.persistencia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import mx.com.qtx.entidades.CatValorSimple;
import mx.com.qtx.entidades.Persona;
import mx.com.qtx.servicios.IGestorDatos;
import mx.com.qtx.util.Util;

@Repository
public class GestorDatosMemoria implements IGestorDatos {
	private static String[] nombres = {"Pedro","Juan", "Jaime", "Marcelo", "Marcela", "Graciela",
			"María", "Angélica", "Angélica María", "María Angélica", "Juan Manuel", "Alejandro", "Verónica",
			"Alma", "Delia", "Eugenia", "Eugenio","Silvia", "Carlos", "Cipriano", "Cutberto","Ramón",
			"Victoria","David","Octavio","Dolores","Olga","Vicente","Vicenta","Xóchitl","Jimena","Mauricio",
			"Mauro", "Melisa", "Nancy", "Martín", "Mayela", "Claudia", "Teresa", "Maria Teresa", "Concepción",
			"Guadalupe","Rocío","Mariana","Marina","Fernando","Víctor","Diego", "Leonel", "Antonio",
			"José Luis", "José Antonio", "Alicia", "Patricia", "Patricio", "Mónica", "Beatriz", "Viviana",
			"Jessica", "Arturo", "José Arturo", "Javier", "Xavier", "Alfredo", "José Alfredo", "Manuel",
			"Benjamín", "Bartolo", "Berenice", "Daniel", "Domingo", "Daniela", "Damián", "Diana", "Efrén",
			"Ernesto", "Eduardo", "Enedina", "Rosalía", "Oscar", "Francisco", "Fernanda","Gustavo", "Fabricio",
			"Gonzalo", "Gumaro", "Genaro","Gabriel", "Gabriela", "Rocío", "Dulce", "Héctor", "Heráclito",
			"Horacio", "Aristóteles", "Hortensia","Ignacio","Idolina","Isabel", "Elizabeth", "Iliana",
			"Lorenzo", "Luis", "Elena", "Ernestina", "Luisa", "Liliana", "Lucrecia", "Laura", "Lidia",
			"Norma", "Nora", "Norberto", "Napoleón", "Osvaldo", "Nemesio", "Ricardo","Raúl", "Rosa",
			"Ramiro","Rigoberto","Rigoberta", "Saúl", "Cecilia", "Samantha", "Cinthia","Sonia", "Tomás",
			"Tobías", "Matías","Alejandra", "Alejo", "Alberto", "Aldo", "Adalberto", "Tulio", "Ubaldo",
			"Omar", "Abelardo", "Karina", "Karla","Úrsula", "Joaquín","Hugo", "Uriel","Gualberto","William",
			"Uziel", "Wanda", "Guillermo", "Andrés", "Hernán", "Isidro", "Germán", "Yoko","Yamel","Yazmín",
			"Jazmín","Violeta","Margarita","Azucena","Vanessa", "Zacarías","Zenón","Zeferino","Zara","Sara",
			"Zelma","Zoila","Dante","Efraín","Lázaro","Hipólito","Leopoldo"};
	
	private static String[] apellidos = {"Alves","Badillo","Bobadilla","Bermejo","Septién","Pérez",
			"Bermúdez", "Barrientos", "Vera", "Castillo", "Corte","Chávez", "Moya", "Moyao", "Hernández",
			"Martinez","Vélez", "Manriquez", "Méndez", "Morales","Zuno", "Zapata","Guerra", "Guerrero","Juárez",
			"García","Goya","Herrero", "Herrera", "Iriarte", "Ibarra", "Jara", "Jáuregui", "López","Lima",
			"Limón", "Núñez", "Ovando", "Parra", "Quiroga", "Quiñónez", "Pardo", "Díaz", "Santiago", "Dorantes",
			"Enríquez", "Sabina", "Serrano", "Salinas", "Esqueda", "Blanco", "Sánchez", "Sosa", "Ramírez",
			"Mena", "Ángeles","Antón","Antuna", "Antunes", "Quintero", "Ruíz","Uribe", "Urbán", "Álvarez",
			"Ancira","Abaroa","Argüeyes","Borja","Cruz","De la Paz","Zavala","Zedillo","Zola","Sola","Cedillo",
			"Castro","Castrejon", "Cerón","Sarabia", "Nonell","Dávila","De la Merced","De la Cruz", "De la Colina",
			"Domínguez","Tena","Guerra","Espíndola", "Zanabria","Madrid","Mena","Luque","Fernández",
			"Farfán","Feria","Arroyo","Funes","González", "Gurría", "Garcilaso", "Gómez","Giménez",
			"Lozano","Cantú", "Garza","Hersch","Heinze","Higareda","Morones","Mora","Insunza","Jiménez",
			"Mata","Bizuet","Bautista","Solano","Villegas","Bravo","Abundis","Abud","Falcón","Carballo",
			"Valle","Pimentel","Monroy","Mendoza","Rojas","Rojo","Bárcenas","Navarro","Loera",
			"Elizalde","Navarrete","Sierra","Montoya","Cortés","Cortez","Durán","Loyola","Armas",
			"Ortega","Nolasco","Maldonado","Pineda","Piña","Alfaro","Ortiz","Anaya","Urbano",
			"Corona","Valadés","Valdés","Vaca","Baeza","Tortolero","Arce","Vidal","Aguilar",
			"Zárate","Izcoa", "Jaramillo","Cossío", "Jerez","Luna","Lizárraga","Lara","Landa",
			"Muñiz","Muñante","Ocampo","Oceguera","Olmo","Otero","Poza","Pinto","Quevedo","Retana",
			"Reyes","Rosas","Ríos","Ruvalcaba","Romo","Martino","Yaber","Coss","Guzmán",
			"Terrés","Turrubiates","Polanco","Barriga","Ubaldo","Uréstegui","Aristegui",
			"Obrador","Bojórquez","Yáñez","Ybarra","Yunes"};
	
	private static Map<Long,CatValorSimple> repositorioValoresSimples = crearValoresSimples();
	private static Map<Long,Persona> repositorioPersonas = crearPersonas();
	
	

	private static Map<Long, Persona> crearPersonas() {
		Map<Long, Persona> mapaPersonas = new HashMap<>();
		long i = 1;
		mapaPersonas.put(i, new Persona(i++, "López", "Santiago","Martín Alonso", Util.crearFecha(5, 3, 1999)));
		mapaPersonas.put(i, new Persona(i++, "Mena", "Pérez","Federico", Util.crearFecha(12, 12, 2000)));
		mapaPersonas.put(i, new Persona(i++, "Mora", "Sosa","Silvia", Util.crearFecha(6, 2, 2001)));
		mapaPersonas.put(i, new Persona(i++, "Velázquez", "Cortés","Laura", Util.crearFecha(2, 3, 2000)));
		mapaPersonas.put(i, new Persona(i++, "Aura", "Dávila","José Miguel", Util.crearFecha(5, 10, 1998)));
		mapaPersonas.put(i, new Persona(i++, "Botero", "Moreno","Federico", Util.crearFecha(7, 7, 2001)));
		mapaPersonas.put(i, new Persona(i++, "Yáñez", "Mejía","Daniela", Util.crearFecha(21, 6, 2000)));
		mapaPersonas.put(i, new Persona(i++, "Ohori", "Hernández","Marco Aurelio", Util.crearFecha(17, 9, 1997)));
		mapaPersonas.put(i, new Persona(i++, "Jiménez", "Machuca","Hermilo", Util.crearFecha(16, 10, 1999)));
		mapaPersonas.put(i, new Persona(i++, "Sosa", "Dondé","Benjamín", Util.crearFecha(11, 11, 2001)));
		mapaPersonas.put(i, new Persona(i++, "Vera", "Cruz","Martina", Util.crearFecha(4, 5, 2000)));
		return mapaPersonas;
	}
	
	

	private static Map<Long, CatValorSimple> crearValoresSimples() {
		Map<Long, CatValorSimple> mapaValoresSimples = new HashMap<>();
		agregarNombresA(mapaValoresSimples);
		agregarApellidosA(mapaValoresSimples);
		return mapaValoresSimples;
	}

	private static void agregarApellidosA(Map<Long, CatValorSimple> mapaValoresSimples) {
		for(String apellidoI : apellidos) {
			long id = mapaValoresSimples.size() + 1;
			mapaValoresSimples.put( id, new CatValorSimple(id, "apellido", apellidoI) );
		}
	}

	private static void agregarNombresA(Map<Long, CatValorSimple> mapaValoresSimples) {
		for(String nombreI : nombres) {
			long id = mapaValoresSimples.size() + 1;
			mapaValoresSimples.put(id, new CatValorSimple(id, "nombre", nombreI) );
		}
	}
	
	//--------------------------------------------------------------------------------------------

	@Override
	public Set<Persona> leerPersonas() {
		
		LinkedHashSet<Persona> setPersonas =  new LinkedHashSet<>();
		GestorDatosMemoria.repositorioPersonas
		                  .values()
		                  .stream()
		                  .sorted( (p1,p2)-> p1.getIdPersona() < p2.getIdPersona() ? -1 : 1 )
		                  .forEach( p-> setPersonas.add(p));
		return setPersonas;
	}
	@Override
	public Set<CatValorSimple> leerValoresCatSimple(String tipoValor) {
		Set<CatValorSimple> setValores = new HashSet<>();
		GestorDatosMemoria.repositorioValoresSimples
		                  .values()
		                  .stream()
		                  .filter( val -> val.getTipoValor().equalsIgnoreCase(tipoValor) )
		                  .forEach( valI -> setValores.add(valI) );
		return setValores;
	}



	@Override
	public Persona leerPersonaXID(long id) {
		return GestorDatosMemoria.repositorioPersonas.get(id);
	}



	@Override
	public Set<CatValorSimple> leerValoresCatSimpleConInicio(String tipoValor, String inicioValAlfanumerico) {
		LinkedHashSet<CatValorSimple> setValoresQueInicianCon = new LinkedHashSet<>();
		this.leerValoresCatSimple(tipoValor)
		                         .stream()
		                         .filter( cvsI -> cvsI.getValorAlfa().startsWith(inicioValAlfanumerico) )
		                         .sorted( (cvs1,cvs2) -> cvs1.getValorAlfa().compareTo(cvs2.getValorAlfa()) )
		                         .forEach( cvsI -> setValoresQueInicianCon.add(cvsI) );
		 return setValoresQueInicianCon;                        
	}

}
