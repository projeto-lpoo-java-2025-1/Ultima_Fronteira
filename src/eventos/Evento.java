package eventos;

import ambientes.Ambiente;
import personagens.Personagem; // Supondo que você tenha essa classe no back-end
import java.util.Arrays;
import java.util.List;

public abstract class Evento {

    private String nomeEvento;
    private String descricaoEvento;
    private int probabilidadeOcorrencia;
    private List<String> impactoEvento;
    private List<String> condicaoAtivacao;

    public Evento(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto, String[] condicao){
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
        this.probabilidadeOcorrencia = probabilidadeOcorrencia;
        this.impactoEvento = Arrays.asList(impacto);
        this.condicaoAtivacao = Arrays.asList(condicao);
    }

    // Getters...
    public String getNomeEvento() { return nomeEvento; }
    public String getDescricaoEvento() { return descricaoEvento; }
    public int getProbabilidadeOcorrencia() { return probabilidadeOcorrencia; }
    public List<String> getImpactoEvento() { return impactoEvento; }
    public List<String> getCondicaoAtivacao() { return condicaoAtivacao; }

    // MÉTODO ABSTRATO CORRIGIDO para retornar String
    public abstract String executar(Personagem personagem, Ambiente local);
}