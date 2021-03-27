package generation.test.modulo2.controllers;

import generation.test.modulo2.exceptions.ParticipanteNotFound;
import generation.test.modulo2.persistence.entity.Participante;
import generation.test.modulo2.persistence.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {
    @Autowired
    ParticipanteRepository  participanteRepository;

    @GetMapping()
    public List<Participante> getAllParticipante(@RequestParam(required = false) String name ){
        if (name == null){
            return participanteRepository.findAll();
        }else{
            return participanteRepository.getByNome(name);
        }
    }

    @GetMapping("{id}")
    public Participante  getByIdParticipante( @PathVariable("id") Long id){
        Optional<Participante> participante =  participanteRepository.findById(id);
        if (participante.isPresent()){
            return participante.get();
        }else{
            throw new ParticipanteNotFound();
        }
    }

    /* NO ERA POSIBLE MAPEAR SOBRE EL PATH / Y EL METODO GET PORQUE YA ESTA DEFINIDO PARA BUSCAR TODOS LOS "PARTICIPANTE"
        EN CAMBIO SE AGREGÓ COMO PARAMETRO NAME EN LA BUSQUEDA POR TODOS "PARTICIPANTE"
    @GetMapping("")
    public Participante getByNameParticipante( @RequestParam("name") String name ){
        return participanteRepository.getByName(name);
    }*/


    @PostMapping()
    public void postParticipante( @RequestBody Participante participante){
        participanteRepository.save(participante);
    }

    @PutMapping()
    public void putParticipante( @RequestBody  Participante participante){
        participanteRepository.save(participante);
    }

    @DeleteMapping("{id}")
    public void deleteParticipante(@PathVariable("id") Long id){
        participanteRepository.deleteById(id);
    }

}
