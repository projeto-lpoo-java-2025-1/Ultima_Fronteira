package sistema;

import ambientes.*;

import java.util.List;

public class Jogo {

    private Ambiente ambienteAtual;

    public void iniciarAmbientes() { // Método que configura os ambientes do jogo e simula mudanças entre eles
        // Criação da lista de ambientes
        List<Ambiente> ambientes = List.of(
                new AmbienteFloresta(),
                new AmbienteMontanha(),
                new AmbienteLagoRio(),
                new AmbienteCaverna(),
                new AmbienteRuinas()
        );

        // Começa no primeiro ambiente
        System.out.println();
        ambienteAtual = ambientes.get(0);
        System.out.println("Ambiente inicial: " + ambienteAtual.getNomeAmbiente());
        System.out.println(ambienteAtual.getDescricaoAmbiente());

        /*System.out.println("Todos os ambientes disponíveis:");
        for (Ambiente ambiente : ambientes) {
            System.out.println();
            System.out.println("Ambiente: " + ambiente.getNomeAmbiente());
            System.out.println("Descrição: " + ambiente.getDescricaoAmbiente());
            System.out.println("Dificuldade: " + ambiente.getDificuldadeExploracao());
            System.out.println("Recursos: " + ambiente.getRecursosDisponiveis());
            System.out.println("Eventos: " + ambiente.getProbabilidadeEventos());
            System.out.println("Clima: " + ambiente.getCondicoesClimaticas());
            System.out.println();
        }
         */

        // Criação do objeto gerenciador do jogo, passando os ambientes, os climas possíveis e um histórico de movimentação vazio
        GerenciadorDeAmbientes gerenciador = new GerenciadorDeAmbientes(
                ambientes,
                new String[]{"Úmido", "Seco", "Frio"},
                new String[]{}
        );

        // Simula a troca de ambientes
        gerenciador.mudarAmbiente(ambientes.get(2));
        gerenciador.mudarAmbiente(ambientes.get(1));
        gerenciador.mudarAmbiente(ambientes.get(3));

        System.out.println("\nHistórico de movimentações:");
        for (String registro : gerenciador.getHistoricoMovimentacao()) {
            System.out.println("- " + registro);

        }


    }

}