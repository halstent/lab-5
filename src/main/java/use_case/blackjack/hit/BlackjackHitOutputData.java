package use_case.blackjack.hit;

import entity.AbstractCard;

import java.awt.*;

/**
 * Output Data for the Blackjack Get Card Use Case.
 */
public class BlackjackHitOutputData {
    private final Image cardImage;
    private final boolean bust;
    private final boolean win;
    private final boolean useCaseFailed;

    public BlackjackHitOutputData(Image cardImage, boolean bust, boolean win, boolean useCaseFailed) {
        this.cardImage = cardImage;
        this.bust = bust;
        this.win = win;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isBust() {
        return bust;
    }

    public boolean isWin() {
        return win;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
