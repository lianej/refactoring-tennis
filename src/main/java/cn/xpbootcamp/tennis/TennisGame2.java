package cn.xpbootcamp.tennis;

import java.util.Optional;
import java.util.stream.Stream;

public class TennisGame2 implements TennisGame {
    public final Player player1 = new Player();
    public final Player player2 = new Player();

    private String scoreText;


    public TennisGame2(String player1Name, String player2Name) {
        this.player1.name = player1Name;
        this.player2.name = player2Name;
    }

    public String getScore() {
        return Stream.of(
                player1.getScoreTextIfWinAgainst(this.player2),
                player1.getScoreTextIfAdvantageOver(this.player2),
                player1.getScoreTextIfDeuceFor(this.player2),
                player1.getScoreTextIfGameProgressing(this.player2)
        ).filter(Optional::isPresent).map(Optional::get).findFirst().orElse(null);
    }

    public void wonPoint(String player) {
        if (player.equals(player1.name)) {
            player1.wonPoint();
        } else
            player2.wonPoint();
    }

    private static class Player {
        private String name;
        private int point = 0;

        private Optional<String> getScoreTextIfWinAgainst(Player otherPlayer) {
            if (point >= 4 && otherPlayer.point >= 0 && (point - otherPlayer.point) >= 2) {
                return Optional.of("Win for " + name);
            }
            if (otherPlayer.point >= 4 && point >= 0 && (otherPlayer.point - point) >= 2) {
                return Optional.of("Win for " + otherPlayer.name);
            }
            return Optional.empty();
        }

        private void wonPoint() {
            point++;
        }

        private Optional<String> getScoreTextIfAdvantageOver(Player otherPlayer) {
            if (point > otherPlayer.point && otherPlayer.point >= 3) {
                return Optional.of("Advantage " + name);
            }

            if (otherPlayer.point > point && point >= 3) {
                return Optional.of("Advantage " + otherPlayer.name);
            }
            return Optional.empty();
        }

        private Optional<String> getScoreTextIfDeuceFor(Player otherPlayer) {
            if (point == otherPlayer.point) {
                return Optional.of(getDeuceText());
            }
            return Optional.empty();
        }

        private String getDeuceText() {
            String scoreText;
            if (point == 0) {
                scoreText = "Love-All";
            } else if (point == 1) {
                scoreText = "Fifteen-All";
            } else if (point == 2) {
                scoreText = "Thirty-All";
            } else {
                scoreText = "Deuce";
            }
            return scoreText;
        }

        private Optional<String> getScoreTextIfGameProgressing(Player otherPlayer) {
            if (point == otherPlayer.point || point > 3 || otherPlayer.point > 3) {
                return Optional.empty();
            }
            String[] mapping = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            String scoreText = String.format("%s-%s", mapping[point], mapping[otherPlayer.point]);
            return Optional.of(scoreText);

        }
    }
}