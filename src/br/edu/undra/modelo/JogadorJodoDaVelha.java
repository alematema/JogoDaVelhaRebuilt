package br.edu.undra.modelo;

import java.util.List;

/**
 * Modela um jogador de jogo da velha.
 *
 * @author alexandre
 * @param <J> algo que extenda de Jogo
 */
public class JogadorJodoDaVelha<J extends Jogo> extends Jogador {

    private int atual = 1;
    private int elemento = 0;

    public JogadorJodoDaVelha(String nome) {
        super(nome);
    }

    public JogadorJodoDaVelha(J jogo) {
        super(jogo);
    }

    public JogadorJodoDaVelha(String nome, J jogo) {
        super(nome, jogo);
    }

    public int getAtual() {
        return atual;
    }

    public void setAtual(int atual) {
        this.atual = atual;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    @Override
    public void desfazerUltimaJogada() {

        if (getJogadas().size() >= 1) {

            String ultimaJogada = (String) getJogadas().get(getJogadas().size() - 1);

            //recupera da ultima jogada a posicao ou linhaXcoluna
            int linha = Integer.parseInt(ultimaJogada.split(",")[0]);
            int coluna = Integer.parseInt(ultimaJogada.split(",")[1]);

            //escreve o valor de POSICAO_LIVRE em linhaXcoluna
            getJogo().getTabuleiro().set(Tabuleiro.POSICAO_LIVRE, linha, coluna);

            getJogadas().remove(getJogadas().size() - 1);

            //atualiza estado do jogador 
            //jogador1 atual 3 e elemento 1
            //jogador2 atual 4 e elemento 2
            //jogador1 atual 5 e elemento 3
            //jogador2 atual 6 e elemento 4
//                if( ( atual - 2 ) < 1 ) {
//                    atual = 1;
//                }else{
//                    atual = atual - 2;
//                }
//                
//                if((elemento - 2 ) < 0){
//                    elemento = 0;
//                }else{
//                    elemento = elemento - 2;
//                }
            getJogo().setProximoAJogar(this);

            for (JogadorJodoDaVelha j : (List<JogadorJodoDaVelha>) getJogo().getJogadores()) {
                if (!j.equals(this)) {

                    if ((j.getAtual() - 2) < 1) {
                        j.setAtual(1);
                    } else {
                        j.setAtual(j.getAtual() - 2);;
                    }

                    if ((j.getElemento() - 2) < 0) {
                        j.setElemento(0);
                    } else {
                        j.setElemento(j.getElemento() - 2);
                    }

                    break;

                }
            }

            setJogou(false);

        } else {
            return;
        }

    }

    @Override
    public JogoDaVelha getJogo() {
        return (JogoDaVelha) super.getJogo();
    }

    @Override
    public void joga() {

        //logica de jogar vai aqui
        String posicao = getJogo().getProximaJogadaParaJogador(this);

        int linha = Integer.parseInt(posicao.split(",")[0]);
        int coluna = Integer.parseInt(posicao.split(",")[1]);

        getJogo().getTabuleiro().set(elemento, linha, coluna);

        getJogadas().add(posicao + "," + elemento);

        getJogo().getUltimosAJogar().add(this);

        System.out.println(getNome() + " jogou");

        setJogou(true);

    }

    public boolean joga(int linha, int coluna) {

        boolean posicoesValidas = getJogo().getTabuleiro().validar(linha, coluna);
        if (!posicoesValidas) {
            return false;
        }

        getJogo().getTabuleiro().set(elemento, linha, coluna);

        getJogo().getUltimosAJogar().add(this);

        System.out.println(getNome() + " jogou " + elemento + " na posicao " + linha + "," + coluna);

        setJogou(true);

        return true;

    }

    public boolean joga(int elemento, int linha, int coluna) {

        setElemento(elemento);
        return joga(linha, coluna);

    }

    public boolean joga(int posicao) {

        int linha = getJogo().getTabuleiro().transformarEmLinha(posicao);
        int coluna = getJogo().getTabuleiro().transformarEmColuna(posicao);

        return joga(linha + 1, coluna);

    }

    public boolean jogaNaPosicao(int elemento, int posicao) {

        setElemento(elemento);
        return joga(posicao);

    }

}
