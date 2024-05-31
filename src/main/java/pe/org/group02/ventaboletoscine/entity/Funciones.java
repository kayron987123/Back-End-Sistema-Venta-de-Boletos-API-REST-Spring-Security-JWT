package pe.org.group02.ventaboletoscine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "funciones")
public class Funciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncion;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "America/Lima")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "id_peligen")
    private PeliculasGenero idPeligen;
    @ManyToOne
    @JoinColumn(name = "id_sala")
    private Salas idSala;
    @OneToMany(mappedBy = "idFuncion")
    @JsonIgnore
    private List<Boletos> boletos;
}
