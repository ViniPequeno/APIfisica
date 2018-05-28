/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "conceito")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Conceito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "significado", nullable = false, length = 100)
    private String significado;

    @OneToOne(cascade = ALL, mappedBy = "conceito")
    private Palavra palavra;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    public Conceito() {
    }

    public Conceito(String significado, Palavra palavra, Usuario usuario) {
        this.significado = significado;
        this.palavra = palavra;
        this.usuario = usuario;
    }

    public Conceito(Long id, String significado, Palavra palavra, Usuario usuario) {
        this.id = id;
        this.significado = significado;
        this.palavra = palavra;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
