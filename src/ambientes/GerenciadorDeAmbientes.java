package ambientes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenciadorDeAmbientes {

    private List<Ambiente> ambientesDisponiveis;
    private List<String> climaGlobal;
    private List<String> historicoMovimentacao;
    private Ambiente ambienteAtual;

    public GerenciadorDeAmbientes(List<Ambiente> ambientesDisponiveis, String[] climaGlobal, String[] historicoMovimentacao){
        this.ambientesDisponiveis = ambientesDisponiveis;
        this.climaGlobal= Arrays.asList(climaGlobal);
        this.historicoMovimentacao = new ArrayList<>(Arrays.asList(historicoMovimentacao));
        this.ambienteAtual = ambientesDisponiveis.get(0);
    }

    public List<Ambiente> getAmbientesDisponiveis(){
        return ambientesDisponiveis;
    }

    public List<String> getClimaGlobal(){
        return climaGlobal;
    }

    public List<String> getHistoricoMovimentacao(){
        return historicoMovimentacao;
    }

    public Ambiente getAmbienteAtual(){
        return ambienteAtual;
    }

    public void mudarAmbiente(Ambiente novoAmbiente) {
        if (ambienteAtual.equals(novoAmbiente)) {
            System.out.println();
            System.out.println("Você já está no ambiente " + ambienteAtual.getNomeAmbiente() + "!");
            return;
        }
        System.out.println();
        System.out.println("Você está deixando: " + ambienteAtual.getNomeAmbiente());
        System.out.println();
        System.out.println("Indo para: " + novoAmbiente.getNomeAmbiente());
        System.out.println(novoAmbiente.getDescricaoAmbiente());

        historicoMovimentacao.add("Saiu de " + ambienteAtual.getNomeAmbiente() + " para " + novoAmbiente.getNomeAmbiente());
        ambienteAtual = novoAmbiente;
    }

    public void gerarEvento(){

    }

    public void modificarRecursos(){

    }

}
