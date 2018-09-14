/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokenpormi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Aluno
 */
public class JokenpoRMIServidor {
 public static void main(String [] args){
        try {
            Registry registro = LocateRegistry.createRegistry(1099);
            
            registro.rebind("JokenpoRMI", new Jokenpo());
            
            System.out.println("Servidor Iniciado!");
        }
        catch (RemoteException rmex){
            System.err.println(rmex.getMessage());
        }
    }
    
}
