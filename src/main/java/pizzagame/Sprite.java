package pizzagame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Sprite {
    private final String pathId;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public Sprite(String pathId, int width, int height) {
        this.pathId = pathId;
        this.width = width;
        this.height = height;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResource(pathId)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPathId() {
        return pathId;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

