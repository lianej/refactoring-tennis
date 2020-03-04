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
            return getScoreText();
        }
    }


    private Player getBackwardPlayer() {
        if (player1Score > player2Score) {
            return new Player(player2Name, player2Score);
        } else {
            return new Player(player1Name, player1Score);
        }
    }

    private Player getLeadPlayer() {
        if (player1Score > player2Score) {
            return new Player(player1Name, player1Score);
        } else {
            return new Player(player2Name, player2Score);
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

    private String getScoreText() {
        Player leadPlayer = getLeadPlayer();
        int delta = leadPlayer.score - getBackwardPlayer().score;
        if(delta == 0){
            return "Deuce";
        }else if(delta == 1){
            return "Advantage " + leadPlayer.name;
        }else {
            return "Win for " + leadPlayer.name;
        }
    }

    private static class Player {
        String name;
        int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
