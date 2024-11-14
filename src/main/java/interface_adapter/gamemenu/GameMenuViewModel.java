package interface_adapter.gamemenu;

import interface_adapter.ViewModel;
import interface_adapter.gamemenu.GameMenuState;

/**
 * The ViewModel for the Game Menu View.
 */
public class GameMenuViewModel extends ViewModel<GameMenuState> {

    public static final String TITLE_LABEL = "Let's Go Gambling!";
    public static final String BLACKJACK_BUTTON_LABEL = "PLAY BLACKJACK";
    public static final String GAUNTLET_BUTTON_LABEL = "PLAY GAUNTLET";
    public static final String OVERUNDER_BUTTON_LABEL = "PLAY OVER/UNDER";
    public static final String BACK_BUTTON_LABEL = "Back to main menu";

    public GameMenuViewModel() {
        super("game menu");
        // setState(new WelcomeState());
    }

}
