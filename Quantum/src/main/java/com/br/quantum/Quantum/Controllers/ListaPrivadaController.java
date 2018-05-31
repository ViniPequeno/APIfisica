/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.ListaPrivada;
import com.br.quantum.Quantum.Repositorio.ListaPrivadaRepositorio;
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
public class ListaPrivadaController {
    
    @Autowired
    ListaPrivadaRepositorio listaPrivadaRepositorio;
    
    @GetMapping("/listaPrivadas")
    public List<ListaPrivada> getAllListaPrivadas() {
        return listaPrivadaRepositorio.findAll();
    }
    
    @GetMapping("/listaPrivadas/{id}")
    public ListaPrivada getListaPrivadaById(@PathVariable(value = "id") Long listaPrivadaId) {
        return (ListaPrivada) listaPrivadaRepositorio.findById(listaPrivadaId)
                .orElseThrow(() -> new ResourceNotFoundException("ListaPrivada", "id", listaPrivadaId));
    }
    
    @PostMapping("/listaPrivadas")
    public ListaPrivada createListaPrivada(@Valid @RequestBody ListaPrivada listaPrivada) {
        return listaPrivadaRepositorio.save(listaPrivada);
    }

    // Update a ListaPrivada
    @PutMapping("/listaPrivadas/{id}")
    public ListaPrivada updateListaPrivada(@PathVariable(value = "id") Long listaPrivadaId,
            @Valid @RequestBody ListaPrivada listaPrivadaDetails) {
        
        ListaPrivada listaPrivada = listaPrivadaRepositorio.findById(listaPrivadaId)
                .orElseThrow(() -> new ResourceNotFoundException("ListaPrivada", "listaPrivada", listaPrivadaId));
        
        listaPrivada.setNome(listaPrivadaDetails.getNome());
        listaPrivada.setCodigo(listaPrivadaDetails.getCodigo());
        listaPrivada.setSenha(listaPrivadaDetails.getSenha());
        ListaPrivada listaPrivadaUpdate = listaPrivadaRepositorio.save(listaPrivada);
        return listaPrivadaUpdate;
    }
    
    @DeleteMapping("/listaPrivadas/{id}")
    public ResponseEntity<?> deleteListaPrivada(@PathVariable(value = "id") Long listaPrivadaId) {
        ListaPrivada listaPrivada = listaPrivadaRepositorio.findById(listaPrivadaId)
                .orElseThrow(() -> new ResourceNotFoundException("ListaPrivada", "id", listaPrivadaId));
        
        listaPrivadaRepositorio.delete(listaPrivada);
        
        return ResponseEntity.ok().build();
    }
}
