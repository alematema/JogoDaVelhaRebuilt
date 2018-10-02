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

        //logica de jogar vai aqui

        getJogo().getUltimosAJogar().add(this);
        System.out.println(getNome() + " jogou");
        setJogou(true);

    }

    public void joga(int posicao) {

    }

}
