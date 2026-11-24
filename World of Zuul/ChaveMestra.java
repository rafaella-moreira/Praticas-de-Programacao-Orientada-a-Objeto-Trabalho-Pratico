public class ChaveMestra {
    private int durabilidade;

    public ChaveMestra(int durabilidade) {
        this.durabilidade = durabilidade;
    }


    //utilizar a chave mestra, reduzindo sua durabilidade
    public void usarChaveMestra() {
        if (durabilidade > 0) {
            durabilidade--;
            System.out.println("Chave mestra usada! Durabilidade restante: " + durabilidade);
        } else {
            System.out.println("A chave mestra nao tem mais durabilidade!");
        }
    }
}
