package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Stephie Furom on 29/03/2016.
 */
public class Villano {
    public static final float VelY = -3f;
    public static final float VelX = 9;

    private Sprite sprite;

    private Animation animacion;
    private float tiempoAnimacion;

    private EstadoMovimiento estadoMov;
    private EstadoSalto estadoSalt;

    private static final float V0 = 40;
    private static final float G = 9.81f;
    private static final float G_2 = G/2;
    private float yInicial;
    private float tiempoVuelo;
    private float tiempoSalto;


    public Villano(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(24,48);
        animacion = new Animation(0.25f, texturaPersonaje[0][2],
                texturaPersonaje[0][1], texturaPersonaje[0][0] );
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMov = EstadoMovimiento.Caer;
        estadoSalt = EstadoSalto.Piso;
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
        float nuevaY = sprite.getY();
        switch (estadoMov) {
            case Caer:
                nuevaY += VelY;
                //if (nuevaY<=PantallaJuego.ANCHO_MUNDO-sprite.getWidth()) {
                sprite.setY(nuevaY);
                //}
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

    public void setPosicion(float x, int y) {
        sprite.setPosition(x,y);
    }

    public EstadoMovimiento getEstadoMovimiento() {
        return estadoMov;
    }

    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento) {
        this.estadoMov = estadoMovimiento;
    }

    public void setEstadoSalto(EstadoSalto estadoSalto) {
        this.estadoSalt = estadoSalto;
    }

    public void saltar() {
        if (estadoSalt==EstadoSalto.Piso) {
            tiempoSalto = 0;
            yInicial = sprite.getY();
            estadoSalt = EstadoSalto.Sube;
            tiempoVuelo = 2 * V0 / G;
        }
    }

    public EstadoSalto getEstadoSalto() {
        return estadoSalt;
    }

    public enum EstadoMovimiento {
        Inicia,
        Reposo,
        MovIzq,
        MovDer,
        Caer
    }

    public enum EstadoSalto {
        Piso,
        Sube,
        Baja,
        Caida
    }
}
