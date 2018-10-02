/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.undra.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class TabuleiroTest {

    public TabuleiroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of set method, of class Tabuleiro.
     */
    @Test
    public void testSet() {

        System.out.println("set");

        int elemento = 10;
        int linha = 1;
        int coluna = 0;
        Tabuleiro tabuleiro = new Tabuleiro(2);
        int expResult = -1;
        int result;

        //testando linha e coluna fora dos limites 
        elemento = 10;
        linha = 2;
        coluna = 0;
        expResult = -1;
        result = (Integer) tabuleiro.set(elemento, linha, coluna);
        assertEquals(expResult, result);

        elemento = 10;
        linha = 0;
        coluna = 2;
        expResult = -1;
        result = (Integer) tabuleiro.set(elemento, linha, coluna);
        assertEquals(expResult, result);

        //testando linha e coluna DENTRO dos limites
        elemento = 10;
        linha = 2;
        coluna = 2;
        expResult = 10;
        result = (Integer) tabuleiro.set(elemento, linha, coluna);
        assertEquals(expResult, result);

        linha = 1;
        coluna = 1;
        expResult = 10;
        result = (Integer) tabuleiro.set(elemento, linha, coluna);
        assertEquals(expResult, result);

        //testando inserir elemento INVÁLIDO -1
        elemento = -1;
        linha = 2;
        coluna = 2;
        expResult = -1;
        result = (Integer) tabuleiro.set(elemento, linha, coluna);
        assertEquals(expResult, result);

    }

    /**
     * Test of get method, of class Tabuleiro.
     */
    @Test
    public void testGet() {

        System.out.println("get");
        int elemento;
        int linha = 0;
        int coluna = 0;
        Tabuleiro tabuleiro = new Tabuleiro(2);
        int expResult = 0;
        int result;

        //testando linha e coluna fora dos limites 
        linha = 2;
        coluna = 0;
        expResult = -1;
        result = (Integer) tabuleiro.get(linha, coluna);
        assertEquals(expResult, result);

        linha = 0;
        coluna = 2;
        expResult = -1;
        result = (Integer) tabuleiro.get(linha, coluna);
        assertEquals(expResult, result);

        linha = 1;
        coluna = 3;
        expResult = -1;
        result = (Integer) tabuleiro.get(linha, coluna);
        assertEquals(expResult, result);

        //testando linha e coluna DENTRO dos limites
        elemento = 10;
        linha = 2;
        coluna = 2;
        expResult = 10;
        tabuleiro.set(elemento, linha, coluna);
        result = (Integer) tabuleiro.get(linha, coluna);
        assertEquals(expResult, result);

        linha = 1;
        coluna = 1;
        expResult = 10;
        tabuleiro.set(elemento, linha, coluna);
        result = (Integer) tabuleiro.get(linha, coluna);
        assertEquals(expResult, result);

        int dimensao = 5;

        tabuleiro = new Tabuleiro(dimensao);

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                String elemento2 = i + "" + j;
                linha = i;
                coluna = j;
                String expResult2 = i + "" + j;

                tabuleiro.set(elemento2, linha, coluna);
                String result2 = (String) tabuleiro.get(linha, coluna);

                assertEquals(expResult2, result2);

                System.out.print(result2 + " ");
            }

            System.out.println("");

        }
        System.out.println("");
        System.out.println("");
        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                assertEquals(i + "" + j, tabuleiro.get(i, j));

                System.out.print(tabuleiro.get(i, j) + " ");
            }
            System.out.println("");

        }

        //testando retorno de posicao vazia
        tabuleiro = new Tabuleiro(dimensao);

        for (int i = 1; i <= dimensao; i++) {

            for (int j = 1; j <= dimensao; j++) {

                assertEquals(0, tabuleiro.get(i, j));

                System.out.print(tabuleiro.get(i, j) + " ");

            }

            System.out.println("");

        }

        tabuleiro.set("ola", 1, 1);
        System.out.println(tabuleiro);
    }

    /**
     * Test of set method, of class Tabuleiro.
     */
    @Test
    public void testSet_Object_int() {
        System.out.println("set_Object_int");
        Object elemento = null;
        int posicao = 0;
        int dimensao = 8;
        Tabuleiro instance = new Tabuleiro(dimensao);
        Object expResult = -1;
        Object result = instance.set(elemento, posicao);
        assertEquals(expResult, result);

        //testa limites : posicao 1 ate posicao NxN = 64 nesse caso
        posicao = 1;
        expResult = 2;
        result = instance.set(expResult, posicao);
        assertEquals(expResult, result);

        posicao = dimensao * dimensao;
        expResult = 2;
        result = instance.set(expResult, posicao);
        assertEquals(expResult, result);

        //testa FORA DOS limites : posicao MENOR DO QUE 1 EOU posicao MAIOR DO QUE NxN = 64 nesse caso
        posicao = 0;
        expResult = -1;
        result = instance.set(10, posicao);
        assertEquals(expResult, result);

        posicao = dimensao * dimensao + 1;
        expResult = -1;
        result = instance.set(10, posicao);
        assertEquals(expResult, result);

        posicao = -1;
        expResult = -1;
        result = instance.set(10, posicao);
        assertEquals(expResult, result);

    }

    /**
     * Test of get method, of class Tabuleiro.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int posicao = 0;
        int dimensao = 10;

        Object expResult = null;
        Object result;
        ;

        //testando retorno de posicao vazia
        Tabuleiro tabuleiro = new Tabuleiro(dimensao);

        for (int i = 1; i <= dimensao * dimensao; i++) {

            assertEquals(0, tabuleiro.get(i));

        }

        //testa limites : posicao 1 ate posicao NxN = 64 nesse caso
        posicao = 1;
        expResult = 2;
        result = tabuleiro.set(expResult, posicao);
        assertEquals(expResult, tabuleiro.get(posicao));

        posicao = dimensao * dimensao;
        expResult = 2;
        result = tabuleiro.set(expResult, posicao);
        assertEquals(expResult, tabuleiro.get(posicao));

        //testa FORA DOS limites : posicao MENOR DO QUE 1 EOU posicao MAIOR DO QUE NxN = 64 nesse caso
        posicao = 0;
        expResult = -1;
        assertEquals(expResult, tabuleiro.get(posicao));

        posicao = dimensao * dimensao + 1;
        expResult = -1;
        assertEquals(expResult, tabuleiro.get(posicao));

        posicao = -1;
        expResult = -1;
        assertEquals(expResult, tabuleiro.get(posicao));

    }

    /**
     * Test of getLinha method, of class Tabuleiro.
     */
    @Test
    public void testGetLinha() {
        System.out.println("getLinha");
        
        int linha = 0;
        int dimensao = 5;
        Tabuleiro tabuleiro = new Tabuleiro(dimensao);

        Map<Integer,List<Object>> lineMap = new HashMap<>();
        
        for (int i = 1; i <= dimensao; i++) {

            List<Object> line = new ArrayList();
            lineMap.put(i, line);
            
            for (int j = 1; j <= dimensao; j++) {
                
                String elemento = i + "" + j;

                tabuleiro.set(elemento, i, j);
                
                line.add(elemento);

            }

        }
        
        List<Object> expResult = Arrays.asList("11", "12", "13", "14", "15");
        assertEquals(expResult, tabuleiro.getLinha(1));

        
        // testa DENTRO dos limites 1 <= linha <= dimensao
        for (int i = 1; i <= dimensao; i++) {
            assertEquals(lineMap.get(i), tabuleiro.getLinha(i));
        }
        
        // testa FORA dos limites : linha <= 0 e linha >= dimensao + 1 <= linha <= dimensao
        assertEquals(null, tabuleiro.getLinha(0));
        assertEquals(null, tabuleiro.getLinha(dimensao+1));
        
        
        
    }

    

    /**
     * Test of getColuna method, of class Tabuleiro.
     */
    @Test
    public void testGetColunaLinha() {
        System.out.println("getColunaLinha");
        int coluna = 0;
        int dimensao = 5;
        Tabuleiro tabuleiro = new Tabuleiro(dimensao);
        
        
        //cria um tabuleiro NxN
        for (int i = 1; i <= dimensao; i++) {
                       
            for (int j = 1; j <= dimensao; j++) {
                
                String elemento = i + "" + j;

                tabuleiro.set(elemento, i, j);
                
            }

        }

        
        //mapeia as colunas do tabuleiro
        Map<Integer,List<Object>> colunaMap = new HashMap<>();
        for (int col = 1; col <= dimensao; col++) {

            List<Object> columm = new ArrayList();
            colunaMap.put(col, columm);
            
            for (int linha = 1; linha <= dimensao; linha++) {
                
                columm.add(tabuleiro.get(linha,col));
                
            }

        }

        // testa DENTRO dos limites 1 <= coluna <= dimensao
        for (int col = 1; col <= dimensao; col++) {
            assertEquals(colunaMap.get(col), tabuleiro.getColuna(col));
        }
        
        // testa FORA dos limites : coluna <= 0 e coluna >= dimensao + 1 
        assertEquals(null, tabuleiro.getColuna(0));
        assertEquals(null, tabuleiro.getColuna(dimensao+1));
        
        System.err.println(tabuleiro);
//        System.err.println(tabuleiro.getDiagonalPrincipal());
//        System.err.println(tabuleiro.getDiagonalSecundaria());

        tabuleiro.set(Tabuleiro.POSICAO_LIVRE, 10);
        System.err.println(tabuleiro);

        System.err.println(tabuleiro.getPosicoesLivres());
        System.err.println(tabuleiro.get(10));
        
        tabuleiro.set(Tabuleiro.POSICAO_LIVRE, 11);
        System.err.println(tabuleiro);

        System.err.println(tabuleiro.getPosicoesLivres());
        System.err.println(tabuleiro.get(11));
        
        tabuleiro = new Tabuleiro(dimensao);
        System.err.println(tabuleiro);

        System.err.println(tabuleiro.getPosicoesLivres());
        System.err.println(tabuleiro.get(10));
        
    }

}
