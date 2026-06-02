// A3 de Algoritmos e Programacao
// Alvaro Dantas
// Davi Oliveira
// Guilherme Pieroni
// Luiz Miguel

package app;

import java.util.Scanner;

public class A3 {
    public static int[] placar = {0,0};
    public static void construir(char[][] matriz) throws Exception { // exibir matriz da velha
        for (int linha = 0; linha < matriz.length; linha++) {
            System.out.print("-> |  ");
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print(matriz[linha][coluna]+" ");
            }
            System.out.print(" | \n");
        }
        System.out.println("_________________");
    }
    public static int iniciorodada(char[][] atual, int em) throws Exception {
        System.out.println("_________________");
        System.out.println("-> | Rodada"+(em+1)+" |");
        construir(atual); // chama a matriz no console
        return em + 1; // retorna a rodada atual
    }
    public static boolean[] fimderodada(char[][] matriz, char user, char robo) throws Exception {
        for (int linha = 0; linha < matriz.length; linha++) { // testa na horizontal se ha uma linha completa
            if (matriz[linha][0] == matriz[linha][1] && matriz[linha][1] == matriz[linha][2]) {
                if (matriz[linha][0] == user) {
                    // vitoria do usuario
                    return new boolean[]{true, true};
                } else if (matriz[linha][0] == robo) {
                    // vitoria do robo
                    return new boolean[]{true, false};
                }
            }
        }
        for (int coluna = 0; coluna < 3; coluna++) { // testa na vertical se ha uma linha completa
            if (matriz[0][coluna] == matriz[1][coluna] && matriz[1][coluna] == matriz[2][coluna]) {
                if (matriz[0][coluna] == user) {
                    // vitoria do usuario
                    return new boolean[]{true, true};
                } else if (matriz[0][coluna] == robo) {
                    // vitoria do robo
                    return new boolean[]{true, false};
                }
            }
        }
        if (matriz[0][0] != '#') { // verifica a diagonal de cima esquerda para baixo direita \
            if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) {
                if (matriz[0][0] == user) {
                    // vitoria do usuario
                    return new boolean[]{true, true};
                } else if (matriz[0][0] == robo) {
                    // vitoria do robo
                    return new boolean[]{true, false};
                }
            }
        }
        if (matriz[0][2] != '#') { // verifica a diagonal de cima direita para baixo esquerda /
            if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) {
                if (matriz[0][2] == user) {
                    // vitoria do usuario
                    return new boolean[]{true, true};
                } else if (matriz[0][2] == robo) {
                    // vitoria do robo
                    return new boolean[]{true, false};
                }
            }
        }
        return new boolean[]{false, false};
    }
    public static char jogo(Scanner sc, char escolhido, int jogadores) throws Exception {
        int rodada = 0; // iniciando partida
        int cursorlinha = 1; // iniciando cursor linha
        int cursorcoluna = 1; // iniciando cursor coluna
        char outrojogador = (escolhido == 'x') ? 'o' : 'x'; // fica com o inverso da escolha inicial
        String nomedojogador = (jogadores == 2) ? "JOGADOR1" : "VOCE"; // nome do jogador
        String nomeoutrojogador = (jogadores == 2) ? "JOGADOR2" : "ROBO"; // nome do outro jogador
        char ganhador = 'D'; // inicia variavel do ganhador
        char[][] matriz = {{'#','#', '#'}, {'#', '#', '#'}, {'#', '#', '#'}}; // limpa a velha
        System.out.println("Iniicializando...");
        System.out.println("_________________");
        System.out.println("  Jogadores: "+jogadores);
        System.out.println("-----------------");
        System.out.println("    [Partida]");
        do { // inicia uma rodada
            rodada = iniciorodada(matriz, rodada); // nova rodada
            if (rodada % 2 != 0 || jogadores == 2) {
                System.out.println("-> Sua vez: 1, 2, 3"); // sempre o usuario vai iniciar
                if (jogadores == 2) {
                    if (rodada % 2 != 0) {
                        nomedojogador = "JOGADOR1";
                        nomeoutrojogador = "JOGADOR2";
                    } else {
                        nomedojogador = "JOGADOR2";
                        nomeoutrojogador = "JOGADOR1";
                    }
                }
                do {
                    System.out.print("-> [" + nomedojogador + "] Linha ");
                    while (!sc.hasNextInt()) {
                        sc.next(); // descartando valor
                        System.out.print("-> [" + nomedojogador + "] Linha ");
                    }
                    cursorlinha = sc.nextInt();
                    if (cursorlinha >= 1 && cursorlinha <= 3) {
                        if (matriz[cursorlinha - 1][0] != '#'
                                && matriz[cursorlinha - 1][1] != '#'
                                && matriz[cursorlinha - 1][2] != '#') {
                            cursorlinha = 0; // linha escolhida sem espaco
                            System.out.println("-> [" + nomedojogador + "] Linha sem espaco ");
                        }
                    }
                } while (cursorlinha > 3 || cursorlinha < 1);
                do {
                    System.out.print("-> [" + nomedojogador + "] Coluna ");
                    while (!sc.hasNextInt()) {
                        sc.next(); // descartando valor
                        System.out.print("-> [" + nomedojogador + "] Coluna ");
                    }
                    cursorcoluna = sc.nextInt();
                    if (cursorcoluna >= 1 && cursorcoluna <= 3) {
                        if (matriz[cursorlinha - 1][cursorcoluna - 1] != '#') {
                            cursorcoluna = 0; // coluna escolhida sem espaco
                            System.out.println("-> [" + nomedojogador + "] Coluna sem espaco ");
                        }
                    }
                } while (cursorcoluna > 3 || cursorcoluna < 1);
                if (rodada % 2 != 0) {
                    matriz[cursorlinha - 1][cursorcoluna - 1] = escolhido; // aqui a marcacao na matriz
                } else {
                    matriz[cursorlinha - 1][cursorcoluna - 1] = outrojogador; // marcacao do jogador2 na matriz
                }
            } else {
                if (matriz[3-cursorlinha][3-cursorcoluna] == '#') {
                    // espelha os movimentos do usuario se tiver espaco
                    matriz[3-cursorlinha][3-cursorcoluna] = outrojogador; // aqui a marcacao do robo na matriz
                    System.out.printf("-> [ROBO] Linha %d\n", (3-cursorlinha)+1);
                    System.out.printf("-> [ROBO] Coluna %d\n", (3-cursorcoluna)+1);
                } else {
                    // faz o primeiro movimento possivel
                    int fez = 0;
                    for (int linha = 0; linha < matriz.length; linha++) {
                        if (fez==1){ break; }
                        for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                            if (matriz[linha][coluna] == '#') {
                                fez = 1; // valor para fuga da repeticao
                                matriz[linha][coluna] = outrojogador; // robo marcando na matriz
                                System.out.printf("-> [ROBO] Linha %d\n", linha+1);
                                System.out.printf("-> [ROBO] Coluna %d\n", coluna+1);
                                break;
                            }
                        }
                    }
                }
            }
            // logica de vitoria
            if (fimderodada(matriz, escolhido, outrojogador)[0]) { // pergunta logica se ja terminou
                System.out.println("_________________");
                System.out.println("-----------------");
                System.out.println("-> |   FIM   |");
                construir(matriz);
                System.out.println("-----------------");
                if (fimderodada(matriz, escolhido, outrojogador)[1]) {
                    System.out.printf("-> [%s] Ganhou! ( %c )\n", nomedojogador, escolhido); // vitoria do usuario
                    ganhador = (escolhido);
                } else {
                    if (jogadores == 2) {  // serve apenas para vitoria do 2 ou robo
                        nomedojogador = "JOGADOR1";
                        nomeoutrojogador = "JOGADOR2";
                    }
                    System.out.printf("-> [%s] Ganhou! ( %c )\n", nomeoutrojogador, outrojogador); // vitoria do outro jogador
                    System.out.printf("-> [%s] Perdeu! ( %c )\n", nomedojogador, escolhido);
                    ganhador = (outrojogador);
                }
                System.out.println("_________________");
                System.out.println("-----------------");
                break;
            }
        } while (rodada < 9); // maximo de rodadas, falta a logica de vitoria
        if (rodada == 9) {
            // deu velha empate
            System.out.println("_________________");
            System.out.println("-----------------");
            System.out.println("-> |   FIM   |");
            construir(matriz);
            System.out.println("-----------------");
            System.out.println("-> Deu VELHA!");
            System.out.println("_________________");
            System.out.println("-----------------");
            ganhador = 'D';
        }
        return ganhador;
    }
    public static int Pontuação(char ganhador, char escolha) throws Exception{// compara o ganhador do jogo com a escolha do jogador para retornar a pontuação correta.
        int ponto = 0; // iniciando variavel ponto
        if (ganhador == escolha){// compara se o ganhador do round foi o jogador1
            ponto = 0; // atribui a posição da array para adicionar ponto
        }
        else {
            ponto = 1;
        }
        return ponto;
    };
    public static void main(String[] args) throws Exception {
        char escolha = 'o'; // valor padrao
        int jogadores = 1; // valor padrao
        char ponto = 'D'; // inicia variavel ponto
        Scanner sc = new Scanner(System.in); // abertura de scanner
        do {
            System.out.print("Quantidade de jogadores (1/2): ");
            jogadores = sc.nextInt(); // escolha do usuario entre 1 ou 2 jogadores
        } while (jogadores != 1 && jogadores != 2);
        do {
            System.out.print("Inicie com (o/x): ");
            escolha = sc.next().charAt(0); // definicao da escolha do usuario entre x e o
        } while (escolha != 'o' && escolha != 'x');
        ponto = jogo(sc, escolha, jogadores); // chamando o jogo com os parametros dentro da variavel ponto para definir o ganhador
        placar[Pontuação(ponto, escolha)]++; // icrementa a pontuação dentro do placar
        System.out.println("  JOGADOR1: " + placar[0]);
        System.out.println("  JOGADOR2: " + placar[1]);
        System.out.println("-----------------");
        char jogarnovamente = '?';
        do {
            System.out.print("Jogar novamente? (y/n): ");
            jogarnovamente = sc.next().charAt(0); // jogar novamente?
        } while (jogarnovamente != 'y' && jogarnovamente != 'n');
        System.out.println("-----------------");
        if (jogarnovamente == 'y') {
            main(new String[0]);
        } else {
            sc.close(); // fechamento esperado
        }
    };
};
