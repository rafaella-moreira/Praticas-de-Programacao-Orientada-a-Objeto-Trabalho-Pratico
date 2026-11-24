public class ComandoSair extends Comando{

    public ComandoSair(String argumento){
        super(PalavraDeComando.SAIR, argumento);
    }

    @Override
    public String executar(Jogo jogo)
    {
        if (temSegundaPalavra()) {
            return "Sair o que?";
        }
        return jogo.sair();
    }
}