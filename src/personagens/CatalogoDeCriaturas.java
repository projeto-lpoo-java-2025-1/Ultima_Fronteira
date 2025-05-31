package personagens;

import enums.TipoAcaoCriatura;

import java.util.ArrayList;
import java.util.List;

public class CatalogoDeCriaturas {
    private List<Criatura> criaturas;

    public CatalogoDeCriaturas() {
        this.criaturas = new ArrayList<>();
        inicializarCriaturas();
    }

    private void inicializarCriaturas() {
        criaturas.add(new Criatura("Lobo", 2, 30, TipoAcaoCriatura.MORDIDA));
        criaturas.add(new Criatura("Urso", 4, 50, TipoAcaoCriatura.GARRAS ));
        criaturas.add(new Criatura("Aranha", 1, 20, TipoAcaoCriatura.PICA_VENENO ));
        criaturas.add(new Criatura("Morcego", 0, 30, TipoAcaoCriatura.VOA ));
        criaturas.add(new Criatura("Lhama", 0, 30, TipoAcaoCriatura.CAMINHA ));
        criaturas.add(new Criatura("Besouro", 0, 15, TipoAcaoCriatura.CAMINHA ));
        criaturas.add(new Criatura("Peixe", 0, 30, TipoAcaoCriatura.NADA ));
        criaturas.add(new Criatura("Coelho", 0, 10, TipoAcaoCriatura.PULA ));
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public Criatura getCriaturaPorNome(String nome) {
        for (Criatura c : criaturas) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }
}
