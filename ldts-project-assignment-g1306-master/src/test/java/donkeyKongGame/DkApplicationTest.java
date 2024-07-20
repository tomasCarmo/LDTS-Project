package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.mockito.Mockito.*;

public class DkApplicationTest {


    @Test
    public void changeStateTest(){
        DkArena dkArena = Mockito.mock(DkArena.class);
        DkApplication dkApplication = new DkApplication();
        ApplicationState state = new PlayState(dkApplication, dkArena,0,0);
        dkApplication.changeState(state);
        Assertions.assertEquals(dkApplication.getState(), state);
    }

    @Test
    public void runTest() throws IOException, InterruptedException {
        Screen screen = Mockito.mock(Screen.class);
        DkApplication dkApplication = new DkApplication(null);
        MenuState state = new MenuState(dkApplication);
        dkApplication.changeState(state);
        dkApplication.screen = screen;
        when(screen.newTextGraphics()).thenReturn(mock(TextGraphics.class));
        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
        dkApplication.run();
        Assertions.assertNull(dkApplication.getState());
    }


}
