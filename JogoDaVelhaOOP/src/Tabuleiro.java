import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {
    
    Scanner scan = new Scanner(System.in);
    private boolean vitoria;
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

    public void comecarJogo() {
        System.out.println("X começa!");
        
        mostrarTabuleiro();
        
        try{
            while(!vitoria) {
                
                if(fazerJogada(jogador1)) {
                    mostrarTabuleiro();
                    vitoria = checarVitoria();
                    if(vitoria == true) {
                        System.out.println("O jogadores com os X's ganhou!");
                        mostrarTabuleiro();
                        break;
                    }
                }

                if(fazerJogada(jogador2)) {
                    mostrarTabuleiro();
                    vitoria = checarVitoria();
                    if(vitoria == true) {
                        System.out.println("O jogadores com os O's ganhou!");
                        mostrarTabuleiro();
                        break;
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        if(!vitoria) {
            System.out.println("Ih, deu velha");
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
