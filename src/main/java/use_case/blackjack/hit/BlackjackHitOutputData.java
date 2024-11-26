package use_case.blackjack.hit;

import java.awt.Image;

/**
 * Output Data for the Blackjack Hit Use Case.
 */
public class BlackjackHitOutputData {
    private final Image cardImage;
    private final String turnState;
    private final boolean useCaseFailed;
    private final int playerScore;

    public BlackjackHitOutputData(Image cardImage, String turnState,
                                  int playerScore, boolean useCaseFailed) {
        this.cardImage = cardImage;
        this.turnState = turnState;
        this.playerScore = playerScore;
        this.useCaseFailed = useCaseFailed;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public String getTurnState() {
        return turnState;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
