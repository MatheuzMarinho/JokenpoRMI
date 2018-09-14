/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.io.Serializable;

/**
 *
 * @author mathe
 */
public class Resultado implements Serializable {

    private String mensagem;
    private String nome;

    public Resultado(String mensagem, String nome) {
        this.mensagem = mensagem;
        this.nome = nome;
    }

    public Resultado(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
