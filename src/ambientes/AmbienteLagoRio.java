package ambientes;

public class AmbienteLagoRio extends Ambiente {

    public String aguaAbundante;
    public boolean possibilidadePesca;
    public boolean terrenoLamacento;

    public AmbienteLagoRio() {
        super(
                "Lago e Rio",
                "Regiões ricas em água, mas que podem esconder riscos como afogamento ou criaturas aquáticas.",
                3,
                new String[]{"Peixes", "Algas comestíveis", "Água doce", "Vegetação ribeirinha"},
                1,
                "Úmido",
                "/maps/lagoErio.txt" // Caminho para o layout do mapa
        );

        this.aguaAbundante = "Abundante"; // Valor definido
        this.possibilidadePesca = true;
        this.terrenoLamacento = true; // Valor definido
    }

    public String getAguaAbundante() {
        return aguaAbundante;
    }

    public boolean isPossibilidadePesca() {
        return possibilidadePesca;
    }

    public boolean isTerrenoLamacento() {
        return terrenoLamacento;
    }

    @Override
    public void gerarEvento() {
        // Lógica futura para eventos específicos do lago/rio
    }

    @Override
    public void modificarClima() {
        // Lógica futura para modificação de clima no lago/rio
    }
}