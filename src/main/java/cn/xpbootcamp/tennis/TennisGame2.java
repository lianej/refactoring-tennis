package cn.xpbootcamp.tennis;

public class TennisGame2 implements TennisGame {
    public String player1Name;
    public String player2Name;
    public int player1Point = 0;
    public int player2Point = 0;


    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String score = "";
        String P1res = "";
        String P2res = "";
        if (player1Point == player2Point && player1Point < 4) {
            if (player1Point == 0)
                score = "Love";
            if (player1Point == 1)
                score = "Fifteen";
            if (player1Point == 2)
                score = "Thirty";
            score += "-All";
        }
        if (player1Point == player2Point && player1Point >= 3)
            score = "Deuce";

        if (player1Point > 0 && player2Point == 0) {
            if (player1Point == 1)
                P1res = "Fifteen";
            if (player1Point == 2)
                P1res = "Thirty";
            if (player1Point == 3)
                P1res = "Forty";

            P2res = "Love";
            score = P1res + "-" + P2res;
        }
        if (player2Point > 0 && player1Point == 0) {
            if (player2Point == 1)
                P2res = "Fifteen";
            if (player2Point == 2)
                P2res = "Thirty";
            if (player2Point == 3)
                P2res = "Forty";

            P1res = "Love";
            score = P1res + "-" + P2res;
        }

        if (player1Point > player2Point && player1Point < 4) {
            if (player1Point == 2)
                P1res = "Thirty";
            if (player1Point == 3)
                P1res = "Forty";
            if (player2Point == 1)
                P2res = "Fifteen";
            if (player2Point == 2)
                P2res = "Thirty";
            score = P1res + "-" + P2res;
        }
        if (player2Point > player1Point && player2Point < 4) {
            if (player2Point == 2)
                P2res = "Thirty";
            if (player2Point == 3)
                P2res = "Forty";
            if (player1Point == 1)
                P1res = "Fifteen";
            if (player1Point == 2)
                P1res = "Thirty";
            score = P1res + "-" + P2res;
        }

        if (player1Point > player2Point && player2Point >= 3) {
            score = "Advantage " + player1Name;
        }

        if (player2Point > player1Point && player1Point >= 3) {
            score = "Advantage " + player2Name;
        }

        if (player1Point >= 4 && player2Point >= 0 && (player1Point - player2Point) >= 2) {
            score = "Win for " + player1Name;
        }
        if (player2Point >= 4 && player1Point >= 0 && (player2Point - player1Point) >= 2) {
            score = "Win for " + player2Name;
        }
        return score;
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            player1Point++;
        else
            player2Point++;
    }
}