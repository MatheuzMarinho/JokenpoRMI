/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.io.Serializable;

/**
 *
 * @author Aluno
 */
public class Jogador implements Serializable {
    
    private String nome;
    private Jogadas jogada;
    private boolean pronto;
    private int pontuacao;
  

    public Jogador(String nome) {
        this.nome = nome;
        this.pronto = false;
        this.pontuacao = 0;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogadas getJogada() {
        return jogada;
    }

    public void setJogada(Jogadas jogada) {
        this.jogada = jogada;
    }

    public boolean isPronto() {
        return pronto;
    }

    public void setPronto(boolean pronto) {
        this.pronto = pronto;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public void setPonto(){
        this.pontuacao++;
    }

}
