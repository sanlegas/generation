package generation.test.modulo2.controllers;

import generation.test.modulo2.exceptions.TurmaNotFound;
import generation.test.modulo2.persistence.entity.Turma;
import generation.test.modulo2.persistence.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping()
    public List<Turma> getAllTurma( @RequestParam(required = false) String name ){
        if (name == null){
            return turmaRepository.findAll();

        }else{
            return turmaRepository.getByDescricrao(name);
        }
    }

    @GetMapping("{id}")
    public Turma    getByIdTurma( @PathVariable("id") Long id){
        Optional<Turma> turma =  turmaRepository.findById(id);
        if (turma.isPresent()){
            return turma.get();
        }else{
           throw new TurmaNotFound();
        }
    }

    /* NO ERA POSIBLE MAPEAR SOBRE EL PATH / Y EL METODO GET PORQUE YA ESTA DEFINIDO PARA BUSCAR TODOS LOS "TURMA"
        EN CAMBIO SE AGREGÓ COMO PARAMETRO NAME EN LA BUSQUEDA POR TODOS "TURMA"
    @GetMapping("")
    public Turma getByNameTurma( @RequestParam("name") String name ){
        return turmaRepository.getByDescricrao(name);
    }*/

    @PostMapping()
    public void postTurma( @RequestBody Turma turma){
        turmaRepository.save(turma);
    }

    @PutMapping()
    public void putTurma( @RequestBody Turma turma){
        turmaRepository.save(turma);
    }

    @DeleteMapping("{id}")
    public void deleteTurma(@PathVariable("id") Long id){
        turmaRepository.deleteById(id);
    }
}
