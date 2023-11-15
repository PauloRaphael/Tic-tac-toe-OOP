import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    
    private Scanner scan = new Scanner(System.in);
    private Random random = new Random();
    private boolean vitoria;
    private boolean jogador1Ganhou = false;
    private boolean modoDeJogoValido = false;
    private String escolha;
    private int jogada;
    private final char jogador1 = 'X';
    private final char jogador2 = 'O';
    private char[] tabuleiro;
    private ArrayList<Integer> ocupados;

    public Tabuleiro() {
        //inicializa os atributos da classe
        vitoria = false;
        escolha = "";
        jogada = 0;
        tabuleiro = new char[]{'A', 'B', 'C',
                               'D', 'E', 'F',
                               'G', 'H', 'I'};
        ocupados =  new ArrayList<Integer>();
    }

    public void comecarJogo() throws InterruptedException {
        
        System.out.println("Bem vindo ao Jogo da Velha!");
        Thread.sleep(2000);

        while(!modoDeJogoValido) {
            System.out.println("____________________________________________________");
            out.println("Deseja jogar contra o [COMPUTADOR], ou um [AMIGO]?");
            String modoDeJogo = scan.nextLine();
            modoDeJogo = modoDeJogo.toUpperCase();
            out.println();
            if(modoDeJogo.equals("COMPUTADOR")) {

                jogoComputador();
                System.out.print("Continuar o jogando?(Sim/Nao): ");
                String continuar = scan.nextLine();
                continuar = continuar.toUpperCase();
                if(continuar.equals("SIM")) {
                    tabuleiro = new char[] {'A', 'B', 'C',
    	    				                'D', 'E', 'F',
                                            'G', 'H', 'I'};
                    ocupados.clear();
                } else {
                    break;
                }
            }
            else if(modoDeJogo.equals("AMIGO")) {
                
                jogoMultiplayer();
                System.out.print("Continuar o jogando?(Sim/Nao): ");
                String continuar = scan.nextLine();
                continuar = continuar.toUpperCase();
                if(continuar.equals("SIM")) {
                    tabuleiro = new char[] {'A', 'B', 'C',
    	    				                'D', 'E', 'F',
                                            'G', 'H', 'I'};
                    ocupados.clear();
                } else {
                    break;
                }
                
            } else {
                System.out.println("Modo de jogo invalido!");
                Thread.sleep(2000);
            }

        }
    }

    public void jogoMultiplayer() throws InterruptedException{

        modoDeJogoValido = true;
                System.out.println("X começa!");

                mostrarTabuleiro();
                Thread.sleep(2000);

                try{
                    while(!vitoria) {
                        
                        if(fazerJogada(jogador1)) {
                            mostrarTabuleiro();
                            vitoria = checarVitoria();
                            jogador1Ganhou = (vitoria ? true : false);
                            if(!vitoria) {
                                if(fazerJogada(jogador2)) {
                                    mostrarTabuleiro();
                                    vitoria = checarVitoria();
                                }
                            }
                        }

                        if(vitoria == true) {
                                System.out.println();
                                out.println("O jogadores com os " + (jogador1Ganhou ? "X's" : "O's") + " ganhou!");
                                Thread.sleep(1000);
                                out.println();
                                break;
                        }
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                
                if(!vitoria) {
                    System.out.println("Ih, deu velha!");
                }
    }

    public void jogoComputador() throws InterruptedException {

        modoDeJogoValido = true;
        System.out.println("Você começa!");

        mostrarTabuleiro();
        Thread.sleep(2000);

        try{
            while(!vitoria) {
                
                if(fazerJogada('X')) {
                    mostrarTabuleiro();
                    vitoria = checarVitoria();
                    jogador1Ganhou = (vitoria ? true : false);
                    if(!vitoria) {
                        fazerJogadaComputador();
                        mostrarTabuleiro();
                        out.println();
                        Thread.sleep(500);
                        vitoria = checarVitoria();  
                    }
                }

                if(vitoria == true) {
                    Thread.sleep(1000);
                    System.out.println();
                    out.print(jogador1Ganhou ? "Você ganhou!" : "Eu  ganhei HAHAHAHAHAHA");
                    Thread.sleep(1000);
                    out.println();
                    break;
                }
                Thread.sleep(500);
                out.println("Sua vez!");  
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        if(!vitoria) {
            System.out.println("Ih, deu velha!");
        }
    }

    public void mostrarTabuleiro() {
        
        int counter = 0;
       
        System.out.println();
        for(char lugar : this.tabuleiro) {
   		    System.out.print(" | " + lugar);
            if(counter == 2 || counter == 5 || counter == 8) {
        	    System.out.println(" | ");
            }
            if(counter == 2 || counter == 5) {
                System.out.println(" -------------");
            }
        counter++;
        }
    }

    public boolean fazerJogada(char jogador) throws InterruptedException{
        
        boolean jogadaValida = false;
        
        System.out.println();
        
        while(!jogadaValida) {
            System.out.println("Escolha a posição que deseja jogar: ");
                
            escolha = scan.nextLine();
            escolha = escolha.toUpperCase();
                
            switch(escolha) {
                case "A":
                    jogada = 0;
                    jogadaValida = true;
                    break;
                case "B":
                    jogada = 1;
                    jogadaValida = true;
                    break;
                case "C":
                    jogada = 2;
                    jogadaValida = true;
                    break;
                case "D":
                    jogada = 3;
                    jogadaValida = true;
                    break;
                case "E":
                    jogada = 4;
                    jogadaValida = true;
                    break;
                case "F":
                    jogada = 5;
                    jogadaValida = true;
                    break;
                case "G":
                    jogada = 6;
                    jogadaValida = true;
                    break;
                case "H":
                    jogada = 7;
                    jogadaValida = true;
                    break;
                case "I":
                    jogada = 8;
                    jogadaValida = true;
                    break;
                default:
                    jogadaValida = false;
                    System.out.println("Escolha uma posição valida!");
                    Thread.sleep(2500);
                    System.out.println();
                    mostrarTabuleiro();
                    jogada = 9;
                break;
            }
            if (jogada <= 8) {
                if(checarOcupados() && ocupados.size() > 0) {
                    System.out.println("Essa posição ja está ocupada!");
                    Thread.sleep(2500);
                    mostrarTabuleiro();
                    System.out.println();
                    jogadaValida = false;
                    
                } else {
                    tabuleiro[jogada] = jogador;
                    ocupados.add(jogada);
                }
            }
        }
        return jogadaValida;
    }

    private void fazerJogadaComputador() throws InterruptedException{
            
            boolean jogadaValida = false;
            
            while(!jogadaValida) {
                jogada = random.nextInt(8);
                if(!checarOcupados() && ocupados.size() > 0) {
                    tabuleiro[jogada] = 'O';
                    ocupados.add(jogada);
                    jogadaValida = true;
                    out.println();
                    Thread.sleep(2000);
                    out.println("Minha vez!");
                    Thread.sleep(700);
                    out.print("Eu vou jogar.");
                    Thread.sleep(700);
                    out.print(".");
                    Thread.sleep(700);
                    out.print(".");
                    Thread.sleep(700);
                    out.print(".");
                    Thread.sleep(700);
                    out.print(" Aqui!");
                    Thread.sleep(1000);
                    out.println();
                }
            }
    }

    public boolean checarOcupados() {

        // Passa por cada elemento do array ocupados, que é incrementado sempre
        // que uma jogada é feita, checando se a jogada é igual alguma
        // das posições ja ocupadas por qualquer jogador
        for(int ocupado : this.ocupados) {
            if(ocupado == this.jogada) {
                return true;
            } 
        }
        return false;
    }

    public boolean checarVitoria(){
        
        char tabuleiro[] = this.tabuleiro; 

        //vitoria vertical
        if(tabuleiro[0] == tabuleiro[3] && tabuleiro[3] == tabuleiro[6]) {
            // X M M
            // X M M
            // X M M
            return true;
        } else if(tabuleiro[1] == tabuleiro[4] && tabuleiro[4] == tabuleiro[7]) {
            // M X M
            // M X M
            // M X M           
            return true;
        } else if(tabuleiro[2] == tabuleiro[5] && tabuleiro[5] == tabuleiro[8]) {
            // M M X
            // M M X
            // M M X
            return true;
        }
        
        //vitoria horizontal
        if(tabuleiro[0] == tabuleiro[1] && tabuleiro[1] == tabuleiro[2]) {
            // X X X
            // M M M
            // M M M
            return true;
        } else if(tabuleiro[3] == tabuleiro[4] && tabuleiro[4] == tabuleiro[5]) {
            // M M M
            // X X X
            // M M M
            return true;
        } else if(tabuleiro[6] == tabuleiro[7] && tabuleiro[7] == tabuleiro[8]) {
            // M M M
            // M M M
            // X X X
            return true;
        }
        
        //vitoria diagonal 
        if(tabuleiro[0] == tabuleiro[4] && tabuleiro[4] == tabuleiro[8]) {
            // X M M
            // M X M
            // M M X
            return true;
        } else if(tabuleiro[2] == tabuleiro[4] && tabuleiro[4] == tabuleiro[6]) {
            // M M X
            // M X M
            // X M M
            return true;
        }

        return false;
    }
}
