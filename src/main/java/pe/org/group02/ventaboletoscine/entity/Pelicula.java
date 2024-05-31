package pe.org.group02.ventaboletoscine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;
    private String nombrePelicula;
    private String directorPelicula;
    private String duracionPelicula;
    private String idiomaPelicula;
    @OneToMany(mappedBy = "pelicula")
    @JsonIgnore
    private List<PeliculasGenero> peliculasGeneros;
}
