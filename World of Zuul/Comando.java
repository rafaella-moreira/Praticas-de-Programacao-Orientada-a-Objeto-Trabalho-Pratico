/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Essa classe guarda informacoes sobre um comando que foi digitado pelo 
 * usuario. Um comando atualmente consiste em duas strings: uma palavra
 * de comando e uma segunda palavra (por exemplo, se o campo for "pegar
 * mapa", entao as duas strings obviamente serao "pegar" e "mapa").
 * 
 * Isso eh usado assim: comandos ja estao validados como comandos validos
 * Se o usuario entrou um comando invalido (uma palavra que nao eh
 * conhecida) entao o a palavra de comando eh <null>.
 *
 * Se o comando tem so uma palavra, a segunda palavra eh <null>
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public abstract class Comando
{
    private PalavraDeComando palavraDeComando;
    private String palavraArgumento;

    /**
     * Cria um objeto comando. Primeira e segunda palavra devem ser 
     * fornecidas, mas qualquer uma (ou ambas) podem ser null.
     * @param primeiraPalavra A primeira palavra do comando. Null se
     * 						  o comando nao foi reconhecido
     * @param palavraArgumento O ambiente de destino do comando.
     */
    public Comando(PalavraDeComando palavraDeComando, String palavraArgumento)
    {
        this.palavraDeComando = palavraDeComando;
        this.palavraArgumento = palavraArgumento;
    }

    
    public PalavraDeComando getPalavraDeComando()
    {
        return palavraDeComando;
    }

    
    public String getPalavraArgumento()
    {
        return palavraArgumento;
    }

    
    public boolean ehDesconhecido()
    {
        return (palavraDeComando == null);
    }


    public boolean temSegundoArgumento()
    {
        return (palavraArgumento != null);
    }

    public String getSegundaPalavra()
    {
        return palavraArgumento;
    }

    
    public boolean temSegundaPalavra()
    {
        return palavraArgumento != null;
    }

    public abstract String executar(Jogo jogo);
}