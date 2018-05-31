/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Palavra;
import com.br.quantum.Quantum.Repositorio.PalavraRepositorio;
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
public class PalavraController {

    @Autowired
    PalavraRepositorio palavraRepositorio;

    @GetMapping("/palavras")
    public List<Palavra> getAllPalavras() {
        return palavraRepositorio.findAll();
    }

    @GetMapping("/palavras/{id}")
    public Palavra getPalavraById(@PathVariable(value = "id") Long palavraId) {
        return (Palavra) palavraRepositorio.findById(palavraId)
                .orElseThrow(() -> new ResourceNotFoundException("Palavra", "id", palavraId));
    }

    @PostMapping("/palavras")
    public Palavra createPalavra(@Valid @RequestBody Palavra palavra) {
        return palavraRepositorio.save(palavra);
    }

    // Update a Produto
    @PutMapping("/palavras/{id}")
    public Palavra updatePalavra(@PathVariable(value = "id") Long palavraId,
            @Valid @RequestBody Palavra palavraDetails) {

        Palavra palavra = palavraRepositorio.findById(palavraId)
                .orElseThrow(() -> new ResourceNotFoundException("Palavra", "palavra", palavraId));

        palavra.setPalavra(palavraDetails.getPalavra());

        Palavra palavraUpdate = palavraRepositorio.save(palavra);
        return palavraUpdate;
    }

    @DeleteMapping("/palavras/{id}")
    public ResponseEntity<?> deletePalavra(@PathVariable(value = "id") Long palavraId) {
        Palavra palavra = palavraRepositorio.findById(palavraId)
                .orElseThrow(() -> new ResourceNotFoundException("Palavra", "id", palavraId));

        palavraRepositorio.delete(palavra);

        return ResponseEntity.ok().build();
    }
}
