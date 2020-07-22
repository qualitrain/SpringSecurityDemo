package mx.com.qtx.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.qtx.entidades.CatValorSimple;

public interface ICatValorSimpleRepository extends JpaRepository<CatValorSimple, Long> {
	List<CatValorSimple> findByTipoValor(String tipoValor);

}
