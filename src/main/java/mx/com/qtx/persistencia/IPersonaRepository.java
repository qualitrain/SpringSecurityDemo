package mx.com.qtx.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.qtx.entidades.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Long> {

}
