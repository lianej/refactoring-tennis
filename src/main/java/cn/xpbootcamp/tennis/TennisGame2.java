package cn.xpbootcamp.tennis;

import java.util.Optional;

public class TennisGame2 implements TennisGame {
    public final Player player1 = new Player();
    public final Player player2 = new Player();

    private String scoreText;


    public TennisGame2(String player1Name, String player2Name) {
        this.player1.playerName = player1Name;
        this.player2.playerName = player2Name;
    }

    public String getScore() {
        ifDeuce();

        ifProgressing();

        ifAdvantage();

        Optional<String> text = player1.ifWin(this.player2);
        if(text.isPresent()){
            scoreText = text.get();
        }
        return scoreText;
    }

    private void ifDeuce() {
        if (player1.playerPoint == player2.playerPoint) {
            if (player1.playerPoint == 0) {
                this.scoreText = "Love-All";
            } else if (player1.playerPoint == 1) {
                this.scoreText = "Fifteen-All";
            } else if (player1.playerPoint == 2) {
                this.scoreText = "Thirty-All";
            } else {
                this.scoreText = "Deuce";
            }
        }
    }

    private void ifProgressing() {
        if (player1.playerPoint == player2.playerPoint || player1.playerPoint > 3 || player2.playerPoint > 3) {
            return;
        }
        String[] mapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        this.scoreText = String.format("%s-%s", mapping[player1.playerPoint], mapping[player2.playerPoint]);

    }

    private void ifAdvantage() {
        if (player1.playerPoint > player2.playerPoint && player2.playerPoint >= 3) {
            this.scoreText = "Advantage " + player1.playerName;
        }

        if (player2.playerPoint > player1.playerPoint && player1.playerPoint >= 3) {
            this.scoreText = "Advantage " + player2.playerName;
        }
    }

    public void wonPoint(String player) {
        if (player.equals(player1.playerName))
            player1.playerPoint++;
        else
            player2.playerPoint++;
    }

    private static class Player {
        public String playerName;
        public int playerPoint = 0;

        private Optional<String> ifWin(Player otherPlayer) {
            if (playerPoint >= 4 && otherPlayer.playerPoint >= 0 && (playerPoint - otherPlayer.playerPoint) >= 2) {
                return Optional.of("Win for " + playerName);
            }
            if (otherPlayer.playerPoint >= 4 && playerPoint >= 0 && (otherPlayer.playerPoint - playerPoint) >= 2) {
                return Optional.of("Win for " + otherPlayer.playerName);
            }
            return Optional.empty();
        }
    }
}