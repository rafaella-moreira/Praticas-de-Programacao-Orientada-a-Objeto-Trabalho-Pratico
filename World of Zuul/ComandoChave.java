public class ComandoChave extends Comando{
    
    public ComandoChave(String argumento){
        super(PalavraDeComando.CHAVE, argumento);
    }

    @Override
    public String executar(Jogo jogo){
        if (!temSegundaPalavra()) {
            return "Usar a chave para ir aonde?";
        }
        return jogo.irComChaveMestra(getPalavraArgumento());
    }
}
