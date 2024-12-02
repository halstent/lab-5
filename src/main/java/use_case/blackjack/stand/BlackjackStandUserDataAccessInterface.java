package use_case.blackjack.stand;

import entity.User;
import org.json.JSONObject;

public interface BlackjackStandUserDataAccessInterface {
    /**
     * Returns the bet of the curren user for the gauntlet game.
     * @return the bet of the current user; null indicates that no one is logged into the application.
     */
    int getBet();

    /**
     * Saves the user's info.
     * @param user the user to save
     * @param info the default user info to save
     */
    void saveNew(User user, JSONObject info);

    /**
     * Returns the user with the associated username.
     * @param username the username of the user
     * @return the user associated with that username
     */
    User get(String username);
}
