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
        
        System.out.println("Voce esta " + ambienteAtual.getDescricao());
    
        System.out.print("Saidas: ");
        if(ambienteAtual.getSaidas().get("norte") != null) {
            System.out.print("norte ");
        }
        if(ambienteAtual.getSaidas().get("leste") != null) {
            System.out.print("leste ");
        }
        if(ambienteAtual.getSaidas().get("sul") != null) {
            System.out.print("sul ");
        }
        if(ambienteAtual.getSaidas().get("oeste") != null) {
            System.out.print("oeste ");
        }
        System.out.println();
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


    public void imprimirDescricaoAmbiente(Ambiente ambiente) {
        System.out.println("Voce esta " + ambiente.getDescricao());
    
        System.out.print("Saidas: ");
        if(ambiente.getSaidas().get("norte") != null) {
            System.out.print("norte ");
        }
        if(ambiente.getSaidas().get("leste") != null) {
            System.out.print("leste ");
        }
        if(ambiente.getSaidas().get("sul") != null) {
            System.out.print("sul ");
        }
        if(ambiente.getSaidas().get("oeste") != null) {
            System.out.print("oeste ");
        }
        System.out.println();
    }
}
