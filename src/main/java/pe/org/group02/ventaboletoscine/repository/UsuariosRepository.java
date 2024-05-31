package pe.org.group02.ventaboletoscine.repository;

import org.springframework.data.repository.CrudRepository;
import pe.org.group02.ventaboletoscine.entity.Usuarios;

public interface UsuariosRepository extends CrudRepository<Usuarios, Integer> {
    Usuarios findByUsuario(String usuario);
}
