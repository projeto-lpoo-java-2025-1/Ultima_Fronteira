package itens;

import enums.PurezaAgua;
import exceptions.SedeCheiaException;
import personagens.Personagem;

public class Agua extends Item {
    private PurezaAgua pureza; // Novo
    private int volume;

    public Agua(String nome, int peso, int durabilidade, PurezaAgua pureza, int volume) {
        super(nome, peso, durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }

    public PurezaAgua getPureza() {
        return pureza;
    }
    public String getPurezaDescricao() {
        return pureza.getDescricao();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String beber(Personagem personagem) {
        if (personagem.getSede() >= 100) {
            throw new SedeCheiaException(personagem.getNome() + " já está com a sede cheia.");
        }
        personagem.recuperarSede(volume);
        return personagem.getNome() + " bebeu " + getNome() + " e recuperou " + volume + " de sede.";
    }

    @Override
    public void usar(Personagem personagem) {
        beber(personagem);
    }
}
