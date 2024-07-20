package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public interface ApplicationState {
    void draw(TextGraphics graphics);
    void run(Screen screen) throws IOException, InterruptedException;
    KeyStroke readInput(Screen screen) throws IOException;
}
