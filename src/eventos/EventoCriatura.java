package eventos;

import ambientes.Ambiente;
import personagens.Criatura; // Refere-se à sua classe Criatura do back-end
import personagens.Personagem; // Refere-se à sua classe Personagem do back-end
import itens.CatalogoDeItens; // Para o método combater
import itens.Alimento; // Para o método combater

import java.util.List;
import java.util.Random;

public class EventoCriatura extends Evento {

    private List<Criatura> criaturas; // Lista de tipos de Criatura do back-end
    private Criatura criaturaAtual;   // A instância específica da criatura encontrada neste evento
    // Removi 'mensagemEvento' como campo, pois 'executar' agora retorna a mensagem diretamente.

    public EventoCriatura(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia,
                          String[] impacto, String[] condicaoAtivacao,
                          List<Criatura> criaturas) {
        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.criaturas = criaturas;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public Criatura getCriaturaAtual() {
        return criaturaAtual;
    }

    @Override
    public String executar(Personagem personagem, Ambiente local) {
        Random random = new Random();
        if (this.criaturas == null || this.criaturas.isEmpty()) {
            return "Nenhuma criatura definida para este evento de encontro.";
        }

        // Sorteia uma criatura da lista de criaturas associadas a este evento
        // (definida no CatalogoDeEventos)
        this.criaturaAtual = this.criaturas.get(random.nextInt(this.criaturas.size()));

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Você encontrou uma criatura: ").append(criaturaAtual.getNome()).append(".\n");

        // Lógica para determinar agressividade baseada no nome (como no seu arquivo original)
        switch (criaturaAtual.getNome().toLowerCase()) {
            case "lobo":
            case "urso":
            case "aranha": // Supondo que aranha também seja agressiva
                mensagem.append("A criatura é agressiva! Prepare-se para lutar ou tente fugir.");
                break;
            default:
                mensagem.append("A criatura parece pacífica por enquanto.");
                break;
        }

        return mensagem.toString();
    }

    public String combater(Personagem personagem, CatalogoDeItens catalogo) {
        if (criaturaAtual == null) return "Nenhuma criatura para combater.";

        String resultadoCombate = "";
        int danoCriatura = criaturaAtual.getNivelPerigo() * 3; // Supondo get/setNivelPerigo em Criatura
        if (danoCriatura > 0) {
            personagem.reduzirVida(danoCriatura); // Supondo reduzirVida em Personagem
            resultadoCombate += "A criatura ataca e causa " + danoCriatura + " de dano!\n";
        }

        personagem.atacar(this.criaturaAtual);
        resultadoCombate += "Você ataca " + criaturaAtual.getNome() + ".\n";

        if (criaturaAtual.getVida() <= 0) { // Supondo get/setVida em Criatura
            resultadoCombate += "Você derrotou " + criaturaAtual.getNome() + "!\n";

            Alimento carne = catalogo.getAlimentoPorNome("Carne"); // Supondo este método no catálogo
            if (carne != null) {
                boolean adicionou = personagem.getInventario().adicionarItem(carne);
                if (adicionou) {
                    resultadoCombate += "Você coletou Carne.";
                } else {
                    resultadoCombate += "Seu inventário está cheio, não foi possível pegar a Carne.";
                }
            } else {
                resultadoCombate += "Item 'Carne' não encontrado no catálogo para drop.";
            }
            this.criaturaAtual = null; // Criatura derrotada
        } else {
            resultadoCombate += criaturaAtual.getNome() + " ainda está de pé! Vida restante: " + criaturaAtual.getVida();
        }
        return resultadoCombate;
    }

    public String fugir(Personagem personagem) {
        if (criaturaAtual == null) return "Nenhuma criatura para fugir.";

        // Supondo getVelocidade em Personagem
        if (personagem.getVelocidade() > 3) {
            this.criaturaAtual = null; // Fuga bem-sucedida
            return "Você conseguiu fugir da criatura!";
        } else {
            int danoFuga = criaturaAtual.getNivelPerigo() * 2;
            personagem.reduzirVida(danoFuga);
            return "Você falhou em fugir e recebeu " + danoFuga + " pontos de dano.";
        }
    }
}