import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import com.googlecode.lanterna.input.KeyStroke;

public class Game {
    private Screen screen;
    private Hero hero;

    public Game(){
        Terminal terminal;
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            hero = new Hero(10, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void processKey(KeyStroke k){

        switch(k.getKeyType()){
            case ArrowUp:
                hero.moveUp();
                break;
            case ArrowDown:
                hero.moveDown();
                break;
            case ArrowRight:
                hero.moveRight();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + k);
        }
    }
    private void draw(){
        screen.clear();
        hero.draw(screen);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        while(true) {
            draw();
            KeyStroke key = null;
            try {
                key = screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            processKey(key);
        }
    }
}