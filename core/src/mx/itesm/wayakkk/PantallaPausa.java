package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 14/03/2016.
 */

public class PantallaPausa implements Screen {
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;


    private Texture texturaFondo;
    private Sprite spriteFondo;

    // Botón Resume
    private Texture texturaBtnResume;
    private Sprite spriteBtnResume;

    // Botón Quit
    private Texture texturaBtnQuit;
    private Sprite spriteBtnQuit;

    private Music musicaMenu;

    private SpriteBatch batch;

    public PantallaPausa(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        cargarTexturasSprites();
        cargarAudio();
        Gdx.input.setCatchBackKey(true);
    }

    private void cargarAudio() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("MenuMus.mp3"));
        musicaMenu.setLooping(true);
        if (PantallaMenu.musica == true)
            musicaMenu.play();
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("PANTALLAfonbn.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnResume = new Texture(Gdx.files.internal("RESUME.png"));
        spriteBtnResume = new Sprite(texturaBtnResume);
        spriteBtnResume.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnResume.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.8));

        texturaBtnQuit = new Texture(Gdx.files.internal("QUIT.png"));
        spriteBtnQuit = new Sprite(texturaBtnQuit);
        spriteBtnQuit.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnQuit.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 3.4));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnResume.draw(batch);
        spriteBtnQuit.draw(batch);

        batch.end();
    }

    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnResume.getX() &&
                    touchX < spriteBtnResume.getX() + spriteBtnResume.getWidth()
                    && touchY >= spriteBtnResume.getY()
                    && touchY <= spriteBtnResume.getY() + spriteBtnResume.getHeight()) {
                principal.setScreen(new PantallaJuego(principal));

            }
            if (touchX >= spriteBtnQuit.getX() &&
                    touchX <= spriteBtnQuit.getX() + spriteBtnQuit.getWidth()
                    && touchY >= spriteBtnQuit.getY()
                    && touchY <= spriteBtnQuit.getY() + spriteBtnQuit.getHeight()) {
                principal.setScreen(new mx.itesm.wayakkk.PantallaMenu(principal));

            }
        }
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
        if (musicaMenu.isPlaying()) {
            musicaMenu.stop();
        }
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        texturaBtnResume.dispose();
        texturaBtnQuit.dispose();
        musicaMenu.dispose();
    }
}
