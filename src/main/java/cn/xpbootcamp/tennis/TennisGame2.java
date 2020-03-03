package cn.xpbootcamp.tennis;

public class TennisGame2 implements TennisGame {
    public String player1Name;
    public String player2Name;
    public int player1Point = 0;
    public int player2Point = 0;

    private String scoreText;


    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        ifDeuce();

        ifProgressing();

        ifAdvantage();

        ifWin();
        return scoreText;
    }

    private void ifDeuce() {
        if (player1Point == player2Point && player1Point < 4) {
            String score = "";
            if (player1Point == 0)
                score = "Love";
            if (player1Point == 1)
                score = "Fifteen";
            if (player1Point == 2)
                score = "Thirty";
            score += "-All";
            this.scoreText = score;
        }
        if (player1Point == player2Point && player1Point >= 3)
            this.scoreText = "Deuce";
    }

    private void ifProgressing() {
        String p1res = "";
        String p2res = "";
        if (player1Point > 0 && player2Point == 0) {
            if (player1Point == 1)
                p1res = "Fifteen";
            if (player1Point == 2)
                p1res = "Thirty";
            if (player1Point == 3)
                p1res = "Forty";

            this.scoreText = p1res + "-" + "Love";
        } else if (player2Point > 0 && player1Point == 0) {
            if (player2Point == 1)
                p2res = "Fifteen";
            if (player2Point == 2)
                p2res = "Thirty";
            if (player2Point == 3)
                p2res = "Forty";

            this.scoreText = "Love" + "-" + p2res;
        } else if (player1Point > player2Point && player1Point < 4) {
            if (player1Point == 2)
                p1res = "Thirty";
            if (player1Point == 3)
                p1res = "Forty";
            if (player2Point == 1)
                p2res = "Fifteen";
            if (player2Point == 2)
                p2res = "Thirty";
            this.scoreText = p1res + "-" + p2res;
        } else if (player2Point > player1Point && player2Point < 4) {
            if (player2Point == 2)
                p2res = "Thirty";
            if (player2Point == 3)
                p2res = "Forty";
            if (player1Point == 1)
                p1res = "Fifteen";
            if (player1Point == 2)
                p1res = "Thirty";
            this.scoreText = p1res + "-" + p2res;
        }
    }

    private void ifAdvantage() {
        if (player1Point > player2Point && player2Point >= 3) {
            this.scoreText = "Advantage " + player1Name;
        }

        if (player2Point > player1Point && player1Point >= 3) {
            this.scoreText = "Advantage " + player2Name;
        }
    }

    private void ifWin() {
        if (player1Point >= 4 && player2Point >= 0 && (player1Point - player2Point) >= 2) {
            this.scoreText = "Win for " + player1Name;
        }
        if (player2Point >= 4 && player1Point >= 0 && (player2Point - player1Point) >= 2) {
            this.scoreText = "Win for " + player2Name;
        }
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            player1Point++;
        else
            player2Point++;
    }
}