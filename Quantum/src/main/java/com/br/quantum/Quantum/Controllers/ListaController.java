/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Lista;
import com.br.quantum.Quantum.Repositorio.ListaRepositorio;
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
public class ListaController {
    @Autowired
    ListaRepositorio listaRepositorio;

    @GetMapping("/listas")
    public List<Lista> getAllListas() {
        return listaRepositorio.findAll();
    }

    @GetMapping("/listas/{id}")
    public Lista getListaById(@PathVariable(value = "id") Long listaId) {
        return (Lista) listaRepositorio.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista", "id", listaId));
    }

    @PostMapping("/listas")
    public Lista createLista(@Valid @RequestBody Lista lista) {
        return listaRepositorio.save(lista);
    }

    // Update a Lista
    @PutMapping("/listas/{id}")
    public Lista updateLista(@PathVariable(value = "id") Long listaId,
            @Valid @RequestBody Lista listaDetails) {

        Lista lista = listaRepositorio.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista", "lista", listaId));

        lista.setNome(listaDetails.getNome());

        Lista listaUpdate = listaRepositorio.save(lista);
        return listaUpdate;
    }

    @DeleteMapping("/listas/{id}")
    public ResponseEntity<?> deleteLista(@PathVariable(value = "id") Long listaId) {
        Lista lista = listaRepositorio.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista", "id", listaId));

        listaRepositorio.delete(lista);

        return ResponseEntity.ok().build();
    }

}
