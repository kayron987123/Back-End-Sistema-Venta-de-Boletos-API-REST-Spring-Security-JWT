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
@Table(name = "peliculas_generos")
public class PeliculasGenero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPeligen;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;
    @ManyToOne(cascade = {CascadeType.DETACH })
    @JoinColumn(name = "id_genero")
    private Genero genero;
    @OneToMany(mappedBy = "idPeligen")
    @JsonIgnore
    private List<Funciones> funciones;
}