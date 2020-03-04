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
        if (player1Point == player2Point) {
            if (player1Point == 0) {
                this.scoreText = "Love-All";
            } else if (player1Point == 1) {
                this.scoreText = "Fifteen-All";
            } else if (player1Point == 2) {
                this.scoreText = "Thirty-All";
            } else {
                this.scoreText = "Deuce";
            }
        }
    }

    private void ifProgressing() {
        if (player1Point == player2Point || player1Point > 3 || player2Point > 3) {
            return;
        }
        String[] mapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        this.scoreText = String.format("%s-%s", mapping[player1Point], mapping[player2Point]);

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