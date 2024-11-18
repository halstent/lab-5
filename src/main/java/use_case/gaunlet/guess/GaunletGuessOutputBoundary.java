package use_case.gaunlet.guess;

import use_case.gaunlet.bet.GaunletBetOutputData;

/**
 * The output boundary for the Gaunlet guess Use Case.
 */
public interface GaunletGuessOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GaunletBetOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}