package mx.com.qtx.config.sec.prov.ud.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.qtx.config.sec.entidades.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,String>{

}
