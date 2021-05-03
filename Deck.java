import java.util.*;

public class Deck{
    private ArrayList<Card> myCards;

    public Deck(){
        this.myCards= new ArrayList<Card>();
    }

    public void fillDeck(){
        for(Suit cardSuit: Suit.values()){ //for every suit
            for(Value cardValue : Value.values()){ //for every card value 
                this.myCards.add(new Card(cardSuit,cardValue)); //adds Cards to deck
            }
        }

    }
    
    public void shuffle(){
        Collections.shuffle(myCards);
    }

    public String toString(){

        String cardList ="";
        for(Card aCard : this.myCards){
            cardList += "\n" + aCard.toString();
        }
        return cardList;
    }

    public int deckSize(){
        return this.myCards.size();
    }

    public void removeCard(int i){
        this.myCards.remove(i);
    }
    public Card getCard(int i){
        return this.myCards.get(i);
    }    
    public void addCard(Card addCard){
        this.myCards.add(addCard);
    }

    public void draw(Deck originDeck){
        this.myCards.add(originDeck.getCard(0));
        originDeck.removeCard(0);
        
    }
    
    public int cardValue(){ 
        int handValue =0;
        int aces = 0;

        for(Card aCard : this.myCards){
            switch(aCard.getValue()){
                case TWO: handValue +=2;
                break;
                case THREE: handValue+=3;
                break;
                case FOUR: handValue+=4;
                break;
                case FIVE: handValue+=5;
                break;
                case SIX: handValue+=6;
                break;
                case SEVEN: handValue+=7;
                break;
                case EIGHT: handValue+=8;
                break;
                case NINE: handValue+=9;
                break;
                case TEN: handValue+=10;
                break;
                case JACK: handValue+=10;
                break;
                case QUEEN: handValue+=10;
                break;
                case KING: handValue+=10;
                break;
                case ACE: aces +=1;
                break;

                
            }
        }
        for(int i=1;i<=aces;i++){
            if(handValue>10){
                handValue+=1;
            }
            else{
                handValue+=11;
            }
        }
        return handValue;

    }

    public void resetDeck(Deck reset){
        int aDeckSize = this.myCards.size();

        for(int i = 0; i<aDeckSize;i++){
            reset.addCard(this.getCard(i));
        }
        for(int i = 0;i<aDeckSize;i++){
        this.removeCard(0);
        }
    }
}
