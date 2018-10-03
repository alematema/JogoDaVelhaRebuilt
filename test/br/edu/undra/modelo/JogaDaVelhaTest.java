package br.edu.undra.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexandre
 */
public class JogaDaVelhaTest {

    JogadorJodoDaVelha jogador1;
    JogadorJodoDaVelha jogador2;
    Tabuleiro tabuleiro;
    JogoDaVelha jogoDaVelha;
    int dimensaoTabuleiro = 3;

    public JogaDaVelhaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        jogador1 = new JogadorJodoDaVelha("Armando");
        jogador2 = new JogadorJodoDaVelha("Claudio");

        List<JogadorJodoDaVelha> jogadores = new ArrayList<>();
        jogadores.add(jogador2);
        jogadores.add(jogador1);

        tabuleiro = new Tabuleiro(dimensaoTabuleiro);

        jogoDaVelha = new JogoDaVelha("Jogo da Velha", jogadores, tabuleiro);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetProximoAJogar() {

        System.err.println("testGetProximoAJogar");

        jogador1.setPrimeiroAJogar(true);

        assertEquals(jogador1, jogoDaVelha.getProximoAJogar());
        assertTrue(!jogador1.jogou());
        assertEquals(jogador1, jogoDaVelha.getProximoAJogar());
        assertTrue(!jogador1.jogou());

        jogoDaVelha.getProximoAJogar().joga();

        assertTrue(jogador1.jogou());

        assertEquals(jogador2, jogoDaVelha.getProximoAJogar());
        assertTrue(!jogador2.jogou());
        assertEquals(jogador2, jogoDaVelha.getProximoAJogar());
        assertTrue(!jogador2.jogou());

        jogoDaVelha.getProximoAJogar().joga();

        assertTrue(jogador2.jogou());

    }

    @Test
    public void testgetProximaJogadaParaJogador() {

        System.err.println("testgetProximaJogadaParaJogador");

        setUp();

        jogador1.setPrimeiroAJogar(true);

        //pegando proxima jogada para jogador que não jogou
        assertNotEquals(null, jogoDaVelha.getProximaJogadaParaJogador(jogador1));

        //pegando proxima jogada para jogador que JOGOU
        jogoDaVelha.getProximoAJogar().joga();
        assertEquals(null, jogoDaVelha.getProximaJogadaParaJogador(jogador1));

        assertEquals(jogador2, jogoDaVelha.getProximoAJogar());
        assertEquals(jogador2, jogoDaVelha.getProximoAJogar());
        assertEquals(jogador2, jogoDaVelha.getProximoAJogar());
        assertEquals(false, jogador2.jogou());
        assertNotEquals(null, jogoDaVelha.getProximaJogadaParaJogador(jogador2));

//        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
//        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
//        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
//        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
//        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        jogoDaVelha.getProximoAJogar().joga();
        assertEquals(true, jogador2.jogou());
        assertEquals(null, jogoDaVelha.getProximaJogadaParaJogador(jogador2));

        jogoDaVelha.getUltimosAJogar().forEach(e -> System.err.println(e));

        jogoDaVelha.getProximoAJogar();
        assertEquals(jogador1, jogoDaVelha.getProximoAJogar());
        System.out.println(jogoDaVelha.getProximaJogadaParaJogador(jogador1));
        System.out.println(jogoDaVelha.getProximaJogadaParaJogador(jogador1));

//        System.out.println(jogoDaVelha.getProximaJogadaParaJogador(jogador1)); 
//        System.out.println(jogoDaVelha.getProximaJogadaParaJogador(jogador1)); 
//        System.out.println(jogoDaVelha.getProximaJogadaParaJogador(jogador1)); 
    }

    @Test
    public void testEstadoJogadorAposGetProximoAJogar() {

        System.err.println("testEstadoJogadorAposGetProximoAJogar");

        setUp();

        jogador1.setPrimeiroAJogar(true);

        assertEquals(1, jogador1.getAtual());
        assertEquals(2, jogador2.getAtual());

        //muda estados atual de jogador1 apenas
        jogoDaVelha.getProximoAJogar();

        assertEquals(3, jogador1.getAtual());
        assertEquals(1, jogador1.getElemento());
        assertEquals(2, jogador2.getAtual());

        //NAO MUDA estado do jogadores
        jogoDaVelha.getProximoAJogar();
        assertEquals(3, jogador1.getAtual());
        assertEquals(2, jogador2.getAtual());
        assertEquals(1, jogador1.getElemento());

        //NAO muda estados APOS jodador1 JOGAR
        jogoDaVelha.getProximoAJogar().joga();

        assertEquals(3, jogador1.getAtual());
        assertEquals(2, jogador2.getAtual());
        assertEquals(1, jogador1.getElemento());

        //muda estados atual de jogador2 apenas
        jogoDaVelha.getProximoAJogar();
        assertEquals(3, jogador1.getAtual());
        assertEquals(1, jogador1.getElemento());
        assertEquals(4, jogador2.getAtual());
        assertEquals(2, jogador2.getElemento());

        //NAO muda estados APOS jodador2 JOGAR
        jogoDaVelha.getProximoAJogar().joga();

        assertEquals(3, jogador1.getAtual());
        assertEquals(1, jogador1.getElemento());
        assertEquals(4, jogador2.getAtual());
        assertEquals(2, jogador2.getElemento());

        //houveram APENAS DUAS jogadas. ENTAO, NUMERO DE POSICOES LIVRES DEVE SER 
        // 9 - 2 = 7 POSICOES LIVRES
        assertEquals(7, jogoDaVelha.getTabuleiro().getPosicoesLivres().size());

        //houveram APENAS DUAS jogadas. ENTAO, AS POSICOES OCUPADAS DEVEM CONTER OS VALORES 1 E 2, APENAS    
        assertEquals(2, jogoDaVelha.getTabuleiro().getPosicoeOcupadas().size());
        List<Object> posicoesOcupadas = jogoDaVelha.getTabuleiro().getPosicoeOcupadas();
        List<Integer> valoresQueNaoDevemEstarNoTabuleiro = Arrays.asList(3, 4, 5, 6, 7, 8, 9);

        for (Object posicao : posicoesOcupadas) {

            String pos = (String) posicao;

            int linha = Integer.parseInt(pos.split(",")[0]);
            int coluna = Integer.parseInt(pos.split(",")[1]);

            int valor = (Integer) jogoDaVelha.getTabuleiro().get(linha, coluna);

            assertTrue(!valoresQueNaoDevemEstarNoTabuleiro.contains(valor));

            if ((valor != 1)) {

                assertEquals(2, valor);

            } else if (valor == 1) {//redundante
            } else {
                fail("ESTADOS INCONSISTENTES DOS JOGADORES. ");
            }

        }

        //muda estados atual de jogador1 apenas
        jogoDaVelha.getProximoAJogar();
        assertEquals(5, jogador1.getAtual());
        assertEquals(3, jogador1.getElemento());
        assertEquals(4, jogador2.getAtual());
        assertEquals(2, jogador2.getElemento());

        //NAO muda estados APOS jodador1 JOGAR
        jogoDaVelha.getProximoAJogar().joga();
        assertEquals(5, jogador1.getAtual());
        assertEquals(3, jogador1.getElemento());
        assertEquals(4, jogador2.getAtual());
        assertEquals(2, jogador2.getElemento());

        //muda estados atual de jogador2 apenas
        jogoDaVelha.getProximoAJogar();
        assertEquals(5, jogador1.getAtual());
        assertEquals(3, jogador1.getElemento());
        assertEquals(6, jogador2.getAtual());
        assertEquals(4, jogador2.getElemento());

        //houveram APENAS TRES jogadas. ENTAO, NUMERO DE POSICOES LIVRES DEVE SER 
        // 9 - 3 = 6 POSICOES LIVRES
        assertEquals(6, jogoDaVelha.getTabuleiro().getPosicoesLivres().size());

        //houveram APENAS TRES jogadas. ENTAO, AS POSICOES OCUPADAS DEVEM CONTER OS VALORES 1,2 E 3, APENAS    
        assertEquals(3, jogoDaVelha.getTabuleiro().getPosicoeOcupadas().size());
        posicoesOcupadas = jogoDaVelha.getTabuleiro().getPosicoeOcupadas();
        valoresQueNaoDevemEstarNoTabuleiro = Arrays.asList(4, 5, 6, 7, 8, 9);
        List<Integer> valoresQueDevemEstarNoTabuleiro = Arrays.asList(1, 2, 3);

        boolean todosValoresDistintosEntreSi = true;
        int ultimoValor=Integer.MAX_VALUE;
        
        for (Object posicao : posicoesOcupadas) {

            String pos = (String) posicao;

            int linha = Integer.parseInt(pos.split(",")[0]);
            int coluna = Integer.parseInt(pos.split(",")[1]);

            int valor = (Integer) jogoDaVelha.getTabuleiro().get(linha, coluna);

            
            assertTrue(!valoresQueNaoDevemEstarNoTabuleiro.contains(valor));

            todosValoresDistintosEntreSi = todosValoresDistintosEntreSi&&(ultimoValor!=valor);
            
            ultimoValor = valor;

        }
        
        if( ! todosValoresDistintosEntreSi )fail("ESTADOS INCONSISTENTES DOS JOGADORES. ");

    }

}
