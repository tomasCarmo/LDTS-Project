package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PlayStateTest {

    @Test
    public void drawPlayStateTest(){
        DkArena dkArena = Mockito.mock(DkArena.class);
        TextGraphics graphics = mock(TextGraphics.class);
        DkApplication dkApplication = mock(DkApplication.class);
        PlayState play = new PlayState(dkApplication,dkArena,0,0);
        play.draw(graphics);
        verify(dkArena, times(1)).draw();
    }


    @Test
    public void readInputPlayTest() throws IOException {

        DkApplication dkApplication = mock(DkApplication.class);
        DkArena dkArena = Mockito.mock(DkArena.class);
        PlayState play = new PlayState(dkApplication,dkArena,0,0);
        Screen screen = mock(Screen.class);
        play.readInput(screen);
        verify(screen,times(1)).pollInput();

    }

    @Test
    public void runPlayTestIfEOF() throws IOException, InterruptedException {
        Screen screen = mock(Screen.class);
        DkArena dkArena = Mockito.mock(DkArena.class);

        DkApplication dkApplication = mock(DkApplication.class);
        PlayState playState = new PlayState(dkApplication,dkArena,0,0);
        KeyStroke key = new KeyStroke(KeyType.EOF);
        when(playState.readInput(screen)).thenReturn(key);
        playState.run(screen);
        verify(screen,times(1)).close();


    }


    @Test
    public void runPlayTestIfOtherKey() throws IOException, InterruptedException {
        Screen screen = mock(Screen.class);
        DkArena dkArena = Mockito.mock(DkArena.class);

        DkApplication dkApplication = mock(DkApplication.class);
        PlayState playState = new PlayState(dkApplication,dkArena,0,0);
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        when(playState.readInput(screen)).thenReturn(key).thenReturn(new KeyStroke(KeyType.EOF));
        playState.run(screen);
        verify(screen,atLeastOnce()).clear();
        verify(dkArena,atLeastOnce()).run(key);
        verify(screen,atLeastOnce()).refresh();




    }

}
