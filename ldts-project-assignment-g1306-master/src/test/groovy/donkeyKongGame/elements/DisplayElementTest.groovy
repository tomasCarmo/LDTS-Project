package donkeyKongGame.elements

import donkeyKongGame.movement.MoveMario
import donkeyKongGame.movement.NoMovement
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import spock.lang.Specification

class DisplayElementTest extends Specification{
    private static TextGraphics graphics
    private static Screen screen
    private static ArrayList<Ladder> ladders = new ArrayList<>()
    def setupSpec(){

            TerminalSize terminalSize = new TerminalSize(70, 60)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            def terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)

            graphics = screen.newTextGraphics()

    }

    def "Basic Display Mario"(){

            given:
            def mario = new Mario(0,0, new MoveMario(ladders), graphics)
            when:
            mario.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'm'
            screen.close()

    }

    def "Basic Display Pauline"(){

            given:
            def pauline = new Pauline(0, 0, new NoMovement(), graphics)

            when:
            pauline.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'q'
            screen.close()

    }

    def "Basic Display Hammer"(){

            given:
            def hammer = new Hammer(0, 0, new NoMovement(), graphics)
            when:
            hammer.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'h'
            screen.close()

    }

    def "Basic Display Ladder"(){
        try {
            given:
            def ladder = new Ladder(0, 0, new NoMovement(), graphics)
            when:
            ladder.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'l'
            screen.close()
        }catch (IOException e) {
            e.printStackTrace()
        }
    }

    def "Basic Display Platform"(){

            given:
            def platform = new Platform(0, 0, new NoMovement(), graphics)
            when:
            platform.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'p'
            screen.close()

    }
}
