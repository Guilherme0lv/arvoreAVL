public class Elemento {

    int valor;
    int altura;
    int bal;
    Elemento esquerda, direita;

    public Elemento(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 0;
        this.bal = 0;

    }

    public String toString() {
        return this.valor + "";
    }
}