import java.util.Random;

public class Porta {
    Ambiente ambiente1;
    Ambiente ambiente2;
    EstadoPorta estadoPorta;
    Random random = new Random();

    public Porta(Ambiente ambiente1, Ambiente ambiente2) {
        this.ambiente1 = ambiente1;
        this.ambiente2 = ambiente2;
    }

    //modifica o estado da porta aleatoriamente
    public void modificarEstadoPorta() {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            estadoPorta = EstadoPorta.EMPERRADA;
        } else {
            estadoPorta = EstadoPorta.FUNCIONANDO_CORRETAMENTE;
        }
    }

    //verifica se a porta permite passagem
    public boolean permitePassagem() {
        return estadoPorta == EstadoPorta.FUNCIONANDO_CORRETAMENTE;
    }

    // Identificar o ambiente conectado a partir de determinado ambiente.
    public Ambiente getAmbienteConectado(Ambiente ambiente) {
        if (ambiente.equals(ambiente1)) {
            return ambiente2;
        } else if (ambiente.equals(ambiente2)) {
            return ambiente1;
        } else {
            return null; // O ambiente fornecido não está conectado a esta porta
        }
    }
}
