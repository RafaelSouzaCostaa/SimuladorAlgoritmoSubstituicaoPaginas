public class Main {
    public static void main(String[] args) {
        //Global.matrizGlobal10x5(2, 1);
        //Global.setValorMatriz10x5(2, 1, 500);
        //Global.matrizGlobal10x5(2, 1);

        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        for(int i = 0; i < 1000; i++) {
            if(i == 900){
                Global.startSimulador(AlgoritimosConstantes.FIFO, true);
            }

            Global.startSimulador(AlgoritimosConstantes.FIFO, false);
        }

        //Global.imprimirMatriz(Global.getMatriz100x5(), "DISCO 100x5");

    }
}
