import java.util.ArrayList;
import java.util.Random;

public class Global{

    private static int[][] matriz10x5 = new int[10][5];
    private static int[][] matriz100x5 = new int[100][5];
    private static Random random = new Random();    

    //Metodo de Criação Inicial Matriz 100x5
    public static Boolean criarMatrizPreenchida100x5() {
        
        int n = 0;
        int i = 1;

        for(int j = 0; j < 100; j++){
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

        atualizandoPaginaNaMatrix100x5(index);

        matriz10x5[index][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
        matriz10x5[index][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.INSTRUCAO_I];
        matriz10x5[index][CamposMatrizConstante.DADO_D] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.DADO_D];
        matriz10x5[index][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
        matriz10x5[index][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_ACESSO_R];
    }

    private static void atualizandoPaginaNaMatrix100x5(int index10x5) {
        

        for(int i = 0; i<100; i++){
            if(matriz10x5[index10x5][CamposMatrizConstante.NUMERO_DE_PAGINA_N] == matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N]){
                matriz100x5[i][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[index10x5][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                matriz100x5[i][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[index10x5][CamposMatrizConstante.INSTRUCAO_I];
                matriz100x5[i][CamposMatrizConstante.DADO_D] = matriz10x5[index10x5][CamposMatrizConstante.DADO_D];
                matriz100x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[index10x5][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                matriz100x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[index10x5][CamposMatrizConstante.BIT_DE_ACESSO_R];
            }
        }
    }
    //-------------- Algoritmo NRU Start --------------\\


    public static void startAlgoritimoNRU() { 

        imprimirMatriz(matriz100x5, "\n\nDISCO 100x5 - NRU\n\n");
        imprimirMatriz(matriz10x5, "\n\nRAM 10x5 - NRU\n\n");

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

        imprimirMatriz(matriz100x5, "\n\nDISCO 100x5 - NRU\n\n");
        imprimirMatriz(matriz10x5, "\n\nRAM 10x5 - NRU\n\n");
    }
    //-------------- Algoritmo NRU End --------------\\

    //-------------- Algoritmo FIFO Start --------------\\
    public static void startAlgoritimoFIFO() {
        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO");

        for(int t = 0; t < 500; t++){
            for(int i = 0; i < matriz10x5.length; i++){ // FIFO primeira pagina que entra e a primeira que sai.
                retirarPagina(i);
            }
        }

        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO");
    }
    //-------------- Algoritmo FIFO End --------------\\

    //-------------- Algoritmo FIFO-SC Start --------------\\
    public static void startAlgoritimoFIFO_SC() {
        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO_SC");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO_SC");

        for(int t = 0; t < 500; t++){
            for(int i = 0; i < matriz10x5.length; i++){ // FIFO_SC na primeira pagina e verificado o bit r, caso 1 a pargina vai para o final da fila
                if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 1){ //BIT R = 1

                    int[][] matrizAux = new int[10][5];
                    matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = 0;

                    for(int f = 0; f < matriz10x5.length - 1; f++){
                        matrizAux[f][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[f+1][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                        matrizAux[f][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[f+1][CamposMatrizConstante.INSTRUCAO_I];
                        matrizAux[f][CamposMatrizConstante.DADO_D] = matriz10x5[f+1][CamposMatrizConstante.DADO_D];
                        matrizAux[f][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[f+1][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                        matrizAux[f][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[f+1][CamposMatrizConstante.BIT_DE_ACESSO_R];
                    }
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[0][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[0][CamposMatrizConstante.INSTRUCAO_I];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.DADO_D] = matriz10x5[0][CamposMatrizConstante.DADO_D];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[0][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[0][CamposMatrizConstante.BIT_DE_ACESSO_R];

                        matriz10x5 = matrizAux;

                }else if(matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] == 0){  //BIT R = 0 > Pagina vai sair

                    atualizandoPaginaNaMatrix100x5(i);
                    int[][] matrizAux = new int[10][5];

                    for(int f = i; f < matriz10x5.length - 1; f++){
                        matrizAux[f][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz10x5[f+1][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                        matrizAux[f][CamposMatrizConstante.INSTRUCAO_I] = matriz10x5[f+1][CamposMatrizConstante.INSTRUCAO_I];
                        matrizAux[f][CamposMatrizConstante.DADO_D] = matriz10x5[f+1][CamposMatrizConstante.DADO_D];
                        matrizAux[f][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz10x5[f+1][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                        matrizAux[f][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz10x5[f+1][CamposMatrizConstante.BIT_DE_ACESSO_R];
                    }
                        int indexSorteado100x5 = random.nextInt(100);
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.NUMERO_DE_PAGINA_N] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.NUMERO_DE_PAGINA_N];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.INSTRUCAO_I] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.INSTRUCAO_I];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.DADO_D] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.DADO_D];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_MODIFICACAO_M];
                        matrizAux[matrizAux.length - 1][CamposMatrizConstante.BIT_DE_ACESSO_R] = matriz100x5[indexSorteado100x5][CamposMatrizConstante.BIT_DE_ACESSO_R];

                        matriz10x5 = matrizAux;
                }
            }
        }

        imprimirMatriz(matriz100x5, "DISCO 100x5 - FIFO_SC");
        imprimirMatriz(matriz10x5, "RAM 10x5 - FIFO_SC");
    }
    //-------------- Algoritmo FIFO End --------------\\

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

    public static Boolean startSimulador(int algoritimo, boolean ativo) {
        int numeroInstrucao = random.nextInt(100)+1;
    
        for(int i = 0; i < 10; i++){
            if(matriz10x5[i][CamposMatrizConstante.INSTRUCAO_I] == numeroInstrucao){
                
                matriz10x5[i][CamposMatrizConstante.BIT_DE_ACESSO_R] = 1;
                if((random.nextInt(100) + 1) <= 30 ){ //probabilidade de 30% 
                    matriz10x5[i][CamposMatrizConstante.DADO_D] += 1;
                    matriz10x5[i][CamposMatrizConstante.BIT_DE_MODIFICACAO_M] = 1;
                }
                return true;
            }else{
                //Instrução não carregada(encontrada) na memoria  //ATENÇÃO // Aqui tenho que chamar os metodos de substituição NRU, FIFO, FIFO-SC, RELÓGIO
                if(ativo == true){
                    if(algoritimo == AlgoritimosConstantes.NRU){
                        startAlgoritimoNRU();
                    }else if(algoritimo == AlgoritimosConstantes.FIFO){
                        startAlgoritimoFIFO();
                    }else if(algoritimo == AlgoritimosConstantes.FIFO_SC){
                        startAlgoritimoFIFO_SC();
                    }else if(algoritimo == AlgoritimosConstantes.RELOGIO){
                        //ATENÇÃO Chamar metodo RELOGIO
                    }
                }
            }
        }
        return null;
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
            System.out.println(matriz[k][CamposMatrizConstante.NUMERO_DE_PAGINA_N] + " ° " + matriz[k][CamposMatrizConstante.INSTRUCAO_I] + " ° "+ matriz[k][CamposMatrizConstante.DADO_D] + " ° " + matriz[k][CamposMatrizConstante.BIT_DE_ACESSO_R] + " ° " + matriz[k][CamposMatrizConstante.BIT_DE_MODIFICACAO_M]);
        }
        System.out.println("---------- END ----------");
    }
}
