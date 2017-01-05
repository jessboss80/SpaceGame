import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Blackhole extends SpaceMass {


    public Blackhole(Point initLoc){
        super();
        try
        {image = ImageIO.read(new File("res/black_hole.png"));}
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mass = 7;
        location = initLoc;
        active = true;
        isBlackhole = true;
        speed = new int[]{0, 0};
        relativeDistance= new int[2];
    }
}
