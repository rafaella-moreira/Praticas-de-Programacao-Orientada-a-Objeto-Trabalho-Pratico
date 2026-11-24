public class ComandoAjuda extends Comando{
    
    public ComandoAjuda(String argumento){
        super(PalavraDeComando.AJUDA, argumento);
    }

    @Override
    public String executar(Jogo jogo){
        return jogo.getAjuda();
    }
}
