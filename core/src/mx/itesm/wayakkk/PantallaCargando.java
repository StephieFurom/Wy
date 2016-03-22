package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 22/03/2016.
 */
public class PantallaCargando implements Screen {
    private Principal principal;

    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    // Imagen cargando
    private Texture texturaCargando;
    private Sprite spriteCargando;

    private AssetManager assetManager;  // Asset manager principal

    public PantallaCargando(Principal principal) {
        this.principal = principal;
        this.assetManager = principal.getAssetManager();
    }

    @Override
    public void show() {
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        assetManager.load("cargando.png", Texture.class);
        assetManager.finishLoading();
        texturaCargando = assetManager.get("cargando.png");
        spriteCargando = new Sprite(texturaCargando);
        spriteCargando.setPosition(Principal.ANCHO_MUNDO / 2 - spriteCargando.getWidth() / 2,
                Principal.ALTO_MUNDO / 2 - spriteCargando.getHeight() / 2);

        cargarRecursos();
    }

    private void cargarRecursos() {
        assetManager.load("SpriteCa.png", Texture.class);
        // Texturas de los botones
        //assetManager.load("derecha.png", Texture.class);
        //assetManager.load("izquierda.png", Texture.class);
        //assetManager.load("salto.png", Texture.class);
        // Fin del juego
        //assetManager.load("ganaste.png", Texture.class);
        // Efecto al tomar la moneda
        //assetManager.load("coin.wav", Sound.class);
        //assetManager.load("mariodie.wav", Sound.class);

        assetManager.load("WINNERPANTALLA2.png", Texture.class);
        assetManager.finishLoading();
    }

    @Override
    public void render(float delta) {
        actualizar();

        borrarPantalla();
        spriteCargando.setRotation(spriteCargando.getRotation() + 15);

        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        spriteCargando.draw(batch);
        batch.end();
    }

    private void actualizar() {

        if (assetManager.update()) {
            principal.setScreen(new PantallaJuego(principal));
        } else {
            float avance = assetManager.getProgress()*100;
            Gdx.app.log("Cargando",avance+"%");
        }
    }


    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);    // r, g, b, alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);

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
        texturaCargando.dispose();

    }
}
