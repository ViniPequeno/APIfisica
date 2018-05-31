/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Conceito;
import com.br.quantum.Quantum.Repositorio.ConceitoRepositorio;
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
public class ConceitoController {
    
    @Autowired
    ConceitoRepositorio conceitoRepositorio;
    
    @GetMapping("/conceitos")
    public List<Conceito> getAllConceitos() {
        return conceitoRepositorio.findAll();
    }
    
    @GetMapping("/conceitos/{id}")
    public Conceito getConceitoById(@PathVariable(value = "id") Long conceitoId) {
        return (Conceito) conceitoRepositorio.findById(conceitoId)
                .orElseThrow(() -> new ResourceNotFoundException("Conceito", "id", conceitoId));
    }
    
    @PostMapping("/conceitos")
    public Conceito createConceito(@Valid @RequestBody Conceito conceito) {
        return conceitoRepositorio.save(conceito);
    }

    // Update a Conceito
    @PutMapping("/conceitos/{id}")
    public Conceito updateConceito(@PathVariable(value = "id") Long conceitoId,
            @Valid @RequestBody Conceito conceitoDetails) {
        
        Conceito conceito = conceitoRepositorio.findById(conceitoId)
                .orElseThrow(() -> new ResourceNotFoundException("Conceito", "conceito", conceitoId));
        
        conceito.setSignificado(conceitoDetails.getSignificado());
        
        Conceito conceitoUpdate = conceitoRepositorio.save(conceito);
        return conceitoUpdate;
    }
    
    @DeleteMapping("/conceitos/{id}")
    public ResponseEntity<?> deleteConceito(@PathVariable(value = "id") Long conceitoId) {
        Conceito conceito = conceitoRepositorio.findById(conceitoId)
                .orElseThrow(() -> new ResourceNotFoundException("Conceito", "id", conceitoId));
        
        conceitoRepositorio.delete(conceito);
        
        return ResponseEntity.ok().build();
    }
}
