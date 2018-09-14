/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aluno
 */
public interface RMIJokenpo extends Remote {

    public String conectarServidor() throws RemoteException;

    public Jogador criarJogador(String m) throws RemoteException;

    public boolean jogadoresConectados() throws RemoteException;

    public boolean jogadasEscolhidas() throws RemoteException;

    public void jogar(Jogadas jogada, String nome) throws RemoteException;

    public Resultado resultado() throws RemoteException;

    public String placar(Jogador jogador, int pontosAdversario) throws RemoteException;

    public void jogarNovamente() throws RemoteException;
}
