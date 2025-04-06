package itens; // Define que esta classe pertence ao pacote 'itens'

import personagens.Personagem; // Importa a classe Personagem para poder usá-la no método beber


// A classe Agua é uma subclasse da classe Item
public class Agua extends Item{
    private String pureza;
    private int volume;

    //Construtor
    public Agua(String nome, int peso, int durabilidade, String pureza, int volume){
        super(nome, peso, durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }

    //Método getters
    public String getPureza(){
        return pureza;
    }

    public int getVolume(){
        return volume;
    }

    //Método Setters
    public void setPureza(String pureza){
        this.pureza = pureza;
    }

    public void setVolume(int volume){
        this.volume = volume;
    }

    //Método beber
    public void beber(Personagem personagem){
        personagem.recuperacaodeSede(volume);
        System.out.println(personagem.getNome() + " bebeu " + getNome() + " e recuperou " + volume + " de sede.");
    }
}
