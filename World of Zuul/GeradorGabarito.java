import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Sorteia o local do tesouro, da chave mestra e das dicas, distribui esses itens
 * pelos ambientes da casa e devolve o gabarito da partida.
 * Chave e dicas nunca ficam no ambiente inicial. Cada dica indica
 * um ambiente onde o tesouro NAO esta.
 */
public class GeradorGabarito {
    private static final int QUANTIDADE_DICAS = 2;

    private CasaAssombrada casa;

    public GeradorGabarito(CasaAssombrada casa) {
        this.casa = casa;
    }

    // shuffle embaralha os elementos aleatoriamente dentro de uma lista
    public Gabarito gerar()
    {
        List<Ambiente> todos = casa.getAmbientes();
        Collections.shuffle(todos);
        Tesouro tesouro = new Tesouro(todos.get(0));
        todos.get(0).adicionarItem(tesouro);

        List<Ambiente> candidatos = casa.getAmbientes();
        candidatos.remove(casa.getAmbienteInicial());
        Collections.shuffle(candidatos);
        // sortear chave mestra com um número entre 1 e 10
        int durabilidade = new Random().nextInt(10) + 1;
        ChaveMestra chave = new ChaveMestra(candidatos.get(0), durabilidade);
        candidatos.get(0).adicionarItem(chave);

        List<Ambiente> semTesouro = casa.getAmbientes();
        semTesouro.remove(tesouro.getLocalizacaoTesouro());
        Collections.shuffle(semTesouro);
        Collections.shuffle(candidatos);

        List<Dica> dicas = new ArrayList<Dica>();
        int quantidade = Math.min(QUANTIDADE_DICAS, Math.min(candidatos.size(), semTesouro.size()));
        for (int i = 0; i < quantidade; i++) {
            Ambiente indicado = semTesouro.get(i);
            Dica dica = new Dica("Um bilhete velho diz: \"O tesouro NAO esta em '"
                    + indicado.getDescricao() + "'.\"", indicado);
            candidatos.get(i).adicionarItem(dica);
            dicas.add(dica);
        }
        return new Gabarito(tesouro, dicas, chave);
    }
}
