package br.edu.undra.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Modela um jogo.
 * @author alexandre
 */
public class Jogo<T extends Jogador>  implements Logica{
    
    private String nome;
    private List<T> jogadores;
    private Tabuleiro tabuleiro;
    
    private Set<T> ultimosAJogar;

    public Jogo() {
        this.ultimosAJogar = new HashSet<>();
    }

    public Jogo(List<T> jogadores) {
        this.ultimosAJogar = new HashSet<>();
        this.jogadores = jogadores;
    }

    public Jogo(List<T> jogadores, Tabuleiro tabuleiro) {
        this.ultimosAJogar = new HashSet<>();
        
        this.jogadores = jogadores;
        this.tabuleiro = tabuleiro;
        
        setUpJogadores();
    }

    public Jogo(String nome, List<T> jogadores, Tabuleiro tabuleiro) {
        this(jogadores, tabuleiro);
        this.ultimosAJogar = new HashSet<>();
        this.nome = nome;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<T> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<T> jogadores) {
        this.jogadores = jogadores;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    @Override
    public String getProximaJogadaParaJogador(Jogador jogador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public T getProximoAJogar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUpJogadores() {
        for(T j :jogadores) j.setJogo(this);
    }

    @Override
    public Set<T> getUltimosAJogar() {
        return ultimosAJogar;
    }

    
    
}
