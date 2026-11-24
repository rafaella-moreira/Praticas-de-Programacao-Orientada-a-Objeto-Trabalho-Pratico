public class Jogador {
    private Ambiente ambienteAtual;
    private int quantidadeAberturaPortas;
    private boolean possuiCargaExplosiva;

    public Jogador(Ambiente ambienteInicial) {
        this.ambienteAtual = ambienteInicial;
        this.quantidadeAberturaPortas = 0;
        this.possuiCargaExplosiva = false;
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    // define a quantidade de aberturas de portas aleatoriamente entre 10 e 30
    private void definirQuantidadeAberturaPortas() {
        this.quantidadeAberturaPortas = (int) (Math.random() * 21) + 10; // Gera um número aleatório entre 10 e 30
    }

    public int getQuantidadeAberturaPortas() {
        definirQuantidadeAberturaPortas();
        return quantidadeAberturaPortas;
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
