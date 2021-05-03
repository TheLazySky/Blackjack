import java.util.*;
import java.io.*;
import java.lang.*;

public class Blackjack{

    public static void wait(int ms){ //Method to create time delay
        try{
        Thread.sleep(ms);
        }
        catch(InterruptedException ex){
        Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args){


        System.out.println("Welcome to BlackJack");
        System.out.println("You Start with 100 Chips, get to 1000 to Win!");
    

        Deck myDeck=  new Deck();
        myDeck.fillDeck();
        myDeck.shuffle();

        Deck playerHand = new Deck(); //Players hand
        Deck dealerHand = new Deck(); //Dealers hand

        // System.out.println(myDeck); //debugging deck

        int playerChips = 100;
        int x;

        for(x=1;playerChips>0 && playerChips<1000;x++){ //Betting
            int Bet=0;

        System.out.println("Round: "+x);
        System.out.println("Your Chips: "+ playerChips+"\nPlace your Bet?");
            try{
                Scanner playerInput = new Scanner(System.in); //Causes new object everytime a new bet is needed
                Bet = playerInput.nextInt();
                wait(500);
            }
            catch(InputMismatchException e){
                System.out.println("We don't accept I's O's U'. Enter Chips as Integer!");
                wait(1000);
                continue;
            }
          
            if(Bet==38193){
                playerChips+=5000;
                System.out.println("Nice job ;)");
                break;
            }
            else if(Bet>playerChips){
                System.out.println("We Don't accept Counterfeit. You only got "+playerChips+ " chips.");
                continue; //returns to beginning if player bet is greater than their chips
            }
            else if(Bet==-1){
                System.out.println("Terminating Program");
                break;
            }
            else if(Bet<-1){
                System.out.println("Nice try... Cheater");
                continue;
            }
            
          
            
            boolean endRound= false;
            playerHand.draw(myDeck);//player draw
            playerHand.draw(myDeck);
            dealerHand.draw(myDeck);//dealer draw
            dealerHand.draw(myDeck);

            while(true){
                System.out.println("\tYour Hand ");
                System.out.print(playerHand.toString());
                System.out.println("\nHand Score: "+playerHand.cardValue());
                System.out.println("_________________________");
                //wait(0); //Delay from players hand to showing dealers hand

                System.out.println("\n\nDealer Drew");
                System.out.print(dealerHand.getCard(0)+"\nUnknown");
                int input =0;
                try{
                    Scanner sc=new Scanner(System.in);
                    System.out.println("\nHit(1) or Stand(2)");
                    input = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Enter Valid Choice");
                }
                if(input==1){
                    playerHand.draw(myDeck);
                    System.out.println("You drew: "+playerHand.getCard(playerHand.deckSize()-1).toString());
                    if(playerHand.cardValue()>21){
                        System.out.println("You've Busted");
                        wait(2000);
                        playerChips -= Bet;
                        endRound=true;
                        break;
                    }

                }

                if(input==2){
                    break;
                }
                }
                wait(500);
                System.out.println("Dealer's Hand: "); 
                wait(500);
                System.out.println(dealerHand.toString()); //The reveal and scoring
                wait(500);
                if((dealerHand.cardValue()<=17)&&(dealerHand.cardValue()>playerHand.cardValue())&& endRound==false){ //review
                    System.out.println("Dealer Wins with " + dealerHand.cardValue() + " against " + playerHand.cardValue());
                    System.out.println("Player Loses " + Bet + " chips.");
                    playerChips-=Bet;
                    endRound=true;
                }
                
                while((dealerHand.cardValue()<17)&&endRound==false){
                    dealerHand.draw(myDeck);
                    wait(500);
                    System.out.println("Dealer Drew: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
                    wait(500);
                }
                wait(500);
                System.out.println("Dealer has " + dealerHand.cardValue());
                wait(500);
                if((dealerHand.cardValue()>21) && endRound==false){
                    System.out.println("Dealer Busts. Player Wins.");
                    System.out.println("You Win " + Bet+" Chips");
                    playerChips += Bet;
                    endRound = true;
                }
                if((playerHand.cardValue()==dealerHand.cardValue())&& endRound==false){
                    System.out.println("It's Draw.");
                    endRound = true;
                }
                if(playerHand.cardValue() > dealerHand.cardValue() &&endRound==false){
                    System.out.println("Player Wins!");
                    System.out.println("You Win " + Bet +" Chips.");
                    playerChips+=Bet;
                    endRound=true;
                }
                else if(endRound==false){
                    System.out.println("Dealer Wins. You lose "+Bet+" chips");
                    playerChips -=Bet;
                    endRound=true;
                }

                playerHand.resetDeck(myDeck);
                dealerHand.resetDeck(myDeck);
                System.out.println("End of Round");
                System.out.println("\n");
                wait(1500);
            }
            if(playerChips<=0){
                System.out.println("You've Run Out Of Chips");
                System.out.println("GAME OVER");
            }
            if(playerChips>=5000){
                System.out.println("\nYou've Reached 1000 Chips!");
                System.out.println("Congratulations, You Won in "+x+" Rounds!");
                System.out.println("Try finding the Easter Egg's Next ;)");
                
            }        
        }

    }
