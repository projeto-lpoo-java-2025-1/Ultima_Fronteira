package eventos;

import ambientes.Ambiente;
import personagens.Personagem;
import java.util.Random;

public class EventoDoencaFerimento extends Evento {

    private int contadorDesidratacao = 0;
    private final int intervaloDesidratacao = 600;

    private int contadorInfeccao = 0;
    private final int intervaloInfeccao = 100;

    public EventoDoencaFerimento(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia,
                                 String[] impacto, String[] condicaoAtivacao,
                                 String[] condicao, String[] tipoImpacto, String[] cura) {
        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);
    }

    public String verificarDesidratacao(Personagem personagem) {
        if (personagem.getSede() <= 2) {
            personagem.setDesidratado(true);
        } else {
            personagem.setDesidratado(false);
            contadorDesidratacao = 0;
        }

        if (personagem.isDesidratado()) {
            contadorDesidratacao++;
            if (contadorDesidratacao >= intervaloDesidratacao) {
                personagem.setEnergia(personagem.getEnergia() - 1);
                personagem.setSanidade(Math.max(0, personagem.getSanidade() - 1));
                contadorDesidratacao = 0;
                return "DESIDRATAÇÃO: Você perdeu 1 ponto de energia e sanidade.";
            }
        }
        return null;  // sem efeito
    }

    public String beberAguaContaminada(Personagem personagem) {
        if (personagem.getSede() < personagem.getSedeMaxima()) {
            personagem.setSede(personagem.getSede() + 1);
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 60) {
                personagem.setInfectado(true);
                return "Você bebeu água contaminada e foi infectado!";
            } else {
                return "Você bebeu água contaminada, mas não ficou infectado.";
            }
        } else {
            return "Você já está hidratado.";
        }
    }

    public String verificarInfeccao(Personagem personagem) {
        if (personagem.isInfectado()) {
            contadorInfeccao++;
            if (contadorInfeccao >= intervaloInfeccao) {
                personagem.setVida(personagem.getVida() - 1);
                contadorInfeccao = 0;
                return "Você está infectado! Perdeu 1 ponto de vida.";
            }
        } else {
            contadorInfeccao = 0;
        }
        return null;
    }

    public boolean verificarDelirio(Personagem personagem) {
        if (personagem.getSanidade() <= 2 || personagem.isDesidratado()) {
            personagem.setVelocidade(1);
            return true;  // está delirando
        } else {
            personagem.setVelocidade(2);
            return false;  // não está delirando
        }
    }

    @Override
    public void executar(Personagem personagem, Ambiente local) {
        verificarDesidratacao(personagem);
        verificarInfeccao(personagem);
        verificarDelirio(personagem);
        // A execução pode ser geral, ou guiada por tipos de evento
    }
}
