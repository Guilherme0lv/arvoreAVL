public class Main {
    public static void main(String[] args) {
        ArvoreAVL a = new ArvoreAVL();

        a.inserir(30);
        a.inserir(10);
        a.inserir(25);
        a.inserir(40);
        a.inserir(5);
        a.inserir(60);

        System.out.println("Pre ordem");
        a.preOrdem();

        System.out.println("Em ordem");
        a.emOrdem();

        System.out.println("Pos ordem");
        a.posOrdem();

        System.out.println(a.raiz);

        
    }
}