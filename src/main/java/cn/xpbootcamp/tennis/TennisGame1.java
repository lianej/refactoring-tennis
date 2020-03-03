package cn.xpbootcamp.tennis;

import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private int player1Score;
    private String player1Name;

    private int player2Score;
    private String player2Name;

    static String[] scoreMapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        if (player1Score < 4 && player2Score < 4) {
            return formatScoreBeforeScoreBothLessThan4();
        } else {
            int minusResult = player1Score - player2Score;
            if (minusResult == 1) {
                return "Advantage " + player1Name;
            } else {
                if (minusResult == -1) {
                    return "Advantage " + player2Name;
                } else if (minusResult >= 2) {
                    return "Win for " + player1Name;
                } else if (minusResult <= -2) {
                    return "Win for " + player2Name;
                } else {
                    return "Deuce";
                }
            }
        }
    }

    private String formatScoreBeforeScoreBothLessThan4() {
        String scoreText = String.format("%s-%s", scoreMapping[player1Score], scoreMapping[player2Score]);
        switch (scoreText) {
            case "Love-Love":
                return "Love-All";
            case "Fifteen-Fifteen":
                return "Fifteen-All";
            case "Thirty-Thirty":
                return "Thirty-All";
            case "Forty-Forty":
                return "Deuce";
            default:
                return scoreText;
        }
    }
}