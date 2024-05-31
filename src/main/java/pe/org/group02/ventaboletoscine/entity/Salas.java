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
@Table(name = "salas")
public class Salas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;
    private String numeroSala;
    private Boolean disponibilidadSala;
    private Integer numeroButacas;
    @ManyToOne
    @JoinColumn(name = "id_cine")
    private Cine idCine;
    @OneToMany(mappedBy = "idSala")
    @JsonIgnore
    private List<Funciones> funciones;
}
