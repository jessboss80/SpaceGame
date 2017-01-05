import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Star extends SpaceMass {


    public Star(Point initLoc){
        super();
        try
        {image = ImageIO.read(new File("res/sun.png"));}
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mass = 6;
        location = initLoc;
        active = false;
        isBlackhole = false;
        maxDistance = 400;
        relativeDistance= new int[2];
    }

}
