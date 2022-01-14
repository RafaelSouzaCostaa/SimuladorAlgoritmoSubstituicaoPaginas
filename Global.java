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
            matriz100x5[j][CamposMatriz.NUMERO_DE_PAGINA_N] = n;
            matriz100x5[j][CamposMatriz.INSTRUCAO_I] = i;
            matriz100x5[j][CamposMatriz.DADO_D] = random.nextInt(50)+1;
            matriz100x5[j][CamposMatriz.BIT_DE_MODIFICACAO_M] = 0;
            matriz100x5[j][CamposMatriz.BIT_DE_ACESSO_R] = 0;

            i += 1;
            n += 1;
        }


        //Teste da matriz 100x5 preenchida - So descomentar abaixo;

        //imprimirMatriz(matriz100x5);

        return true;
    }

    //Preenchendo matriz 10x5 com valores sorteados apartir da 100x5

    public static void preencherMatriz10x5ComSorteoMatriz100x5() {
    
        for(int i = 0; i < 10; i++){
            int index = random.nextInt(100);
            matriz10x5[i][CamposMatriz.NUMERO_DE_PAGINA_N] = matriz100x5[index][CamposMatriz.NUMERO_DE_PAGINA_N];
            matriz10x5[i][CamposMatriz.INSTRUCAO_I] = matriz100x5[index][CamposMatriz.INSTRUCAO_I];
            matriz10x5[i][CamposMatriz.DADO_D] = matriz100x5[index][CamposMatriz.DADO_D];
            matriz10x5[i][CamposMatriz.BIT_DE_MODIFICACAO_M] = matriz100x5[index][CamposMatriz.BIT_DE_MODIFICACAO_M];
            matriz10x5[i][CamposMatriz.BIT_DE_ACESSO_R] = matriz100x5[index][CamposMatriz.BIT_DE_ACESSO_R];
        }
    }
    
    // Matriz para modelar as molduras da paginas na memoria RAM 10x5

    public static void matrizGlobal10x5(int linha, int coluna) {
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

    public static Boolean startSimulador() {
        int numeroInstrucao = random.nextInt(100)+1;
    
        for(int i = 0; i < 10; i++){
            if(matriz10x5[i][CamposMatriz.INSTRUCAO_I] == numeroInstrucao){
                
                matriz10x5[i][CamposMatriz.BIT_DE_ACESSO_R] = 1;
                if((random.nextInt(100) + 1) <= 30 ){ //probabilidade de 30% 
                    matriz10x5[i][CamposMatriz.DADO_D] += 1;
                    matriz10x5[i][CamposMatriz.BIT_DE_MODIFICACAO_M] = 1;
                }
                return true;
            }else{
                //Instrução não carregada na memoria  //ATENÇÃO // Aqui tenho que colocar os algoritmos de substituição //OK NRU, FIFO, FIFO-SC, RELÓGIO
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

    //Impressão de Matrizes Generica.
    public static void imprimirMatriz(int[][] matriz) {
        for(int k = 0; k< matriz.length; k++){
            System.out.println(matriz[k][CamposMatriz.NUMERO_DE_PAGINA_N] + " ° " + matriz[k][CamposMatriz.INSTRUCAO_I] + " ° "+ matriz[k][CamposMatriz.DADO_D] + " ° " + matriz[k][CamposMatriz.BIT_DE_MODIFICACAO_M] + " ° " + matriz[k][CamposMatriz.BIT_DE_ACESSO_R]);
        }
    }
}
