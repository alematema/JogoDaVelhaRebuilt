package br.edu.undra.modelo;

import java.util.ArrayList;
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

        tabuleiro = new Tabuleiro(5);

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
    public void testgetProximaJogadaParaJogador(){
        
        System.err.println("testgetProximaJogadaParaJogador");
        
        setUp();

        jogador1.setPrimeiroAJogar(true);
        
        //pegando proxima jogada para jogador que não jogou
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador1));
        
        //pegando proxima jogada para jogador que JOGOU
        jogoDaVelha.getProximoAJogar().joga();
        assertEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador1));
        
        assertEquals(jogador2,jogoDaVelha.getProximoAJogar());
        assertEquals(jogador2,jogoDaVelha.getProximoAJogar());
        assertEquals(jogador2,jogoDaVelha.getProximoAJogar());
        assertEquals(false, jogador2.jogou());        
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        assertNotEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        
        jogoDaVelha.getProximoAJogar().joga();
        assertEquals(true, jogador2.jogou());        
        assertEquals(null,jogoDaVelha.getProximaJogadaParaJogador(jogador2));
        
        jogoDaVelha.getUltimosAJogar().forEach(e -> System.err.println(e));
        
        
    }

}
