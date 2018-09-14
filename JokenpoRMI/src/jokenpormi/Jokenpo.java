/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Aluno
 */
public class Jokenpo extends UnicastRemoteObject implements RMIJokenpo {

    private Jogador jogadorUm;
    private Jogador jogadorDois;
    private boolean jogadoresConectados;

    private final static String VENCEU = "Venceu! :D";
    private final static String EMPATE = "Empate!";

    public Jokenpo() throws RemoteException {
        super();
        jogadoresConectados = false;
    }

    public String conectarServidor() throws RemoteException {
        return "Seja bem-vindo ao JokenpoMania :D";
    }

    public Jogador criarJogador(String nome) throws RemoteException {
        if (jogadorUm == null) {
            jogadorUm = new Jogador(nome);
            return jogadorUm;
        } else {
            jogadorDois = new Jogador(nome);
            jogadoresConectados = true;
            return jogadorDois;
        }
    }

    public boolean jogadoresConectados() throws RemoteException {
        return this.jogadoresConectados;
    }

    public boolean jogadasEscolhidas() throws RemoteException {
        if (jogadorUm.isPronto() && jogadorDois.isPronto()) {
            return true;
        }
        return false;
    }

    public void jogar(Jogadas jogada, String nome) {
        if (nome.equals(jogadorUm.getNome())) {
            jogadorUm.setJogada(jogada);
            jogadorUm.setPronto(true);
        } else {
            jogadorDois.setJogada(jogada);
            jogadorDois.setPronto(true);
        }
    }

    public Resultado resultado() {
        Jogadas jogada = jogadorUm.getJogada();
        if (jogada.equals(Jogadas.PAPEL)) {
            switch (jogadorDois.getJogada()) {
                case PAPEL:
                    return new Resultado(EMPATE);
                case PEDRA:
                    return new Resultado(VENCEU, jogadorUm.getNome());
                case TESOURA:
                    return new Resultado(VENCEU, jogadorDois.getNome());
            }
        }
        if (jogada.equals(Jogadas.PEDRA)) {
            switch (jogadorDois.getJogada()) {
                case PAPEL:
                    return new Resultado(VENCEU, jogadorDois.getNome());
                case PEDRA:
                    return new Resultado(EMPATE);
                case TESOURA:
                    return new Resultado(VENCEU, jogadorUm.getNome());
            }
        }
        if (jogada.equals(Jogadas.TESOURA)) {
            switch (jogadorDois.getJogada()) {
                case PAPEL:
                   return new Resultado(VENCEU, jogadorUm.getNome());
                case PEDRA:
                    return new Resultado(VENCEU, jogadorDois.getNome());
                case TESOURA:
                  return new Resultado(EMPATE);
            }
        }
        return null;
    }

    public String placar(Jogador jogador, int pontosAdversario) {
        String nomeAdversario = "";
        if(jogador.getNome().equals(jogadorUm.getNome())){
            nomeAdversario = jogadorDois.getNome();
        }else{
            nomeAdversario = jogadorUm.getNome();
        }
        return "---------PLACAR--------\n"
                + jogador.getNome() + ": " + jogador.getPontuacao() + " PONTOS\n"
                + nomeAdversario + ": " + pontosAdversario + " PONTOS\n";
    }

    public void jogarNovamente() {
        jogadorUm.setPronto(false);
        jogadorDois.setPronto(false);
    }
}
