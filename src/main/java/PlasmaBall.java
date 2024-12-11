import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class PlasmaBall extends PApplet {
    public static void main(String[] args) {
        PApplet.main(PlasmaBall.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        blendMode(BLEND_MODE);
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        for (int angularFactor = MINIMUM_ANGULAR_FACTOR; angularFactor < MAXIMUM_ANGULAR_FACTOR; angularFactor++) {
            float maxLength = min(WIDTH, HEIGHT) * (MAX_LENGTH_EXPECTANCY + MAX_LENGTH_VARIANCE * randomGaussian());
            for (float angle = 0; angle < TWO_PI; angle += ANGULAR_STEP) {
                PVector p = new PVector(WIDTH / 2f, HEIGHT / 2f);
                for (int k = 0; k < maxLength; k++) {
                    point(p.x, p.y);
                    p.add(PVector.fromAngle(angle + TWO_PI * (0.5f - floor(angularFactor * noise(p.x * NOISE_SCALE, p.y * NOISE_SCALE)) / (float) angularFactor))
                            .mult(NOISE_FORCE));
                }
            }
        }

        saveSketch(this);
    }
}
