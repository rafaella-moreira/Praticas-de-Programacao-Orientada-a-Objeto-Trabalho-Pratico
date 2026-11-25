import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gabarito {
    private Tesouro tesouro;
    private List<Dica> dicas;
    private ChaveMestra chaveMestra;

    public Gabarito(Tesouro tesouro, List<Dica> dicas, ChaveMestra chaveMestra) {
        this.tesouro = tesouro;
        // copia defensiva: alteracoes na lista original nao afetam o gabarito
        this.dicas = new ArrayList<Dica>(dicas);
        this.chaveMestra = chaveMestra;
    }

    public Tesouro getTesouro() {
        return tesouro;
    }

    // devolve uma visao somente leitura da lista de dicas
    public List<Dica> getDicas() {
        return Collections.unmodifiableList(dicas);
    }

    public ChaveMestra getChaveMestra() {
        return chaveMestra;
    }

}
