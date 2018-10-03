package br.edu.undra.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modela o tabuleiro NxN do jogo.
 *
 * @author alexandre
 */
public class Tabuleiro {

    static public Object POSICAO_LIVRE = 0;
    static public Object POSICAO_INVALIDA = -1;
    static public Object SEPARADOR = "-";

    /**
     * A dimensão do tabuleiro : 2 ou 3 ou 4 ... ou N.
     */
    private int dimensao;

    /**
     * O tabuleiro : 2x2 ou 3x3 ou 4x4 ... ou NxN.
     */
    private Object[] tabuleiro;

    public Tabuleiro(int dimensao) {

        if (dimensao == 0) {
            throw new IllegalArgumentException("A dimensão não pode ser nula, ou zero.");
        }

        this.dimensao = dimensao < 0 ? -dimensao : dimensao;

        tabuleiro = new Object[dimensao * dimensao];

        for (int i = 0; i < dimensao; i++) {

            for (int j = 0; j < dimensao; j++) {

                tabuleiro[i * dimensao + j] = POSICAO_LIVRE;

            }

        }

    }

    /**
     * get a dimensao
     *
     * @return a dimensao
     */
    public int getDimensao() {
        return dimensao;
    }

    /**
     * Insere o elemento no cruzamento da linha e da coluna.
     *
     * @param elemento o elemento a ser colocado em linhaXcoluna;
     * @param linha a linha em que se colocará o elemento; contada a partir da
     * linha 1.
     * @param coluna a coluna em que se colocará o elemento; contada a partir da
     * coluna 1;
     * @return o elemento que está em linhaXcoluna,<br>ou -1 caso linha ou
     * coluna sejam inválidas.<br> ou -1 caso elemento seja -1;
     */
    public Object set(Object elemento, int linha, int coluna) {

        if (!validar(linha, coluna)) {
            return POSICAO_INVALIDA;
        }

        int linhaZeroBased = linha - 1;
        int colunaZeroBased = coluna - 1;

        //elemento inválido
        try {
            if (((Integer) elemento).equals(POSICAO_INVALIDA)) {
                return POSICAO_INVALIDA;
            }
        } catch (Exception e) {
        }

        tabuleiro[(linhaZeroBased * dimensao + colunaZeroBased)] = elemento;

        return tabuleiro[(linhaZeroBased * dimensao + colunaZeroBased)];

    }

    /**
     * Insere o elemento na posicao, usando divisao modular.
     *
     * @param elemento o elemento a ser colocado em posicao;
     * @param posicao a posicao que será determinada usando divisao
     * modular.<br>Inicia contagem a partir de 1 e vai até NxN;
     * @return o elemento que está em posicao<br>ou -1 caso posicão seja
     * inválida.<br> ou -1 caso elemento seja -1;
     */
    public Object set(Object elemento, int posicao) {

        int linha = transformarEmLinha(posicao);
        int coluna = transformarEmColuna(posicao);

        return set(elemento, linha, coluna);

    }

    /**
     * Recupera o elemento que está no cruzamento da linha e da coluna.
     *
     * @param linha a linha em que está o elemento; contada a partir da linha 1.
     * @param coluna a coluna em que está o elemento; contada a partir da coluna
     * 1;
     * @return o elemento que está em linhaXcoluna, <br>ou zero (caso nada
     * esteja na posicao linha,coluna) <br>ou -1 caso linha ou coluna sejam
     * inválidas. (linha ou coluna < 1 ou linha/coluna > dimensao)
     */
    public Object get(int linha, int coluna) {

        if (!validar(linha, coluna)) {
            return POSICAO_INVALIDA;
        }

        int linhaZeroBased = linha - 1;
        int colunaZeroBased = coluna - 1;

        return tabuleiro[(linhaZeroBased * dimensao + colunaZeroBased)];

    }

    /**
     * Recupera o elemento da posicao, usando divisao modular.
     *
     * @param posicao a posicao que será determinda usando divisao
     * modular.<br>Inicia contagem a partir de 1 e vai até NxN;
     * @return o elemento que está em posição, <br>ou zero (caso nada esteja na
     * posicao ) <br>ou -1 caso posicao seja inválidas (posicao < 1 ou posicao >
     * NxN)
     */
    public Object get(int posicao) {

        int linha = transformarEmLinha(posicao);
        int coluna = transformarEmColuna(posicao);

        return get(linha, coluna);

    }

    /**
     * Recupera a linha do tabuleiro.
     *
     * @param linha o numero da linha (1 <= linha e linha <= dimensao) @ret urn
     * Lista dos elementos da linha<br> ou null, caso linha seja invalida.(linha < 1 ou linha
     * > dimensao)
     */
    public List<Object> getLinha(int linha) {

        if (linha < 1 || linha > dimensao) {
            return null;
        }

        List<Object> l = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            l.add(get(linha, i));

        }

        return l;

    }

    /**
     * Recupera a coluna do tabuleiro.
     *
     * @param coluna o numero da coluna (1 <= coluna e coluna <= dimensao) @ret
     * urn Lista dos elementos da coluna<br> ou null, caso coluna seja invalida.
     * (coluna < 1 ou coluna > dimensao)
     */
    public List<Object> getColuna(int coluna) {

        if (coluna < 1 || coluna > dimensao) {
            return null;
        }

        List<Object> l = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            l.add(get(i, coluna));

        }

        return l;

    }

    /**
     * Recupera a diagonal principal do tabuleiro.
     *
     * @return Lista dos elementos da diagonal principal.
     */
    public List<Object> getDiagonalPrincipal() {

        List<Object> diagonalPrincipal = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            diagonalPrincipal.add(get(i, i));

        }

        return diagonalPrincipal;

    }

    /**
     * Recupera a diagonal secundária do tabuleiro.
     *
     * @return Lista dos elementos da diagonal secundária.
     */
    public List<Object> getDiagonalSecundaria() {

        List<Object> diagonalSecundária = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                if (i + j == dimensao + 1) {
                    diagonalSecundária.add(get(i, j));
                }

            }

        }

        return diagonalSecundária;

    }

    /**
     * Recupera posiçoes OCUPADAS do tabuleiro.
     *
     * @return Lista dos elementos OCUPADOS do tabuleiro.
     */
    public List<Object> getPosicoeOcupadas() {

        List<Object> posicoesOcupadas = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                if (!get(i, j).equals(POSICAO_LIVRE)) {

                    posicoesOcupadas.add(i + "," + j + "," + (((i - 1) * dimensao + (j - 1)) + 1));

                }

            }

        }

        return posicoesOcupadas;

    }

    /**
     * Recupera posiçoes livres do tabuleiro.
     *
     * @return Lista dos elementos desocupados do tabuleiro.
     */
    public List<Object> getPosicoesLivres() {

        List<Object> posicoesLivres = new ArrayList<>();

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                if (get(i, j).equals(POSICAO_LIVRE)) {

                    posicoesLivres.add(i + "," + j + "," + (((i - 1) * dimensao + (j - 1)) + 1));

                }

            }

        }

        return posicoesLivres;

    }

    /**
     * Diz se uma posicao indicada por linhaXcoluna está livre.
     *
     * @param linha a linha
     * @param coluna a coluna
     * @return true, se está livre a posicao.<br>false, caso ocupada, ou
     * linhaXcoluna seja inválida.
     */
    public boolean isPosicaoLivre(int linha, int coluna) {

        Object o = get(linha, coluna);

        if (o.equals(POSICAO_INVALIDA)) {
            return false;
        }

        return o.equals(POSICAO_LIVRE);

    }

    /**
     * Diz se uma posicao indicada por posicao está livre.
     *
     * @param posicao a posicao
     * @return true, se está livre a posicao.<br>false, caso ocupada, ou posicao
     * seja inválida.
     */
    public boolean isPosicaoLivre(int posicao) {

        if (posicao <= 0) {
            return isPosicaoLivre(0, 0);
        }
        if (posicao == 1) {
            return isPosicaoLivre(1, 1);
        }
        if (posicao == dimensao * dimensao) {
            return isPosicaoLivre(dimensao, dimensao);
        }

        int linha = transformarEmLinha(posicao);
        int coluna = transformarEmColuna(posicao);

        return isPosicaoLivre(linha , coluna);

    }

    /**
     * Retorna uma String correspondente ao estado do tabuleiro.<br>
     * O estado são os valores que estao armazenados nas posicoes do tabuleiro.
     * <br>Os valores serao separados por hifen -
     * <br>1,1,0 corresponde ao valor 0, que esta na linha 1 e na coluna 1
     *
     * @return String da forma 1,1,0-1,2,0-...
     */
    public String getEstado() {

        String estado = "";

        for (int linha = 1; linha <= dimensao; linha++) {

            for (int coluna = 1; coluna <= dimensao; coluna++) {
                estado += linha + "," + coluna + "," + get(linha, coluna) + SEPARADOR;
            }

        }

        return estado.substring(0, estado.length() - 1);

    }

    /**
     * Transforma uma posicao em linha que lhe corresponde.
     * <br>Exemplo : 1 e 2 e 3 sao trasformadas em linha 1, num tabuleiro 5x5.<br>
     * 6,7 e 8 são transformadas em linha 2.
     * @param posicao a posicao a se transformar
     * @return a linha correspondente à posicao
     */
    public int transformarEmLinha(int posicao) {
        if (posicao == 0) {
            return 0;
        }
        return ((posicao - 1) / dimensao)+1;
    }

    /**
     * Transforma uma posicao em coluna que lhe corresponde.
     * <br>Exemplo : 1 e 2 e 3 sao trasformadas em coluna 1, coluna 2 e coluna 3
     * num tabuleiro 5x5.<br>
     * 6,7 e 8 são transformadas em coluna 1, coluna 2 e coluna 3.
     *
     * @param posicao a posicao a se transformar
     * @return a coluna correspondente à posicao
     */
    public int transformarEmColuna(int posicao) {

        int coluna = posicao % dimensao;
        if (coluna == 0) {
            coluna = dimensao;
        }

        return coluna;
    }

    
    /**
     * Valida se linha e coluna estao dentro dos limites.
     * @param linha  1 menor ou igual que linha E linha menor ou igual que dimensao
     * @param coluna 1 menor ou igual que coluna E coluna menor ou igual que dimensao
     * @return true , se 1 menor ou igual que linha E linha menor ou igual que dimensao<br>
     * &&<br>
     * 1 menor ou igual que coluna E coluna menor ou igual que dimensao.<br>
     * false, caso contrario.
     */
    public boolean validar(int linha, int coluna) {

        int linhaZeroBased = linha - 1;
        int colunaZeroBased = coluna - 1;

        //linha INVÁLIDA
        if (linhaZeroBased < 0 || linhaZeroBased > dimensao - 1) {
            return false;
        }
        //coluna INVÁLIDA
        if (colunaZeroBased < 0 || colunaZeroBased > dimensao - 1) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {

        String toString = "";

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                toString += get(i, j);
                toString += " ";

            }

            toString += "\n";

        }

        return toString;

    }
    
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(5);
        System.out.println(tabuleiro.transformarEmColuna(1));
        System.out.println(tabuleiro.transformarEmColuna(2));
        System.out.println(tabuleiro.transformarEmColuna(3));
        System.out.println(tabuleiro.transformarEmColuna(6));
        System.out.println(tabuleiro.transformarEmColuna(7));
        System.out.println(tabuleiro.transformarEmColuna(8));
        
        System.out.println();

        
        System.out.println(tabuleiro.transformarEmLinha(1));
        System.out.println(tabuleiro.transformarEmLinha(2));
        System.out.println(tabuleiro.transformarEmLinha(3));
        System.out.println(tabuleiro.transformarEmLinha(6));
        System.out.println(tabuleiro.transformarEmLinha(7));
        System.out.println(tabuleiro.transformarEmLinha(8));
        
    }

}
