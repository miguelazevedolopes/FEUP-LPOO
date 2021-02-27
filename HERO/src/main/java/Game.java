import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import com.googlecode.lanterna.input.KeyStroke;

public class Game {
    private Screen screen;
    private Arena arena;
    public Game(){
        Terminal terminal;
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            arena=new Arena(20,20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void processKey(KeyStroke k){
        arena.processKey(k);
    }

    private void draw(){
        screen.clear();
        arena.draw(screen.newTextGraphics());
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