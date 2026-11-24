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

}
