package blackjack;
// This is the main program for the blackjack game.

public class Blackjack {

    // This method should:
    //	- Ask the user how many people want to play (up to 3, not including the dealer).
    //	- Create an array of playerList.
    //	- Create a Blackjack window.
    // 	- Play rounds until the playerList want to quit the game.
    //	- Close the window.
    public static void main(String[] args) {
        // complete this method
        int numOfPlayers = 0;
        boolean errorInput = true;
        String[] player;
        Player[] playerList;

        while (errorInput) {
            numOfPlayers = GIO.readInt("Number of player?") + 1;
            if (numOfPlayers < 0 || numOfPlayers > 4) {
                GIO.displayMessage("Range 1-4!!!");
            } else {
                errorInput = false;
            }
        }
        player = new String[numOfPlayers];
        boolean[] isDealer = new boolean[numOfPlayers];
        player[0] = "Dealer";
        isDealer[0] = true;
        for (int i = 1; i < numOfPlayers; i++) {
            player[i] = GIO.readString("What is player's name?");
            isDealer[i] = false;
        }
        playerList = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            playerList[i] = new Player(player[i], isDealer[i]);
        }
        BlackjackWindow gameWindow = new BlackjackWindow(playerList);
        gameWindow.redraw();
        boolean playAgain = true;
        while (playAgain) {
            playRound(playerList, gameWindow);
            playAgain = GIO.readBoolean("Do you want to play again?");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].getHand().discardAll();
            }

        }
        gameWindow.close();
    }

    // This method executes an single round of play (for all playerList).  It should:
    //	- Create and shuffle a deck of cards.
    //	- Start the round (deal cards) for each player, then the dealer.
    //	- Allow each player to play, then the dealer.
    //	- Finish the round (announce results) for each player.
    public static void playRound(Player[] playerList, BlackjackWindow gameWindow) {
        // complete this method
        Deck cardDeck = new Deck();
        cardDeck.shuffle();

        for (int i = 1; i < playerList.length; i++) {
            playerList[i].startRound(cardDeck, gameWindow);
        }

        playerList[0].startRound(cardDeck, gameWindow);

        for (int i = 1; i < playerList.length; i++) {
            playerList[i].playRound(cardDeck, gameWindow);
        }

        playerList[0].playRound(cardDeck, gameWindow);

        for (int i = 1; i < playerList.length; i++) {
            playerList[i].finishRound(playerList[0].getHand().getScore(), gameWindow);
        }

    }
}
