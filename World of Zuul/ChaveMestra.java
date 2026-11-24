public class ChaveMestra extends Item {
    public Ambiente localizacaoChaveMestra;
    private int durabilidade;

    public ChaveMestra(Ambiente localizacao, int durabilidade) {
        super("Chave Mestra");
        this.localizacaoChaveMestra = localizacao;
        this.durabilidade = durabilidade;
    }


    //metodo para saber se posso usar a chave mestra
    public boolean podeUsarChaveMestra() {
        return durabilidade > 0;
    }
}
