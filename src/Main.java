import javax.swing.*;

public class Main {

    private int width = 800;
    private int height = 600;
    private boolean running;
    private Interface outerSpace;

    public void init() {
        outerSpace = new Interface();

        running = true;
        outerSpace.setSize(width, height);
        outerSpace.setLocationRelativeTo(null);

        outerSpace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outerSpace.setVisible(true);
        loop();
    }

    private void loop() {

        long lastTime = System.nanoTime();
        long lastFpsTime = 0;
        int fps = 0;
        int skip = 0;

        while (running) {
            long now = System.nanoTime();
            long update = now - lastTime;
            lastTime = now;
            lastFpsTime += update;
            fps++;
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            render();

            if (lastFpsTime >= 1000000000) {
                outerSpace.updateFps(fps);
                lastFpsTime = 0;
                fps = 0;
            }

            if (skip == 2) {
                update();
                skip = 0;
            }
            skip++;

            try {
                Thread.sleep((lastTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (Exception e) {
                System.out.println("Game loop error");
            }
        }
    }

    private void update() {
        outerSpace.update();
    }


    private void render() {outerSpace.render(); }


    public static void main(String[] args) {
        new Main().init();
    }

}
