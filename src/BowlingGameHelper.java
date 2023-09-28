import java.util.ArrayList;
import java.util.Random;

public class BowlingGameHelper {
    protected int maxNumberOfPins = 10;
    protected int maxNumberOfFrames = 10;
    protected static final Random randomizer = new Random();
    private final ArrayList<Integer> turns = new ArrayList<Integer>();

    protected BowlingGameHelper() {}
    protected BowlingGameHelper(int _maxNumberOfPins, int _maxNumberOfFrames) {
        maxNumberOfFrames = _maxNumberOfFrames;
        maxNumberOfPins = _maxNumberOfPins;
    }

    /**
     * Adds the number of downed pins in the list of turns.
     * @param shotPins it holds the number of pins that were hit.
     */
    protected void takeTurn(int shotPins) {
        turns.add(shotPins);
    }
    /**
     * Checks if the last shot was a Strike or a Spare.
     * If yes then the player takes another shot, which will repeat once more if the extra shot is a Strike.
     * It will add the shots to the list of turns.
     */
    protected void checkAndTakeExtraShots() {
        if (isStrike(turns.size() - 1) || isSpare(turns.size() - 2)) {
            takeTurn(randomizer.nextInt(maxNumberOfPins));
            if (isStrike(turns.size() - 1)) {
                takeTurn(randomizer.nextInt(maxNumberOfPins));
            }
        }
    }

    /**
     * It checks what kind of turn it is and prints to the consoles messages that describe the kind of frame it was and the number of pins downed after each shot.<br></br>
     * If the player scored a Strike, then in this frame the player took one shot.<br></br>
     * If the player scored a Spare or nothing special, then in this frame the player took two shots.
     * @param frame the current frame.
     * @param turnIndex the current shot that is being checked.
     */
    protected void printFrameNumberAndTurnGameplay(int frame, int turnIndex) {
        System.out.println("Frame " + (frame + 1));
        if (isStrike(turnIndex)) {
            System.out.println("\tSTRIKE!!!\n\tYou shot down all of the pins!");
            if(frame == 9) {
                printExtraShots(turnIndex + 1);
            }
        } else if (isSpare(turnIndex)) {
            System.out.println("\tSPARE!\n\tYou hit " + turns.get(turnIndex) + " pins the first time and " + turns.get(turnIndex + 1) + " pins the second time.");
            if(frame == 9) {
                printExtraShots(turnIndex + 2);
            }
        } else {
            System.out.println("\tYou hit " + turns.get(turnIndex) + " pins the first time and " + turns.get(turnIndex + 1) + " pins the second time.");
        }
    }
    /**
     * It checks the nature of the extra shots (if they exist) and prints to console messages that describe the nature of those shots.
     * @param turnIndex the current shot that is being checked.
     */
    protected void printExtraShots(int turnIndex) {
        if (isStrike(turnIndex)) {
            System.out.println("\tSTRIKE!!!\n\tYou shot down all of the pins with your extra shot!");
            if (isStrike(turnIndex + 1)) {
                System.out.println("\tSTRIKE!!!\n\tYou shot down all of the pins with your second extra shot!");
            } else {
                System.out.println("\tYou hit " + turns.get(turnIndex + 1) + " pins with your second extra shot.");
            }
        } else {
            System.out.println("\tYou hit " + turns.get(turnIndex) + " pins with your extra shot.");
        }
    }
    /**
     *It prints to console the total score for one single frame.
     * @param frameScore the total score of the specific frame
     */
    protected void printFrameScore(int frameScore) {
        System.out.println("\tFrame score is " + frameScore);
    }

    /**
     *
     * @param frame the current frame
     * @return true if it's the last frame in the game, or otherwise false
     */
    protected boolean isLastFrame(int frame) {
        return frame == maxNumberOfFrames - 1;
    }
    /**
     * Checks if the number of downed pins is equal to the max number of pins in the game.<br></br>
     * If the max number of pins have been downed in <b>one single shot</b> then it's considered a Strike.
     * @param turnIndex the current shot that it being checked
     * @return true if the shot is a Strike, or otherwise false
     */
    protected boolean isStrike(int turnIndex) {
        return turns.get(turnIndex) == maxNumberOfPins;
    }
    /**
     * Checks if the number of downed pins is equal to the max number of pins in the game.<br></br>
     * If the max number of pins have been downed in <b>two shots</b> part of the same turn then it's considered a Spare.
     * @param turnIndex the current shot that it being checked
     * @return true if the turn is a Spare, or otherwise false
     */
    protected boolean isSpare(int turnIndex) {
        return turns.get(turnIndex) + turns.get(turnIndex + 1) == maxNumberOfPins;
    }
    /**
     * It calculates the bonus value after a shot that is considered a Strike.<br></br>
     * @param turnIndex the current shot that is being checked
     * @return the total value of the bonus
     */
    protected int strikeBonus(int turnIndex) {
        if(turnIndex > turns.size() + 1)
            return turns.get(turnIndex + 1);
        return turns.get(turnIndex + 1) + turns.get(turnIndex + 2);
    }
    /**
     * It calculates the bonus value after a turn with two shots that is considered a Spare.<br></br>
     * @param turnIndex the current shot that is being checked
     * @return the total value of the bonus
     */
    protected int spareBonus(int turnIndex) {
        return turns.get(turnIndex + 2);
    }
    /**
     * It calculates the amount of downed pins after a turn that consists of two shots.
     * @param turnIndex the current shot that is being checked
     * @return the total amount of downed pins
     */
    protected int totalKnockedDownPins(int turnIndex) {
        return turns.get(turnIndex) + turns.get(turnIndex + 1);
    }
}
