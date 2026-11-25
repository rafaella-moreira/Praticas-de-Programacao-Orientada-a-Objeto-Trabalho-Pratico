/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

import java.util.ArrayList;
import java.util.List;

public class Jogo 
{
    private static final String ARQUIVO_MAPA = "mapa.txt";
    private static final String ARQUIVO_GABARITO = "gabarito.txt";

    private Analisador analisador;
    private CasaAssombrada casa;
    private Jogador jogador;
    private Gabarito gabarito;
    private InterfaceTerminal interfaceTerminal;
    private final PalavrasComando palavrasComando;
    private SituacaoJogo situacao;
    
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     * @throws ArquivoInvalidoException se o arquivo de mapa nao puder ser carregado.
     */
    public Jogo() throws ArquivoInvalidoException
    {
        criarAmbientes();
        analisador = new Analisador();
        palavrasComando = new PalavrasComando();
        interfaceTerminal = new InterfaceTerminal();
        situacao = SituacaoJogo.EM_ANDAMENTO;
    }

    /**
     * Cria todos os ambientes e liga as saidas deles (lendo o arquivo de mapa),
     * cria o jogador, sorteia o gabarito e o grava em arquivo.
     * O arquivo de gabarito eh obrigatorio: se nao puder ser gravado, a partida nao inicia.
     */
    private void criarAmbientes() throws ArquivoInvalidoException
    {
        casa = new CasaAssombrada(ARQUIVO_MAPA);
        jogador = new Jogador(casa.getAmbienteInicial());
        gabarito = new GeradorGabarito(casa).gerar();

        new GerenciadorArquivoGabarito(gabarito).escreverGabarito(ARQUIVO_GABARITO);
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        interfaceTerminal.ambienteAtual = jogador.getAmbienteAtual();
        interfaceTerminal.imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
    }    

    /**
     * Dado um comando, processa-o (ou seja, executa-o).
     * Cada subclasse de Comando sabe se executar (polimorfismo).
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        Ambiente antes = jogador.getAmbienteAtual();

        System.out.println(comando.executar(this));

        if (situacao != SituacaoJogo.EM_ANDAMENTO) {
            return true;
        }
        if (jogador.getAmbienteAtual() != antes) {
            explorarAmbienteAtual();
        }
        return false;
    }

    /**
     * Mostra o ambiente em que o jogador acabou de entrar, explorando
     * os itens (dicas, chave mestra...) que estiverem la.
     */
    private void explorarAmbienteAtual()
    {
        Ambiente atual = jogador.getAmbienteAtual();
        interfaceTerminal.ambienteAtual = atual;
        interfaceTerminal.imprimirDescricaoAmbiente(atual);

        // copia da lista: itens (como a chave mestra) podem sair do ambiente durante a exploracao
        List<Item> itens = new ArrayList<Item>(atual.getItens());
        for (Item item : itens) {
            String mensagem = item.aoExplorar(jogador, atual); // polimorfismo: cada item se comporta a sua maneira
            if (mensagem != null) {
                System.out.println(mensagem);
            }
        }

        interfaceTerminal.imprimirAmbientesSaidas(atual);
    }

    // Implementacoes dos comandos do usuario

    /** 
     * Tenta abrir a porta para o ambiente informado (gasta 1 tentativa).
     * A porta pode emperrar; nesse caso o jogador continua onde estava.
     * @return mensagem com o resultado.
     */
    public String irParaAmbiente(String nomeDestino) 
    {
        Ambiente atual = jogador.getAmbienteAtual();
        Porta porta = atual.getPortaPara(nomeDestino);
        if (porta == null) {
            return "Nao ha porta para '" + nomeDestino + "' a partir daqui!";
        }
        if (!jogador.temTentativas()) {
            return "Suas tentativas de abrir portas acabaram! " + opcoesSemTentativas();
        }

        jogador.gastarTentativa();

        String restantes = "(tentativas restantes: " + jogador.getQuantidadeAberturaPortasRestante() + ")";
        String resultado;
        if (porta.permitePassagem()) {
            jogador.setAmbienteAtual(porta.getAmbienteConectado(atual));
            resultado = "A porta abriu! " + restantes;
        } else {
            resultado = "A porta emperrou! " + restantes;
        }
        // apos cada tentativa, o estado de TODAS as portas eh sorteado novamente
        casa.atualizarEstadosPortas();
        if (!jogador.temTentativas()) {
            resultado += "\nSuas tentativas acabaram! " + opcoesSemTentativas();
        }
        return resultado;
    }

    /**
     * Usa a chave mestra para abrir a porta (nao gasta tentativa).
     * @return mensagem com o resultado.
     */
    public String irComChaveMestra(String nomeDestino)
    {
        ChaveMestra chave = jogador.getChaveMestra();
        if (chave == null) {
            return "Voce ainda nao encontrou a chave mestra!";
        }
        if (!chave.podeUsarChaveMestra()) {
            return "A chave mestra quebrou e nao pode mais ser usada!";
        }

        Ambiente atual = jogador.getAmbienteAtual();
        Porta porta = atual.getPortaPara(nomeDestino);
        if (porta == null) {
            return "Nao ha porta para '" + nomeDestino + "' a partir daqui!";
        }

        chave.usar();
        jogador.setAmbienteAtual(porta.getAmbienteConectado(atual));
        return "Voce destrancou a porta com a chave mestra! (usos restantes: " + chave.getDurabilidade() + ")";
    }

    /**
     * Usa a unica carga explosiva no ambiente atual. Se o tesouro estiver
     * aqui o jogador vence; caso contrario, perde.
     * @return mensagem com o resultado.
     */
    public String explodir()
    {
        if (!jogador.usarCargaExplosiva()) {
            return "Voce nao possui carga explosiva!";
        }

        Ambiente atual = jogador.getAmbienteAtual();
        Ambiente local = gabarito.getTesouro().getLocalizacaoTesouro();
        String mensagem = "BOOM! Voce explodiu '" + atual.getDescricao() + "'!\n";
        if (atual == local) {
            situacao = SituacaoJogo.VITORIA;
            return mensagem + "Entre os escombros voce encontrou o TESOURO! Parabens, voce venceu!";
        }
        situacao = SituacaoJogo.DERROTA;
        return mensagem + "Nao havia nada aqui... O tesouro estava em '" + local.getDescricao()
                + "'. Voce perdeu!";
    }

    private String opcoesSemTentativas()
    {
        return "Resta usar a chave mestra (se a tiver) ou a carga explosiva neste ambiente.";
    }

    //
    public String getAjuda(){
        return "Você é um caçador de tesouros em uma casa abandonada e mal assombrada.\n"
             + "O tesouro está enterrado em um dos ambientes. Explore a casa abrindo\n"
             + "portas (algumas podem emperrar!) e use sua única carga explosiva\n"
             + "no ambiente onde você acha que o tesouro está.\n\n"
             + "Suas palavras de comando são:\n"
             + palavrasComando.getDescricoesComandos();
    }

    public String sair()
    {
        situacao = SituacaoJogo.ENCERRADO;
        return "Obrigado por jogar. Até mais!";
    }
}

