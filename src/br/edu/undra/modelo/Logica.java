package br.edu.undra.modelo;

import java.util.Set;

/**
 * Modela a lógica de um jogo.
 * @author alexandre
 */
public interface Logica<T extends Jogador> {
    
    String getProximaJogada();
    T getProximoAJogar();
    Set<T> getUltimosAJogar();
    
}
