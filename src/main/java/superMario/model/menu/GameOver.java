package superMario.model.menu;

import superMario.Game;
import superMario.controller.command.buttonCommand.ExitCommand;
import superMario.controller.command.buttonCommand.RestartCommand;
import superMario.gui.FileReader;
import superMario.model.Position;

import java.io.IOException;

public class GameOver extends Menu{

    public GameOver(Game game) throws IOException {
        super(game);
        options.add(new Button(new FileReader().readFile("./src/main/resources/textFiles/gameOver/playAgain.txt"), new Position(20, 90), new Position(220, 60),  "#00cccc","#0062d8", "#000000"));
        options.add(new Button(new FileReader().readFile("./src/main/resources/textFiles/menu/exit.txt"), new Position(20, 50), new Position(220, 20), "#00cccc","#0062d8", "#000000"));
        options.get(0).setAction(new RestartCommand(game));
        options.get(1).setAction(new ExitCommand(game));
        options.get(0).setActive(true);
    }
}
