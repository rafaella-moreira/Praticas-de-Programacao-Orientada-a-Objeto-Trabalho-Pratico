public class InterfaceTerminal {

    public Ambiente ambienteAtual;
    
    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    public void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirDescricaoAmbiente(ambienteAtual);    
        imprimirAmbientesSaidas(ambienteAtual);
    }

    // imprime os comandos possiveis para ajuda 
    public void imprimirAjuda() {
        System.out.println("Comandos validos: ");
        System.out.println("ir <nome do ambiente> : tenta abrir a porta e ir para o ambiente (gasta 1 tentativa)");
        System.out.println("chave <nome do ambiente>: usa a chave mestra para abrir a porta que dá acesso ao ambiente (não gasta tentativa) ");
        System.out.println("explodir: usa a carga explosiva no ambiente atual");
        System.out.println("ajuda: mostra esta ajuda");
        System.out.println("sair: encerra o jogo");
    }


    public void imprimirAmbientesSaidas(Ambiente ambiente) {

        System.out.print("Saidas: ");
        for (Porta porta : ambiente.getPortas()) {
            Ambiente ambienteConectado = porta.getAmbienteConectado(ambiente);
            if (ambienteConectado != null) {
                System.out.print("[" + ambienteConectado.getDescricao() + "] ");
            }
        }
        System.out.println();
    }

    //imprimir descricao ambiente
    public void imprimirDescricaoAmbiente(Ambiente ambiente) {
        System.out.println("Voce esta em: " + ambiente.getDescricao());
    }

    public void verificarDicaDescoberta(Dica dica) {
        if (dica.dicaDescoberta()) {
            System.out.println("Dica descoberta: " + dica.getTexto());
        } else {
            System.out.println("Esta dica ainda nao foi descoberta!");
        }

    }
}
