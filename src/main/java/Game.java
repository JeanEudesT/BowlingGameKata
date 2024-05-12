import java.util.ArrayList;
import java.util.List;

public class Game {

    private int SPARE = 10;
    private int STRIKE = 10;
    private List<Integer> rollList = new ArrayList<>();

    public void roll(Integer pins) {
        rollList.add(pins);
    }

    public int score() {
        List<Integer> framesScore = new ArrayList<>();
        int frameIndex = 0;
        for (int frame = 0; frame<10; frame++) {
            if (isStrike(frameIndex)) {
                framesScore.add(STRIKE + strikeBonus(frameIndex));
                frameIndex = frameIndex + 1;
            } else if (isSpare(frameIndex)) {
                framesScore.add(SPARE + spareBonus(frameIndex));
                frameIndex = frameIndex + 2;
            } else {
                framesScore.add(sumBallsInFrame(frameIndex));
                frameIndex = frameIndex + 2;
            }
        }

        return framesScore.stream().reduce(0, (Integer a, Integer b) -> a + b);
    }

    private int sumBallsInFrame(int frameIndex) {
        return rollList.get(frameIndex) + rollList.get(frameIndex + 1);
    }

    private int strikeBonus(int frameIndex) {
        return rollList.get(frameIndex + 1) + rollList.get(frameIndex + 2);
    }

    private Integer spareBonus(int frameIndex) {
        return rollList.get(frameIndex + 2);
    }

    private boolean isSpare(int frameIndex) {
        return (rollList.get(frameIndex) + rollList.get(frameIndex + 1)) == SPARE;
    }

    private boolean isStrike(int frameIndex) {
        return rollList.get(frameIndex) == STRIKE;
    }

}

//              5    14   29   40   43  $
// rollList = [1,4, 4,5, 6,4, 5,5, 1,2, 10, 3,6] OK
// first iteration -> i = 0 -> [i] = 1 OK
// check current_frame = roll[i] + roll[i+1] OK
// if current_frame == spare -> check next roll -> current_frame += roll[i+2] OK
// if current_frame != spare -> save the result then go to the next iteration OK
// if current_frame == strike -> check next two rolls -> current_frame += roll[i+1] + roll[i+2] OK
// after the last iteration, sum all frame score OK

// * = SPARE
// $ = STRIKE













