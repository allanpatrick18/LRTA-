/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author allan
 */
public class Estado {

    private int line;
    private int colunm;
    private String valor;
    private String nome;
    public boolean explorado;


    public Estado() {
    }

    public Estado(int line, int colunm) {
        this.colunm = colunm;
        this.line = line;
    }
    public Estado(int line, int colunm, String valor) {
        this.colunm = colunm;
        this.line = line;
        this.valor = valor;
    }

    public Estado(int line, int colunm, String valor,boolean explorado) {
        this.line = line;
        this.colunm = colunm;
        this.valor = valor;
        this.explorado = explorado;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColunm() {
        return colunm;
    }

    public void setColunm(int colunm) {
        this.colunm = colunm;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void printEstado() {
        System.out.println("(" + line + "," + colunm + ")");
    }

    public boolean isExplorado() {
        return explorado;
    }
    
    public void setExplorado(boolean explorado) {
        this.explorado = explorado;
    }
    
    public boolean compereTo(Estado um, Estado dois) {
        if (um.getValor().equals(dois.getValor())) {
            return true;
        } else {
            return false;
        }

    }

}
