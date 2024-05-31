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
@Table(name = "cine")
public class Cine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCine;
    private String nombre;
    private String direccion;
    private String ciudad;
    @OneToMany(mappedBy = "idCine")
    @JsonIgnore
    private List<Salas> salas;
    @OneToMany(mappedBy = "idCine")
    @JsonIgnore
    private List<Empleados> empleados;
}
