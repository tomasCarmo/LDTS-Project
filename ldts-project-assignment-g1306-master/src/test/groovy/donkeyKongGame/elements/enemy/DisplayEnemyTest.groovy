package donkeyKongGame.elements.enemy

import donkeyKongGame.elements.OilBarrel
import donkeyKongGame.movement.MoveFireBall
import donkeyKongGame.movement.MoveNormalBarrel
import donkeyKongGame.movement.NoMovement
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import spock.lang.Specification

class DisplayEnemyTest extends Specification{
    private static TextGraphics graphics
    private static Screen screen

    def setupSpec(){

            TerminalSize terminalSize = new TerminalSize(70, 60)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            def terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)

            graphics = screen.newTextGraphics()

    }

    def "Basic Display Donkey Kong"(){

            given:
            def dk = new DonkeyKong(0, 0, new NoMovement(), graphics)
            when:
            dk.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'd'
            screen.close()

    }

    def "Basic Display Barrel"(){

            given:
            def barrel = new Barrel(0, 0, new MoveNormalBarrel(), graphics)
            when:
            barrel.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'b'
            screen.close()

    }

    def "Basic Display FireBall"() {

            given:
            def fireball = new FireBall(0, 0, new MoveFireBall(null), graphics)
            when:
            fireball.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'r'
            screen.close()

    }

    def "Basic Display Oil Barrel"(){

            given:
            def oilBarrel = new OilBarrel(0, 0, new NoMovement(), graphics)
            when:
            oilBarrel.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'o'
            screen.close()

    }

    def "Basic Display Blue Barrel"(){

            given:
            def barrel = new BlueBarrel(0, 0, new MoveNormalBarrel(), graphics)
            when:
            barrel.display()
            then:
            graphics.getCharacter(0, 0).getCharacterString() == 'f'
            screen.close()

    }
}
