package gui.objetos;

import gui.entidades.Entidade;
import gui.system.PainelJogo;
import itens.Remedio;

public class REMEDIO_Analgesico extends Entidade {

    private Remedio remedioLogico;

    public REMEDIO_Analgesico(PainelJogo gp) {

        super(gp);

        remedioLogico=new Remedio("Remédio", 1, 10, "Analgésico", "Alivia dor", 1);

        setNome(remedioLogico.getTipo());
        setDown1(setup("/objetos/analgesico"));
        setColisao(true);
        setDescricao("["+ getNome() +"]\nAlivia a dor rapidamente.\nÚtil em situações de\nemergência.");

    }
}
