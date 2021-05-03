public class Card{
    
    private Suit suit; //Suit of Card
    private Value value; //Value of Card (Ace is 1 or 11, face cards are 10)

    public Card(Suit suit, Value value){
        this.value=value;
        this.suit=suit;
    }

    public String toString(){

        return this.value.toString() + " of " + this.suit.toString() + "'s";
    }

    public Value getValue(){
        return this.value;
    }
}
