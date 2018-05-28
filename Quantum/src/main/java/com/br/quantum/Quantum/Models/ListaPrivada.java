/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "listaprivada")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class ListaPrivada extends Lista {

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;
    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    public ListaPrivada() {
        super();
    }

    public ListaPrivada(String codigo, String senha, String nome, Usuario usuario) {
        super(nome, usuario);
        this.codigo = codigo;
        this.senha = senha;
    }

    public ListaPrivada(String codigo, String senha, Long id, String nome, Usuario usuario) {
        super(id, nome, usuario);
        this.codigo = codigo;
        this.senha = senha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
