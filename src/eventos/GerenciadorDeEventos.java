package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ambientes.Ambiente;
import personagens.Personagem;
import itens.CatalogoDeItens;

public class GerenciadorDeEventos {

    private List<Evento> eventosPossiveis;
    private int probabilidadeOcorrencia;
    private List<Evento> historicoEventos;
    private Random random;

    // Referência para o catálogo de eventos
    private CatalogoDeEventos catalogoDeEventos;

    public GerenciadorDeEventos(int probabilidadeOcorrencia, CatalogoDeItens catalogoItens) {
        this.eventosPossiveis = new ArrayList<>();
        this.probabilidadeOcorrencia = probabilidadeOcorrencia;
        this.historicoEventos = new ArrayList<>();
        this.random = new Random();

        // Cria o catálogo de eventos passando o catálogo de itens
        this.catalogoDeEventos = new CatalogoDeEventos(catalogoItens);

        // Inicializa eventos possíveis com todos os eventos do catálogo
        inicializarEventosPossiveis();
    }

    private void inicializarEventosPossiveis() {
        eventosPossiveis.clear();
        eventosPossiveis.addAll(catalogoDeEventos.getEventosClimaticos());
        eventosPossiveis.addAll(catalogoDeEventos.getEventosDescoberta());
        eventosPossiveis.addAll(catalogoDeEventos.getEventosDoencaFerimento());
        eventosPossiveis.addAll(catalogoDeEventos.getEventosCriatura());
    }

    public void adicionarEvento(Evento evento) {
        eventosPossiveis.add(evento);
    }

    public Evento sortearEvento(Ambiente local) {
        List<Evento> eventosCompatíveis = new ArrayList<>();

        for (Evento evento : eventosPossiveis) {
            if (eventoCompativel(evento, local)) {
                eventosCompatíveis.add(evento);
            }
        }

        if (eventosCompatíveis.isEmpty()) {
            return null;
        }

        int chance = random.nextInt(100);
        if (chance >= probabilidadeOcorrencia) {
            return null;  // Não ocorre evento neste turno
        }

        Evento sorteado = eventosCompatíveis.get(random.nextInt(eventosCompatíveis.size()));

        if (historicoEventos.contains(sorteado)) {
            return null;  // Evita repetição excessiva
        }

        historicoEventos.add(sorteado);
        return sorteado;
    }

    public void aplicarEvento(Evento evento, Personagem jogador, Ambiente local) {
        if (evento != null) {
            evento.executar(jogador, local);
        }
    }

    public void removerEvento(Evento evento) {
        historicoEventos.remove(evento);
    }

    private boolean eventoCompativel(Evento evento, Ambiente local) {
        String nomeAmbiente = local.getClass().getSimpleName().toLowerCase();
        for (String condicao : evento.getCondicaoAtivacao()) {
            if (nomeAmbiente.contains(condicao.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
