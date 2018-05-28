package com.br.quantum.Quantum.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "alternativas")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Alternativas implements Serializable {

    @Id
    private char letra;

    private String resposta;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "questao", nullable = false)
    private Questoes questao;

    public Alternativas() {
    }

    public Alternativas(char letra, String resposta, Questoes questao) {
        this.letra = letra;
        this.resposta = resposta;
        this.questao = questao;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Questoes getQuestao() {
        return questao;
    }

    public void setQuestao(Questoes questao) {
        this.questao = questao;
    }

}
