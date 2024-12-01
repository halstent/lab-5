package interface_adapter.und_ovr.play;

import entity.CardAbs;
import entity.User;

/**
 * The state for the Over/Under Play View Model.
 */
public class OverUnderPlayState {
    private User user;
    private CardAbs currentCard;
    private CardAbs nextCard;
    private boolean guess;
    private String guessError;
    private int wrongGuesses;
    private boolean gameEnded;

    // User-related methods
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    // Card-related methods
    public CardAbs getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CardAbs currentCard) {
        this.currentCard = currentCard;
    }

    public CardAbs getNextCard() {
        return nextCard;
    }

    public void setNextCard(CardAbs nextCard) {
        this.nextCard = nextCard;
    }

    // Guess-related methods
    public boolean getGuess() {
        return guess;
    }

    public void setGuess(boolean guess) {
        this.guess = guess;
    }

    public String getGuessError() {
        return guessError;
    }

    public void setGuessError(String guessError) {
        this.guessError = guessError;
    }

    // Score-related methods
    public int getWrongGuesses() {
        return this.wrongGuesses;
    }

    /**
     * Increments number of wrong guesses by 1.
     */
    public void setWrongGuesses() {
        this.wrongGuesses += 1;
    }

    // Game status
    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    @Override
    public String toString() {
        return "OverUnderPlayState{" + "user=" + user + ", currentCard=" + currentCard + '\''
                + ", nextCard=" + nextCard + '\''
                + ", guess='" + guess + '\''
                + ", guessError='" + guessError + '\''
                + ", wrongGuesses=" + wrongGuesses + '\''
                + ", gameEnded=" + gameEnded + '}';
    }
}
