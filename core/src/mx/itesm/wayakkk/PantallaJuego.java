package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
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

    public static int puntos;
    public static int vidas=3;

    private Texto texto;

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;

    private Texture texturaMael;
    private Personaje Mael;

    private Texture texturaPaleta;
    private Objetos paleta;

    private Texture texturaPayaso;
    private Villano payaso;

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

    //private Music efectoAtrapa;
    private Music musicaJuego;



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
        estadoJuego=EstadosJuego.Jugando;
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void cargarAudio() {
        //efectoAtrapa = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
        //efectoAtrapa.setLooping(true);
        //efectoAtrapa.play(); //

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

        texturaPaleta = new Texture(Gdx.files.internal("palsprite.png"));
        paleta = new Objetos(texturaPaleta);
        paleta.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaPayaso = new Texture(Gdx.files.internal("SPRITESpayasobn.png"));
        payaso = new Villano(texturaPayaso);
        payaso.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaHelado = new Texture(Gdx.files.internal("SPRITESHELADO.png"));
        helado = new Vidas(texturaHelado);
        helado.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vida.png"));
        vidaUno = new Sprite(texturaVida);
        vidaUno.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vida.png"));
        vidaDos = new Sprite(texturaVida);
        vidaDos.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaVida = new Texture(Gdx.files.internal("vida.png"));
        vidaTres = new Sprite(texturaVida);
        vidaTres.setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);
    }


    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondoferia.jpg"));
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

        texturaVida = new Texture(Gdx.files.internal("vida.png"));

        vidaUno = new Sprite(texturaVida);
        vidaUno.setPosition((float) (Principal.ANCHO_MUNDO / 1.58 - spriteBtnPause.getWidth() / 2),
               (float) (Principal.ALTO_MUNDO / 1.19));

        vidaDos = new Sprite(texturaVida);
        vidaDos.setPosition((float) (Principal.ANCHO_MUNDO / 1.8 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.19));

        vidaTres = new Sprite(texturaVida);
        vidaTres.setPosition((float) (Principal.ANCHO_MUNDO / 2.1 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.19));
    }


    @Override
    public void render(float delta) {

        if(estadoJuego==EstadosJuego.Jugando) {
            moverPersonaje();
            actualizarMael();
            actualizarCamara();
            probarChoque();
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
                    principal.setScreen(new PantallaGana(principal));
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
                Gdx.app.log("Render","Jugando");
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
        Rectangle a = paleta.getSprite().getBoundingRectangle();
        Rectangle b = Mael.getSprite().getBoundingRectangle();
        if (b.overlaps(a)) {
            puntos++;
            //efectoAtrapa.play();
            paleta.getSprite().setY(Principal.ALTO_MUNDO);
            paleta.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }
        Rectangle c = helado.getSprite().getBoundingRectangle();
        if (b.overlaps(c)) {
            if (vidas < 3)
                vidas = vidas + 1;
            //efectoAtrapa.play();
            helado.getSprite().setY(Principal.ALTO_MUNDO);
            helado.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }

        Rectangle d = payaso.getSprite().getBoundingRectangle();
        if (b.overlaps(d)) {
            vidas = vidas - 1;
            payaso.getSprite().setY(Principal.ALTO_MUNDO);
            payaso.getSprite().setX(randX.nextInt((int) principal.ANCHO_MUNDO));
        }
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void actualizarMael() {
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
        assetManager.unload("palsprite.png");
        assetManager.unload("SPRITESHELADO.png");
        assetManager.unload("SPRITEpayasobn.png");
        assetManager.unload("vida.png");
        texturaFondo.dispose();
        texturaFondoU.dispose();
        texturaBtnPause.dispose();
        texturaBtnQuit.dispose();
        texturaBtnResume.dispose();
        texturaMael.dispose();
        //efectoAtrapa.dispose();
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
            if ( touchX>=spriteBtnQuit.getX() &&
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


        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
        }


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
