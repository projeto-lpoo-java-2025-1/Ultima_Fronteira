package eventos;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import personagens.*;

public class EventoClimatico extends Evento {

    private String tipoClima;
    private int duracao;
    private List<String> efeitoAmbiente;

    public EventoClimatico(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto, String[] condicaoAtivacao,
                           String tipoClima, int duracao, String[] efeito) {


        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);

        this.tipoClima = tipoClima;
        this.duracao = duracao;
        this.efeitoAmbiente = Arrays.asList(efeito);
    }

    public String getTipoClima() {
        return tipoClima;
    }

    public int getDuracao() {
        return duracao;
    }

    public List<String> getEfeitoAmbiente() {
        return efeitoAmbiente;
    }

    @Override
    public void executar(Personagem personagem) {
        switch (tipoClima.toLowerCase()) {
            case "tempestade":
                System.out.println("Iniciando tempestade: " + getNomeEvento() + " - Efeitos: " + getEfeitoAmbiente());
                // Adicionar efeitos específicos para tempestade
                personagem.reducaodeVida(10);
                System.out.println("O personagem está com a roupa encharcada.");
                System.out.println("A temperatura corporal do personagem baixou.");
                System.out.println("A vida do personagem foi impacta em 10!");

                break;
            case "nevasca":
                System.out.println("Iniciando nevasca: " + getNomeEvento() + " - Efeitos: " + getEfeitoAmbiente());
                // Adicionar efeitos específicos para nevasca
                personagem.reducaodeVida(20);

                break;
            case "calor extremo":
                System.out.println("Iniciando calor extremo: " + getNomeEvento() + " - Efeitos: " + getEfeitoAmbiente());
                // Adicionar efeitos específicos para calor extremo
                personagem.reducaodeVida(15);
                personagem.setSede(30);
                break;
            default:
                System.out.println("Evento climático desconhecido.");

        }
    }
}