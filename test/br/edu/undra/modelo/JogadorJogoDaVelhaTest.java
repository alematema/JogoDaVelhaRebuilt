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
public class JogadorJogoDaVelhaTest {
    
    JogadorJodoDaVelha jogador1;
    JogadorJodoDaVelha jogador2;
    Tabuleiro tabuleiro;
    JogoDaVelha jogoDaVelha;
    
    public JogadorJogoDaVelhaTest() {
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

        jogoDaVelha = new JogoDaVelha("Jogo da Velha", jogadores, tabuleiro);
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
    public void testJoga(){
        
        System.err.println("testJoga");

        jogador1.setPrimeiroAJogar(true);
        
        assertEquals(jogador1, jogoDaVelha.getProximoAJogar());
        assertTrue(!jogador1.jogou());
        
        
        
    }
    
    
    @Test
    public void testJoga_int_int(){
        
        System.out.println("testJoga_int_int");
        
        setUp();
        
        jogador1.setPrimeiroAJogar(true);
        
        //Tabuleiro 3X3
        //testando posicoes invalidas
        
        
        int linha = 0;//linha INVALIDA
        int coluna = 2;//COLUNA VALIDA
        
        assertFalse(jogador1.joga(linha, coluna));
        
        linha = 0;//linha INVALIDA
        coluna = 4;//COLUNA INVALIDA
        
        assertFalse(jogador1.joga(linha, coluna));
        
        linha = 4;//linha INVALIDA
        coluna = 3;//COLUNA VALIDA
        
        assertFalse(jogador1.joga(linha, coluna));
        
        linha = 3;//linha VALIDA
        coluna = 0;//COLUNA INVALIDA
        
        assertFalse(jogador1.joga(linha, coluna));
        
        
        //testando posicoes VALIDAS
        linha = 1;//linha VALIDA
        coluna = 1;//COLUNA IALIDA
        
        assertTrue(jogador1.joga(linha, coluna));
        
        assertEquals(jogador1.getJogo().getTabuleiro().get(linha, coluna),0);
        
        
    }
    
    @Test
    public void testJoga_int_int_int(){
        
        System.out.println("testJoga_int_int_int");
        
        setUp();
        
        jogador1.setPrimeiroAJogar(true);
        
        //Tabuleiro 3X3
        int linha = 0;
        int coluna = 0;
        
        //Tabuleiro 3X3
        //testando posicoes invalidas
        linha = 0;//linha INVALIDA
        coluna = 2;//COLUNA VALIDA
        
        assertFalse(jogador1.joga(linha, coluna));
        
        //testando posicoes VALIDAS
        linha = 1;//linha VALIDA
        coluna = 1;//COLUNA IALIDA
        
        assertTrue(jogador1.joga(10,linha, coluna));
        
        assertEquals(jogador1.getJogo().getTabuleiro().get(linha, coluna),10);
        
    }
    
    @Test public void testJoga_int(){
        
        System.out.println("testJoga_int");
        
        setUp();
        
        jogador1.setPrimeiroAJogar(true);
        System.out.println(jogador1.joga(2));
        
        System.out.println(jogoDaVelha);
        
        
        
        
    }
    
    @Test public void testJogaNaPosicao_int_int(){
        
        System.out.println("testJogaNaPosicao_int_int");
        
        setUp();
        
        jogador1.setPrimeiroAJogar(true);
        System.out.println(jogador1.jogaNaPosicao(3,-1));
        
        System.out.println(jogoDaVelha);
        
        
    }
    
    
}
