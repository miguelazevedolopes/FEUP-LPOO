import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    int width,height;
    private Hero hero;
    private List<Wall> walls;

    public Arena(int w,int h){
        this.height=h;
        this.width=w;
        this.walls=createWalls();
        hero = new Hero(10, 10);
    }

    private List<Wall> createWalls() {
        List<Wall> walls=new ArrayList<>();
        for (int c=0;c<width;c++){
            walls.add(new Wall(c,0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private void moveHero(Position pos){
        if(canMove(pos))
            hero.setPosition(pos);
    }

    private boolean canMove(Position pos) {
        if(pos.getX()<width-1&&pos.getY()<height-1&&pos.getX()>0&&pos.getY()>0)
            return true;
        return false;
    }

    public void processKey(KeyStroke k){

        switch(k.getKeyType()){
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + k);
        }
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        hero.draw(graphics);
    }
}
