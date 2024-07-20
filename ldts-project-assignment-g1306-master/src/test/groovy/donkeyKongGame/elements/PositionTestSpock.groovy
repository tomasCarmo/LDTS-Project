package donkeyKongGame.elements

import spock.lang.Specification

class PositionTestSpock extends Specification{

    def "Testing Move Up"(){
        given:
            def position = new Position(0,0)
        when:
            position.moveUp()
        then:
            position.getY() == -1
    }

    def "Testing Move Right"(){
        given:
        def position = new Position(0,0)
        when:
        position.moveRight()
        then:
        position.getX() == 1
    }

    def "Testing Move Down"(){
        given:
        def position = new Position(0,0)
        when:
        position.moveDown()
        then:
        position.getY() == 1
    }

    def "Testing Move Left"(){
        given:
        def position = new Position(0,0)
        when:
        position.moveLeft()
        then:
        position.getX() == -1
    }

}
