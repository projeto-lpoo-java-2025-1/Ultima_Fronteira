package ambientes;

public class AmbienteMontanha extends Ambiente {

    public AmbienteMontanha(){
        super("Montanha",
                "Uma região de difícil acesso, mas rica em minérios e pedras preciosas",
                4,
                new String[]{"Rochas", "Minérios raros", "Pequenos lagos subterrâneos", "Ossos", "Vestígios de exploradores antigos"},
                1,
                "Instável");
    }
}
