public class ComandoDesconhecido extends Comando{

    public ComandoDesconhecido(){
        super(PalavraDeComando.DESCONHECIDO, null);
    }

    @Override
    public String executar(Jogo jogo){
        return "Eu não entendi o que você disse... Digite 'ajuda' para ver os comandos.";
    }
}