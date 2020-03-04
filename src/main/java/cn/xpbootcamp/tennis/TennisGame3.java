package cn.xpbootcamp.tennis;

import java.util.Objects;

public class TennisGame3 implements TennisGame {

    private int point1;
    private String player1Name;

    private int point2;
    private String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (point1 < 4 && point2 < 4 && point1 + point2 < 6) {
            String[] pointScoreMapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            String player1Score = pointScoreMapping[point1];
            if (point1 == point2){
                return player1Score + "-All";
            }else {
                return player1Score + "-" + pointScoreMapping[point2];
            }
        } else if (point1 == point2) {
            return "Deuce";
        } else {
            String leadPlayerName = point1 > point2 ? player1Name : player2Name;
            if ((point1 - point2) * (point1 - point2) == 1) {
                return "Advantage " + leadPlayerName;
            }else {
                return "Win for " + leadPlayerName;
            }
        }
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1Name))
            this.point1 += 1;
        else
            this.point2 += 1;

    }

}