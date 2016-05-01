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
 * Created by Stephie Furom on 21/04/2016.
 */
public class PantallaCandyland implements Screen {
    public static final float ANCHO_MUNDO = 1280;
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaFondoU;
    private Sprite spriteFondoU;

    public int puntos;
    public int vidas=3;

    private Texto texto;

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;

    private Texture texturaMael;
    private Personaje Mael;

    private Texture texturaHielo;
    private Hielo paletaHielo;

    private Texture texturaBroccoli;
    private Broccoli brocco;

    private Texture texturaHelado;
    private Vidas helado;

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

    public PantallaCandyland(Principal principal) {
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
        estadoJuego=EstadosJuego.Jugando;
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void cargarAudio() {
        musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
        musicaJuego.setLooping(true);
        if (PantallaMenu.musica==true)
            musicaJuego.play();
    }

    private void crearObjetos() {
        texto = new Texto();
        Random rand = new Random();
        AssetManager assetManager = principal.getAssetManager();
        texturaMael = new Texture(Gdx.files.internal("SpriteCa.png"));
        Mael = new Personaje(texturaMael);
        Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO * 0.10f);

        texturaHielo = new Texture(Gdx.files.internal("SPRITES2.png"));
        paletaHielo = new Hielo(texturaHielo);
        paletaHielo.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaBroccoli = new Texture(Gdx.files.internal("malobrocop1nuevo.png"));
        brocco = new Broccoli(texturaBroccoli);
        brocco.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

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

        texturaFondo = new Texture(Gdx.files.internal("candylanfinal.png"));
        spriteFondo = new Sprite(texturaFondo);

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

        if(estadoJuego==EstadosJuego.Jugando) {
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
        switch(estadoJuego) {
            case Gana:
                break;
            case Jugando:
                spriteFondo.draw(batch);
                spriteBtnPause.draw(batch);

                if (puntos >= 20) {
                    principal.setScreen(new PantallaToyStation(principal));
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
                paletaHielo.render(batch);
                helado.render(batch);
                brocco.render(batch);
                texto.mostrarMensaje(batch, "Paletas: " + puntos, (float) (Principal.ANCHO_MUNDO / 4), Principal.ALTO_MUNDO * 0.97f);
                //Gdx.app.log("Render","Jugando");
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

    public void probarChoque(){
        Random randX = new Random();
        Rectangle a = paletaHielo.getSprite().getBoundingRectangle();
        Rectangle b = Mael.getSprite().getBoundingRectangle();
        if (b.overlaps(a)) {
            float offset = b.getWidth() * .15f;
            b.setX(b.getX() + offset);
            b.setWidth((float) (b.getWidth() - 1.5 * offset));
            offset = a.getWidth() * .15f;
            a.setX(a.getX() + offset);
            a.setWidth((float) (a.getWidth() - 1.5 * offset));
            puntos++;
            sonidoAtrapa.play();
            paletaHielo.getSprite().setY(Principal.ALTO_MUNDO);
            paletaHielo.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }
        Rectangle c = helado.getSprite().getBoundingRectangle();
        if (b.overlaps(c)) {
            float offset = c.getWidth() * .15f;
            c.setX(c.getX() + offset);
            c.setWidth((float) (c.getWidth() - 1.5 * offset));
            if (vidas < 3)
                vidas = vidas + 1;
            sonidoAtrapa.play();
            helado.getSprite().setY(Principal.ALTO_MUNDO);
            helado.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }

        Rectangle d = brocco.getSprite().getBoundingRectangle();
        if (b.overlaps(d)) {
            float offset = d.getWidth() * .20f;
            d.setX(d.getX() + offset);
            d.setWidth((float) (d.getWidth() - 2.0 * offset));
            offset = d.getHeight() * 30f;
            d.setY(d.getY() + offset);
            d.setHeight((float) (d.getHeight() - 3.0 * offset));
            vidas = vidas - 1;
            sonidoMalo.play();
            brocco.getSprite().setY(Principal.ALTO_MUNDO);
            brocco.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
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
        if (posX>=Principal.ANCHO_MUNDO/2 && posX<=ANCHO_MUNDO-Principal.ANCHO_MUNDO/2) {
            camara.position.set((int)posX, camara.position.y, 0);
        } else if (posX>ANCHO_MUNDO-Principal.ANCHO_MUNDO/2) {
            camara.position.set(ANCHO_MUNDO-Principal.ANCHO_MUNDO/2, camara.position.y, 0);
        }
        camara.update();
    }

    private void moverPersonaje(){
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
        if ( musicaJuego.isPlaying() ) {
            musicaJuego.stop();
        }
    }

    @Override
    public void dispose() {
        AssetManager assetManager = principal.getAssetManager();
        assetManager.unload("SpriteCa.png");
        assetManager.unload("SPRITES2.png");
        assetManager.unload("heladosprite.png");
        assetManager.unload("malobrocop1nuevo.png");
        assetManager.unload("vidabn.png");
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
                estadoJuego=EstadosJuego.Pausado;

            if ( touchX>=spriteBtnResume.getX() &&
                    touchX<spriteBtnResume.getX()+spriteBtnResume.getWidth()
                    && touchY>=spriteBtnResume.getY()
                    && touchY<=spriteBtnResume.getY()+spriteBtnResume.getHeight() ) {
                estadoJuego=EstadosJuego.Jugando;

            }
            if ( estadoJuego ==EstadosJuego.Pausado && touchX>=spriteBtnQuit.getX() &&
                    touchX<=spriteBtnQuit.getX()+spriteBtnQuit.getWidth()
                    && touchY>=spriteBtnQuit.getY()
                    && touchY<=spriteBtnQuit.getY()+spriteBtnQuit.getHeight() ) {
                principal.setScreen(new mx.itesm.wayakkk.PantallaMenu(principal));

            }
        }
    }
    public class ProcesadorEntrada extends InputAdapter
    {
        private Vector3 coordenadas = new Vector3();
        private float x, y;



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
