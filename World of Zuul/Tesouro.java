public class Tesouro extends Item {
    private Ambiente localizacaoTesouro;

    public Tesouro(Ambiente localizacaoTesouro) {
        super("Tesouro");
        this.localizacaoTesouro = localizacaoTesouro;
    }

    public Ambiente getLocalizacaoTesouro() {
        return localizacaoTesouro;
    }

    // o tesouro fica enterrado no ambiente: so eh recuperado com a carga explosiva
    @Override
    public String aoExplorar(Jogador jogador, Ambiente ambiente) {
        return null;
    }
}
