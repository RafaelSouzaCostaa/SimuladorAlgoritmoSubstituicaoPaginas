public class Main {
    public static void main(String[] args) {
        //Global.matrizGlobal10x5(2, 1);
        //Global.setValorMatriz10x5(2, 1, 500);
        //Global.matrizGlobal10x5(2, 1);

        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        
        Global.imprimirMatriz(Global.getMatriz100x5());
        System.out.println("---------------------------");
        Global.imprimirMatriz(Global.getMatriz10x5());
        System.out.println("---------------------------");
        Global.startSimulador();

        Global.imprimirMatriz(Global.getMatriz100x5());

    }
}
