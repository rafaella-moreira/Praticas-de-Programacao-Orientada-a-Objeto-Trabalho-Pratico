import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 * 
 * Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    private Scanner entrada;         // origem da entrada de comandos

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    /**
     * @return O proximo comando do usuario
     */
    public Comando pegarComando() 
    {
        String linha;   // guardara uma linha inteira
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");     // imprime o prompt

        linha = entrada.nextLine();

        // Tenta encontrar a palavra de comando e o ambeinte de destino independente de quantas palavras o usuario digitou.
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.nextLine().trim();      // pega as palavras referentes ao ambiente de destino. 
                // nao ignoramos o resto da linha.
            }
        }

        
        return criarComando(palavrasDeComando.getPalavra(palavra1), palavra2);
        
    }

    private Comando criarComando(PalavraDeComando palavra, String argumento)
    {
        switch (palavra) {
            case IR:
                return new ComandoIr(argumento);
            case CHAVE:
                return new ComandoChave(argumento);
            case EXPLODIR:
                return new ComandoExplodir(argumento);
            case AJUDA:
                return new ComandoAjuda(argumento);
            case SAIR:
                return new ComandoSair(argumento);
            default:
                return new ComandoDesconhecido();
        }
    }    
    
}
