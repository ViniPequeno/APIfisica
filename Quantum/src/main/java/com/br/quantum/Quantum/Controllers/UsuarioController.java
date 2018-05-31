/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Exception.ResourceNotFoundException;
import com.br.quantum.Quantum.Models.Usuario;
import com.br.quantum.Quantum.Repositorio.UsuarioRepositorio;
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

/**
 *
 * @author vinic
 */
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
        return (Usuario) usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
    }

    @PostMapping("/usuarios")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Update a Usuario
    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable(value = "id") Long usuarioId,
            @Valid @RequestBody Usuario usuarioDetails) {

        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "usuario", usuarioId));

        usuario.setUsuario(usuarioDetails.getUsuario());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());

        Usuario usuarioUpdate = usuarioRepositorio.save(usuario);
        return usuarioUpdate;
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        usuarioRepositorio.delete(usuario);

        return ResponseEntity.ok().build();
    }

}
