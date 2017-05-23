/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Vector;
import model.Estado;

/**
 *
 * @author allan
 */
public class Ambiente {

    public static String[][] espaco;
    public static double [][] espaco2D;
    private static int colunm;
    private static int line;
    private Vector<Integer> plano = new Vector();

    public Ambiente(String[][] espaco) {
        this.espaco = espaco;
    }
     public Ambiente(String[][] espaco, double[][]espaco2D ) {
        this.espaco = espaco;
        this.espaco2D = espaco2D;
    }

    public static void printMatrix() {

        try {
            String[][] matrix = espaco;

            int rows = matrix.length;
            int columns = matrix[0].length;
            String str = "|\t";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += matrix[i][j] + "\t";
                }

                System.out.println(str + "|");
                str = "|\t";
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }

    }

    public void colocarParede(int m, int n) {
        espaco[m][n] = "x";
        espaco2D[m][n] = 0.0;


    }

    public void colocarAgente(int m, int n) {
        espaco[m][n] = "A";
        printMatrix();

    }

    public void colocarObjetivo(int m, int n) {
        espaco[m][n] = "O";
        printMatrix();

    }

    public void moveAgentenoAmbiente(int x) {
        lerPosicaoAmbiente();
        int maxLine = espaco.length;
        int maxColunm = espaco[0].length;

        if (x == 1) {
            if (line - 1 >= 0 && colunm - 1 >= 0) {
                if (!espaco[line - 1][colunm - 1].equals("x")) {
                    if (espaco[line - 1][colunm - 1].equals("0")) {
                        espaco[line - 1][colunm - 1] = "A";
                    }
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 2) {
            if (line - 1 >= 0) {
                if (!espaco[line - 1][colunm].equals("x")) {
                    espaco[line - 1][colunm] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 3) {
            if (colunm + 1 < maxColunm - 1 && line - 1 >= 0) {
                if (!espaco[line - 1][colunm + 1].equals("x")) {
                    espaco[line - 1][colunm + 1] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 4) {
            if (colunm - 1 >= 0) {
                if (!espaco[line][colunm - 1].equals("x")) {
                    espaco[line][colunm - 1] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 6) {
            if (colunm + 1 <= maxColunm - 1) {
                if (!espaco[line][colunm + 1].equals("x")) {
                    espaco[line][colunm + 1] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 7) {
            if (colunm - 1 >= 0 && line + 1 <= maxLine - 1) {
                if (!espaco[line + 1][colunm - 1].equals("x")) {
                    espaco[line + 1][colunm - 1] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }

        if (x == 8) {
            if (line + 1 <= maxLine - 1) {
                if (!espaco[line + 1][colunm].equals("x")) {
                    espaco[line + 1][colunm] = "A";
                    espaco[line][colunm] = "_";
                }
            }
        }
        if (x == 9) {
            if (colunm + 1 <= maxColunm - 1 && line + 1 <= maxLine - 1) {
                if (!espaco[line + 1][colunm + 1].equals("x")) {
                    espaco[line + 1][colunm + 1] = "A";
                    espaco[line][colunm] = "+";

                }
            }
        }

    }
    public double custo(Estado estadoi, Estado estadof,int acao ){
        
        if(acao==2||acao==4||acao==6||acao==8){
            return 1;
        }else{
            return 1.5;
        }

    }
     public double custo(int acao ){
        
        if(acao==2||acao==4||acao==6||acao==8){
            return 1;
        }else{
            return 1.5;
        }

    }


    public void executaPlano() {

        for (int i = 0; i < plano.size(); i++) {
            moveAgentenoAmbiente(plano.get(i).intValue());
            espaco[line][colunm] = "+";
        }
        printMatrix();
    }

    public boolean objetivo() {

        if (espaco[line][colunm].equals("O")) {
            System.out.println("Sucesso!");
            return false;
        } else {
            return true;
        }
    }

    public void lerPosicaoAmbiente() {
        String tmp = null;
        String[][] matrix = espaco;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("A")) {
                    tmp = i + "," + j;
                    colunm = j;
                    line = i;
                    break;
                }
            }

        }

    }

    public static String[][] getEspaco() {
        return espaco;
    }

    public static void setEspaco(String[][] espaco) {
        Ambiente.espaco = espaco;
    }

    public Vector<Integer> getPlano() {
        return plano;
    }

    public void setPlano(Vector<Integer> plano) {
        this.plano = plano;
    }
    
    

}
