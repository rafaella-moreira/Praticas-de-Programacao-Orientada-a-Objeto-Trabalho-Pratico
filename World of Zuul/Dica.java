public class Dica extends Item {
    private String texto;
    private Ambiente ambienteIndicado;
    private boolean dicaDescoberta;

    public Dica(String texto, Ambiente ambienteIndicado) {
        super("Dica");
        this.texto = texto;
        this.ambienteIndicado = ambienteIndicado;
        this.dicaDescoberta = false;
    }

    public String getTexto() {
        return texto;
    }

    public Ambiente getAmbienteIndicado() {
        return ambienteIndicado;
    }

    public boolean dicaDescoberta() {
        return dicaDescoberta;
    }

    //marca a dica como descoberta pelo jogador
    public void descobrir() {
        this.dicaDescoberta = true;
    }

    // a dica permanece no ambiente e eh revelada na primeira vez que o jogador o explora
    @Override
    public String aoExplorar(Jogador jogador, Ambiente ambiente) {
        if (dicaDescoberta) {
            return null;
        }
        descobrir();
        return "Dica descoberta: " + texto;
    }

}
