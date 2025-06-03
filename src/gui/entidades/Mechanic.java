package gui.entidades;



import gui.system.EventosTeclado;
import gui.system.PainelJogo;
import personagens.Mecanico;
import itens.Arma;
import itens.CatalogoDeItens;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Mechanic extends Jogador {

    private Mecanico mecanico;

    public Mechanic(PainelJogo gp, EventosTeclado eventosTeclado) {
        super(gp, eventosTeclado);
        //CatalogoDeItens catalogo = new CatalogoDeItens();
        //Arma armaInicial = (Arma) catalogo.getItemPorNome("Espada");
        this.mecanico = new Mecanico("Mecanico", 100, 100, 100, 100, 100, null, "Floresta", 36.5, 100, 1, false, false, false, null);
        getImagemJogador();
        //mecanico.exibirStatus();
    }

    @Override
    public void  getImagemJogador() {
        try {
            setUp1(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_up_01.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_up_02.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_down_01.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_down_02.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_left_01.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_left_02.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_right_01.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/mechanic/mecanica_right_02.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
