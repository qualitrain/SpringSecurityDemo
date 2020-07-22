package mx.com.qtx.persistencia;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import mx.com.qtx.entidades.CatValorSimple;
import mx.com.qtx.entidades.Persona;
import mx.com.qtx.servicios.IGestorDatos;

@Primary
@Repository
public class GestorDatosJpaRepository implements IGestorDatos {
	@Autowired
	IPersonaRepository repo;
	
	@Autowired
	ICatValorSimpleRepository repoCat;

	@Override
	public Set<CatValorSimple> leerValoresCatSimple(String tipoValor) {
		Set<CatValorSimple> setValores = new HashSet<>();
		setValores.addAll(repoCat.findByTipoValor(tipoValor));
		return setValores;
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

	@Override
	public Set<Persona> leerPersonas() {
		return new HashSet<Persona> (repo.findAll());
	}

	@Override
	public Persona leerPersonaXID(long id) {
		Optional<Persona> opPersona = repo.findById(id);
		if(opPersona.isPresent())
			return opPersona.get();
		return null;
	}

}
