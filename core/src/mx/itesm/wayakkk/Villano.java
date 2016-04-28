package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
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
    private Sprite spriteGrande;

    private Animation animacion;
    private float tiempoAnimacion;

    private Animation animacionGrande;
    private float tiempoAnimacionGrande;

    private EstadoMovimiento estadoMov;


    public Villano(Texture textura, Texture texturaPayasote) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(70,78);
        animacion = new Animation(0.25f, texturaPersonaje[0][2],
                texturaPersonaje[0][1], texturaPersonaje[0][0] );
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);

        TextureRegion texturaCompleta1 = new TextureRegion(texturaPayasote);
        TextureRegion[][] texturaPersonaje1 = texturaCompleta1.split(583,650);
        animacionGrande = new Animation(0.25f, texturaPersonaje1[0][2],
                texturaPersonaje1[0][1], texturaPersonaje1[0][0] );
        animacionGrande.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacionGrande = 0;
        spriteGrande = new Sprite(texturaPersonaje1[0][0]);
        estadoMov = EstadoMovimiento.Caer;
    }

    public void finalizar() {
        estadoMov = EstadoMovimiento.Finalizando;
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
            case Finalizando:
                actualizar();
                tiempoAnimacion += Gdx.graphics.getDeltaTime();
                region = animacionGrande.getKeyFrame(tiempoAnimacion);
                batch.draw(region, spriteGrande.getX(), spriteGrande.getY());
                Gdx.app.log("Render","Finalizando");
                break;
        }
    }
    public void actualizar() {
        Random rand = new Random();
        float nuevaY = spriteGrande.getY();
        if (estadoMov==EstadoMovimiento.Caer){
            nuevaY = sprite.getY();
        }
        switch (estadoMov) {
            case Caer:
            case Finalizando:
                nuevaY += VelY;
                //if (nuevaY<=PantallaJuego.ANCHO_MUNDO-sprite.getWidth()) {
                sprite.setY(nuevaY);
                spriteGrande.setY(nuevaY);
                //}
                if (sprite.getY()<=0|| spriteGrande.getY()<=0){
                    int nuevaX = rand.nextInt((int) Principal.ANCHO_MUNDO);
                    sprite.setY(Principal.ALTO_MUNDO);
                    sprite.setX(nuevaX);
                    spriteGrande.setY(Principal.ALTO_MUNDO);
                    spriteGrande.setX(nuevaX);
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

    public Sprite getSpriteGrande() {
        return spriteGrande;
    }

    public enum EstadoMovimiento {
        Inicia,
        Caer,
        Finalizando
    }
}
