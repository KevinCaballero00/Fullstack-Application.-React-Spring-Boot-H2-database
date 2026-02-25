package com.kevcab.full_stack.controller;

import com.kevcab.full_stack.model.Nota;
import com.kevcab.full_stack.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "http://localhost:5173") // Permitir solicitudes desde el frontend
public class NotaController {

    @Autowired
    NotaRepository notaRepository;

    //Obtener todas las notas
    @GetMapping
    public List<Nota> obtenerNotas(){
        return notaRepository.findAll();
    }

    //Crear una nueva nota
    @PostMapping
    public Nota crearNota(@RequestBody Nota nota){
        return notaRepository.save(nota);
    }

    //Actualizar una nota existente
    @PutMapping("/{id}")
    public Nota actualizarNota(@PathVariable Long id, @RequestBody Nota notaDetalles){
        Optional<Nota> optionalNota = notaRepository.findById(id);

        if(optionalNota.isPresent()){
            Nota nota = optionalNota.get();
            nota.setTitulo(notaDetalles.getTitulo());
            nota.setDescripcion(notaDetalles.getDescripcion());

            return notaRepository.save(nota);
        } else return null;
    }

    //Eliminar una nota
    @DeleteMapping("/{id}")
    public void eliminarNota(@PathVariable Long id) {
        notaRepository.deleteById(id);
    }
}
