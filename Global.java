import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Global{

    private static int[][] matriz10x5 = new int[10][5];
    private static int[][] matriz100x5 = new int[100][5];
    private static Random random = new Random();    

    //Metodo de Criação Inicial Matriz 100x5
    public static Boolean criarMatrizPreenchida100x5() {
        
        int n = 0;
        int i = 1;

        for(int j = 0; j < 100; j++){
            matriz100x5[j][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = n;
            matriz100x5[j][CamposMatrizConstante.INSTRUCAO_I] = i;
            matriz100x5[j][CamposMatrizConstante.DADO_D] = random.nextInt(50)+1;
            matriz100x5[j][CamposMatrizConstante.BIT_DE_ACESSO_R] = 0;
            matriz100x5[j][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = 0;

            i += 1;
            n += 1;
        }
        return true;
    }

    private static void retirarPagina(int index) {
        int indexSorteado100x5 = random.nextInt(100);

        atualizandoPaginasNaMatrix100x5();

        matriz10x5[index][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
        matriz10x5[index][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.INSTRUCAO_I];
        matriz10x5[index][CamposMatrizConstante.DADO_D] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.DADO_D];
        matriz10x5[index][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
        matriz10x5[index][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_ACESSO_R];
    }

    private static void atualizandoPaginasNaMatrix100x5() {
        
        for(int t = 0; t < matriz10x5.length; t++){
            if(matriz10x5[t][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 1){
                for(int i = 0; i<100; i++){
                    if(matriz10x5[t][CamposMatrizConstante.NUMERO_DE_PAGINA_N] == matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N]){
                        matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[t][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                        matriz100x5[i][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[t][CamposMatrizConstante.INSTRUCAO_I];
                        matriz100x5[i][CamposMatrizConstante.DADO_D] = matriz10x5[t][CamposMatrizConstante.DADO_D];
                        matriz100x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[t][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                        matriz100x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[t][CamposMatrizConstante.BIT_DE_ACESSO_R];
                    }
                }
            }
        }
    }

    private static void atualizandoUmaPaginaNaMatrix100x5(int index) {
        

        for(int i = 0; i<100; i++){
            if(matriz10x5[index][CamposMatrizConstante.NUMERO_DE_PAGINA_N] == matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N]){
                matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[index][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                matriz100x5[i][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[index][CamposMatrizConstante.INSTRUCAO_I];
                matriz100x5[i][CamposMatrizConstante.DADO_D] = matriz10x5[index][CamposMatrizConstante.DADO_D];
                matriz100x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[index][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                matriz100x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[index][CamposMatrizConstante.BIT_DE_ACESSO_R];
            }
        }
    }
    //-------------- Algoritmo NRU Start --------------\\


    public static void startAlgoritimoNRU() { 

        imprimirMatriz(matriz100x5, "\n\nDISCO 100x5 - NRU - Antiga\n\n");
        imprimirMatriz(matriz10x5, "\n\nRAM 10x5 - NRU - Antiga\n\n");

        for(int t = 0; t < 500; t++){
            for(int i = 0; i < matriz10x5.length; i++){
                if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 0 && matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 0){
                    retirarPagina(i);
                }else if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 0 && matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 1) {
                    retirarPagina(i);
                }else if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1 && matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 0){
                    retirarPagina(i);
                }else if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1 && matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 1){
                    retirarPagina(i);
                }
            }
        }

        imprimirMatriz(matriz100x5, "\n\nDISCO 100x5 - NRU - Atual\n\n");
        imprimirMatriz(matriz10x5, "\n\nRAM 10x5 - NRU - Atual\n\n");
    }
    //-------------- Algoritmo NRU End --------------\\

    //-------------- Algoritmo FIFO Start --------------\\
    public static void startAlgoritimoFIFO() {
        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO - Antiga");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO - Antiga");

        for(int t = 0; t < 500; t++){
            for(int i = 0; i < matriz10x5.length; i++){ // FIFO primeira pagina que entra e a primeira que sai.
                retirarPagina(i);
            }
        }

        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO - Atual");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO - Atual");
    }
    //-------------- Algoritmo FIFO End --------------\\

    //-------------- Algoritmo FIFO-SC Start --------------\\
    public static void startAlgoritimoFIFO_SC() {
        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO_SC - Antiga");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO_SC - Antiga");

        int primeiroDaFila= 0;

        for(int t = 0; t < 500; t++){
            // FIFO_SC na primeira pagina e verificado o bit r, caso 1 a pargina vai para o final da fila
            if(matriz10x5[primeiroDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1){ //BIT R = 1
            
                matriz10x5[primeiroDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] = 0;
                primeiroDaFila++;

                if(primeiroDaFila == ( matriz10x5.length - 1) ){
                    primeiroDaFila = 0;
                }


            }else if(matriz10x5[primeiroDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] == 0){  //BIT R = 0 > Pagina vai sair

                atualizandoUmaPaginaNaMatrix100x5(primeiroDaFila);

                int novaPaginaSorteadaDisco = random.nextInt(100);
                matriz10x5[primeiroDaFila][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                matriz10x5[primeiroDaFila][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.INSTRUCAO_I];
                matriz10x5[primeiroDaFila][CamposMatrizConstante.DADO_D] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.DADO_D];
                matriz10x5[primeiroDaFila][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                matriz10x5[primeiroDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.BIT_DE_ACESSO_R];

                primeiroDaFila++;

                if(primeiroDaFila == ( matriz10x5.length - 1) ){
                    primeiroDaFila = 0;
                }
            }
        }

        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO_SC - Atual");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO_SC - Atual");
    }
    //-------------- Algoritmo FIFO-SC End --------------\\

    //-------------- Algoritmo RELOGIO Start --------------\\

    public static void startAlgoritimoRELOGIO() {

        imprimirMatriz(matriz100x5, "DISCO 100x5 - RELOGIO - Antiga");
        imprimirMatriz(matriz10x5, "RAM 10x5 - RELOGIO - Antiga");
        
        int inicioDaFila = 0;

        for(int t = 0; t < 500; t++){
            if(matriz10x5[inicioDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1){
                matriz10x5[inicioDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] = 0;
                
                if(matriz10x5.length - 1 == 9){
                    inicioDaFila = 0;
                }else{
                    inicioDaFila += 1;
                }
            }else if(matriz10x5[inicioDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] == 0) {
                
                atualizandoPaginasNaMatrix100x5();
                int novaPaginaSorteadaDisco = random.nextInt(100);
                matriz10x5[inicioDaFila][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                matriz10x5[inicioDaFila][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.INSTRUCAO_I];
                matriz10x5[inicioDaFila][CamposMatrizConstante.DADO_D] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.DADO_D];
                matriz10x5[inicioDaFila][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                matriz10x5[inicioDaFila][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[novaPaginaSorteadaDisco][CamposMatrizConstante.BIT_DE_ACESSO_R];

                if(matriz10x5.length - 1 == 9){
                    inicioDaFila = 0;
                }else{
                    inicioDaFila += 1;
                }
            }
        }

        imprimirMatriz(matriz100x5, "DISCO 100x5 - RELOGIO - Atual");
        imprimirMatriz(matriz10x5, "RAM 10x5 - RELOGIO - Atual");
    }

    //-------------- Algoritmo RELOGIO End --------------\\

    //Preenchendo matriz 10x5 com valores sorteados apartir da 100x5

    public static void preencherMatriz10x5ComSorteoMatriz100x5() {
    
        for(int i = 0; i < 10; i++){
            int index = random.nextInt(100);
            
            matriz10x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[index][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
            matriz10x5[i][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[index][CamposMatrizConstante.INSTRUCAO_I];
            matriz10x5[i][CamposMatrizConstante.DADO_D] = matriz100x5[index][CamposMatrizConstante.DADO_D];
            matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[index][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
            matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[index][CamposMatrizConstante.BIT_DE_ACESSO_R];
        }
    }
    
    // Matriz para modelar as molduras da paginas na memoria RAM 10x5

    public static void imprimirMatrizGlobal10x5(int linha, int coluna) {
        System.out.println(matriz10x5[linha][coluna]);
    }

    public static void setValorMatriz10x5(int linha, int coluna, int value){
        matriz10x5[linha][coluna] = value;
    }

    //Matriz para representar as pagians em disco. 100x5

    public static void matrizGlobal100x5(int linha, int coluna) {
        System.out.println(matriz100x5[linha][coluna]);
    }

    public static void setValorMatriz100x5(int linha, int coluna, int value){
        matriz100x5[linha][coluna] = value;
    }

    //iniciar a execução do simulador

    public static void startSimulador(int algoritimo, boolean ativo) {
        int numeroInstrucao = random.nextInt(100)+1;
    
        for(int i = 0; i < 10; i++){
            if(matriz10x5[i][CamposMatrizConstante.INSTRUCAO_I] == numeroInstrucao){
                
                matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = 1;
                
                if( (random.nextInt(100) + 1) <= 30 ){ //probabilidade de 30% 
                        
                    matriz10x5[i][CamposMatrizConstante.DADO_D] += 1;
                    matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = 1;
                        
                }                
            }
        }
        //Instrução não carregada(encontrada) na memoria  //ATENÇÃO // Aqui tenho que chamar os metodos de substituição NRU, FIFO, FIFO-SC, RELÓGIO
        if(ativo == true){
            if(algoritimo == AlgoritimosConstantes.NRU){
                startAlgoritimoNRU();
            }else if(algoritimo == AlgoritimosConstantes.FIFO){
                startAlgoritimoFIFO();
            }else if(algoritimo == AlgoritimosConstantes.FIFO_SC){
                startAlgoritimoFIFO_SC();
            }else if(algoritimo == AlgoritimosConstantes.RELOGIO){
                startAlgoritimoRELOGIO();
            }
        }
    }

    //Gets Matrizes
    public static int[][] getMatriz10x5() {
        return matriz10x5;
    }

    public static int[][] getMatriz100x5() {
        return matriz100x5;
    }

    //Impressão de Matrizes Genérica.
    public static void imprimirMatriz(int[][] matriz, String nomeMatriz) {

        System.out.println(" " + nomeMatriz);
        for(int k = 0; k< matriz.length; k++){
            String auxAlterados;
            if(matriz[k][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1 && matriz[k][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 1){
                if(matriz.length == matriz10x5.length){
                    auxAlterados = " => Acessado e Modificado - RAM";
                }else{
                    auxAlterados = " => Acessado e Modificado - DISCO";
                }
            }else if(matriz[k][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1){
                if(matriz.length == matriz10x5.length){
                    auxAlterados = " => Acessado - RAM";
                }else{
                    auxAlterados = " => Acessado - DISCO";
                }
            }else if(matriz[k][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] == 1){
                auxAlterados = " => Modificado";
                if(matriz.length == matriz10x5.length){
                    auxAlterados = " => Modificado - RAM";
                }else{
                    auxAlterados = " => Modificado - DISCO";
                }
            }else{
                auxAlterados = "";
            }

            System.out.println(matriz[k][CamposMatrizConstante.NUMERO_DE_PAGINA_N] + " ° " + matriz[k][CamposMatrizConstante.INSTRUCAO_I] + " ° "+ matriz[k][CamposMatrizConstante.DADO_D] + " ° " + matriz[k][CamposMatrizConstante.BIT_DE_ACESSO_R] + " ° " + matriz[k][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] + auxAlterados);
        }
        System.out.println("---------- END ----------");
    }

}
