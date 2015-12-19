package blackjack;

import java.util.Random;

// This class represents the deck of cards from which cards are dealt to players.
public class Deck {

    private Card[] cardDeck;
    private int size = 52;
    private int currentIndex = 0;

    // This constructor builds a deck of 52 cards.
    public Deck() {
        cardDeck = new Card[52];
        int index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                cardDeck[index++] = new Card(i, j);
            }
        }
    }

    // This method shuffles the deck (randomizes the array of cards).
    // Hint: loop over the cards and swap each one with another card in a random position.
    public void shuffle() {
        Random rand = new Random();

        for (Card c : cardDeck) {
            int val = rand.nextInt(52);
            Card tempCard = c;
            c = cardDeck[val];
            cardDeck[val] = tempCard;
        }
    }

    // This method takes the top card off the deck and returns it.
    public Card drawCard() {
        Card current = cardDeck[currentIndex];
        cardDeck[currentIndex] = null;
        currentIndex++;
        size--;
        return current;
    }

    // This method returns the number of cards left in the deck.
    public int getSize() {
        return size;
    }
}
