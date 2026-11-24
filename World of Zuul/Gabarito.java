import java.util.ArrayList;

public class Gabarito {
    public Tesouro tesouro;
    public ArrayList<Dica> dicas;
    public ChaveMestra chaveMestra;
    public Jogador jogador;

    public Gabarito(Tesouro tesouro, ArrayList<Dica> dicas, ChaveMestra chaveMestra, Jogador jogador) {
        this.tesouro = tesouro;
        this.dicas = dicas;
        this.chaveMestra = chaveMestra;
        this.jogador = jogador;
    }

}
