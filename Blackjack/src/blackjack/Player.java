package blackjack;

// This class represents one blackjack player (or the dealer)
public class Player {

    private String playerName;
    private boolean dealer;
    private Hand hand;

    // This constructor creates a player.
    // If isDealer is true, this Player object represents the dealer.
    public Player(String playerName, boolean dealer) {
        this.playerName = playerName;
        this.dealer = dealer;
        this.hand = new Hand();
    }

    // This method retrieves the player's name.
    public String getName() {
        return playerName;
    }

    // This method retrieves the player's hand of cards.
    public Hand getHand() {
        return hand;
    }

    // This method deals two cards to the player (one face down if this is the dealer).
    // The window input should be used to redraw the window whenever a card is dealt.
    public void startRound(Deck deck, BlackjackWindow window) {
        if (!dealer) {
            Card cardOne = deck.drawCard();
            cardOne.turnFaceUp();
            hand.addCard(cardOne);
            window.redraw();

            Card cardTwo = deck.drawCard();
            cardTwo.turnFaceUp();
            hand.addCard(cardTwo);
            window.redraw();
        } else {//(dealer)

            Card cardOne = deck.drawCard();
            cardOne.turnFaceDown();
            hand.addCard(cardOne);
            window.redraw();

            Card cardTwo = deck.drawCard();
            cardTwo.turnFaceUp();
            hand.addCard(cardTwo);
            window.redraw();
        }
    }

    // This method executes gameplay for one player.
    // If this player is the dealer:
    //	- hits until score is at least 17
    // If this is an ordinary player:
    //	- repeatedly asks the user if they want to hit (draw another card)
    //	  until either the player wants to stand (not take any more cards) or
    //	  his/her score exceeds 21 (busts).
    // The window input should be used to redraw the window whenever a card is dealt or turned over.
    public void playRound(Deck deck, BlackjackWindow window) {
        Card cardOne;
        if (dealer) {
            cardOne = hand.getCard(0);
            cardOne.turnFaceUp();     //flipping first card

            window.redraw();
            while (hand.getScore() < 18) {
                Card card = deck.drawCard();
                card.turnFaceUp();
                hand.addCard(card);
                window.redraw();
            }
        } else {
            while (hand.getScore() < 22) {
                boolean hit = GIO.readBoolean(playerName.toUpperCase() + ": Your turn, Hit(yes) or Stay(no)?");
                if (hit) {
                    Card card = deck.drawCard();
                    card.turnFaceUp();
                    hand.addCard(card);
                    window.redraw();
                } else {
                    break;
                }
            }
        }
    }

    // This method informs the player about whether they won, lost, or pushed.
    // It also discards the player's cards to prepare for the next round.
    // The window input should be used to redraw the window after cards are discarded.
    public void finishRound(int dealerScore, BlackjackWindow window) {
        if (!dealer) {
            if (hand.getScore() == 21) {
                GIO.displayMessage("Congratulations!!! " + playerName.toUpperCase() + " Blackjack!!!");
            } else if (hand.getScore() > 21) {
                GIO.displayMessage("Sorry!!! Better luck next time " + playerName.toUpperCase() + ", you have lost.");
            } else if ((dealerScore > 21) || (hand.getScore() > dealerScore)) {
                GIO.displayMessage("Congratulations!!! " + playerName.toUpperCase() + ", you have won!");
            } else if (hand.getScore() == dealerScore) {
                GIO.displayMessage("Well, well, " + playerName.toUpperCase() + ", you have pushed.");
            }
        }

        hand.discardAll();
        window.redraw();
    }
}
