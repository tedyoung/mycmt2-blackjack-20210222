package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {
  @Test
  public void displayTenAsString() throws Exception {
    Card tenCard = new Card(Suit.HEARTS, Rank.TEN);

    assertThat(ConsoleCard.display(tenCard))
        .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
  }

  @Test
  public void displayNonTenAsString() throws Exception {
    Card nonTenCard = new Card(Suit.CLUBS, Rank.QUEEN);

    assertThat(ConsoleCard.display(nonTenCard))
        .isEqualTo("[30m┌─────────┐[1B[11D│Q        │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│        Q│[1B[11D└─────────┘");
  }
}