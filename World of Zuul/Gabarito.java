import java.util.ArrayList;

public class Gabarito {
    public Tesouro tesouro;
    public ArrayList<Dica> dicas;
    public ChaveMestra chaveMestra;

    public Gabarito(Tesouro tesouro, ArrayList<Dica> dicas, ChaveMestra chaveMestra) {
        this.tesouro = tesouro;
        this.dicas = dicas;
        this.chaveMestra = chaveMestra;
    }

}
