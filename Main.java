public class Main {
    public static void main(String[] args) {
    
        System.out.println( "---------------- Algoritomo NRU ------------- \n\n Start");
        
        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        for(int i = 0; i < 100; i++) {
            if(i == 99){
                Global.startSimulador(AlgoritmosConstantes.NRU, true);
                break;
            }

            Global.startSimulador(AlgoritmosConstantes.NRU, false);
        }




        System.out.println( "---------------- Algoritomo FIFO ------------- \n\n Start");
        
        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        for(int i = 0; i < 100; i++) {
            if(i == 99){
                Global.startSimulador(AlgoritmosConstantes.FIFO, true);
                break;
            }

            Global.startSimulador(AlgoritmosConstantes.FIFO, false);
        }



        System.out.println( "---------------- Algoritomo FIFO-SC ------------- \n\n Start");
        
        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        for(int i = 0; i < 100; i++) {
            if(i == 99){
                Global.startSimulador(AlgoritmosConstantes.FIFO_SC, true);
                break;
            }

            Global.startSimulador(AlgoritmosConstantes.FIFO_SC, false);
        }

        

        System.out.println( "---------------- Algoritomo RELOGIO ------------- \n\n Start");
        
        Global.criarMatrizPreenchida100x5();
        Global.preencherMatriz10x5ComSorteoMatriz100x5();

        for(int i = 0; i < 100; i++) {
            if(i == 99){
                Global.startSimulador(AlgoritmosConstantes.RELOGIO, true);
                break;
            }

            Global.startSimulador(AlgoritmosConstantes.RELOGIO, false);
        }
    }
}
