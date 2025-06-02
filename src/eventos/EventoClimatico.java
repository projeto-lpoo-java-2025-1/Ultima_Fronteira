package eventos;

import ambientes.Ambiente;
import personagens.Personagem;

public class EventoClimatico extends Evento {

    private String tipoClima;
    private int duracao;

    public EventoClimatico(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia,
                           String[] impacto, String[] condicaoAtivacao, String tipoClima,
                           int duracao, String[] efeito) {
        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
    }

    @Override
    public String executar(Personagem personagem, Ambiente local) {
        // A lógica de impacto agora está encapsulada e retorna uma mensagem para a GUI
        if (this.getNomeEvento().equalsIgnoreCase("Chuva")) {
            personagem.setEnergia(personagem.getEnergia() - 1);
            return "Uma chuva forte começou! Você se sente mais cansado.";
        }
        if (this.getNomeEvento().equalsIgnoreCase("Nevasca")) {
            personagem.setEnergia(personagem.getEnergia() - 1);
            return "O vento gelado de uma nevasca sopra com força...";
        }
        if (this.getNomeEvento().equalsIgnoreCase("Calor")) {
            personagem.setSede(personagem.getSede() - 2);
            return "Uma onda de calor intenso faz você sentir mais sede.";
        }

        return getDescricaoEvento();
    }
}