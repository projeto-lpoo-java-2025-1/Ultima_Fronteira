package eventos;

import ambientes.Ambiente;
import personagens.Criatura;
import itens.Alimento;
import itens.CatalogoDeItens;
import personagens.Personagem;

import java.util.List;
import java.util.Random;

public class EventoCriatura extends Evento {

    private List<Criatura> criaturas;
    private Criatura criaturaAtual;
    private String mensagemEvento;

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

    public String getMensagemEvento() {
        return mensagemEvento;
    }

    @Override
    public void executar(Personagem personagem, Ambiente local) {
        Random random = new Random();
        Criatura sorteada = criaturas.get(random.nextInt(criaturas.size()));
        this.criaturaAtual = sorteada;

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Você encontrou uma criatura: ").append(sorteada.getNome()).append(".\n");

        switch (sorteada.getNome().toLowerCase()) {
            case "lobo":
            case "urso":
            case "aranha":
                mensagem.append("A criatura é agressiva! Você pode escolher combater ou fugir.\n");
                break;
            default:
                mensagem.append("A criatura é neutra e não representa perigo imediato.\n");
                break;
        }

        this.mensagemEvento = mensagem.toString();
    }

    public void combater(Personagem personagem, CatalogoDeItens catalogo) {
        if (criaturaAtual == null) return;

        int danoCriatura = criaturaAtual.getNivelPerigo() * 3;
        if (danoCriatura > 0) {
            personagem.reduzirVida(danoCriatura);
        }

        // Personagem ataca a criatura
        personagem.atacar(this.getCriaturaAtual());

        if (criaturaAtual.getVida() <= 0) {
            this.mensagemEvento = "Você derrotou a criatura! Você obteve Carne como recompensa.\n";

            Alimento carne = catalogo.getAlimentoPorNome("Carne");
            if (carne != null) {
                boolean adicionou = personagem.getInventario().adicionarItem(carne);
                if (!adicionou) {
                    this.mensagemEvento += "Mas seu inventário está cheio e você não conseguiu pegar a carne.\n";
                }
            } else {
                this.mensagemEvento += "Carne não encontrada no catálogo!\n";
            }
        }
    }

    public void fugir(Personagem personagem) {
        if (criaturaAtual == null) return;

        if (personagem.getVelocidade() > 5) {
            this.mensagemEvento = "Você conseguiu fugir da criatura!\n";
        } else {
            int danoFuga = criaturaAtual.getNivelPerigo() * 2;
            personagem.reduzirVida(danoFuga);
            this.mensagemEvento = "Você falhou em fugir e recebeu " + danoFuga + " pontos de dano.\n";
        }
    }

    // Método chamado quando personagem ataca
    public void receberDano(double dano) {
        if (criaturaAtual != null) {
            criaturaAtual.receberDano(dano);
        }
    }
}
