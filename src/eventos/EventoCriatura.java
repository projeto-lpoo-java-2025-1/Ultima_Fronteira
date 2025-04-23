package eventos;

import personagens.Personagem;

import java.util.Arrays;
import java.util.List;

public class EventoCriatura extends Evento{

    private List<String> tipoCriatura;
    private int nivelPerigo;
    private String acao;
    private double vida = 100;

    public void receberDano(double dano) {
        vida -= dano;
        System.out.println("Criatura recebeu " + dano + " de dano. Vida restante: " + vida);
    }

    public EventoCriatura(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto, String[] condicaoAtivacao,
                           String[] criatura, int nivelPerigo, String acao) {


        super(nomeEvento, descricaoEvento, probabilidadeOcorrencia, impacto, condicaoAtivacao);

        this.tipoCriatura=Arrays.asList(criatura);
        this.nivelPerigo=nivelPerigo;
        this.acao=acao;
    }

    public List<String> getTipoCriatura(){
        return tipoCriatura;
    }

    public int getNivelPerigo(){
        return nivelPerigo;
    }

    public String acao(){
        return acao;
    }

    @Override
    public void executar(Personagem personagem) {
        // Itera sobre todos os tipos de criaturas presentes na lista
        for (String criatura : tipoCriatura) {
            switch (criatura.toLowerCase()) {
                case "lobo":
                    System.out.println("Um lobo atacou!");
                    personagem.reducaodeVida(15);  // Exemplo: dano causado pelo lobo
                    System.out.println("O personagem sofreu 15 de dano por um lobo!");
                    break;

                case "urso":
                    System.out.println("Um urso apareceu!");
                    personagem.reducaodeVida(30);  // Exemplo: dano maior causado pelo urso
                    System.out.println("O personagem sofreu 30 de dano por um urso!");
                    break;

                case "cobra":
                    System.out.println("Uma cobra atacou!");
                    personagem.reducaodeVida(10);  // Exemplo: dano causado pela cobra
                    System.out.println("O personagem sofreu 10 de dano por uma cobra!");
                    break;
                case "corvo":
                    System.out.println("Um corvo atacou!");
                    personagem.reducaodeVida(5);  // Exemplo: dano causado pela cobra
                    System.out.println("O personagem sofreu 5 de dano por um corvo!");

                default:
                    System.out.println("Criatura desconhecida!");
                    break;
            }
        }
    }
}