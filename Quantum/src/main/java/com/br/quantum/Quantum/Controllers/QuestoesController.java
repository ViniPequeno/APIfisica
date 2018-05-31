/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Questoes;
import com.br.quantum.Quantum.Repositorio.QuestoesRepositorio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestoesController {

    @Autowired
    QuestoesRepositorio questoesRepositorio;
    
    @GetMapping("/questoes")
    public List<Questoes> getAllQuestoess() {
        return questoesRepositorio.findAll();
    }
    
    @GetMapping("/questoes/{id}")
    public Questoes getQuestoesById(@PathVariable(value = "id") Long questoesId) {
        return (Questoes) questoesRepositorio.findById(questoesId)
                .orElseThrow(() -> new ResourceNotFoundException("Questoes", "id", questoesId));
    }
    
    @PostMapping("/questoes")
    public Questoes createQuestoes(@Valid @RequestBody Questoes questoes) {
        return questoesRepositorio.save(questoes);
    }

    // Update a Questoes
    @PutMapping("/questoes/{id}")
    public Questoes updateQuestoes(@PathVariable(value = "id") Long questoesId,
            @Valid @RequestBody Questoes questoesDetails) {
        
        Questoes questoes = questoesRepositorio.findById(questoesId)
                .orElseThrow(() -> new ResourceNotFoundException("Questoes", "questoes", questoesId));
        
        questoes.setCodigo(questoesDetails.getCodigo());
        questoes.setEnunciado(questoesDetails.getEnunciado());
        questoes.setRespostaCerta(questoesDetails.getRespostaCerta());
        
        Questoes questoesUpdate = questoesRepositorio.save(questoes);
        return questoesUpdate;
    }
    
    @DeleteMapping("/questoes/{id}")
    public ResponseEntity<?> deleteQuestoes(@PathVariable(value = "id") Long questoesId) {
        Questoes questoes = questoesRepositorio.findById(questoesId)
                .orElseThrow(() -> new ResourceNotFoundException("Questoes", "id", questoesId));
        
        questoesRepositorio.delete(questoes);
        
        return ResponseEntity.ok().build();
    }
}
