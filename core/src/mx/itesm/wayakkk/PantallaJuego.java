package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p
public class PantallaJuego implements Screen {
    public static final float ANCHO_MAPA = 1280;
    private Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private SpriteBatch batch;

    private Texture texturaMael;
    private Personaje Mael;

    private int paleta;
    private Texto texto;
    //private Sound sonidoPaleta;

    private Texture texturaWinner;

    public PantallaJuego(Principal principal) {
        this.principal = principal;
    }
    //private Boton btnWinner;
    //private Sound sonidoGameOver;

    private void crearObjetos() {
        AssetManager assetManager = principal.getAssetManager();
        Texture texturaPersonaje = assetManager.get("Sprite-Camiar.png");
        Mael = new Personaje(texturaPersonaje);
        Mael.getSprite().setPosition(principal.ANCHO_MUNDO / 10, principal.ALTO_MUNDO * 0.90f);


    }

    @Override
    public void show() {


        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();


}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);


        batch.begin();


        batch.end();

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

    }
}