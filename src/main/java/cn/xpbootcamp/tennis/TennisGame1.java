package cn.xpbootcamp.tennis;

import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private int player1Score;
    private String player1Name;

    private int player2Score;
    private String player2Name;

    String[] scoreMapping = new String[]{"Love","Fifteen","Thirty","Forty"};

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
        if (player1Score == player2Score) {
            switch (player1Score) {
                case 0:
                    return "Love-All";
                case 1:
                    return "Fifteen-All";
                case 2:
                    return "Thirty-All";
                default:
                    return "Deuce";
            }
        } else if (player1Score >= 4 || player2Score >= 4) {
            int minusResult = player1Score - player2Score;
            if (minusResult == 1) {
                return "Advantage " + player1Name;
            } else {
                if (minusResult == -1) {
                    return "Advantage " + player2Name;
                } else if (minusResult >= 2) {
                    return "Win for " + player1Name;
                } else {
                    return "Win for " + player2Name;
                }
            }
        } else {
            return String.format("%s-%s",scoreMapping[player1Score],scoreMapping[player2Score]);
        }
    }
}