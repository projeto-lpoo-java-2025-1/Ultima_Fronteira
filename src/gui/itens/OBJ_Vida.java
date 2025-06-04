package gui.itens;

import gui.entidades.Entidade;
import gui.system.PainelJogo;

public class OBJ_Vida extends Entidade {

    PainelJogo gp;

    public OBJ_Vida(PainelJogo gp) {
        super(gp);

        this.gp = gp;

        setNome("Vida");
        setImagem(setup("/status/vida_cheia", gp.getTamanhoBloco(), gp.getTamanhoBloco()));
        setImagem2(setup("/status/vida_metade", gp.getTamanhoBloco(), gp.getTamanhoBloco()));
        setImagem3(setup("/status/vida_vazia", gp.getTamanhoBloco(), gp.getTamanhoBloco()));
    }
}
