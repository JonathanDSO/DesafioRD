package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

	Usuario findByIp(String ip);
	
}
