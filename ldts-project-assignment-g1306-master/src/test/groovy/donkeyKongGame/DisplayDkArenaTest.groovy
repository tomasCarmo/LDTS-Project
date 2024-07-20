package donkeyKongGame

import donkeyKongGame.elements.Platform
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import donkeyKongGame.movement.NoMovement
import spock.lang.Specification

class DisplayDkArenaTest extends Specification{
    private static TextGraphics graphics
    private static Screen screen
    private static ArrayList<Platform> platforms = new ArrayList<>()

    def setupSpec(){

            TerminalSize terminalSize = new TerminalSize(70, 60)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            def terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)

            graphics = screen.newTextGraphics()

    }

    def "Basic Display DkArena"() {

            given:
            def dkArena = new DkArena(graphics)
            when:
            dkArena.draw()
            then:

            graphics.getCharacter(24, 14).getCharacterString() == 'd'
            graphics.getCharacter(25, 44).getCharacterString() == 'm'
            graphics.getCharacter(30, 10).getCharacterString() == 'q'


            //Check for ladder individually
            for (int i = 0; i < 5; i++) {
                if (i != 2) {
                    graphics.getCharacter(29, 40 + i).getCharacterString() == 'l'
                    graphics.getCharacter(30, 16 + i).getCharacterString() == 'l'
                }
                if (i != 1) {
                    graphics.getCharacter(27, 28 + i).getCharacterString() == 'l'
                    if (i != 2) {
                        graphics.getCharacter(40, 22 + i).getCharacterString() == 'l'
                    }
                }
                graphics.getCharacter(42, 40 + i).getCharacterString() == 'l'
                graphics.getCharacter(25, 34 + i).getCharacterString() == 'l'
                graphics.getCharacter(31, 34 + i).getCharacterString() == 'l'
                graphics.getCharacter(33, 28 + i).getCharacterString() == 'l'
                graphics.getCharacter(42, 28 + i).getCharacterString() == 'l'
                graphics.getCharacter(25, 22 + i).getCharacterString() == 'l'
                graphics.getCharacter(28, 22 + i).getCharacterString() == 'l'
                graphics.getCharacter(42, 16 + i).getCharacterString() == 'l'

                if(i < 4) graphics.getCharacter(34,14-i).getCharacterString() == 'l'
                graphics.getCharacter(29,14-i).getCharacterString() == 'l'
                graphics.getCharacter(28,14-i).getCharacterString() == 'l'
            }

            //Check for platforms
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 26; j++) {
                    if (i % 2 == 0 || i == 5) {
                        graphics.getCharacter(21 + j, 15 + (6 * i)).getCharacterString() == 'p'
                    } else {
                        graphics.getCharacter(23 + j, 15 + (6 * i)).getCharacterString() == 'p'
                    }
                }
            }
            graphics.getCharacter(21 + 26, 15 + 30).getCharacterString() == 'p'
            graphics.getCharacter(21 + 27, 15 + 30).getCharacterString() == 'p'
            for(int i=0; i<6;i++)
                platforms.add(new Platform(30+i,11,new NoMovement(),graphics))


            graphics.getCharacter(50,5).getCharacterString() == '0'
            graphics.getCharacter(10,5).getCharacterString() == '0'
            graphics.getCharacter(10,7).getCharacterString() == 'B'
            graphics.getCharacter(10,8).getCharacterString() == '5'
            screen.close()

    }
}
