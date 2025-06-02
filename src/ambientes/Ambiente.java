package ambientes;

import eventos.Evento;
import java.util.Arrays;
import java.util.List;

public abstract class Ambiente {

    private String nomeAmbiente;
    private String descricaoAmbiente;
    private int dificuldadeExploracao;
    private List<String> recursosDisponiveis;
    private double probabilidadeEventos;
    private String condicoesClimaticas;
    private String caminhoMapa; // Atributo adicionado
    private List<Evento> eventosPossiveis;

    public Ambiente(String nomeAmbiente, String descricaoAmbiente, int dificuldadeExploracao, String[] recursos, double probabilidadeEventos, String condicoesClimaticas, String caminhoMapa) {
        this.nomeAmbiente = nomeAmbiente;
        this.descricaoAmbiente = descricaoAmbiente;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.recursosDisponiveis = Arrays.asList(recursos);
        this.probabilidadeEventos = probabilidadeEventos;
        this.condicoesClimaticas = condicoesClimaticas;
        this.caminhoMapa = caminhoMapa; // Construtor atualizado
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public String getDescricaoAmbiente() {
        return descricaoAmbiente;
    }

    public int getDificuldadeExploracao() {
        return dificuldadeExploracao;
    }

    public List<String> getRecursosDisponiveis() {
        return recursosDisponiveis;
    }

    public double getProbabilidadeEventos() {
        return probabilidadeEventos;
    }

    public String getCondicoesClimaticas() {
        return condicoesClimaticas;
    }

    // Getter para o novo atributo
    public String getCaminhoMapa() {
        return caminhoMapa;
    }

    public void setNomeAmbiente(String nomeAmbiente) {
        this.nomeAmbiente = nomeAmbiente;
    }

    public void explorar() {
    }

    public abstract void gerarEvento();

    public abstract void modificarClima();
}