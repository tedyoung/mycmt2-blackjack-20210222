package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

  @Test
  public void playerBeatsDealer() throws Exception {
    Deck playerBeatsDealerDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                              Rank.NINE, Rank.QUEEN);
    Game game = new Game(playerBeatsDealerDeck);

    game.initialDeal();
    game.playerStands();
    game.dealerTurn();

    assertThat(game.determineOutcome())
        .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
  }

  @Test
  public void playerHitsGoesBustThenLoses() throws Exception {
    Deck playerGoesBustUponHitDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                                  Rank.NINE, Rank.QUEEN,
                                                  Rank.FIVE);
    Game game = new Game(playerGoesBustUponHitDeck);
    game.initialDeal();

    game.playerHits();
    game.dealerTurn();

    assertThat(game.determineOutcome())
        .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
  }

}