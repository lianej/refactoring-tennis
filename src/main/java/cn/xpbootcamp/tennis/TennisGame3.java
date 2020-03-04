package cn.xpbootcamp.tennis;

import java.util.Objects;

public class TennisGame3 implements TennisGame {

    private final Player player1 = new Player();
    private final Player player2 = new Player();

    public TennisGame3(String player1Name, String player2Name) {
        this.player1.name = player1Name;
        this.player2.name = player2Name;
    }

    public String getScore() {
        String score = player1.getScoreIfGameProcessing(this.player2);
        if (score != null) {
            return score;
        }
        if (player1.point == player2.point) {
            score = "Deuce";
        }
        if (score != null) {
            return score;
        }
        String leadPlayerName = player1.point > player2.point ? player1.name : player2.name;
        if (Math.abs(player1.point - player2.point) == 1) {
            score = "Advantage " + leadPlayerName;
        } else {
            score = "Win for " + leadPlayerName;
        }
        return score;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1.name)) {
            this.player1.wonPoint();
        } else {
            this.player2.wonPoint();
        }
    }

    public static class Player {
        int point;
        String name;

        private void wonPoint() {
            point++;
        }

        private String getScoreIfGameProcessing(Player otherPlayer) {
            if (point < 4 && otherPlayer.point < 4 && point + otherPlayer.point < 6) {
                if (point == otherPlayer.point) {
                    return getPlayerScore() + "-All";
                } else {
                    return getPlayerScore() + "-" + otherPlayer.getPlayerScore();
                }
            }
            return null;
        }

        private String getPlayerScore() {
            String[] pointScoreMapping = {"Love", "Fifteen", "Thirty", "Forty"};
            return pointScoreMapping[point];
        }
    }

}