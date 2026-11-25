public class ChaveMestra extends Item {
    private Ambiente localizacaoChaveMestra;
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

    //consome um uso da chave mestra
    public void usar() {
        if (durabilidade > 0) {
            durabilidade--;
        }
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public Ambiente getLocalizacaoChaveMestra() {
        return localizacaoChaveMestra;
    }

    // a chave eh recolhida: sai do ambiente e passa para o inventario do jogador
    @Override
    public String aoExplorar(Jogador jogador, Ambiente ambiente) {
        if (jogador.possuiItem(getNome())) {
            return null;
        }
        ambiente.removerItem(this);
        jogador.adicionarItem(this);
        return "Voce encontrou a Chave Mestra! Ela pode abrir portas "
                + durabilidade + " vezes sem gastar tentativas.";
    }
}
