package com.jitterted.ebp.blackjack;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackGameApplication {

  public static void main(String[] args) {
//    SpringApplication.run(BlackjackGameApplication.class, args);

    // Run the ConsoleAdapter-based game
    GameApplication.main(args);
  }

}
