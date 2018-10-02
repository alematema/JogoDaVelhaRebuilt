package br.edu.undra.modelo;

/**
 * Modela um jogador.
 * @author alexandre
 */
public abstract class Jogador {
    
    private String nome;
    
    private boolean primeiroAJogar = false;
    private boolean jogou = false;
    private String proximaJogada;
    
    private Jogo jogo;

    public Jogador() {
    }

    public Jogador(String nome) {
        this.nome = nome;
    }
    
    public Jogador(Jogo jogo) {
        this.jogo = jogo;
    }

    public Jogador(String nome, Jogo jogo) {
        this.nome = nome;
        this.jogo = jogo;
    }

    abstract public void joga();
    
    public boolean isPrimeiroAJogar() {
        return primeiroAJogar;
    }

    public void setPrimeiroAJogar(boolean primeiroAJogar) {
        this.primeiroAJogar = primeiroAJogar;
    }

    public boolean jogou() {
        return jogou;
    }

    public void setJogou(boolean jogou) {
        this.jogou = jogou;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String getProximaJogada() {
        return proximaJogada;
    }

    public void setProximaJogada(String proximaJogada) {
        this.proximaJogada = proximaJogada;
    }
    
}
