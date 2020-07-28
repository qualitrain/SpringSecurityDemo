package mx.com.qtx.sec.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.qtx.sec.entidades.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,String>{

}
