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
    private Scanner entrada;         // entrada do terminal (criada apenas em pegarComando)

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
    }

    /**
     * Le uma linha do terminal e a interpreta como comando.
     * @return O proximo comando do usuario
     */
    public Comando pegarComando() {
        if (entrada == null) {
            entrada = new Scanner(System.in);   // so cria a entrada de terminal quando for realmente usada
        }

        System.out.print("> ");     // imprime o prompt

        if (!entrada.hasNextLine()) {   // fim da entrada: encerra o jogo
            return new ComandoSair(null);
        }
        return interpretarComando(entrada.nextLine());
    }

    /**
     * Transforma um texto (de qualquer origem: terminal, GUI...) em um Comando.
     * @param linha O texto digitado pelo usuario.
     * @return O Comando correspondente ao texto.
     */
    public Comando interpretarComando(String linha) {
        String palavra1 = null;
        String palavra2 = null;
        if (linha == null) {
            linha = "";
        }

        // Tenta encontrar a palavra de comando e o ambeinte de destino independente de quantas palavras o usuario digitou.
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next().toLowerCase();      // pega a primeira palavra
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
