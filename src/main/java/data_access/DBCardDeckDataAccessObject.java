package data_access;

import entity.AbstractCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Card;
import entity.CardFactory;

import use_case.blackjack.get_card.BlackjackGetCardDataAccessInterface;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * The DAO for the Deck of Cards api.
 */
public class DBCardDeckDataAccessObject implements BlackjackGetCardDataAccessInterface {
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String SUCCESS_CODE_LABEL = "success";

    private final CardFactory cardFactory;
    private String currentDeckId;

    public DBCardDeckDataAccessObject(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public void createNewDeck() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url("https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=6")
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean(SUCCESS_CODE_LABEL)) {
                final String deckId = responseBody.getString("deck_id");
                currentDeckId = deckId;
            }
            else {
                throw new RuntimeException("Deck Initialization Failed");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getDeckID() {
        return currentDeckId;
    }

    @Override
    public boolean hasDeck() {
        return currentDeckId != null;
    }

    @Override
    public void shuffleDeck(String deckId) {
        if (!this.hasDeck()) {
            throw new NullPointerException("No deck initialized");
        }

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("https://www.deckofcardsapi.com/api/deck/%s/shuffle/", this.currentDeckId))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (!responseBody.getBoolean(SUCCESS_CODE_LABEL)) {
                throw new RuntimeException("Deck Shuffling Failed");
            }

        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public AbstractCard drawCard(String deckId) {
        if (!this.hasDeck()) {
            throw new NullPointerException("No deck initialized");
        }

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("https://www.deckofcardsapi.com/api/deck/%s/draw/?count=1", this.currentDeckId))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean(SUCCESS_CODE_LABEL)) {

                final JSONArray drawnCards = responseBody.getJSONArray("cards");
                final JSONObject drawnCard = drawnCards.getJSONObject(0);
                final int value = Integer.parseInt(drawnCard.getString("value"));
                final String suit = drawnCard.getString("suit");
                final String name = drawnCard.getString("code");

                final JSONObject imageURLS = drawnCard.getJSONObject("images");
                final String pngImageURL = imageURLS.getString("png");

                final Request imageRequest = new Request.Builder()
                        .url(String.format(pngImageURL, this.currentDeckId))
                        .build();

                Image img = null;

                try {
                    final Response imageResponse = client.newCall(imageRequest).execute();

                    if (imageResponse.isSuccessful()) {
                        final InputStream inputStream = response.body().byteStream();
                        final BufferedImage bufferedImage = ImageIO.read(inputStream);
                        img = bufferedImage.getScaledInstance(
                                (int) Math.round(bufferedImage.getWidth() * 0.12),
                                (int) Math.round(bufferedImage.getHeight() * 0.12), Image.SCALE_SMOOTH);

                    }
                    else {
                        throw new RuntimeException("Image Loading Failed");
                    }
                }
                catch (IOException | JSONException ex) {
                    throw new RuntimeException(ex);
                }

                final Card card = this.cardFactory.create(value, suit, name, img);
                return card;
            }
            else {
                throw new RuntimeException("Drawing Card Failed");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }

    }
}
