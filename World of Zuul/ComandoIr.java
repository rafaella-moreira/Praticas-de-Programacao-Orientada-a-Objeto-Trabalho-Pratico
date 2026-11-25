public class ComandoIr extends Comando{
    
    public ComandoIr(String argumento){
        super(PalavraDeComando.IR, argumento);
    }

    @Override
    public String executar(Jogo jogo){
        if (!temSegundaPalavra()) {
            return "Ir para onde?";
        }
        return jogo.irParaAmbiente(getPalavraArgumento());
    }
}