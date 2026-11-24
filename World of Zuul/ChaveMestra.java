public class ChaveMestra {
    public Ambiente localizacaoChaveMestra;
    private int durabilidade;

    public ChaveMestra(Ambiente localizacao, int durabilidade) {
        this.localizacaoChaveMestra = localizacao;
        this.durabilidade = durabilidade;
    }


    //metodo para saber se posso usar a chave mestra
    public boolean podeUsarChaveMestra() {
        return durabilidade > 0;
    }
}
