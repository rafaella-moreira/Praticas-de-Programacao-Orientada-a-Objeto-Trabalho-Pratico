public class ComandoExplodir extends Comando{

    public ComandoExplodir(String argumento)
    {
        super(PalavraDeComando.EXPLODIR, argumento);
    }

    @Override
    public String executar(Jogo jogo){
        if (temSegundaPalavra()) {
            return "Explodir o que? Basta digitar 'explodir'.";
        }
        return jogo.explodir();
    }
}