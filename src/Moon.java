import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Moon extends SpaceMass {


    public Moon(Point initLoc){
        super();
        try
        {image = ImageIO.read(new File("res/redmoon.png"));}
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mass = 2;
        location = initLoc;
        active = false;
        isBlackhole = false;
        maxDistance = 400;
        relativeDistance= new int[2];

    }
}
