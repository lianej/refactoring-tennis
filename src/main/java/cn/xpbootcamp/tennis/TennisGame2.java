package cn.xpbootcamp.tennis;

import java.util.Optional;

public class TennisGame2 implements TennisGame {
    public final Player player1 = new Player();
    public final Player player2 = new Player();

    private String scoreText;


    public TennisGame2(String player1Name, String player2Name) {
        this.player1.name = player1Name;
        this.player2.name = player2Name;
    }

    public String getScore() {
        ifDeuce();

        ifProgressing();

        player1.ifAdvantage(this.player2).ifPresent(s -> scoreText = s);
        player1.ifWin(this.player2).ifPresent(s -> scoreText = s);
        return scoreText;
    }

    private void ifDeuce() {
        if (player1.point == player2.point) {
            if (player1.point == 0) {
                this.scoreText = "Love-All";
            } else if (player1.point == 1) {
                this.scoreText = "Fifteen-All";
            } else if (player1.point == 2) {
                this.scoreText = "Thirty-All";
            } else {
                this.scoreText = "Deuce";
            }
        }
    }

    private void ifProgressing() {
        if (player1.point == player2.point || player1.point > 3 || player2.point > 3) {
            return;
        }
        String[] mapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        this.scoreText = String.format("%s-%s", mapping[player1.point], mapping[player2.point]);

    }

    public void wonPoint(String player) {
        if (player.equals(player1.name)) {
            player1.wonPoint();
        } else
            player2.wonPoint();
    }

    private static class Player {
        private String name;
        private int point = 0;

        private Optional<String> ifWin(Player otherPlayer) {
            if (point >= 4 && otherPlayer.point >= 0 && (point - otherPlayer.point) >= 2) {
                return Optional.of("Win for " + name);
            }
            if (otherPlayer.point >= 4 && point >= 0 && (otherPlayer.point - point) >= 2) {
                return Optional.of("Win for " + otherPlayer.name);
            }
            return Optional.empty();
        }

        private void wonPoint() {
            point++;
        }

        private Optional<String> ifAdvantage(Player otherPlayer) {
            if (point > otherPlayer.point && otherPlayer.point >= 3) {
                return Optional.of("Advantage " + name);
            }

            if (otherPlayer.point > point && point >= 3) {
                return Optional.of("Advantage " + otherPlayer.name);
            }
            return Optional.empty();
        }
    }
}