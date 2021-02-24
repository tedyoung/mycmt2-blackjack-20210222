package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BlackjackControllerTest {

  @Test
  public void startGameThenInitialCardsDealt() throws Exception {
    Game game = new Game();
    BlackjackController blackjackController = new BlackjackController(game);

    blackjackController.startGame();

    assertThat(game.playerHand().cards())
        .hasSize(2);
  }

  @Test
  public void gameViewReturnsViewModelWithSnapshotOfGameState() throws Exception {
    Deck stubDeck = new StubDeck(List.of(new Card(Suit.DIAMONDS, Rank.TEN),
                                         new Card(Suit.HEARTS, Rank.TWO),
                                         new Card(Suit.DIAMONDS, Rank.KING),
                                         new Card(Suit.CLUBS, Rank.THREE)));

    Game game = new Game(stubDeck);
    BlackjackController blackjackController = new BlackjackController(game);
    blackjackController.startGame();

    Model model = new ConcurrentModel();
    blackjackController.gameView(model);

    GameView gameView = (GameView) model.getAttribute("gameView");

    assertThat(gameView.getPlayerCards())
        .containsExactly("10♦", "K♦");
    assertThat(gameView.getDealerCards())
        .containsExactly("2♥", "3♣");
  }

  @Test
  public void hitCommandDealsCardToPlayer() throws Exception {
    Game game = new Game();
    BlackjackController blackjackController = new BlackjackController(game);
    blackjackController.startGame();

    blackjackController.hitCommand();

    assertThat(game.playerHand().cards())
        .hasSize(3);
  }

  @Test
  public void hitAndGoesBustRedirectsToDonePage() throws Exception {
    Deck playerGoesBustUponHitDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                                  Rank.NINE, Rank.QUEEN,
                                                  Rank.FIVE);
    Game game = new Game(playerGoesBustUponHitDeck);
    BlackjackController blackjackController = new BlackjackController(game);
    blackjackController.startGame();

    String viewName = blackjackController.hitCommand();

    assertThat(viewName)
        .isEqualTo("redirect:/done");
  }

}