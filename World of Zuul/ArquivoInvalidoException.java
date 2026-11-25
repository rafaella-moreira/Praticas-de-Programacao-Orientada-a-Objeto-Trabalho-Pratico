/**
 * Excecao lancada quando um arquivo do jogo (mapa ou gabarito) nao pode ser
 * lido/escrito ou possui conteudo invalido.
 */
public class ArquivoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ArquivoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public ArquivoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
