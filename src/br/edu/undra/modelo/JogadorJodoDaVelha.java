package br.edu.undra.modelo;

/**
 *
 * @author alexandre
 */
public class JogadorJodoDaVelha extends Jogador {
    
    public JogadorJodoDaVelha(String nome) {
        super(nome);
    }
    
    public JogadorJodoDaVelha(Jogo jogo) {
        super(jogo);
    }

    public JogadorJodoDaVelha(String nome, Jogo jogo) {
        super(nome, jogo);
    }

    @Override
    public void joga() {
        super.joga();
        setJogou(true);
    }
    
    
    
}
