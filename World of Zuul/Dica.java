public class Dica {
    public String texto;
    public Ambiente ambienteIndicado;
    public boolean dicaDescoberta;

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
        if (dicaDescoberta) {
            System.out.println("Dica ja foi descoberta!");
        } else {
            System.out.println("Dica descoberta: " + texto);
            setDicaDescoberta(true);
        }
        return dicaDescoberta;
    }

    private void setDicaDescoberta(boolean dicaDescoberta) {
        this.dicaDescoberta = dicaDescoberta;
    }

}
