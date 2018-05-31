/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "questao")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Questoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String enunciado;
    private Character respostaCerta;

    @ManyToOne
    @JoinColumn(name = "lista", nullable = false)
    private Lista lista;

    @OneToMany(cascade = ALL, mappedBy = "questao")
    private List<Alternativas> alternativas;

    public Questoes() {
    }

    public Questoes(String codigo, String enunciado, char respostaCerta, Lista lista, List<Alternativas> alternativas) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.respostaCerta = respostaCerta;
        this.lista = lista;
        this.alternativas = alternativas;
    }

    public Questoes(Long id, String codigo, String enunciado, char respostaCerta, Lista lista, List<Alternativas> alternativas) {
        this.id = id;
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.respostaCerta = respostaCerta;
        this.lista = lista;
        this.alternativas = alternativas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public char getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(char respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public List<Alternativas> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativas> alternativas) {
        this.alternativas = alternativas;
    }

}
