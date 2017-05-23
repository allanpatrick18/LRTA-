/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static controller.main.ambiente;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import model.Estado;
import model.No;

/**
 *
 * @author allan
 */
public class Algoritmo {

    Agente agente;
    Estado estadoFinal;
    Estado estadoInicial;
    static No no;
    List<No> fronteiras = new ArrayList<>();
    List<No> conhecidos = new ArrayList<>();
    List<Integer> acoes = new ArrayList<>();
    String valorobj;
    List<No> arvore = new ArrayList<>();
    List<Integer> custototal = new ArrayList<>();

    public Algoritmo(Agente agente, Estado estadoInicial, Estado estadoFinal) {
        this.agente = agente;
        this.estadoFinal = estadoFinal;
        this.estadoInicial = estadoInicial;
    }

    public Algoritmo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean algoritomo() {
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        if (!estadoFinal.getValor().equals(estadoInicial.getValor())) {
            return false;
        }

        acoes.addAll(agente.acoesPosiveis(estadoAtual));
        No noTmp = new No();
        noTmp.setEsplorado(true);
        noTmp.setPosicao(estadoAtual);
        for (int i = 0; i < acoes.size(); i++) {
            No nos = new No();
            nos.setEsplorado(false);
            nos.setPosicao(agente.funcaoSucessora(estadoAtual, acoes.get(i)));
            noTmp.getAdjacentes().add(nos);
        }

        No noFront = noTmp.getAdjacentes().get(0);
        noFront.setEsplorado(true);
        while (true) {
            if (noFront == null) {
                return false;
            }
            acoes = agente.acoesPosiveis(estadoAtual);
            for (int i = 0; i < acoes.size(); i++) {
                if (!noFront.getAdjacentes().get(i).isEsplorado()) {
                    if (noFront.getAdjacentes().get(i).getPosicao().getValor().equals(valorobj)) {
                        return true;
                    }
                    noFront = noTmp;
                }

            }

        }

    }

    public boolean algoritomo2() {
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        if (estadoFinal.getValor().equals(estadoInicial.getValor())) {
            return true;
        }

        no = new No();
        setAcoes(agente.acoesPosiveis(estadoAtual));
        acoes.size();
        agente.marcarPosicao(estadoInicial);
        no.setEsplorado(true);

        no.setPosicao(estadoInicial);
        No fronteira = null;
        arvore.add(no);
        arvore.add(null);
        fronteira = no;
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                if (filhos.getPosicao().getValor().equals("O")) {
                    return true;
                } else {
                    filhos.setEsplorado(false);
                    fronteira.getAdjacentes().add(filhos);
                    agente.marcarPosicao(filhos.getPosicao());
                    conhecidos.add(filhos);
                    arvore.add(filhos);
                    arvore.add(no);
                }
            }
            arvore.add(null);
            if (conhecidos.size() > 0) {
                fronteira = conhecidos.get(0);
                conhecidos.remove(0);
                fronteira.setEsplorado(true);
                setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
            }
        }
    }

    public boolean algoritomo3() {
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        if (estadoFinal.getValor().equals(estadoInicial.getValor())) {
            return true;
        }

        no = new No();
        no.setPai(null);
        no.setCusto(0);
        setAcoes(agente.acoesPosiveis(estadoAtual));
        agente.marcarPosicao(estadoInicial);
        no.setEsplorado(true);
        no.setPosicao(estadoInicial);
        No fronteira = null;
        arvore.add(no);
        arvore.add(null);
        fronteira = no;
        agente.marcarPosicao(fronteira.getPosicao());
        int visitados=0;
        int esplorados =0;
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                double cost = fronteira.getCusto();
                cost = cost + agente.custo(acoes.get(i));
                filhos.setCusto(cost);
                visitados++;
                if (filhos.getPosicao().getValor().equals("O")) {
                    System.out.println("Chegeuei!!!!");
                    System.out.println("Visitados " +visitados+ " Esplorados: " +esplorados);
                    filhos.setPai(fronteira);
                
                    while(fronteira.getPai()!=null){
                        agente.marcarCaminho(fronteira.getPosicao());
                        fronteira.getPosicao().printEstado();
                        fronteira = fronteira.getPai();
                    
                    }
                    return true;
                } else {
                    filhos.setPai(fronteira);
                    filhos.setEsplorado(false);
                    conhecidos.add(filhos);
                }
            }
            if (conhecidos.size() > 0) {
                esplorados++;
                ordenar(conhecidos);
                fronteira = conhecidos.get(0);
                conhecidos.remove(0);
                fronteira.setEsplorado(true);
                agente.marcarPosicao(fronteira.getPosicao());
                setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
            }
        }
    }
    
       public boolean algoritomo4() {
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        if (estadoFinal.getValor().equals(estadoInicial.getValor())) {
            return true;
        }
        no = new No();
        no.setPai(null);
        no.setCusto(0);
        setAcoes(agente.acoesPosiveis(estadoAtual));
        agente.marcarPosicao(estadoInicial);
        no.setEsplorado(true);
        no.setPosicao(estadoInicial);
        No fronteira = null;
        arvore.add(no);
        arvore.add(null);
        fronteira = no;
         int visitados=0;
        int esplorados =0;
        agente.marcarPosicao(fronteira.getPosicao());
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                double cost = fronteira.getCusto();
                cost = cost + agente.custo(acoes.get(i));
                double heuristica =  calculaheuristica(filhos.getPosicao());
                filhos.setFn(heuristica+cost);
                visitados++;
                if (filhos.getPosicao().getValor().equals("O")) {
                 System.out.println("Visitados " +visitados+ " Esplorados: " +esplorados);
                    while(fronteira.getPai()!=null){
                        agente.marcarCaminho(fronteira.getPosicao());
                        fronteira.getPosicao().printEstado();
                        fronteira = fronteira.getPai();
                        
                    }
                    return true;
                } else {
                    filhos.setPai(fronteira);
                    filhos.setEsplorado(false);
                    conhecidos.add(filhos);

                }
            }
            if (conhecidos.size() > 0) {
                esplorados++;
                ordenarbyFn(conhecidos);
                fronteira = conhecidos.get(0);
                conhecidos.remove(0);
                fronteira.setEsplorado(true);
                agente.marcarPosicao(fronteira.getPosicao());
                setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
            }
        }
    }
   public boolean algoritomo5() {
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        if (estadoFinal.getValor().equals(estadoInicial.getValor())) {
            return true;
        }
        no = new No();
        no.setPai(null);
        no.setCusto(0);
        setAcoes(agente.acoesPosiveis(estadoAtual));
        agente.marcarPosicao(estadoInicial);
        no.setEsplorado(true);
        no.setPosicao(estadoInicial);
        No fronteira = null;
        arvore.add(no);
        arvore.add(null);
        fronteira = no;
        int visitados=0;
        int esplorados =0;
        agente.marcarPosicao(fronteira.getPosicao());
        List<No> temp = new ArrayList<>();
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                double cost = fronteira.getCusto();
                cost = cost + agente.custo(acoes.get(i));
             // double heuristica =  calculaheuristica(filhos.getPosicao());
                double heuristica = agente.getValor(filhos.getPosicao());
                filhos.setFn(heuristica+cost);
                visitados++;
                if (filhos.getPosicao().getValor().equals("O")) {
                 System.out.println("Visitados " +visitados+ " Esplorados: " +esplorados);
                    while(fronteira.getPai()!=null){
                        agente.marcarCaminho(fronteira.getPosicao());
                        fronteira.getPosicao().printEstado();
                        fronteira = fronteira.getPai();   
                    }
                    return true;
                } else {
                    filhos.setPai(fronteira);
                    filhos.setEsplorado(false);
                    conhecidos.add(filhos);
                 
                }
            }
            if (conhecidos.size() > 0) {
                esplorados++;
                List<Integer>  l = new ArrayList<>();
                ordenarbyFn(conhecidos);
                if(conhecidos.size() > 1)
                 
                for(int i = 0; i+1 < conhecidos.size(); i++){
                    if(conhecidos.get(i).getFn()==conhecidos.get(i+1).getFn()){
                        l.add(i);   
                    }else{break;}
                }
                if(l.size()>0){
                    Random r = new Random();
                    Integer n = r.nextInt(l.size()+1);
                    fronteira = conhecidos.get(n);           
                }else{
                fronteira = conhecidos.get(0);          
                }
             
                conhecidos = new ArrayList<>();
                double tmp = fronteira.getFn();    
                fronteira.setH(tmp);
                agente.setValor(fronteira.getPosicao(),fronteira.getFn());
                fronteira.setEsplorado(true);
                setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
            }
        }
    }


    public void ordenar(List<No> nos) {
        nos.sort(new Comparator<No>() {
            public int compare(No no1, No no2) {
                return Double.compare(no1.getCusto(), no2.getCusto());
            }
        });

    }
       public void ordenarbyFn(List<No> nos) {
        nos.sort(new Comparator<No>() {
            public int compare(No no1, No no2) {
                return Double.compare(no1.getFn(), no2.getFn());
            }
        });

    }
    
    
    public double calculaheuristica(Estado atual){
        int y = abs(atual.getLine()- estadoFinal.getLine());
        int x = abs(atual.getColunm()- estadoFinal.getColunm());
        double t = Math.sqrt( Math.pow(y,2)+ Math.pow(x, 2));
//        t = Math.floor(t);
//        t = Math.round(t);
        return t;
    
    }

    public No insereNo(No filho, No fronteira) {

        return new No();

    }

    public List<Integer> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Integer> acoes) {
        this.acoes = acoes;
    }

}
