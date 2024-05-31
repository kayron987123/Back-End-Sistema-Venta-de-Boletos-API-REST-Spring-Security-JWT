package pe.org.group02.ventaboletoscine.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.org.group02.ventaboletoscine.entity.Genero;
import pe.org.group02.ventaboletoscine.entity.Pelicula;
import pe.org.group02.ventaboletoscine.entity.PeliculasGenero;

public interface PeliculasGeneroRepository extends CrudRepository<PeliculasGenero, Integer> {

    @Transactional()
    @Query("SELECT pg FROM PeliculasGenero pg WHERE pg.pelicula.idPelicula = :idMovie")
    PeliculasGenero getMovie(Integer idMovie);
    @Transactional()
    @Query("SELECT pg FROM PeliculasGenero pg WHERE pg.genero.idGenero = :idGender")
    Iterable<PeliculasGenero> getGender(Integer idGender);
}
