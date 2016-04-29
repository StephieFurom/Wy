package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Random;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p

public class PantallaJuego implements Screen {

    public static final float ANCHO_MUNDO = 1280;
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaFondoU;
    private Sprite spriteFondoU;

    private Texture texturaFondoC;
    private Sprite spriteFondoC;

    public int puntos;
    public int vidas = 3;

    private Texto texto;

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;

    private Texture texturaMael;
    private Personaje Mael;

    private Texture texturaPaleta;
    private Objetos paleta;

    private Texture texturaPayaso;
    private Villano payaso;

    private Texture texturaPayasote;

    private Texture texturaHelado;
    private Vidas helado;

    private Texture texturaPato;
    private Pato patito;

    private Texture texturaGanaste;
    private Sprite spriteGanaste;

    private Texture texturaBtnResume;
    private Sprite spriteBtnResume;

    private Texture texturaBtnQuit;
    private Sprite spriteBtnQuit;

    private Texture texturaVida;
    private Sprite vidaUno;
    private Sprite vidaDos;
    private Sprite vidaTres;

    private SpriteBatch batch;
    private Object EstadoMovimiento;
    private EstadosJuego estadoJuego;

    private Music musicaJuego;

    private Sound sonidoAtrapa;
    private Sound sonidoMalo;

    public PantallaJuego(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        crearObjetos();
        cargarTexturasSprites();
        cargarAudio();
        estadoJuego = EstadosJuego.Jugando;
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void cargarAudio() {
        musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
        musicaJuego.setLooping(true);
        if (PantallaMenu.musica == true)
            musicaJuego.play();
    }

    private void crearObjetos() {
        texto = new Texto();
        Random rand = new Random();
        AssetManager assetManager = principal.getAssetManager();
        texturaMael = new Texture(Gdx.files.internal("SpriteCa.png"));
        Mael = new Personaje(texturaMael);
        Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO * 0.10f);

        texturaPaleta = new Texture(Gdx.files.internal("SPRITESNUEVOS.png"));
        paleta = new Objetos(texturaPaleta);
        paleta.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaPayaso = new Texture(Gdx.files.internal("payasin.png"));
        texturaPayasote = new Texture(Gdx.files.internal("payasote.png"));
        payaso = new Villano(texturaPayaso, texturaPayasote);
        payaso.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaHelado = new Texture(Gdx.files.internal("heladosprite.png"));
        helado = new Vidas(texturaHelado);
        helado.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vidabn.png"));
        vidaUno = new Sprite(texturaVida);
        vidaUno.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vidabn.png"));
        vidaDos = new Sprite(texturaVida);
        vidaDos.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vidabn.png"));
        vidaTres = new Sprite(texturaVida);
        vidaTres.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        sonidoAtrapa = Gdx.audio.newSound(Gdx.files.internal("AgarrarCosas_16.wav"));
        sonidoMalo = Gdx.audio.newSound(Gdx.files.internal("CosasMalas2.wav"));
    }


    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondoferia.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        texturaFondoC = new Texture(Gdx.files.internal("candy.jpg"));
        spriteFondoC = new Sprite(texturaFondoC);

        texturaBtnPause = new Texture(Gdx.files.internal("PAUSACHIQUITO.png"));
        spriteBtnPause = new Sprite(texturaBtnPause);
        spriteBtnPause.setPosition((float) (Principal.ANCHO_MUNDO / 1.06 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.19));

        texturaGanaste = new Texture(Gdx.files.internal("PAUSACHIQUITO.png"));
        spriteGanaste = new Sprite(texturaGanaste);
        spriteGanaste.setPosition((float) (Principal.ANCHO_MUNDO / 1.06 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.19));

        texturaFondoU = new Texture(Gdx.files.internal("PANTALLAfonbn.png"));
        spriteFondoU = new Sprite(texturaFondoU);

        texturaBtnResume = new Texture(Gdx.files.internal("RESUME.png"));
        spriteBtnResume = new Sprite(texturaBtnResume);
        spriteBtnResume.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnResume.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.8));

        texturaBtnQuit = new Texture(Gdx.files.internal("QUIT.png"));
        spriteBtnQuit = new Sprite(texturaBtnQuit);
        spriteBtnQuit.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnQuit.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 3.4));

        texturaVida = new Texture(Gdx.files.internal("vidabn.png"));

        vidaUno = new Sprite(texturaVida);
        vidaUno.setPosition((float) (Principal.ANCHO_MUNDO / 1.217 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.13));

        vidaDos = new Sprite(texturaVida);
        vidaDos.setPosition((float) (Principal.ANCHO_MUNDO / 1.3 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.13));

        vidaTres = new Sprite(texturaVida);
        vidaTres.setPosition((float) (Principal.ANCHO_MUNDO / 1.4 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.13));

    }


    @Override
    public void render(float delta) {

        if (estadoJuego == EstadosJuego.Jugando) {
            moverPersonaje();
            actualizarMael();
            actualizarCamara();
            probarChoque();
            probarAcelerometro();
        }
        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        switch (estadoJuego) {
            case Gana:
                break;
            case Jugando:
                spriteFondo.draw(batch);
                spriteBtnPause.draw(batch);

                if (puntos >= 10) {
                    principal.setScreen(new PantallaCandyland(principal));
                }

                if (puntos >= 7) {
                    payaso.finalizar();
                }

                switch (vidas) {
                    case 3:
                        vidaUno.draw(batch);
                        vidaDos.draw(batch);
                        vidaTres.draw(batch);
                        break;
                    case 2:
                        vidaDos.draw(batch);
                        vidaTres.draw(batch);
                        break;
                    case 1:
                        vidaTres.draw(batch);
                        break;
                    case 0:
                        principal.setScreen(new PantallaGameOver(principal));
                }

                Mael.render(batch);
                paleta.render(batch);
                helado.render(batch);
                payaso.render(batch);
                texto.mostrarMensaje(batch, "Paletas: " + puntos, (float) (Principal.ANCHO_MUNDO / 4), Principal.ALTO_MUNDO * 0.97f);
                break;

            case Pausado:
                spriteFondoU.draw(batch);
                spriteBtnQuit.draw(batch);
                spriteBtnResume.draw(batch);
                break;

            case Perdio:
                break;

            case Caer:
                break;
        }
        batch.end();
    }

    public void probarChoque() {
        Random randX = new Random();
        Rectangle a = paleta.getSprite().getBoundingRectangle();
        Rectangle b = Mael.getSprite().getBoundingRectangle();
        if (b.overlaps(a)) {
            puntos++;
            sonidoAtrapa.play();
            paleta.getSprite().setY(Principal.ALTO_MUNDO);
            paleta.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }
        Rectangle c = helado.getSprite().getBoundingRectangle();
        if (b.overlaps(c)) {
            if (vidas < 3)
                vidas = vidas + 1;
            sonidoAtrapa.play();
            helado.getSprite().setY(Principal.ALTO_MUNDO);
            helado.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }

        //Rectangle d = payaso.getSprite().getBoundingRectangle();
        Rectangle rp = payaso.getSpriteGrande().getBoundingRectangle();
        if (puntos >= 7) {
            float offset = rp.getWidth() * .20f;
            rp.setX(rp.getX() + offset);
            rp.setWidth(rp.getWidth() - 2 * offset);
            if (b.overlaps(rp)) {
                vidas = vidas - 1;
                sonidoMalo.play();
                int nuevaX = randX.nextInt((int) principal.ANCHO_MUNDO);
                payaso.getSprite().setY(Principal.ALTO_MUNDO);
                payaso.getSprite().setX(nuevaX);
                payaso.getSpriteGrande().setY(Principal.ALTO_MUNDO);
                payaso.getSpriteGrande().setX(nuevaX);
            }
        }
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void actualizarMael() {
    }

    private void probarAcelerometro() {
        float accelY = Gdx.input.getAccelerometerY();
        if ((accelY >= 1)) {
            Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovDer);
        } else if ((accelY <= -1)) {
            Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovIzq);
        } else {
            Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.Reposo);
        }
    }

    private void actualizarCamara() {
        float posX = Mael.getX();
        if (posX >= Principal.ANCHO_MUNDO / 2 && posX <= ANCHO_MUNDO - Principal.ANCHO_MUNDO / 2) {
            camara.position.set((int) posX, camara.position.y, 0);
        } else if (posX > ANCHO_MUNDO - Principal.ANCHO_MUNDO / 2) {
            camara.position.set(ANCHO_MUNDO - Principal.ANCHO_MUNDO / 2, camara.position.y, 0);
        }
        camara.update();
    }

    private void moverPersonaje() {
        switch (Mael.getEstadoMovimiento()) {
            case Inicia:
                break;
            case MovDer:
            case MovIzq:
                break;
        }
    }


    @Override
    public void resize(int width, int height) {
        vista.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        if (musicaJuego.isPlaying()) {
            musicaJuego.stop();
        }
    }

    @Override
    public void dispose() {
        AssetManager assetManager = principal.getAssetManager();
        assetManager.unload("SpriteCa.png");
        assetManager.unload("SPRITESNUEVOS.png");
        assetManager.unload("heladosprite.png");
        assetManager.unload("payasin.png");
        assetManager.unload("vidabn.png");
        assetManager.unload("payasote.png");
        texturaFondo.dispose();
        texturaFondoU.dispose();
        texturaBtnPause.dispose();
        texturaBtnQuit.dispose();
        texturaBtnResume.dispose();
        texturaMael.dispose();
        sonidoAtrapa.dispose();
        sonidoMalo.dispose();
        musicaJuego.dispose();
    }


    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnPause.getX() &&
                    touchX < spriteBtnPause.getX() + spriteBtnPause.getWidth()
                    && touchY >= spriteBtnPause.getY()
                    && touchY <= spriteBtnPause.getY() + spriteBtnPause.getHeight())
                estadoJuego = EstadosJuego.Pausado;

            if (touchX >= spriteBtnResume.getX() &&
                    touchX < spriteBtnResume.getX() + spriteBtnResume.getWidth()
                    && touchY >= spriteBtnResume.getY()
                    && touchY <= spriteBtnResume.getY() + spriteBtnResume.getHeight()) {
                estadoJuego = EstadosJuego.Jugando;

            }
            if (estadoJuego == EstadosJuego.Pausado && touchX >= spriteBtnQuit.getX() &&
                    touchX <= spriteBtnQuit.getX() + spriteBtnQuit.getWidth()
                    && touchY >= spriteBtnQuit.getY()
                    && touchY <= spriteBtnQuit.getY() + spriteBtnQuit.getHeight()) {
                principal.setScreen(new mx.itesm.wayakkk.PantallaMenu(principal));

            }
        }
    }

    public class ProcesadorEntrada extends InputAdapter {
        private Vector3 coordenadas = new Vector3();
        private float x, y;


       // @Override
        /**public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);

            if ((x >= 640)) {
                Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovDer);
                //Gdx.app.log("touchDown", "CaminaDerecha");
            } else {
                Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovIzq);
                Mael.actualizar();
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.Reposo);
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return true;
        }*/


        private void transformarCoordenadas(int screenX, int screenY) {
            coordenadas.set(screenX, screenY, 0);
            camara.unproject(coordenadas);
            x = coordenadas.x;
            y = coordenadas.y;
        }
    }

    public enum EstadosJuego {
        Gana,
        Jugando,
        Pausado,
        Perdio,
        Caer
    }
}
