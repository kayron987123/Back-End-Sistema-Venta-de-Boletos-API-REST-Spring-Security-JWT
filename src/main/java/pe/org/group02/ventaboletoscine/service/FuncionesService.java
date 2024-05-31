package pe.org.group02.ventaboletoscine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.org.group02.ventaboletoscine.entity.Funciones;
import pe.org.group02.ventaboletoscine.repository.FuncionesRepository;
import pe.org.group02.ventaboletoscine.response.Response;
import pe.org.group02.ventaboletoscine.response.ResponseConsultas;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("function")
public class FuncionesService {

    @Autowired
    private FuncionesRepository funcionesRepository;

    @PostMapping("/add")
    public Response addFuncione(@RequestBody Funciones funciones){
        if(funciones.getIdFuncion() != null){
            return new Response(401, "Id no permitido");
        }
        funcionesRepository.save(funciones);
        return new Response(200, null);
    }

    @GetMapping("/find")
    public ResponseConsultas<Funciones> findById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        Iterable<Funciones> funciones = null;
        if (id > 0) {
            funciones = funcionesRepository.findAllById(List.of(id));
        } else if (id == 0) {
            funciones = funcionesRepository.findAll();
        } else {
            return new ResponseConsultas<Funciones>(404, "not found id", null);
        }

        return new ResponseConsultas<Funciones>( 200 , null, funciones);
    }
    @PostMapping("/update")
    public Response updateFuncion(@RequestBody Funciones funciones){
        if(!funcionesRepository.findById(funciones.getIdFuncion()).isPresent()){
            return new Response(404, "Not Found");
        }

        funcionesRepository.save(funciones);
        return new Response(200, null);
    }

    @PostMapping("/delete")
    public Response deleteFuncion(@RequestBody Funciones funciones) {
        if (!funcionesRepository.findById(funciones.getIdFuncion()).isPresent()) {
            return new Response(404, "Not Found");
        }
        funcionesRepository.delete(funciones);
        return new Response(200, null);
    }
}
