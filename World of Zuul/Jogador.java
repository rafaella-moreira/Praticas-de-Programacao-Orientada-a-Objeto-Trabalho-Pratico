import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogador {
    private Ambiente ambienteAtual;
    private int quantAberturaPortas;
    private int quantAberturaPortasRestante;
    private boolean possuiCargaExplosiva;
    private List<Item> inventario;

    public Jogador(Ambiente ambienteInicial) {
        this.ambienteAtual = ambienteInicial;
        this.quantAberturaPortas = 0;
        this.quantAberturaPortasRestante = 0;
        this.possuiCargaExplosiva = true; // o cacador de tesouros tem uma unica carga explosiva
        this.inventario = new ArrayList<Item>();
        definirQuantidadeAberturaPortas();
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    // define a quantidade de aberturas de portas aleatoriamente entre 10 e 30
    private void definirQuantidadeAberturaPortas() {
        this.quantAberturaPortasRestante = (int) (Math.random() * 21) + 10; // Gera um número aleatório entre 10 e 30
        this.quantAberturaPortas = quantAberturaPortasRestante;
    }

    public int getQuantidadeAberturaPortas() {
        return quantAberturaPortas;
    }

    public int getQuantidadeAberturaPortasRestante() {
        return quantAberturaPortasRestante;
    }

    //consome uma tentativa de abertura de porta
    public void gastarTentativa() {
        if (quantAberturaPortasRestante > 0) {
            quantAberturaPortasRestante--;
        }
    }

    public boolean temTentativas() {
        return quantAberturaPortasRestante > 0;
    }

    // adiciona um item ao inventario do jogador
    public void adicionarItem(Item item) {
        if (item != null) {
            inventario.add(item);
        }
    }

    // remove um item do inventario do jogador
    public void removerItem(Item item) {
        inventario.remove(item);
    }

    // verifica se o jogador possui um item com o nome informado
    public boolean possuiItem(String nome) {
        if (nome == null) {
            return false;
        }
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nome.trim())) {
                return true;
            }
        }
        return false;
    }

    // retorna uma visao somente leitura do inventario
    public List<Item> getInventario() {
        return Collections.unmodifiableList(inventario);
    }

    // retorna a chave mestra do inventario (ou null se o jogador ainda nao a possui)
    public ChaveMestra getChaveMestra() {
        for (Item item : inventario) {
            if (item instanceof ChaveMestra) {
                return (ChaveMestra) item;
            }
        }
        return null;
    }

    public boolean possuiCargaExplosiva() {
        return possuiCargaExplosiva;
    }

    /**
     * Usa a carga explosiva (que eh unica).
     * @return true se o jogador tinha a carga e ela foi usada.
     */
    public boolean usarCargaExplosiva() {
        if (possuiCargaExplosiva) {
            possuiCargaExplosiva = false; // Remove a carga explosiva do jogador
            return true;
        }
        return false;
    }

}
