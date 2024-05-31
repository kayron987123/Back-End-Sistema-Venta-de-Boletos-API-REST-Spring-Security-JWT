package pe.org.group02.ventaboletoscine.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.org.group02.ventaboletoscine.entity.PeliculasGenero;
import pe.org.group02.ventaboletoscine.repository.PeliculasGeneroRepository;
import pe.org.group02.ventaboletoscine.response.Response;
import pe.org.group02.ventaboletoscine.response.ResponseConsultas;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class PeliculasGeneroService {
    @Autowired
    private PeliculasGeneroRepository peliculasGeneroRepository;
    private final Integer noId = 0;

    @PostMapping("/add")
    public Response add(@RequestBody PeliculasGenero pg){
        if(pg == null) return new Response(422, null);
        peliculasGeneroRepository.save(pg);
        return new Response(200, null);
    }

    @GetMapping("/find")
    public ResponseConsultas<PeliculasGenero> findByIds(@RequestParam(required = false)  Integer idMovie,@RequestParam(required = false)  Integer idGender ) {
        List<PeliculasGenero> peliculasGeneros = new ArrayList<PeliculasGenero>();
        PeliculasGenero moviesGenders= null;
        if (idMovie != null && idGender ==null){
            moviesGenders = peliculasGeneroRepository.getMovie(idMovie);
            if(moviesGenders == null) return new ResponseConsultas(404, "Not Found" , null);
            peliculasGeneros.add(moviesGenders);

        }
        if (idGender != null){
            peliculasGeneros =(List<PeliculasGenero>)peliculasGeneroRepository.getGender(idGender);
            if(peliculasGeneros.size() == 0 ) return new ResponseConsultas(404, "Not Found" , null);
        }
        return new ResponseConsultas<PeliculasGenero>( 200 , null, peliculasGeneros);
    }

    @PatchMapping("/update")
    public Response updateMovie(@RequestBody PeliculasGenero peliculasGenero){
        PeliculasGenero moviesGenders= null;
        List<PeliculasGenero> peliculasGeneros = new ArrayList<PeliculasGenero>();

        if (peliculasGenero.getGenero() != null){
            peliculasGeneros  = (List<PeliculasGenero>)peliculasGeneroRepository.getGender(peliculasGenero.getGenero().getIdGenero());
            if(peliculasGeneros.size() == 0) return new Response(404, "Not Found 1 " );

        }
        if (peliculasGenero.getPelicula() != null){
            moviesGenders =peliculasGeneroRepository.getMovie(peliculasGenero.getPelicula().getIdPelicula());
            if(moviesGenders == null) return new Response(404, "Not Found 2 " );
            peliculasGeneroRepository.save(peliculasGenero);
        }

        return new Response(200, null);
    }

    @PostMapping("/delete")
    public Response delete(@RequestParam  Integer idMovie,@RequestParam  Integer idGender) {
        PeliculasGenero moviesGenders= null;
        List<PeliculasGenero> peliculasGeneros = new ArrayList<PeliculasGenero>();

        if (idMovie != null){
            moviesGenders = peliculasGeneroRepository.getMovie(idMovie);
            if(moviesGenders == null)
                return new Response(404, "Not Found" );

        }
        if (idGender != null){
            peliculasGeneros = (List<PeliculasGenero>) peliculasGeneroRepository.getGender(idGender);
            if(moviesGenders == null)
                return new Response(404, "Not Found" );
        }
        peliculasGeneroRepository.delete(moviesGenders);
        return new Response(200, null);
    }
}
