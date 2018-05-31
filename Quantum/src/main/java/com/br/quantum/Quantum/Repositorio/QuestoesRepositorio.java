/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.quantum.Quantum.Repositorio;

import com.br.quantum.Quantum.Models.Questoes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vinic
 */
public interface QuestoesRepositorio extends JpaRepository<Questoes, Long> {

}
