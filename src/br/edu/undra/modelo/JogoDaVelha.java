package br.edu.undra.modelo;

import java.util.List;

/**
 * Um jogo da velha
 *
 * @author alexandre
 */
public class JogoDaVelha<T extends Jogador> extends Jogo {

    private String id;

    public JogoDaVelha(String nome, List <T> jogadores, Tabuleiro tabuleiro) {
        super(nome, jogadores, tabuleiro);
    }

    public JogoDaVelha(List<T> jogadores, Tabuleiro tabuleiro) {
        super(jogadores, tabuleiro);
    }

    public JogoDaVelha(String nome, String id, List<T> jogadores, Tabuleiro tabuleiro) {
        super(nome, jogadores, tabuleiro);
        this.id = id;
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
    
    JogadorJodoDaVelha ultimoAJogar = null;
    JogadorJodoDaVelha proximoAJogar = null;
    
    @Override
    public JogadorJodoDaVelha getProximoAJogar() {
       
        if ( proximoAJogar != null ) {
            
            if( !proximoAJogar.jogou() ) return  proximoAJogar;
                       
            for (JogadorJodoDaVelha j : (List<JogadorJodoDaVelha>)getJogadores()) {
                if (!j.equals(ultimoAJogar)) {
                   
                    proximoAJogar = j;
                    break;
                    
                }
            }
            
            
        }else{
            
            for (JogadorJodoDaVelha j : (List<JogadorJodoDaVelha>)getJogadores()) {
                
                if (j.isPrimeiroAJogar()) {
                    
                    proximoAJogar = j;
                    
                }
                
            }
            
        }
        
        proximoAJogar.setJogou(false);
        ultimoAJogar = proximoAJogar;
        
        return proximoAJogar;

    }

//    public static void main(String[] args) {
//
//        Jogador jogador1 = new Jogador("Armando");
//        Jogador jogador2 = new Jogador("Claudio");
//
//        List<Jogador> jogadores = new ArrayList<>();
//        jogadores.add(jogador2);
//        jogadores.add(jogador1);
//
//        jogador1.setPrimeiroAJogar(true);
//        jogador1.setProximoAJogar(true);
//
//        Tabuleiro tabuleiro = new Tabuleiro(3);
//
//        JogoDaVelha jogoDaVelha = new JogoDaVelha("Jogo da Velha", jogadores, tabuleiro);
//
//        System.err.println(jogoDaVelha);
//
//    }


}
