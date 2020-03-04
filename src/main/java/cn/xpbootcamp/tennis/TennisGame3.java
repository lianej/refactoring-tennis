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
        String s;
        if (point1 < 4 && point2 < 4 && !(point1 + point2 == 6)) {
            String[] p = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            s = p[point1];
            return (point1 == point2) ? s + "-All" : s + "-" + p[point2];
        } else {
            if (point1 == point2)
                return "Deuce";
            s = point1 > point2 ? player1Name : player2Name;
            return ((point1 - point2)*(point1 - point2) == 1) ? "Advantage " + s : "Win for " + s;
        }
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1Name))
            this.point1 += 1;
        else
            this.point2 += 1;

    }

}