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

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    public void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }


    public void imprimirAmbientesSaidas(Ambiente ambiente) {

        System.out.print("Saidas: ");
        for (Porta porta : ambienteAtual.getPortas()) {
            Ambiente ambienteConectado = porta.getAmbienteConectado(ambienteAtual);
            if (ambienteConectado != null) {
                System.out.print(ambienteConectado.getDescricao() + " ");
            }
        }
        System.out.println();
    }

    //imprimir descricao ambiente
    public void imprimirDescricaoAmbiente(Ambiente ambiente) {
        System.out.println("Voce esta " + ambiente.getDescricao());
    }

    public void verificarDicaDescoberta(Dica dica) {
        if (dica.dicaDescoberta()) {
            System.out.println("Dica descoberta: " + dica.getTexto());
        } else {
            System.out.println("Dica ja foi descoberta!");
        }

    }
}
