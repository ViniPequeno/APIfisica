/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @OneToMany(cascade = ALL, mappedBy = "usuario")
    private List<Conceito> conceitos;

    @OneToMany(cascade = ALL, mappedBy = "usuario")
    private List<Lista> listas;

    public Usuario() {
    }

    public Usuario(String email, String usuario, String senha, List<Conceito> conceitos, List<Lista> listas) {
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.conceitos = conceitos;
        this.listas = listas;
    }

    public Usuario(Long id, String email, String usuario, String senha, List<Conceito> conceitos, List<Lista> listas) {
        this.id = id;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.conceitos = conceitos;
        this.listas = listas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conceito> getConceitos() {
        return conceitos;
    }

    public void setConceitos(List<Conceito> conceitos) {
        this.conceitos = conceitos;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

}
