public class BowlingGame extends BowlingGameHelper {

    private BowlingGame() {}
    private  BowlingGame(int _maxNumberOfFrames, int _maxNumberOfPins) {
        super(_maxNumberOfFrames, _maxNumberOfPins);
    }

    /**
     * This method handles the entire gameplay part of the bowling game. It will iterate through the shots, calculate the scores and print messages to the console.
     * @return The total score of the game.
     */
    private int play() {
        int totalScore = 0;

        for (int frame = 0, turnIndex = 0, frameScore = 0; frame < maxNumberOfFrames; frame++) {
            printFrameNumberAndTurnGameplay(frame, turnIndex);

            if (isStrike(turnIndex)) {
                frameScore = maxNumberOfPins + strikeBonus(turnIndex);
                turnIndex++;
            } else if (isSpare(turnIndex)) {
                frameScore = maxNumberOfPins + spareBonus(turnIndex);
                turnIndex += 2;
                if(frame == maxNumberOfFrames - 1 && isStrike((turnIndex))) {
                    frameScore += spareBonus(turnIndex - 1);
                }
            } else {
                frameScore = totalKnockedDownPins(turnIndex);
                turnIndex += 2;
            }

            printFrameScore(frameScore);
            totalScore += frameScore;
        }
        return totalScore;
    }

    public static void main(String[] args) {
        BowlingGame game = new BowlingGame(10, 10);

        for (int frame = 0; frame < game.maxNumberOfFrames; frame++) {
            int firstTurn = randomizer.nextInt(game.maxNumberOfPins + 1);
            game.takeTurn(firstTurn);

            if(firstTurn < game.maxNumberOfPins)  {
                int remainingPins = game.maxNumberOfPins + 1 - firstTurn;
                int secondTurn = randomizer.nextInt(remainingPins);
                game.takeTurn(secondTurn);
            }

            if(game.isLastFrame(frame))
                game.checkAndTakeExtraShots();
        }
        System.out.println("\nTotal score is " + game.play());
    }
}