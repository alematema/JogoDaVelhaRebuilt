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

        tabuleiro = new Tabuleiro(3);

        jogoDaVelha = new JogoDaVelha("Jogo da Velha","id1", jogadores, tabuleiro);

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
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

}
