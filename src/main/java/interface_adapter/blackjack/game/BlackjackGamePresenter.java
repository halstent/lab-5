package interface_adapter.blackjack.game;


import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.blackjack.bet.BlackjackBetOutputData;
import use_case.blackjack.game.BlackjackGameOutputBoundary;
import use_case.blackjack.game.BlackjackGameOutputData;

/**
 * The Presenter for the Blackjack Game Use Case.
 */
public class BlackjackGamePresenter implements BlackjackGameOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final BlackjackGameViewModel blackjackGameViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;
    private final BlackjackBetViewModel blackjackBetViewModel;

    public BlackjackGamePresenter(SignupViewModel signupViewModel,
                                  BlackjackGameViewModel blackjackGameViewModel,
                                  GameMenuViewModel gameMenuViewModel,
                                  ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, BlackjackBetViewModel blackjackBetViewModel) {
        this.signupViewModel = signupViewModel;
        this.blackjackGameViewModel  = blackjackGameViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.viewManagerModel = viewManagerModel;

        this.menuViewModel = menuViewModel;
        this.blackjackBetViewModel = blackjackBetViewModel;
    }

    @Override
    public void prepareSuccessView(BlackjackGameOutputData outputData) {
        if (outputData.getUseCase().equals("Stop")) {
            prepareEndGameView(outputData);
        } else if (outputData.getUseCase().equals("Start")) {

        } else if (outputData.getUseCase().equals("Play Again")) {
            preparePlayAgain();
        }
    }

    private void preparePlayAgain() {
        final BlackjackGameState gameState = new BlackjackGameState();
        blackjackGameViewModel.setState(gameState);
        blackjackGameViewModel.firePropertyChanged();

        viewManagerModel.setState(blackjackBetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    private void prepareEndGameView(BlackjackGameOutputData outputData) {
        final BlackjackGameState gameState = blackjackGameViewModel.getState();
        final User user = gameState.getUser();
        user.updateBalance(outputData.getAmountWon());
        blackjackGameViewModel.setState(gameState);
        blackjackGameViewModel.firePropertyChanged();

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(user);
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

        GameMenuState gameMenuState = gameMenuViewModel.getState();
        gameMenuState.setUser(user);
        this.gameMenuViewModel.setState(gameMenuState);
        gameMenuViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setError(errorMessage);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
