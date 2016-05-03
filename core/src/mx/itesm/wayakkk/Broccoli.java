package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created by Stephie Furom on 12/04/2016.
 */

public class Broccoli {

    Random randC2 = new Random();
    float[] arr = {-1f, -2f, -2.5f,};
    public final float VelY = arr[randC2.nextInt(2)];

    private Sprite sprite;

    private float velX;

    private Animation animacion;
    private float tiempoAnimacion;

    private EstadoMovimiento estadoMov;


    public Broccoli(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(90, 100);
        animacion = new Animation(0.25f, texturaPersonaje[0][2],
                texturaPersonaje[0][1], texturaPersonaje[0][0]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMov = EstadoMovimiento.Caer;
        velX = 17;
    }

    public void render(SpriteBatch batch) {
        switch (estadoMov) {
            case Inicia:
            case Caer:
                actualizar();
                tiempoAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(tiempoAnimacion);
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
        }
    }

    public void actualizar() {
        Random rand = new Random();
        float nuevaY = sprite.getY();
        float newX=sprite.getX()+velX;
        sprite.setX(newX);
        if (sprite.getX()>=1280 || sprite.getX()<=0){
            velX=velX*-1;
        }
        Gdx.app.log("x","x="+sprite.getX());

        switch (estadoMov) {
            case Caer:
                nuevaY += VelY;
                sprite.setY(nuevaY);
                //}
                if (sprite.getY() == 0) {
                    sprite.setY(Principal.ALTO_MUNDO);
                    sprite.setX(rand.nextInt((int) Principal.ANCHO_MUNDO));
                }
                break;
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public enum EstadoMovimiento {
        Inicia,
        Caer
    }
}
