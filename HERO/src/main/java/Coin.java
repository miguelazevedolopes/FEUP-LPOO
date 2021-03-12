import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    public Coin(int x,int y){
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1, 1), 'O');
    }
}
