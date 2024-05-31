package pe.org.group02.ventaboletoscine.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comprobante_pago")
public class ComprobantePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComprobante;
    @ManyToOne
    @JoinColumn(name = "id_boleto")
    private Boletos idBoleto;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios idUsuario;
    private Double montoTotal;
    private String metodoPago;
}
