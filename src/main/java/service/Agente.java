/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.Ambiente;
import static controller.main.ambiente;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Estado;
import static service.Ambiente.espaco;
import static service.Ambiente.espaco2D;

/**
 *
 * @author allan
 */
public class Agente {

    public static String[][] repreAmbiente;
    private String posicao;
    private int line;
    private int colunm;
    private int[] actionPlan;
    private Vector<Integer> way;
    public List<Integer> acoes;
    public int columnObjetivo;
    public int lineObjetivo;
    public List<Estado> estado;

    public Agente(String[][] repreAmbiente) {
        this.repreAmbiente = repreAmbiente;

    }

    public String movaAgente(int x) {

        int maxLine = repreAmbiente.length;
        int maxColunm = repreAmbiente[0].length;

        if (!verificaObjetivo()) {
            System.out.println("Sucessso!!");
        }
        repreAmbiente[line][colunm] = "+";

        if (x == 1) {
            if (line - 1 >= 0 && colunm - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm - 1].equals("x")) {
                    repreAmbiente[line - 1][colunm - 1] = "A";
                    line = line - 1;
                    colunm = colunm - 1;
                }
            }
        }
        if (x == 2) {
            if (line - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm].equals("x")) {
                    repreAmbiente[line - 1][colunm] = "A";
                    line = line - 1;
                }
            }
        }

        if (x == 3) {
            if (colunm + 1 < maxColunm - 1 && line - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm + 1].equals("x")) {
                    repreAmbiente[line - 1][colunm + 1] = "A";
                    line = line - 1;
                    colunm = colunm + 1;

                }
            }
        }

        if (x == 4) {
            if (colunm - 1 >= 0) {
                if (!repreAmbiente[line][colunm - 1].equals("x")) {
                    repreAmbiente[line][colunm - 1] = "A";
                    colunm = colunm - 1;

                }
            }
        }

        if (x == 6) {
            if (colunm + 1 <= maxColunm - 1) {
                if (!repreAmbiente[line][colunm + 1].equals("x")) {
                    repreAmbiente[line][colunm + 1] = "A";
                    line = line + 1;

                }
            }
        }

        if (x == 7) {
            if (colunm - 1 >= 0 && line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm - 1].equals("x")) {
                    repreAmbiente[line + 1][colunm - 1] = "A";
                    line = line + 1;
                    colunm = colunm - 1;
                }
            }
        }

        if (x == 8) {
            if (line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm].equals("x")) {
                    repreAmbiente[line + 1][colunm] = "A";
                    line = line + 1;
                }
            }
        }

        if (x == 9) {
            if (colunm + 1 <= maxColunm - 1 && line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm + 1].equals("x")) {
                    repreAmbiente[line + 1][colunm + 1] = "A";
                    line = line + 1;
                    colunm = colunm + 1;
                }
            }
        }

        if (!verificaObjetivo()) {
            System.out.println("Sucessso!!");
        }

        return lerPos();

    }

    public Estado funcaoSucessora(Estado state, int acao) {
        line = state.getLine();
        colunm = state.getColunm();
        int maxLine = repreAmbiente.length;
        int maxColunm = repreAmbiente[0].length;

//        repreAmbiente[line][colunm] = "+";

        if (acao == 1) {
            if (line - 1 >= 0 && colunm - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm - 1].equals("x")) {
                    return new Estado(line - 1, colunm - 1, repreAmbiente[line - 1][colunm - 1], true);

                }
            }
        }
        if (acao == 2) {
            if (line - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm].equals("x") ) {
                    return new Estado(line - 1, colunm, repreAmbiente[line - 1][colunm], true);
                }
            }
        }

        if (acao == 3) {
            if (colunm + 1 < maxColunm - 1 && line - 1 >= 0) {
                if (!repreAmbiente[line - 1][colunm + 1].equals("x") ) {
                    return new Estado(line - 1, colunm + 1, repreAmbiente[line - 1][colunm + 1], true);
                }
            }
        }

        if (acao == 4) {
            if (colunm - 1 >= 0) {
                if (!repreAmbiente[line][colunm - 1].equals("x") ) {
                    return new Estado(line, colunm - 1, repreAmbiente[line][colunm - 1], true);
                }
            }
        }

        if (acao == 6) {
            if (colunm + 1 <= maxColunm - 1) {
                if (!repreAmbiente[line][colunm + 1].equals("x")) {
                    return new Estado(line, colunm + 1, repreAmbiente[line][colunm + 1], true);
                }
            }
        }

        if (acao == 7) {
            if (colunm - 1 >= 0 && line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm - 1].equals("x") ) {
                    return new Estado(line + 1, colunm - 1, repreAmbiente[line + 1][colunm - 1], true);
                }
            }
        }

        if (acao == 8) {
            if (line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm].equals("x") ) {
                    return new Estado(line + 1, colunm, repreAmbiente[line + 1][colunm], true);
                }
            }
        }

        if (acao == 9) {
            if (colunm + 1 <= maxColunm - 1 && line + 1 <= maxLine - 1) {
                if (!repreAmbiente[line + 1][colunm + 1].equals("x") ) {
                    return new Estado(line + 1, colunm + 1, repreAmbiente[line + 1][colunm + 1], true);
                }
            }
        }
        lerPos();

        return null;

    }

    public String lerPos() {
        String tmp = null;
        String[][] matrix = Ambiente.espaco;
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
        posicao = tmp;
        return tmp;
    }

    public Estado lerPosicao() {

        String[][] matrix = Ambiente.espaco;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("A")) {
                    return new Estado(i, j);
                }
            }

        }

        return null;
    }

    public void printCdm() {
        System.out.println("IR (x)");
        String[][] matrix = {
            {"1", "2", "3"},
            {"4", "A", "6"},
            {"7", "8", "9"},};

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public  void printCrencas() {

        System.out.println("\ncrenças do agente acerca do ambiente grid 2D");

        try {
            String[][] matrix = repreAmbiente;

            int rows = matrix.length;
            int columns = matrix[0].length;
            String str = "|  ";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += matrix[i][j] + "  ";
                }

                System.out.println(str + "|");
                str = "|  ";
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }

    }

    public void printCaminho() {

        try {
            String[][] matrix = repreAmbiente;

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

    public  void printCore() {

        System.out.println("\n valor do matriz");

        try {
            double[][] matrix = espaco2D;
            DecimalFormat df = new DecimalFormat("0.##");
            String dx ;

            int rows = matrix.length;
            int columns = matrix[0].length;
            String str = "|  ";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    dx = df.format( matrix[i][j]);
                    str += dx + "  ";
                }

                System.out.println(str + "|");
                str = "|  ";
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }

    }
    public List<Integer> acoesPosiveis(Estado state) {

        line = state.getLine();
        colunm = state.getColunm();
        int maxLine = repreAmbiente.length;
        int maxColunm = repreAmbiente[0].length;
        acoes = new ArrayList<>();
//        repreAmbiente[line][colunm] ="+";
        if(line==6&&colunm==1||line==0 &&colunm==2){
            System.out.println("aquu");
        }
        if (line - 1 >= 0) {
           if (!repreAmbiente[line - 1][colunm].equals("x") && !repreAmbiente[line - 1][colunm].equals("+")) {
                acoes.add(2);
            }
        }

        if (colunm + 1 < maxColunm - 1 && line - 1 >= 0) {
             if (!repreAmbiente[line - 1][colunm + 1].equals("x") && !repreAmbiente[line - 1][colunm+1].equals("+")) {
                acoes.add(3);
            }
        }

        if (colunm + 1 <= maxColunm - 1) {
              if (!repreAmbiente[line][colunm + 1].equals("x") && !repreAmbiente[line][colunm + 1].equals("+")) {
                acoes.add(6);
            }
        }
        if (colunm + 1 <= maxColunm - 1 && line + 1 <= maxLine - 1) {
            if (!repreAmbiente[line + 1][colunm + 1].equals("x") && !repreAmbiente[line + 1][colunm + 1].equals("+")) {
                acoes.add(9);
            }
        }
        if (line + 1 <= maxLine - 1) {
           if (!repreAmbiente[line + 1][colunm].equals("x") && !repreAmbiente[line + 1][colunm].equals("+")) {
                acoes.add(8);
            }
        }
        if (colunm - 1 >= 0 && line + 1 <= maxLine - 1) {
             if (!repreAmbiente[line + 1][colunm - 1].equals("x") && !repreAmbiente[line + 1][colunm - 1].equals("+")) {
                acoes.add(7);
 
            }
        }
        if (colunm - 1 >= 0) {
           if (!repreAmbiente[line][colunm - 1].equals("x") && !repreAmbiente[line][colunm-1].equals("+")) {
                acoes.add(4);
            }
        }
        if (line - 1 >= 0 && colunm - 1 >= 0) {
           if (!repreAmbiente[line - 1][colunm - 1].equals("x") && !repreAmbiente[line - 1][colunm - 1].equals("+")) {
                acoes.add(1);
            }
        }

        lerPos();
 // 
 //       printAcoes();
        return acoes;
    }

    public void printAcoes() {

        System.out.println("Estado");
        System.out.println("(" + this.line + "," + this.colunm + ")");
        System.out.print("{");
        for (int i = 0; i < acoes.size(); i++) {
            System.out.print("ir(" + acoes.get(i) + "),");
        }
        System.out.println("}");
//        acoes.clear();
    }

    public void custos() {
        double t = 1.5;
        int k = 1;
        Integer[] a = {2, 4, 6, 8};

        for (int i = 0; i < acoes.size(); i++) {
            for (int j = 0; j < a.length; j++) {
                if (acoes.get(i) % 2 == 0) {
                    k++;
                } else {
                    t = t + 1.5;
                }
            }
        }
        System.out.println("Caminho custo" + k + t);
    }
    
     public double custo(int acao ){
        
        if(acao==2||acao==4||acao==6||acao==8){
            return 1;
        }else{
            return 1.5;
        }

    }

    public void cicloDeRaciocinio() {
        printCrencas();
        acoesPosiveis(new Estado(line, colunm));
        if (way.size() > 0) {
            way.get(0);
            System.out.println("Proxima acao do plano: ir(" + way.get(0) + ")");
            ambiente.moveAgentenoAmbiente(way.get(0));
            movaAgente(way.get(0));
            way.remove(0);
        }
    }

    public void setPosicaoObjetivo(int lineObjetivo, int columnObjetivo) {
        this.lineObjetivo = lineObjetivo;
        this.columnObjetivo = columnObjetivo;

    }

    public boolean verificaObjetivo() {
        if (colunm != columnObjetivo && line != lineObjetivo) {
            return true;
        } else {
            return false;
        }
    }
    
    public void marcarPosicao(Estado state){
    
        repreAmbiente[state.getLine()][state.getColunm()]= "+";
    
    }
    
     public void marcarCaminho(Estado state){
    
        repreAmbiente[state.getLine()][state.getColunm()]= "U";
    
    }
    public void marcarAgete(Estado state) {

        repreAmbiente[state.getLine()][state.getColunm()] = "A";

    }
    public Vector<Integer> getWay() {
        return way;
    }

    public void setWay(Vector<Integer> way) {
        this.way = way;

    }

    public List<Integer> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Integer> acoes) {
        this.acoes = acoes;
    }
    public void setValor(Estado state, double valor) {
        espaco2D[state.getLine()][state.getColunm()] = valor;
    }
    public double getValor(Estado state) {
        return espaco2D[state.getLine()][state.getColunm()];
    }

    public static String[][] getRepreAmbiente() {
        return repreAmbiente;
    }
    
    public static void colocarParede(int m, int n) {
        repreAmbiente[m][n] = "x";

    }

    public static void setRepreAmbiente() {

           int rows = repreAmbiente.length;
           int columns = repreAmbiente[0].length;
           for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                repreAmbiente[i][j] = "_";

            }
            
        }
//        repreAmbiente[8][0] = "A";
        repreAmbiente[2][8] = "O";
        colocarParede(0, 0);
        colocarParede(0, 1);
        colocarParede(0, 4);
        colocarParede(0, 5);
        colocarParede(0, 6);
        colocarParede(0, 7);
        colocarParede(1, 0);
        colocarParede(1, 7);
        colocarParede(2, 3);
        colocarParede(2, 4);
        colocarParede(2, 5);
        colocarParede(3, 3);
        colocarParede(3, 4);
        colocarParede(3, 5);
        colocarParede(3, 7);
        colocarParede(5, 1);
        colocarParede(5, 2);
        colocarParede(5, 5);
        colocarParede(5, 7);
        colocarParede(6, 1);
        colocarParede(6, 4);
        colocarParede(6, 5);
        colocarParede(6, 7);
        colocarParede(7, 1);
        colocarParede(7, 4);
        colocarParede(7, 7);
        colocarParede(8, 1);
        colocarParede(8, 2);
    }
    

}
