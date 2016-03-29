package mx.itesm.wayakkk;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;


/**
 * Created by Stephie Furom on 29/03/2016.
 */
public class Villano {
    Random randC2 = new Random();
    float[] arr = {-2f,-3f,-4f,-5f,-6f,-7f};
    public final float VelY = arr[randC2.nextInt(5)] ;


    private Sprite sprite;

    private Animation animacion;
    private float tiempoAnimacion;

    private EstadoMovimiento estadoMov;


    public Villano(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(24,48);
        animacion = new Animation(0.25f, texturaPersonaje[0][2],
                texturaPersonaje[0][1], texturaPersonaje[0][0] );
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMov = EstadoMovimiento.Caer;
    }

    public void render(SpriteBatch batch) {
        switch (estadoMov) {
            case Inicia:
            case Caer:
                actualizar();
                sprite.draw(batch);
                break;
        }

    }
    public void actualizar() {
        Random rand = new Random();
        float nuevaY = sprite.getY();
        switch (estadoMov) {
            case Caer:
                nuevaY += VelY;
                //if (nuevaY<=PantallaJuego.ANCHO_MUNDO-sprite.getWidth()) {
                sprite.setY(nuevaY);
                //}
                if(sprite.getY()==0){
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
