package cn.xpbootcamp.tennis;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class TennisGame3 implements TennisGame {

    private final Player player1 = new Player();
    private final Player player2 = new Player();

    public TennisGame3(String player1Name, String player2Name) {
        this.player1.name = player1Name;
        this.player2.name = player2Name;
    }

    public String getScore() {
        return player1.getScoreTextWith(this.player2);
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1.name)) {
            this.player1.wonPoint();
        } else {
            this.player2.wonPoint();
        }
    }

    public static class Player {
        int point;
        String name;

        private void wonPoint() {
            point++;
        }

        private Optional<String> getScoreIfGameProcessing(Player otherPlayer) {
            if (point >= 4 || otherPlayer.point >= 4 || point + otherPlayer.point >= 6) {
                return Optional.empty();
            }
            if (point == otherPlayer.point) {
                return Optional.of(getPlayerScore() + "-All");
            } else {
                return Optional.of(getPlayerScore() + "-" + otherPlayer.getPlayerScore());
            }
        }

        private String getPlayerScore() {
            String[] pointScoreMapping = {"Love", "Fifteen", "Thirty", "Forty"};
            return pointScoreMapping[point];
        }

        private Optional<String> getScoreIfAdvantageOrWin(Player otherPlayer) {
            String leadPlayerName = point > otherPlayer.point ? name : otherPlayer.name;
            if (Math.abs(point - otherPlayer.point) == 1) {
                return Optional.of("Advantage " + leadPlayerName);
            } else {
                return Optional.of("Win for " + leadPlayerName);
            }
        }

        private Optional<String> getScoreIfDeuce(Player otherPlayer) {
            if (point == otherPlayer.point) {
                return Optional.of("Deuce");
            }
            return Optional.empty();
        }

        public String getScoreTextWith(Player otherPlayer) {
            return Stream.of(
                    getScoreIfGameProcessing(otherPlayer),
                    getScoreIfDeuce(otherPlayer),
                    getScoreIfAdvantageOrWin(otherPlayer)
            ).filter(Optional::isPresent).map(Optional::get).findFirst().orElse(null);
        }
    }

}