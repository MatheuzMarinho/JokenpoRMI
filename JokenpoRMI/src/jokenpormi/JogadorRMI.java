/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class JogadorRMI {

    private static RMIJokenpo objremoto;
    private static boolean esperando = false;
    private static Jogador jogador;
    private static int pontosAdversario = 0;

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);
            objremoto = (RMIJokenpo) registro.lookup("JokenpoRMI");
            iniciarJogo();
        } catch (RemoteException rmex) {
            System.err.println(rmex.getMessage());
        } catch (NotBoundException nbex) {
            System.err.println(nbex.getMessage());
        }
    }

    public static void iniciarJogo() throws RemoteException {
        // Conectando ao servidor!
        System.out.println(objremoto.conectarServidor());

        System.out.println("Digite o seu nome: ");
        Scanner scanner = new Scanner(System.in);

        jogador = objremoto.criarJogador(scanner.nextLine());

        jogadoresConectados();
        jogadasEscolhidas();
    }

    public static void jogadoresConectados() throws RemoteException {
        while (!objremoto.jogadoresConectados()) {
            if (!esperando) {
                esperando = true;
                System.out.println("Esperando Adversário!");
            }
        }
        esperando = false;
        System.out.println("O jogo vai começar :D");
    }

    public static void jogadasEscolhidas() throws RemoteException {
        if (!jogador.isPronto()) {
            System.out.println("Digite sua jogada: ");
            Scanner scanner = new Scanner(System.in);
            Jogadas jogada = Jogadas.valueOf(scanner.nextLine());
            objremoto.jogar(jogada, jogador.getNome());
        }
        while (!objremoto.jogadasEscolhidas()) {
            if (!esperando) {
                esperando = true;
                System.out.println("Esperando Adversário!");
            }
        }
        esperando = false;
        resultado();
    }

    public static void resultado() throws RemoteException {
        Resultado r = objremoto.resultado();
        if(r.getNome() != null){
            if(r.getNome().equals(jogador.getNome())){
                 jogador.setPonto();
                 System.out.println("Você Venceu!");
            }else{
                  pontosAdversario++;
                  System.out.println("Você Perdeu!");
            }
        }else{
              System.out.println("Empataram!");
        }
        System.out.println(objremoto.placar(jogador, pontosAdversario));
        jogador.setPronto(false);
        objremoto.jogarNovamente();
        jogadasEscolhidas();
    }

}
