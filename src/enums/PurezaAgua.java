package enums;

public enum PurezaAgua {
    PURA("Limpa"),
    CONTAMINADA("Contaminada");

    private String descricao;
    PurezaAgua(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }
}