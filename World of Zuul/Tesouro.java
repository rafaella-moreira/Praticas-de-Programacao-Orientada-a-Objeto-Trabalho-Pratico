public class Tesouro extends Item {
    private Ambiente localizacaoTesouro;

    public Tesouro(Ambiente localizacaoTesouro) {
        super("Tesouro");
        this.localizacaoTesouro = localizacaoTesouro;
    }

    public Ambiente getLocalizacaoTesouro() {
        return localizacaoTesouro;
    }
}
