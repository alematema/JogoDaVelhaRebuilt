package br.edu.undra.modelo;

/**
 *
 * @author alexandre
 */
public class JogadorJodoDaVelha<J extends Jogo> extends Jogador {
    
    public JogadorJodoDaVelha(String nome) {
        super(nome);
    }
    
    public JogadorJodoDaVelha(J jogo) {
        super(jogo);
    }

    public JogadorJodoDaVelha(String nome, J jogo) {
        super(nome, jogo);
    }

    @Override
    public void joga() {
        super.joga();
        setJogou(true);
    }
    
    
    
}
