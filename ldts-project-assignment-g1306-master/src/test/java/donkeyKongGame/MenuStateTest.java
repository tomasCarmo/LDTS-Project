package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MenuStateTest {

    @Test
    public void drawMenuStateTest(){
        DkApplication dkApplication = new DkApplication();
        MenuState menu = new MenuState(dkApplication);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        menu.draw(graphicsMock);
        verify(graphicsMock,times(19)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    public void readInputTest() throws IOException {
        DkApplication dkApplication = mock(DkApplication.class);
        MenuState menuState = new MenuState(dkApplication);
        KeyStroke key = new KeyStroke(KeyType.Enter);
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screen.readInput()).thenReturn(key);
        KeyStroke returnedKey = menuState.readInput(screen);
        Assertions.assertEquals(returnedKey, key);

        key = new KeyStroke(KeyType.Escape);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.readInput()).thenReturn(key);
        returnedKey = menuState.readInput(screen);
        Assertions.assertEquals(returnedKey, key);

        key = new KeyStroke(KeyType.EOF);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.readInput()).thenReturn(key);
        returnedKey = menuState.readInput(screen);
        Assertions.assertEquals(returnedKey, key);
    }

    @Test
    public void runTest() throws IOException, InterruptedException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        KeyStroke key = new KeyStroke(KeyType.Enter);
        DkApplication dkApplication = mock(DkApplication.class);
        MenuState menu = Mockito.spy(new MenuState(dkApplication));

        when(screen.readInput()).thenReturn(key);
        doNothing().when(dkApplication).run();

        menu.run(screen);

        verify(dkApplication, times(1)).changeState(any(ApplicationState.class));


        key = new KeyStroke(KeyType.Escape);
        menu = Mockito.spy(new MenuState(dkApplication));
        when(menu.readInput(screen)).thenReturn(key);

        menu.run(screen);


        key = new KeyStroke(KeyType.EOF);
        menu = Mockito.spy(new MenuState(dkApplication));
        when(menu.readInput(screen)).thenReturn(key);

        menu.run(screen);

        verify(screen, times(2)).close();
    }
}
