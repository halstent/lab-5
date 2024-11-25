package use_case.blackjack.get_card;


/**
 * Input Boundary for actions related to playing the Blackjack Game..
 */
public interface BlackjackGetCardInputBoundary {

    /**
     * Executes the blackjack game use case.
     * @param blackjackGetCardInputData the input data
     */
    void execute(BlackjackGetCardInputData blackjackGetCardInputData);

    /**
     * Executes the switch to login use case.
     */
    void switchToLoginView();

}
