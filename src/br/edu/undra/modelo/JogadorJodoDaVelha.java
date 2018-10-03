package br.edu.undra.modelo;

/**
 * Modela um jogador de jogo da velha.
 * @author alexandre
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
    public void joga() {

        //logica de jogar vai aqui
        
        getJogo().getUltimosAJogar().add(this);
        System.out.println(getNome() + " jogou");
        setJogou(true);

    }

    public boolean joga(int linha, int coluna) {

        boolean posicoesValidas = getJogo().getTabuleiro().validar(linha, coluna);
        if (!posicoesValidas) {
            return false;
        }

        

//        if (isPrimeiroAJogar()) {
//
//        } else {
//            if (atual == 1) {
//                atual = 2;
//            }
//        }
//
//        elemento = atual;
//        atual += 2;

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
        
        return joga(linha+1, coluna);
        
    }
    
    public boolean jogaNaPosicao(int elemento,int posicao) {
        
        setElemento(elemento);
        return joga(posicao);
        
    }

}
