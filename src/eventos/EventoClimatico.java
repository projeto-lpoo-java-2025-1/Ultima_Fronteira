package eventos;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import enums.TipoEventoClima;
import personagens.Personagem;
import ambientes.Ambiente;

public class EventoClimatico extends Evento {

    private String tipoClima;
    private int duracao;
    private List<String> efeitoAmbiente;

    private final Random random = new Random();

    private int contadorChuva = 0;
    private final int intervaloChuva = 1200;

    private int contadorCalor = 0;
    private final int intervaloCalor = 1200;

    private int contadorNevasca = 0;
    private final int intervaloNevasca = 600;

    private boolean chuvaAtiva = false;
    private boolean calorAtivo = false;
    private boolean nevascaAtiva = false;

    private TipoEventoClima eventoAtual = TipoEventoClima.NENHUM;

    public EventoClimatico(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia,
                           String[] impacto, String[] condicaoAtivacao, String tipoClima,
                           int duracao, String[] efeito) {
        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
        this.efeitoAmbiente = Arrays.asList(efeito);
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

        // se há evento ativo, só diminui a duração.
        if (chuvaAtiva || calorAtivo || nevascaAtiva) {
            duracao--;
            if (duracao <= 0) {
                chuvaAtiva = false;
                calorAtivo = false;
                nevascaAtiva = false;
                eventoAtual = TipoEventoClima.NENHUM;
            }
            return;  // Não ativa outro evento enquanto um está ativo.
        }

        // não há nenhum evento ativo.

        // Evento chuva
        if (contadorChuva >= intervaloChuva) {
            int chance = random.nextInt(100);
            if (chance < 10) {  // 10% de chance
                jogador.setVida(jogador.getEnergia() - 1);
                jogador.setTemperaturaCorporal(jogador.getTemperaturaCorporal() - 2);
                chuvaAtiva = true;
                duracao = 100;
                eventoAtual = TipoEventoClima.CHUVA;
                contadorChuva = 0;
                return;
            }
            contadorChuva = 0;
        } else {
            contadorChuva++;
        }

        // Evento calor extremo
        if (contadorCalor >= intervaloCalor) {
            int chance = random.nextInt(100);
            if (chance < 15) {  // 15% de chance
                jogador.setSede(jogador.getSede() - 2);
                jogador.setTemperaturaCorporal(jogador.getTemperaturaCorporal() + 1);
                calorAtivo = true;
                duracao = 100;
                eventoAtual = TipoEventoClima.CALOR;
                contadorCalor = 0;
                return;
            }
            contadorCalor = 0;
        } else {
            contadorCalor++;
        }

        // Evento nevasca
        if (contadorNevasca >= intervaloNevasca) {
            int chance = random.nextInt(100);
            if (chance < 5) {  // 5% de chance
                jogador.setEnergia(jogador.getEnergia() - 1);
                jogador.setTemperaturaCorporal(jogador.getTemperaturaCorporal() - 2);
                nevascaAtiva = true;
                duracao = 100;
                eventoAtual = TipoEventoClima.NEVASCA;
                contadorNevasca = 0;
                return;
            }
            contadorNevasca = 0;
        } else {
            contadorNevasca++;
        }

        // nada ativou, permanece sem evento.
        eventoAtual = TipoEventoClima.NENHUM;
    }

    // Getters
    public String getTipoClima() {
        return tipoClima;
    }

    public int getDuracao() {
        return duracao;
    }

    public List<String> getEfeitoAmbiente() {
        return efeitoAmbiente;
    }

    public TipoEventoClima getEventoAtual() {
        return eventoAtual;
    }
}
