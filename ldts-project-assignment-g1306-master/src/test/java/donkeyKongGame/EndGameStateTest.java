package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.*;

public class EndGameStateTest {

    @Test
    public void drawTestWon(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        EndGameState win = new EndGameState(new DkApplication(),700,true);
        win.setLeaderboard(new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt"));
        win.draw(graphics);

        Mockito.verify(graphics,Mockito.times(1)).putString(anyInt(),anyInt(), anyString());
    }

    @Test
    public void drawTestLost(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        EndGameState lost = new EndGameState(new DkApplication(),700,false);
        lost.setLeaderboard(new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt"));
        lost.draw(graphics);

        Mockito.verify(graphics,Mockito.times(2)).putString(anyInt(),anyInt(), anyString());
    }

    @Test
    public void readInputTest() throws IOException {
        Screen screen = Mockito.mock(Screen.class);

        EndGameState win = new EndGameState(new DkApplication(),700,true);
        win.setLeaderboard(new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt"));
        KeyStroke key = KeyStroke.fromString("a");
        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("-"),key);

        Assertions.assertEquals(key, win.readInput(screen));
    }

    @Test
    public void runTest() throws IOException, InterruptedException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        DkApplication dkApplication = Mockito.mock(DkApplication.class);
        EndGameState endGameState = new EndGameState(dkApplication, 700, true);
        endGameState.setLeaderboard(new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt"));

        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("N"),KeyStroke.fromString("A"),KeyStroke.fromString("M"),KeyStroke.fromString("E"),KeyStroke.fromString("5"), new KeyStroke(KeyType.Enter));
        Mockito.doNothing().when(dkApplication).run();

        ArrayList<ArrayList<Object>> expectedLeaderboard = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME5", 700)),
                        new ArrayList<>(Arrays.asList("NAME3", 500))

                ));

        endGameState.run(screen);
        Assertions.assertEquals(expectedLeaderboard, endGameState.getLeaderboard().leaderboardList);
        Mockito.verify(dkApplication).changeState(any(ApplicationState.class));

        endGameState.getLeaderboard().leaderboardList = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("NAME1", 1000)),
                        new ArrayList<>(Arrays.asList("NAME2", 800)),
                        new ArrayList<>(Arrays.asList("NAME3", 500)),
                        new ArrayList<>(Arrays.asList("NAME4", 100))

                ));
        endGameState.getLeaderboard().writeInFile();
    }

    @Test
    public void readNameTest() throws IOException {

        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        EndGameState endGameState = new EndGameState(new DkApplication(), 700, false);
        endGameState.setLeaderboard(new Leaderboard("src/test/java/DonkeyKongGame/leaderboardTest.txt"));

        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("N"),KeyStroke.fromString("A"),KeyStroke.fromString("M"),KeyStroke.fromString("E"),KeyStroke.fromString("1"));
        Assertions.assertEquals("NAME1", endGameState.readName(screen));

        Mockito.when(endGameState.readInput(screen)).thenReturn(KeyStroke.fromString("N")).thenReturn(new KeyStroke(KeyType.Enter));
        Assertions.assertEquals("N    ",endGameState.readName(screen));

        Mockito.when(endGameState.readInput(screen)).thenReturn(KeyStroke.fromString("N")).thenReturn(KeyStroke.fromString("A")).thenReturn(KeyStroke.fromString("M")).thenReturn(KeyStroke.fromString("E")).thenReturn(KeyStroke.fromString("1")).thenReturn(KeyStroke.fromString("2"));
        Assertions.assertEquals("NAME1",endGameState.readName(screen));

    }
}
