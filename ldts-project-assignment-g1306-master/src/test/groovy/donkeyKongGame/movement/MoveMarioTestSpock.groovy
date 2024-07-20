package donkeyKongGame.movement


import donkeyKongGame.elements.Ladder
import donkeyKongGame.elements.Mario
import donkeyKongGame.elements.Platform
import donkeyKongGame.elements.Position
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import spock.lang.Specification


class MoveMarioTestSpock extends Specification{
    private static TextGraphics graphics
    private static Screen screen
    private static ArrayList<Platform> platforms = new ArrayList<>()
    private static ArrayList<Ladder> ladders = new ArrayList<>()

    def setupSpec(){
        TerminalSize terminalSize = new TerminalSize(70, 60)
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
        def terminal = terminalFactory.createTerminal()
        screen = new TerminalScreen(terminal)

        graphics = screen.newTextGraphics()

        Platform p1 = new Platform(21, 45, new NoMovement(), graphics)
        Platform p2 = new Platform(22, 45, new NoMovement(), graphics)
        Platform p3 = new Platform(20, 45, new NoMovement(), graphics)
        Ladder l1 = new Ladder(21, 44, new NoMovement(), graphics)

        p1.display()
        p2.display()
        p3.display()
        platforms.add(p1)
        platforms.add(p2)
        platforms.add(p3)
        ladders.add(l1)

    }

    def "Testing Marios' Move Right"(){
        given:
        def mario = new Mario(21,44,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        KeyStroke key = new KeyStroke(KeyType.ArrowRight)
        marioMock.getPosition() >> new Position(22,44)
        when:
        mario.move(key)
        then:
        mario.getPosition() == marioMock.getPosition()
    }

    def "Testing Marios' Move Left"(){
        given:
        def mario = new Mario(21,44,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft)
        marioMock.getPosition() >> new Position(20,44)
        when:
        mario.move(key)
        then:
        mario.getPosition() == marioMock.getPosition()
    }

    def "Testing Marios' Movement Up"(){
        given:
        def mario = new Mario(21,44,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        KeyStroke key = new KeyStroke(KeyType.ArrowUp)
        marioMock.getPosition() >> new Position(21,43)
        when:
        mario.move(key)
        then:
        mario.getPosition() == marioMock.getPosition()
    }

    def "Testing Marios' Movement Down"(){
        given:
        def mario = new Mario(21,43,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        KeyStroke key = new KeyStroke(KeyType.ArrowDown)
        marioMock.getPosition() >> new Position(21,44)
        when:
        mario.move(key)
        then:
        mario.getPosition() == marioMock.getPosition()
    }

    def "Testing Marios' Movement Normal Jump Ascend"(){
        given:
        def mario = new Mario(20,44,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        def key = new KeyStroke(' ' as Character, false, false)
        marioMock.getJumpStrength() >> 0
        marioMock.getJumpState() >> true
        marioMock.getPosition() >> new Position(20,41)
        when:
        mario.move(key)
        mario.move(null)
        then:
        mario.getPosition() == marioMock.getPosition()
        mario.getJumpStrength() == marioMock.getJumpStrength()
        mario.getJumpState() == marioMock.getJumpState()
    }

    def "Testing Marios' Movement Normal Jump Descend"(){
        given:
        def mario = new Mario(20,44,new MoveMario(ladders),graphics)
        def marioMock = Mock(Mario)
        def key = new KeyStroke(' ' as Character, false, false)
        marioMock.getJumpStrength() >> 3
        marioMock.getJumpState() >> false
        marioMock.getPosition() >> new Position(20,44)
        when:
        mario.move(key)
        mario.move(null)
        mario.move(null)
        mario.move(null)
        mario.move(null)
        mario.move(null)
        then:
        mario.getPosition() == marioMock.getPosition()
        mario.getJumpStrength() == marioMock.getJumpStrength()
        mario.getJumpState() == marioMock.getJumpState()
    }

    def "Testing Marios' can move Right"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(21,44,moveStrategy,graphics)

        when:
        mario.getDirection().setDirectionID(0)
        then:
        moveStrategy.canMove(mario)
    }

    def "Testing Marios' can move Left"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(21,44,moveStrategy,graphics)

        when:
        mario.getDirection().setDirectionID(2)
        then:
        moveStrategy.canMove(mario)
    }
    def "Testing Marios' can move Up"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(21,44,moveStrategy,graphics)

        when:
        mario.getDirection().setDirectionID(3)
        then:
        moveStrategy.canMove(mario)
    }

    def "Testing Marios' can move Down"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(24,44,moveStrategy,graphics)

        when:
        mario.getDirection().setDirectionID(1)
        then:
        moveStrategy.canMove(mario)
    }

    def "Testing Marios' can move no direction"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(21,44,moveStrategy,graphics)

        when:
        mario.getDirection().setDirectionID(4)
        then:
        moveStrategy.canMove(mario)
    }

    def "Testing Marios' move function"(){
        given:
        def moveStrategy = new MoveMario(ladders)
        def mario = new Mario(21,44, moveStrategy as MoveStrategy,graphics)
        def mockMario = Mock(Mario)
        mockMario.getPosition() >> new Position(22,44)

        when:
        mario.move(new KeyStroke(KeyType.ArrowRight))
        mario.move(new KeyStroke(KeyType.ArrowLeft))
        mario.move(new KeyStroke(KeyType.ArrowDown))
        mario.move(new KeyStroke(KeyType.ArrowUp))
        mario.move(new KeyStroke(KeyType.ArrowDown))
        mario.move(new KeyStroke(KeyType.ArrowRight))

        then:
        mario.getPosition() == mockMario.getPosition()
    }
}