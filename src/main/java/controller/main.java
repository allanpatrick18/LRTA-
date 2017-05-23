/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.Agente;
import service.Ambiente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Estado;
import service.Algoritmo;

/**
 *
 * @author allan
 */
public class main {

    public static Ambiente ambiente = null;

    public static void main(String[] args) throws IOException {

        String m, n;
        String line, colunm, selection;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("N:");
        n = "9";
        System.out.println("M:");
        m = "9";

        int t = Integer.parseInt(n);
        int t0 = Integer.parseInt(m);
        String[][] space = new String[t][t0];
        double[][] amb = new double[t][t0];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t0; j++) {
                space[i][j] = "_";

            }
        }
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t0; j++) {
                amb[i][j] = 1;

            }
        }

        ambiente = new Ambiente(space, amb);
        ambiente.printMatrix();
        ambiente.colocarParede(0, 0);
        ambiente.colocarParede(0, 1);
        ambiente.colocarParede(0, 4);
        ambiente.colocarParede(0, 5);
        ambiente.colocarParede(0, 6);
        ambiente.colocarParede(0, 7);
        ambiente.colocarParede(1, 0);
        ambiente.colocarParede(1, 7);
        ambiente.colocarParede(2, 3);
        ambiente.colocarParede(2, 4);
        ambiente.colocarParede(2, 5);
        ambiente.colocarParede(3, 3);
        ambiente.colocarParede(3, 4);
        ambiente.colocarParede(3, 5);
        ambiente.colocarParede(3, 7);
        ambiente.colocarParede(5, 1);
        ambiente.colocarParede(5, 2);
        ambiente.colocarParede(5, 5);
        ambiente.colocarParede(5, 7);
        ambiente.colocarParede(6, 1);
        ambiente.colocarParede(6, 4);
        ambiente.colocarParede(6, 5);
        ambiente.colocarParede(6, 7);
        ambiente.colocarParede(7, 1);
        ambiente.colocarParede(7, 4);
        ambiente.colocarParede(7, 7);
        ambiente.colocarParede(8, 1);
        ambiente.colocarParede(8, 2);

        System.out.println("Set inicail posiçao");
        System.out.println("Line");
        line = "8";
        System.out.println("Colunm");
        colunm = "0";
//        ambiente.colocarAgente(Integer.parseInt(line), Integer.parseInt(colunm));
        Estado inicial = new Estado(Integer.parseInt(line), Integer.parseInt(colunm), "A");
        System.out.println("Set objetivo posiçao");
        System.out.println("Line");
        line = "2";
        System.out.println("Colunm");
        colunm = "8";
        ambiente.colocarObjetivo(Integer.parseInt(line), Integer.parseInt(colunm));
        Estado estfinal = new Estado(Integer.parseInt(line), Integer.parseInt(colunm), "O");

        Agente agente = new Agente(ambiente.getEspaco());
        agente.setPosicaoObjetivo(Integer.parseInt(line), Integer.parseInt(colunm));
        Algoritmo ag = new Algoritmo(agente, inicial, estfinal);
        boolean novamente = true;
        while (true) {
            if (ag.algoritomo5()) {
                agente.marcarAgete(inicial);
                System.out.println("Sucesso");
                agente.printCrencas();
                agente.printCore();
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String cadena = br.readLine();
                if(cadena.equals(" ")){
                novamente = false;
                }
                agente.setRepreAmbiente();
                
            }
            
        }

    }

}
