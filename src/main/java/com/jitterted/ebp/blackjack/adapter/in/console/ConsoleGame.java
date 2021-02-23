package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Game;
import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleGame {

  private final Game game;

  public ConsoleGame(Game game) {
    this.game = game;
  }

  private void resetScreen() {
    System.out.println(ansi().reset());
  }

  private void displayWelcomeScreen() {
    System.out.println(ansi()
                           .bgBright(Ansi.Color.WHITE)
                           .eraseScreen()
                           .cursor(1, 1)
                           .fgGreen().a("Welcome to")
                           .fgRed().a(" Jitterted's")
                           .fgBlack().a(" BlackJack"));
  }

  // CONTROLLER method
  public void start() {
    displayWelcomeScreen();

    game.initialDeal();

    playerPlays();

    game.dealerTurn();

    game.displayFinalGameState();

    game.determineOutcome();

    resetScreen();
  }

  // Player Game Loop
  public void playerPlays() {
    while (!game.isPlayerDone()) {
      game.displayGameState();
      String command = inputFromPlayer();
      handle(command);
    }
  }

  public String inputFromPlayer() {
    System.out.println("[H]it or [S]tand?");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  // DISPATCHER
  public void handle(String command) {
    if (command.toLowerCase().startsWith("h")) {
      game.playerHits();
    } else if (command.toLowerCase().startsWith("s")) {
      game.playerStands();
    }
  }

}
