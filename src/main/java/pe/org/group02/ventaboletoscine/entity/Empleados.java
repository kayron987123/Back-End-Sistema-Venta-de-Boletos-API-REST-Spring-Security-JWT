package pe.org.group02.ventaboletoscine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;
    private String nombreEmpleado;
    private String correoEmpleado;
    private String telefonoEmpleado;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Roles idRol;
    @ManyToOne
    @JoinColumn(name = "id_cine")
    private Cine idCine;
    @OneToMany(mappedBy = "idEmpleado", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Usuarios> usuarios;
}
