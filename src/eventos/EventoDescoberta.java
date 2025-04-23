package eventos;


import personagens.Personagem;
import itens.*;

import java.util.*;


public class EventoDescoberta extends Evento {

    private List<String> tipoDescoberta;
    private List<String> recursosEncontrados;
    private List<String> condicaoEspecial;

    Random random = new Random();


    public EventoDescoberta(String nomeEvento, String descricaoEvento, int probabilidadeOcorrencia, String[] impacto, String[] condicaoAtivacao,
                            String[] descoberta, String[] encontrados, String[] especial) {

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

    @Override
    public void executar(Personagem personagem) {
        System.out.println("\n[EVENTO DE DESCOBERTA]");
        System.out.println("Nome: " + getTipoDescoberta());
        //System.out.println("Recursos encontrados: " + getRecursosEncontrados());
        System.out.println("Condição especial: " + getCondicaoEspecial());

        // Exemplos de impacto
        for (String impacto : tipoDescoberta) {
            switch (impacto.toLowerCase()) {
                case "caverna":
                    // Futuramente adicionados
                    break;
                case "abrigo":
                    // Futuramente adicionados
                    personagem.setEnergia(personagem.getEnergia() + 10);
                    break;
                case "suprimentos abandonados":
                    System.out.println(personagem.getNome() + " encontrou suprimentos abandonados!");

                    // Lista de todas as opções possíveis
                    List<Item> possiveis = Arrays.asList(
                            new Alimento("Feijão Enlatado", 2, 10, "Comida", 25, 180) {},
                            new Alimento("Sopa de Legumes",   1,  8, "Comida", 20, 120) {},
                            new Alimento("Carne Enlatada",    3, 12, "Comida", 30, 200) {},
                            new Ferramenta("Martelo", 3, 20, "Construção", 70){} ,
                            new Ferramenta("Chave Inglesa", 2, 25, "Manutenção", 60){}
                    );

                    // Embaralha a lista
                    Collections.shuffle(possiveis, random);

                    // Decide quantos itens
                    int max   = Math.min(3, possiveis.size());
                    int qtd   = 1 + random.nextInt(max);

                    // Seleciona os primeiros 'qtd'
                    List<Item> encontrados = new ArrayList<>(possiveis.subList(0, qtd));

                    //Atualiza e imprime recursosEncontrados
                    this.recursosEncontrados = new ArrayList<>();
                    for (Item it : encontrados) {
                        this.recursosEncontrados.add(it.getNome());
                    }
                    System.out.println("Recursos encontrados: " + this.recursosEncontrados);

                    // Tenta adicionar cada item ao inventário
                    for (Item item : encontrados) {
                        if (personagem.getInventario().adicionarItem(item)) {
                            //System.out.println("  + " + item.getNome() + " adicionado ao inventário.");
                        } else {
                            System.out.println("  ! Não coube: " + item.getNome());
                        }
                    }
                    break;

                case "ruinas":
                    // Futuramente adicionados

                default:
                    System.out.println("Condição não reconhecida: " + impacto);
            }
        }
    }
}
