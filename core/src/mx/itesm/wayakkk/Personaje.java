package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Stephie Furom on 20/03/2016.
 */
public class Personaje {

    public static final float VelY = -4f;
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


    public Personaje(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(88,144);
        animacion = new Animation(0.25f,texturaPersonaje[0][4],
                texturaPersonaje[0][3], texturaPersonaje[0][2],
                texturaPersonaje[0][1], texturaPersonaje[0][0] );
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMov = EstadoMovimiento.Inicia;
        estadoSalt = EstadoSalto.Piso;
    }

    public void render(SpriteBatch batch) {
        switch (estadoMov) {
            case MovDer:
            case MovIzq:
                actualizar();
                tiempoAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(tiempoAnimacion);
                if (estadoMov==EstadoMovimiento.MovIzq) {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
            case Inicia:
            case Reposo:
                sprite.draw(batch);
                break;
        }

    }
    public void actualizar() {
        float nuevaX = sprite.getX();
        switch (estadoMov) {
            case MovDer:
                nuevaX += VelX;
                if (nuevaX<=PantallaJuego.ANCHO_MUNDO-sprite.getWidth()) {
                    sprite.setX(nuevaX);
                }
                break;
            case MovIzq:
                nuevaX -= VelX;
                if (nuevaX>=0) {
                    sprite.setX(nuevaX);
                }
                break;
        }}

    public void caer() {
        sprite.setY(sprite.getY() + VelY);
    }

    public void actualizarSalto() {
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if (tiempoSalto > tiempoVuelo / 2) {
            estadoSalt = EstadoSalto.Baja;
        }
        tiempoSalto += 10 * Gdx.graphics.getDeltaTime();
        sprite.setY(yInicial + y);
        if (y < 0) {
            sprite.setY(yInicial);
            estadoSalt = EstadoSalto.Piso;
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
        MovDer
    }

    public enum EstadoSalto {
        Piso,
        Sube,
        Baja,
        Caida
    }
}