import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Planet extends SpaceMass{


    public Planet(Point initLoc){
        super();
        try
        {image = ImageIO.read(new File("res/greenearth.png"));}
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mass = 3;
        location = initLoc;
        active = false;
        isBlackhole = false;
        maxDistance = 400;
        relativeDistance= new int[2];
    }
}
