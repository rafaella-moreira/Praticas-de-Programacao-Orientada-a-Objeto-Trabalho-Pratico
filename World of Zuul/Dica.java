public class Dica {
    private String texto;
    private Ambiente ambienteIndicado;
    private boolean dicaDescoberta;

    public Dica(String texto, Ambiente ambienteIndicado) {
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
