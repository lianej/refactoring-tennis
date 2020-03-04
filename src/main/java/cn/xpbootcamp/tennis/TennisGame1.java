package cn.xpbootcamp.tennis;

import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private Player player1;
    private Player player2;


    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name, 0);
        this.player2 = new Player(player2Name, 0);
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1.name)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    public String getScore() {
        if (player1.isDeuceFor(player2)) {
            return "Deuce";
        } else if (player1.isScoreLessThan4() && player2.isScoreLessThan4()) {
            return formatScoreIfScoreBothLessThan4();
        } else {
            return formatScore();
        }
    }

    private Player getBackwardPlayer() {
        if (player1.score > player2.score) {
            return player2;
        } else {
            return player1;
        }
    }

    private Player getLeadPlayer() {
        if (player1.score > player2.score) {
            return player1;
        } else {
            return player2;
        }
    }

    private String formatScoreIfScoreBothLessThan4() {
        String[] scoreTextMapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        String scoreText = String.format("%s-%s",
                scoreTextMapping[player1.score], scoreTextMapping[player2.score]);
        switch (scoreText) {
            case "Love-Love":
                return "Love-All";
            case "Fifteen-Fifteen":
                return "Fifteen-All";
            case "Thirty-Thirty":
                return "Thirty-All";
            default:
                return scoreText;
        }
    }

    private String formatScore() {
        Player leadPlayer = getLeadPlayer();
        int delta = leadPlayer.score - getBackwardPlayer().score;
        if (delta == 1) {
            return "Advantage " + leadPlayer.name;
        } else {
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

        private boolean isScoreLessThan4() {
            return score < 4;
        }

        private void wonPoint() {
            score++;
        }

        public boolean isDeuceFor(Player otherPlayer) {
            return score >= 3 && score == otherPlayer.score;
        }
    }
}
