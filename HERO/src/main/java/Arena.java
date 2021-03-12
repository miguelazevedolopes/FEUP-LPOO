import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    int width,height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int w,int h){
        this.height=h;
        this.width=w;
        this.walls=createWalls();
        this.coins=createCoins();
        this.monsters=createMonster();
        hero = new Hero(10, 10);
    }

    private List<Wall> createWalls() {
        List<Wall> walls=new ArrayList<>();
        for (int c=0;c<width;c++){
            walls.add(new Wall(c,0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height-1 ; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Monster> createMonster() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }
    private void moveMonsters(){
        for(Monster monster:monsters){
            Position pos=monster.move();
            if(canMove(pos))
                monster.setPosition(pos);
        }
    }

    private void moveHero(Position pos){
        if(canMove(pos)) {
            hero.setPosition(pos);
            retrieveCoins(pos);
            moveMonsters();
        }
    }

    private boolean canMove(Position pos) {
       for(int i=0;i<walls.size();i++) {
           if (walls.get(i).getPosition().equals(pos)) {
               return false;
           }
       }
        return true;
    }
    private void retrieveCoins(Position pos){
        for(Coin coin:coins){
            if(hero.position.equals(coin.position)){
                coins.remove(coin);
                break;
            }
        }
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
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for( Coin coin: coins) {
            coin.draw(graphics);
        }
        for( Monster monster: monsters) {
            monster.draw(graphics);
        }
        hero.draw(graphics);
    }
}
