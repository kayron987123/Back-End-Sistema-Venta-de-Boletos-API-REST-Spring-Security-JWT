package pe.org.group02.ventaboletoscine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.org.group02.ventaboletoscine.entity.Boletos;
import pe.org.group02.ventaboletoscine.entity.Funciones;
import pe.org.group02.ventaboletoscine.repository.BoletosRepository;
import pe.org.group02.ventaboletoscine.response.Response;
import pe.org.group02.ventaboletoscine.response.ResponseConsultas;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class BoletosService {
    @Autowired
    private BoletosRepository boletosRepository;

    @PostMapping("/add")
    public Response addBoleto(@RequestBody Boletos boletos){
        if(boletos.getIdBoleto() != null){
            return new Response(401, "Id no permitido");
        }
        boletosRepository.save(boletos);
        return new Response(200, null);
    }

    @GetMapping("/find")
    public ResponseConsultas<Boletos> findById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        Iterable<Boletos> boletos = null;
        if (id > 0) {
            boletos = boletosRepository.findAllById(List.of(id));
        } else if (id == 0) {
            boletos = boletosRepository.findAll();
        } else {
            return new ResponseConsultas<Boletos>(404, "not found id", null);
        }

        return new ResponseConsultas<Boletos>( 200 , null, boletos);
    }

    @PostMapping("/update")
    public Response updateBoleto(@RequestBody Boletos boletos){
        if(!boletosRepository.findById(boletos.getIdBoleto()).isPresent()){
            return new Response(404, "Not Found");
        }

        boletosRepository.save(boletos);
        return new Response(200, null);
    }

    @PostMapping("/delete")
    public Response deleteBoleto(@RequestBody Boletos boletos) {
        if (!boletosRepository.findById(boletos.getIdBoleto()).isPresent()) {
            return new Response(404, "Not Found");
        }
        boletosRepository.delete(boletos);
        return new Response(200, null);
    }
}
