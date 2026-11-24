import java.util.HashMap;
import java.util.Map;

public class PalavrasComando{
    private final Map<String, PalavraDeComando> comandosValidos;

   
    public PalavrasComando(){
        comandosValidos = new HashMap<String, PalavraDeComando>();
        for (PalavraDeComando palavra : PalavraDeComando.values()) {
            if (palavra != PalavraDeComando.DESCONHECIDO) {
                comandosValidos.put(palavra.getTexto(), palavra);
            }
        }
    }

    /**
     * Converte um texto digitado em palavra de comando.
     *
     * @param texto primeira palavra digitada (pode ser null).
     * @return a palavra correspondente ou {@link PalavraDeComando#DESCONHECIDO}.
     */

    //
    public PalavraDeComando getPalavra(String texto)
    {
        PalavraDeComando palavra = comandosValidos.get(texto);
        if (palavra != null) {
            return palavra;
        }
        else {
            return PalavraDeComando.DESCONHECIDO;
        }
        
    }

    
    public boolean ehComando(String texto)
    {
        return getPalavra(texto) != PalavraDeComando.DESCONHECIDO;
    }

    //lista os comandos e suas descrições
    public String getDescricoesComandos()
    {
        StringBuilder sb = new StringBuilder();
        for (PalavraDeComando palavra : PalavraDeComando.values()) {
            if (palavra != PalavraDeComando.DESCONHECIDO) {
                sb.append("   ").append(palavra.getDescricao()).append("\n");
            }
        }
        return sb.toString();
    }
}
