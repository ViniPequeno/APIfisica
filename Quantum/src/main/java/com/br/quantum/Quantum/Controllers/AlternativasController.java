/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Alternativas;
import com.br.quantum.Quantum.Repositorio.AlternativasRepositorio;
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
public class AlternativasController {

    @Autowired
    AlternativasRepositorio alternativasRepositorio;

    @GetMapping("/alternativas")
    public List<Alternativas> getAllAlternativass() {
        return alternativasRepositorio.findAll();
    }

    @GetMapping("/alternativas/{id}")
    public Alternativas getAlternativasById(@PathVariable(value = "id") Character alternativaId) {
        return (Alternativas) alternativasRepositorio.findById(alternativaId)
                .orElseThrow(() -> new ResourceNotFoundException("Alternativas", "id", alternativaId));
    }

    @PostMapping("/alternativas")
    public Alternativas createAlternativas(@Valid @RequestBody Alternativas alternativa) {
        return alternativasRepositorio.save(alternativa);
    }

    // Update a Alternativas
    @PutMapping("/alternativas/{id}")
    public Alternativas updateAlternativas(@PathVariable(value = "id") Character alternativaId,
            @Valid @RequestBody Alternativas alternativaDetails) {

        Alternativas alternativa = alternativasRepositorio.findById(alternativaId)
                .orElseThrow(() -> new ResourceNotFoundException("Alternativas", "alternativa", alternativaId));

        alternativa.setResposta(alternativaDetails.getResposta());

        Alternativas alternativaUpdate = alternativasRepositorio.save(alternativa);
        return alternativaUpdate;
    }

    @DeleteMapping("/alternativas/{id}")
    public ResponseEntity<?> deleteAlternativas(@PathVariable(value = "id") Character alternativaId) {
        Alternativas alternativa = alternativasRepositorio.findById(alternativaId)
                .orElseThrow(() -> new ResourceNotFoundException("Alternativas", "id", alternativaId));

        alternativasRepositorio.delete(alternativa);

        return ResponseEntity.ok().build();
    }
}
