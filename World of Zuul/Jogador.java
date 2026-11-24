public class Jogador {
    private Ambiente ambienteAtual;
    private int quantAberturaPortas;
    private int quantAberturaPortasRestante;
    private boolean possuiCargaExplosiva;
    private ChaveMestra chaveMestra;

    public Jogador(Ambiente ambienteInicial) {
        this.ambienteAtual = ambienteInicial;
        this.quantAberturaPortas = 0;
        this.quantAberturaPortasRestante = 0;
        this.possuiCargaExplosiva = false;
        this.chaveMestra = null;
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

    //usar carga explosiva
    public void usarCargaExplosiva() {
        if (possuiCargaExplosiva) {
            System.out.println("Voce explodiu " + ambienteAtual + "!");
            possuiCargaExplosiva = false; // Remove a carga explosiva do jogador
        } else {
            System.out.println("Voce nao possui carga explosiva!");
        }
    }

}
