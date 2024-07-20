package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class LeaderboardTest {


    Leaderboard leaderboard = new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt");
    static ArrayList<ArrayList<Object>> expectedLeaderboard = new ArrayList<>();
    @BeforeAll
    public static void setUp(){
        expectedLeaderboard = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME5", 700)),
                        new ArrayList<>(Arrays.asList("NAME3", 500))

                ));
    }


    public void restoreChanges() throws IOException {
        leaderboard.leaderboardList = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME3", 500)),
                        new ArrayList<>(Arrays.asList("NAME4", 100))

                ));
        leaderboard.writeInFile();
    }

    @Test
    public void goesToLeaderboardTest(){
        Assertions.assertTrue(leaderboard.goesToLeaderboard(700));
        Assertions.assertFalse(leaderboard.goesToLeaderboard(100));
    }

    @Test
    public void updateFileTest() throws IOException {

        leaderboard.updateFile("NAME5",700);
        Assertions.assertEquals(expectedLeaderboard, leaderboard.leaderboardList);

        leaderboard.leaderboardList = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME3", 500))

                ));
        leaderboard.updateFile("NAME5",700);
        Assertions.assertEquals(expectedLeaderboard, leaderboard.leaderboardList);

        restoreChanges();
    }


    @Test
    public void writeInFileTest() throws IOException {
        leaderboard.leaderboardList = expectedLeaderboard;
        leaderboard.writeInFile();
        leaderboard.readLeaderboard();

        Assertions.assertEquals(leaderboard.leaderboardList, expectedLeaderboard);

        restoreChanges();
    }

    @Test
    public void readLeaderboardTest(){
        ArrayList<ArrayList<Object>> expectedLeaderboard = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME3", 500)),
                        new ArrayList<>(Arrays.asList("NAME4", 100))

                ));

        Assertions.assertEquals(expectedLeaderboard, leaderboard.leaderboardList);

    }

    @Test
    public void displayLeaderTest(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        leaderboard.displayLeaderboard(graphics);

        Mockito.verify(graphics, Mockito.times((leaderboard.leaderboardList.size() * 3)+3)).putString(anyInt(), anyInt(), anyString());
    }
}
