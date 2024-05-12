import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

public class bowlingGameTest extends TestCase {
    private Game game;
    protected void setUp() throws Exception {
        game = new Game();
    }

    private void rollMany(int n, int pins) {
        for(int i=0; i<n; i++) {
            game.roll(pins);
        }
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare(int f, int s) {
        game.roll(f);
        game.roll(s);
    }

    public void test_should_returns_a_score_of_0_when_no_knock_down() {
        rollMany(20,0);

        Assertions.assertEquals(game.score(), 0);
    }

    public void test_should_returns_a_score_of_5_when_5_knock_down() {
        game.roll(1);
        game.roll(4);
        rollMany(18, 0);

        Assertions.assertEquals(game.score(), 5);

    }

    public void test_should_returns_a_score_of_14_when_5_plus_9_knock_down() {
        game.roll(1);
        game.roll(4);
        game.roll(4);
        game.roll(5);
        rollMany(16, 0);

        Assertions.assertEquals(game.score(), 14);
    }

    public void test_should_return_a_score_of_43_when_there_is_a_spare_in_the_game_session() {
        // Frame 1
        game.roll(1);
        game.roll(4);

        // Frame 2
        game.roll(4);
        game.roll(5);

        // Frame 3
        rollSpare(6,4);

        // Frame 4
        rollSpare(5,5);

        // Frame 5
        game.roll(1);
        game.roll(2);

        rollMany(10, 0);

        Assertions.assertEquals(game.score(), 43);

    }

    public void test_should_return_a_score_of_71_when_there_is_a_strike_in_the_game_session() {
        // Frame 1
        game.roll(1);
        game.roll(4);

        // Frame 2
        game.roll(4);
        game.roll(5);

        // Frame 3
        rollSpare(6,4);

        // Frame 4
        rollSpare(5,5);

        // Frame 5
        game.roll(1);
        game.roll(2);

        // Frame 6
        rollStrike();

        // Frame 7
        game.roll(3);
        game.roll(6);

        rollMany(6,0);

        Assertions.assertEquals(game.score(), 71);

    }

    public void test_should_returns_133() {
        // Frame 1
        game.roll(1);
        game.roll(4);

        // Frame 2
        game.roll(4);
        game.roll(5);

        // Frame 3
        rollSpare(6,4);

        // Frame 4
        rollSpare(5,5);

        // Frame 5
        game.roll(10);

        // Frame 6
        game.roll(0);
        game.roll(1);

        // Frame 7
        rollSpare(7,3);

        // Frame 7
        rollSpare(6,4);

        // Frame 7
        rollStrike();

        // Frame 7
        rollSpare(2,8);
        game.roll(6);


        Assertions.assertEquals(game.score(), 133);

    }
}



/*
RULES :
 1 - The game consists of 10 frames as shown above.  In each frame the player has
     two opportunities to knock down 10 pins.  The score for the frame is the total
     number of pins knocked down, plus bonuses for strikes and spares.
 2 - The score for the frame is the total number of pins knocked down, plus bonuses for strikes and spares.
 3 - A spare is when the player knocks down all 10 pins in two tries. The bonus for
     that frame is the number of pins knocked down by the next roll
 4 - A strike is when the player knocks down all 10 pins on his first try.  The bonus
     for that frame is the value of the next two balls rolled.
 5 - In the tenth frame a player who rolls a spare or strike is allowed to roll the extra
     balls to complete the frame.  However no more than three balls can be rolled in
     tenth frame.
 */
