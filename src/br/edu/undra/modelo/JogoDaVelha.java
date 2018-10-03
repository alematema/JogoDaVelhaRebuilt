package br.edu.undra.modelo;

import java.util.Arrays;
import java.util.List;

/**
 * Um jogo da velha
 *
 * @author alexandre
 */
public class JogoDaVelha<T extends Jogador> extends Jogo {

    private String id;

    private JogadorJodoDaVelha jogador1;
    private JogadorJodoDaVelha jogador2;

    public JogoDaVelha(String nome) {

        super();

        jogador1 = new JogadorJodoDaVelha("jogador 1");
        jogador2 = new JogadorJodoDaVelha("jogador 2");
        Tabuleiro tabuleiro = new Tabuleiro(3);

        List<JogadorJodoDaVelha> jogadores = Arrays.asList(jogador1, jogador2);
        setNome(nome);
        setJogadores(jogadores);
        setTabuleiro(tabuleiro);

        setUpJogadores();
    }

    public JogoDaVelha(String nome, List<T> jogadores, Tabuleiro tabuleiro) {
        super(nome, jogadores, tabuleiro);
        if (jogadores.size() != 2) {
            throw new IllegalArgumentException("Devem haver EXATAMENTE 2 jogadores para o jogo da velha ok.");
        }
        setUpJogadores();
    }

    public JogoDaVelha(List<T> jogadores, Tabuleiro tabuleiro) {
        super(jogadores, tabuleiro);
        if (jogadores.size() != 2) {
            throw new IllegalArgumentException("Devem haver EXATAMENTE 2 jogadores para o jogo da velha ok.");
        }
        setUpJogadores();
    }

    public JogoDaVelha(String nome, String id, List<T> jogadores, Tabuleiro tabuleiro) {
        super(nome, jogadores, tabuleiro);
        if (jogadores.size() != 2) {
            throw new IllegalArgumentException("Devem haver EXATAMENTE 2 jogadores para o jogo da velha ok.");
        }
        this.id = id;
        setUpJogadores();
    }

    @Override
    public void setUpJogadores() {
        for (JogadorJodoDaVelha jogador : (List<JogadorJodoDaVelha>) getJogadores()) {
            jogador.setJogo(this);
        }
    }

    public JogadorJodoDaVelha getJogador1() {
        return jogador1;
    }

    public JogadorJodoDaVelha getJogador2() {
        return jogador2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String toString = "";

        toString += getNome();
        toString += "\n\n";
        toString += getTabuleiro().toString();

        return toString;
    }

    JogadorJodoDaVelha proximoAJogar = null;

    public void setProximoAJogar(JogadorJodoDaVelha proximoAJogar) {
        this.proximoAJogar = proximoAJogar;
    }

    @Override
    public JogadorJodoDaVelha getProximoAJogar() {

        if (proximoAJogar != null) {

            if (!proximoAJogar.jogou()) {
                return proximoAJogar;
            }

            getUltimosAJogar().clear();

            for (JogadorJodoDaVelha j : (List<JogadorJodoDaVelha>) getJogadores()) {

                if (!j.equals(proximoAJogar)) {

                    proximoAJogar = j;
                    break;

                }
            }

        } else {

            for (JogadorJodoDaVelha j : (List<JogadorJodoDaVelha>) getJogadores()) {

                if (j.isPrimeiroAJogar()) {

                    proximoAJogar = j;

                }

            }

        }

        proximoAJogar.setElemento(proximoAJogar.getAtual());
        proximoAJogar.setAtual(proximoAJogar.getAtual() + 2);

        proximoAJogar.setJogou(false);

        return proximoAJogar;

    }

    @Override
    public String getProximaJogadaParaJogador(Jogador jogador) {

        if (jogador.jogou()) {
            return null;
        }

        List<Object> posicoesLivres = getTabuleiro().getPosicoesLivres();

        int posicao = (int) Math.random() * posicoesLivres.size();

        String posicaoLivre = (String) posicoesLivres.get(posicao);

        String[] p = posicaoLivre.split(",");

        System.out.println("pegando proxima jogada " + p[0] + "," + p[1] + " para " + jogador.getNome() + ", " + System.nanoTime());

        return p[0] + "," + p[1];

    }

    public boolean jogadorVenceu(JogadorJodoDaVelha jogador) {

        boolean venceu = false;

        List<Object> elementos;

        //varre colunas procurando trinca
        for (int coluna = 1; coluna <= getTabuleiro().getDimensao(); coluna++) {

            elementos = getTabuleiro().getColuna(coluna);

            if (aoMenosUmaTrinca(elementos, jogador)) {
                venceu = true;
                break;
            }

        }

        if (!venceu) {//CONTINUA PROCURANDO TRINCA ...

            //varre linhas procurando trinca
            for (int linha = 1; linha <= getTabuleiro().getDimensao(); linha++) {

                elementos = getTabuleiro().getLinha(linha);

                if (aoMenosUmaTrinca(elementos, jogador)) {
                    venceu = true;
                    break;
                }

            }

        }
        
        if (!venceu) {//CONTINUA PROCURANDO TRINCA ...

            //varre diagonal principal procurando trinca
            for (int i = 1; i <= getTabuleiro().getDimensao(); i++) {

                elementos = getTabuleiro().getDiagonalPrincipal();

                if (aoMenosUmaTrinca(elementos, jogador)) {
                    venceu = true;
                    break;
                }

            }

        }
        
        if (!venceu) {//CONTINUA PROCURANDO TRINCA ...

            //varre diagonal secundaria procurando trinca
            for (int i = 1; i <= getTabuleiro().getDimensao(); i++) {

                elementos = getTabuleiro().getDiagonalSecundaria();

                if (aoMenosUmaTrinca(elementos, jogador)) {
                    venceu = true;
                    break;
                }

            }

        }

        return venceu;
    }

    public boolean aoMenosUmaTrinca(List<Object> elementos, JogadorJodoDaVelha jogador) {

        boolean aoMenosUmaTrinca = true;

        if (jogador.isPrimeiroAJogar()) {
            //procura por apenas IMPARES
            for (Object e : elementos) {
                if (((Integer) e) % 2 == 0) {
                    aoMenosUmaTrinca = false;
                    break;
                }
            }

        } else {

            //procura por apenas PARES, DIFERENTES DE MARCADOR POSICAO_LIVRE
            for (Object e : elementos) {
                if (((Integer) e) == (Integer) Tabuleiro.POSICAO_LIVRE || (((Integer) e) % 2 != 0)) {
                    aoMenosUmaTrinca = false;
                    break;
                }
            }

        }

        return aoMenosUmaTrinca;
    }
}
