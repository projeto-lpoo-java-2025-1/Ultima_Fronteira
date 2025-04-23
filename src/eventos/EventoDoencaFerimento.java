package eventos;

import java.util.Arrays;
import java.util.List;
import personagens.*;


public class EventoDoencaFerimento extends Evento{

    private List<String> tipoCondicao;
    private List<String> tipoImpacto;
    private List<String> curaDisponivel;

    public EventoDoencaFerimento(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto, String[] condicaoAtivacao,
                           String[] condicao, String[] tipoImpacto, String[] cura) {

        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);

        this.tipoCondicao= Arrays.asList(condicao);
        this.tipoImpacto= Arrays.asList(tipoImpacto);
        this.curaDisponivel= Arrays.asList(cura);
    }

    public List<String> getTipoCondicao(){
        return tipoCondicao;
    }

    public List<String> getTipoImpacto(){
        return tipoImpacto;
    }

    public List<String> getCuraDisponivel(){
        return curaDisponivel;
    }

    @Override
    public void executar(Personagem personagem) {
        System.out.println("\n[EVENTO DE DOENÇA OU FERIMENTO]");
        System.out.println("Nome: " + getNomeEvento());
        System.out.println("Descrição: " + getDescricaoEvento());
        System.out.println("Condições: " + tipoCondicao);
        System.out.println("Impactos: " + tipoImpacto);
        System.out.println("Cura disponível: " + curaDisponivel);

        // Exemplos de impacto
        for (String impacto : tipoCondicao) {
            switch (impacto.toLowerCase()) {
                case "hipotermia":
                    personagem.reducaodeVida(10);
                    break;
                case "infecção":
                    personagem.reducaodeVida(15);
                    personagem.setEnergia(personagem.getEnergia() - 10);
                    break;
                case "desidratação":
                    personagem.setSede(40);
                    personagem.setSanidade(personagem.getSanidade() - 10);
                    break;
                case "fratura":
                    personagem.setEnergia(personagem.getEnergia() - 20);
                    break;
                default:
                    System.out.println("Condição não reconhecida: " + impacto);
            }
        }
    }
}