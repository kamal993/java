package blackjack;
// This class represents the set of cards held by one player (or the dealer).

public class Hand {

    private Card[] cardHand;
    private int numOfCards, score;

    // This constructor builds a hand (with no cards, initially).
    public Hand() {
        cardHand = new Card[12];
        score = 0;
        numOfCards = 0;
    }

    // This method retrieves the size of this hand.
    public int getNumberOfCards() {
        return numOfCards;
    }

    // This method retrieves a particular card in this hand.  The card number is zero-based.
    public Card getCard(int index) {
        return cardHand[index];
    }

    // This method takes a card and places it into this hand.
    public void addCard(Card newcard) {
        cardHand[numOfCards++] = newcard;
        score = score + newcard.getValue();
    }

    // This method computes the score of this hand.
    public int getScore() {
        int totalScore = 0;

        for (int i = 0; i < cardHand.length; i++) {
            if (cardHand[i] != null) {
                totalScore = totalScore + cardHand[i].getValue();
            }
        }
        //not calculating acess

        boolean aceCard = false;
        for (int i = 0; i < cardHand.length; i++) {
            if (cardHand[i] != null) {
                if (cardHand[i].getFace() == 1) {
                    aceCard = true;
                }
            }
        }
        // aces value evaluation

        if (aceCard == true && totalScore + 10 < 22) {
            totalScore = totalScore + 10;
        }

        return totalScore; // replace this line with your code
    }

    // This methods discards all cards in this hand.
    public void discardAll() {
        cardHand = new Card[12];
        numOfCards = 0;
    }

}
