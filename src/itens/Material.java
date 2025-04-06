package itens; // Define que esta classe pertence ao pacote 'itens'

// A classe Material é uma subclasse da classe Item
public class Material extends Item{
    private String tipo;
    private int resistencia;

    //Construtor
    public Material(String nome, int peso, int durabilidade, String tipo, int resistencia){
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    //Método getters
    public String getTipo(){
        return tipo;
    }

    public int getResistencia(){
        return resistencia;
    }

    //Método setters
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setResistencia(int resistencia){
        this.resistencia = resistencia;
    }

    //Metodo combinar
    public Item combinar(Material outroMaterial) {
        String novoNome = this.getNome() + "-" + outroMaterial.getNome();
        int novoPeso = this.getPeso() + outroMaterial.getPeso();
        int novaDurabilidade = (this.getDurabilidade() + outroMaterial.getDurabilidade()) / 2;
        int eficienciaMedia = (this.resistencia + outroMaterial.resistencia) / 2;
        System.out.println("Você combinou " + this.getNome() + " com " + outroMaterial.getNome()
                + " e criou: " + novoNome);
        return new Ferramenta(novoNome, novoPeso, novaDurabilidade, "Ferramenta Combinada", eficienciaMedia);
    }
}
