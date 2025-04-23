package eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenciadorDeEventos {

    private List<Evento> eventosPossiveis;
    private int frequenciaEventos;

    public GerenciadorDeEventos(Evento[] eventos, int frequenciaEventos) {
        this.eventosPossiveis = Arrays.asList(eventos);  // Agora é uma lista de Eventos
        this.frequenciaEventos = frequenciaEventos;
    }

    public List<Evento> getEventosPossiveis(){
        return eventosPossiveis;
    }

    public int getFrequenciaEventos(){
        return frequenciaEventos;
    }

    public void sortearEvento(){

    }

    public void aplicarEvento(){

    }

    public void removerEvento(Evento evento){

    }

}
