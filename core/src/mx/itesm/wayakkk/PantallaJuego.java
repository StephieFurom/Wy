package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;

    private Texture texturaMael;
    private Personaje Mael;


    private SpriteBatch batch;
    private Object EstadoMovimiento;
    private EstadosJuego estadoJuego;


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
    }

    private void crearObjetos() {
        //AssetManager assetManager = principal.getAssetManager();
        //texturaMael = assetManager.get("SpriteCa.png");
        texturaMael = new Texture(Gdx.files.internal("SpriteCa.png"));
        Mael = new Personaje(texturaMael);
        Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 8, Principal.ALTO_MUNDO * 0.10f);}

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondoferia.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnPause = new Texture(Gdx.files.internal("PAUSA.png"));
        spriteBtnPause = new Sprite(texturaBtnPause);
        spriteBtnPause.setPosition((float) (Principal.ANCHO_MUNDO / 1.15 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.3));
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        actualizarMael();
        actualizarCamara();

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();

        spriteFondo.draw(batch);
        spriteBtnPause.draw(batch);
        Mael.render(batch);


        batch.end();
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        AssetManager assetManager = principal.getAssetManager();
        assetManager.unload("SpriteCa.png");
    }

    public void touch(int screenX, int screenY, int pointer, int button) {
        transformarCoordenadas(screenX, screenY);
        if (EstadoMovimiento==EstadosJuego.Jugando) {
            // Preguntar si las coordenadas están sobre el botón derecho
            if ((screenX<= 640)){
                Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovDer);
            } else {
                Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovIzq);
            }
        }
    }

    private void transformarCoordenadas(int screenX, int screenY) {
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

    public enum EstadosJuego {
        Gana,
        Jugando,
        Pausado,
        Perdio
    }
}
