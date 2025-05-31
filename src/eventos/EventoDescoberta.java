package eventos;

import ambientes.Ambiente;
import itens.CatalogoDeItens;
import itens.Item;
import personagens.Personagem;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EventoDescoberta extends Evento {

    private List<String> tipoDescoberta;
    private List<String> recursosEncontrados;
    private List<String> condicaoEspecial;

    private String mensagemEvento;

    public EventoDescoberta(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto,
                            String[] condicaoAtivacao, String[] descoberta, String[] encontrados, String[] especial) {
        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoDescoberta = Arrays.asList(descoberta);
        this.recursosEncontrados = Arrays.asList(encontrados);
        this.condicaoEspecial = Arrays.asList(especial);
    }

    public List<String> getTipoDescoberta() {
        return tipoDescoberta;
    }

    public List<String> getRecursosEncontrados() {
        return recursosEncontrados;
    }

    public List<String> getCondicaoEspecial() {
        return condicaoEspecial;
    }

    public String getMensagemEvento() {
        return mensagemEvento;
    }

    @Override
    public void executar(Personagem personagem, Ambiente local) {
        StringBuilder mensagem = new StringBuilder();
        Random random = new Random();

        // Seleciona uma descoberta aleatória
        String descoberta = tipoDescoberta.get(random.nextInt(tipoDescoberta.size()));
        mensagem.append("Você fez uma descoberta: ").append(descoberta).append(".\n");

        // Verifica condição especial
        if (!condicaoEspecial.isEmpty()) {
            mensagem.append("Condição especial: ").append(String.join(", ", condicaoEspecial)).append(".\n");
        }

        mensagem.append("Recursos encontrados:\n");
        for (String recurso : recursosEncontrados) {
            mensagem.append("- ").append(recurso).append("\n");
        }

        this.mensagemEvento = mensagem.toString();
    }

    public void coletarRecursos(Personagem personagem, CatalogoDeItens catalogo) {
        StringBuilder resultado = new StringBuilder();

        for (String nomeRecurso : recursosEncontrados) {
            boolean adicionado = false;

            for (Item item : catalogo.obterTodosOsItens()) {
                if (item.getNome().equalsIgnoreCase(nomeRecurso)) {
                    adicionado = personagem.getInventario().adicionarItem(item);
                    if (adicionado) {
                        resultado.append("Você coletou: ").append(item.getNome()).append(".\n");
                    } else {
                        resultado.append("Inventário cheio, não foi possível pegar: ").append(item.getNome()).append(".\n");
                    }
                    break;
                }
            }

            if (!adicionado) {
                resultado.append("Recurso não encontrado no catálogo: ").append(nomeRecurso).append(".\n");
            }
        }

        // Atualiza mensagem de evento com o resultado da coleta
        this.mensagemEvento += resultado.toString();
    }
}
