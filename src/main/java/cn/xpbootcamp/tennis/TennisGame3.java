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
        score = player1.getScoreIfDeuce(this.player2);
        if (score != null) {
            return score;
        }
        score = player1.getScoreIfAdvantageOrWin(this.player2);
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

        private String getScoreIfAdvantageOrWin(Player otherPlayer) {
            String leadPlayerName = point > otherPlayer.point ? name : otherPlayer.name;
            if (Math.abs(point - otherPlayer.point) == 1) {
                return "Advantage " + leadPlayerName;
            } else {
                return "Win for " + leadPlayerName;
            }
        }

        private String getScoreIfDeuce(Player otherPlayer) {
            if (point == otherPlayer.point) {
                return "Deuce";
            }
            return null;
        }
    }

}