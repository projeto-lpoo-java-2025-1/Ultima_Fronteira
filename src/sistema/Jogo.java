package sistema;

import ambientes.*;
import eventos.*;
import personagens.Inventario;
import personagens.Personagem;

import java.util.List;

public class Jogo {

    private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciadorDeEventos;
    private Personagem personagem;

    public Jogo() {
        // Inicializa o gerenciador de eventos, com eventos de exemplo e a frequência dos eventos
        EventoClimatico tempestade = new EventoClimatico(
                "Tempestade Violenta",
                "Uma tempestade com raios e chuvas intensas",
                70,
                new String[]{"Dano a infraestrutura", "Perda de visibilidade"},
                new String[]{"Tempestade no radar"},
                "Tempestade", 3,
                new String[]{"Chuvas intensas", "Raios"}
        );

        EventoClimatico nevasca = new EventoClimatico(
                "Nevasca Perigosa",
                "Neve intensa que pode levar ao congelamento de corpos e equipamentos",
                50,
                new String[]{"Desconforto", "Dificuldade de locomoção"},
                new String[]{"Temperatura abaixo de zero"},
                "Nevasca", 5,
                new String[]{"Neve intensa", "Congelamento"}
        );

        EventoClimatico calorExtremo = new EventoClimatico(
                "Calor Extremo",
                "Temperaturas muito altas que causam risco de desidratação",
                80,
                new String[]{"Fadiga", "Desidratação"},
                new String[]{"Temperatura acima de 40ºC"},
                "Calor Extremo", 2,
                new String[]{"Temperatura elevada", "Desidratação"}

        );

        EventoCriatura lobo = new EventoCriatura(
                "Ataque de Lobo",
                "Um lobo selvagem ataca o personagem!",
                50,
                new String[]{"Dano à saúde do personagem"},
                new String[]{"Personagem em área de floresta"},
                new String[]{"Lobo"},
                3,
                "Lutar ou Fugir"
        );

        // Criação do gerenciador de eventos com a lista de eventos possíveis
        this.gerenciadorDeEventos = new GerenciadorDeEventos(
                new Evento[]{tempestade, nevasca, calorExtremo, lobo},
                60  // Frequência dos eventos
        );
    }

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
        System.out.println("[AMBIENTE INICIAL] " + ambienteAtual.getNomeAmbiente());
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

        // Cria uma instância da classe SelecaoDePersonagem passando o nome "Diego" e a classe escolhida "mecanico"
        // Isso simula o processo de seleção de personagem no início do jogo
        SelecaoDePersonagem selecao = new SelecaoDePersonagem("Adam", "mecanico");

        // Usa o método criarPersonagem() para gerar um objeto da subclasse correspondente (no caso, Mecanico)
        this.personagem = selecao.criarPersonagem();


        System.out.println();

        // Exibe no console o nome do personagem criado
        System.out.println("[PERSONAGEM CRIADO] " + personagem.getNome());

        // Exibe no console o valor inicial de vida do personagem
        System.out.println("❤️ Vida atual: " + personagem.getVida());
        System.out.println("🍽️ Fome atual:  " + personagem.getFome());
        System.out.println("💧 Sede atual: " + personagem.getSede());
        System.out.println("\uD83D\uDD0B Energia:  " + personagem.getEnergia());
        System.out.println("🧠 Sanidade: " + personagem.getSanidade());

        Inventario inventario = new Inventario(40); // Capacidade máxima de 50 unidades de peso
        personagem.setInventario(inventario); // Atribui o inventário ao personagem

        // Simula a troca de ambientes
        gerenciador.mudarAmbiente(ambientes.get(2), personagem);
        gerenciador.mudarAmbiente(ambientes.get(1), personagem);
        gerenciador.mudarAmbiente(ambientes.get(3), personagem);

        System.out.println("\n[HISTÓRICO DE MOVIMENTAÇÕES]");
        for (String registro : gerenciador.getHistoricoMovimentacao()) {
            System.out.println("- " + registro);

        }

        //System.out.println(personagem.getLocalizacao());

    }

    public void ocorrerEventoClimatico() {
        // Evento de exemplo
        Evento eventoClimatico = new EventoClimatico(
                "Tempestade Violenta",
                "Uma tempestade com raios e chuvas intensas",
                70,
                new String[]{"Dano a infraestrutura", "Perda de visibilidade"},
                new String[]{"Tempestade no radar"},
                "Tempestade", 3,
                new String[]{"Chuvas intensas", "Raios"}
        );


        // Aplica o evento escolhido
        System.out.println("\n[EVENTO CLIMÁTICO]");
        eventoClimatico.executar(personagem);  // Chama o método de execução do evento

        // Status após cada evento
        System.out.println("❤️ Vida atual: " + personagem.getVida());
        System.out.println("🍽️ Fome atual:  " + personagem.getFome());
        System.out.println("💧 Sede atual: " + personagem.getSede());
        System.out.println("\uD83D\uDD0B Energia:  " + personagem.getEnergia());
        System.out.println("🧠 Sanidade: " + personagem.getSanidade());

    }

    public void ocorrerEventoCriatura() {

        EventoCriatura eventoCriatura = new EventoCriatura(
                "Ataque de Lobo",
                "Um lobo selvagem ataca o personagem!",
                50,
                new String[]{"Dano à saúde do personagem"},
                new String[]{"Personagem em área de floresta"},
                new String[]{"Lobo"},
                3,
                "Lutar ou Fugir"
        );

        System.out.println("\n[EVENTO CRIATURA]");
        eventoCriatura.executar(personagem);  // Chama o método de execução do evento


        // Status após cada evento
        System.out.println("❤️ Vida atual: " + personagem.getVida());
        System.out.println("🍽️ Fome atual:  " + personagem.getFome());
        System.out.println("💧 Sede atual: " + personagem.getSede());
        System.out.println("\uD83D\uDD0B Energia:  " + personagem.getEnergia());
        System.out.println("🧠 Sanidade: " + personagem.getSanidade());


    }

    public void ocorrerEventoDoencaFerimento() {

        EventoDoencaFerimento desidratacao = new EventoDoencaFerimento(
                "Desidratação",
                "Falta de água está afetando o corpo do personagem.",
                70,
                new String[]{"Sede aumentada", "Sanidade reduzida"},
                new String[]{"Exposição prolongada ao calor"},
                new String[]{"Desidratação"},
                new String[]{"Energia -15", "Sanidade -10"},
                new String[]{"Água potável", "Sombra"}
        );

        desidratacao.executar(personagem);


        // Status após cada evento
        System.out.println("❤️ Vida atual: " + personagem.getVida());
        System.out.println("🍽️ Fome atual:  " + personagem.getFome());
        System.out.println("💧 Sede atual: " + personagem.getSede());
        System.out.println("\uD83D\uDD0B Energia:  " + personagem.getEnergia());
        System.out.println("🧠 Sanidade: " + personagem.getSanidade());

    }

    public void ocorrerEventoDescoberta() {
        // 1) Parâmetros básicos do evento
       String nomeEvento = "Descoberta de Suprimentos Abandonados";
        String descricaoEvento = "O personagem encontra suprimentos abandonados no caminho.";
        int probabilidade = 70;
        String[] impacto = {"suprimentos abandonados"};
        String[] condicaoAtivacao = {"qualquer"};
        String[] descoberta = {"suprimentos abandonados"};
        String[] encontrados = {};
        String[] especial = {};

        // Cria o evento
        EventoDescoberta suprimentos = new EventoDescoberta(
                nomeEvento,
                descricaoEvento,
                probabilidade,
                impacto,
                condicaoAtivacao,
                descoberta,
                encontrados,
                especial
        );


        suprimentos.executar(personagem);

        // Status após cada evento
        System.out.println("❤️ Vida atual: " + personagem.getVida());
        System.out.println("🍽️ Fome atual:  " + personagem.getFome());
        System.out.println("💧 Sede atual: " + personagem.getSede());
        System.out.println("\uD83D\uDD0B Energia:  " + personagem.getEnergia());
        System.out.println("🧠 Sanidade: " + personagem.getSanidade());
    }
}
