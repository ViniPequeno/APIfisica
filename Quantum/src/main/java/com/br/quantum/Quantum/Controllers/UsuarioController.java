/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Controllers;

import com.br.quantum.Quantum.Models.Usuario;
import com.br.quantum.Quantum.Repositorio.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
