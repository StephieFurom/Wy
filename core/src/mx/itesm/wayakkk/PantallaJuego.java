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

    public int puntos;
    public int vidas=3;

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

    private Texture texturaVida;
    private Sprite vidaUno;
    private Sprite vidaDos;
    private Sprite vidaTres;

    private SpriteBatch batch;
    private Object EstadoMovimiento;
    private EstadosJuego estadoJuego;

    private Music efectoAtrapa;
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

        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void cargarAudio() {
        efectoAtrapa = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
        efectoAtrapa.setLooping(true);
        efectoAtrapa.play(); //

         musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
         musicaJuego.setLooping(true);
         musicaJuego.play();
    }

    private void crearObjetos() {
        texto = new Texto();
        Random rand = new Random();
        AssetManager assetManager = principal.getAssetManager();
        texturaMael = new Texture(Gdx.files.internal("SpriteCa.png"));
        Mael = new Personaje(texturaMael);
        Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO* 0.10f);

        texturaPaleta = new Texture(Gdx.files.internal("SPRITEPALETA.png"));
        paleta = new Objetos(texturaPaleta);
        paleta.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaPayaso = new Texture(Gdx.files.internal("SpritesMiniPayasos.png"));
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        moverPersonaje();

        actualizarMael();
        actualizarCamara();
        probarChoque();

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

            principal.setScreen(new PantallaWin(principal));


        batch.begin();

        spriteFondo.draw(batch);
        spriteBtnPause.draw(batch);
        //Gdx.app.log("render","puntos = "+puntos);

        if (puntos >= 25){
            principal.setScreen(new PantallaWin(principal));
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

        // Paletas recolectadas
        texto.mostrarMensaje(batch,"Paletas: "+puntos, (float) (Principal.ANCHO_MUNDO/4),Principal.ALTO_MUNDO*0.97f);
        batch.end();
    }

    public void probarChoque(){
        Rectangle a = paleta.getSprite().getBoundingRectangle();
        Rectangle b = Mael.getSprite().getBoundingRectangle();
        if (b.contains(a)) {
            puntos++;
            efectoAtrapa.play();
            paleta.getSprite().setY(Principal.ALTO_MUNDO);
        }
                if(vidas<3){
            Rectangle c = helado.getSprite().getBoundingRectangle();
            if (b.contains(c)) {
                vidas=vidas+1;}
                helado.getSprite().setY(Principal.ALTO_MUNDO);
            }

                Rectangle d = payaso.getSprite().getBoundingRectangle();
                if (b.contains(d)) {
                    vidas=vidas-1;
                    payaso.getSprite().setY(Principal.ALTO_MUNDO);
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
        assetManager.unload("SPRITEPALETA.png");
        assetManager.unload("SPRITESHELADO.png");
        assetManager.unload("SpritesMiniPayasos.png");
        assetManager.unload("vida.png");
        texturaFondo.dispose();
        texturaBtnPause.dispose();
        texturaMael.dispose();
        efectoAtrapa.dispose();
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
                principal.setScreen((Screen) new PantallaPausa(principal));
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
                    //Gdx.app.log("touchDown", "CaminaIzquierda");
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
