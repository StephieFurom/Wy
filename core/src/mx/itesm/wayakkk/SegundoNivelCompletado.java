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
 * Created by Stephie Furom on 03/05/2016.
 */
public class SegundoNivelCompletado implements Screen {
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;


    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaBtnContinue;
    private Sprite spriteBtnContinue;

    private Texture texturaBtnQuit;
    private Sprite spriteBtnQuit;

    private Texture texturaBtnReturn;
    private Sprite spriteBtnReturn;

    private Music musicaMenu;

    private SpriteBatch batch;

    public SegundoNivelCompletado(Principal principal) {
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
    }

    private void cargarAudio() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("MenuMus.mp3"));
        musicaMenu.setLooping(true);
        if (PantallaMenu.musica == true)
            musicaMenu.play();
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("congratulations.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnQuit = new Texture(Gdx.files.internal("QUIT.png"));
        spriteBtnQuit = new Sprite(texturaBtnQuit);
        spriteBtnQuit.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnQuit.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.7));

        texturaBtnContinue = new Texture(Gdx.files.internal("sonno.png"));
        spriteBtnContinue = new Sprite(texturaBtnContinue);
        spriteBtnContinue.setPosition((float) (Principal.ANCHO_MUNDO / 2 - spriteBtnContinue.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 2.75));


        texturaBtnReturn = new Texture(Gdx.files.internal("RETURN.png"));
        spriteBtnReturn = new Sprite(texturaBtnReturn);
        spriteBtnReturn.setPosition((float) (Principal.ANCHO_MUNDO / 1.2 - spriteBtnReturn.getWidth() / 2),
                Principal.ALTO_MUNDO / 7);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnQuit.draw(batch);
        spriteBtnContinue.draw(batch);
        spriteBtnReturn.draw(batch);

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
        if (musicaMenu.isPlaying()) {
            musicaMenu.stop();
        }
    }

    @Override
    public void dispose() {
        musicaMenu.dispose();

    }

    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnContinue.getX() &&
                    touchX < spriteBtnContinue.getX() + spriteBtnContinue.getWidth()
                    && touchY >= spriteBtnContinue.getY()
                    && touchY <= spriteBtnContinue.getY() + spriteBtnContinue.getHeight()) {
                principal.setScreen(new PantallaCandyland(principal));

            }
            if (touchX >= spriteBtnQuit.getX() &&
                    touchX <= spriteBtnQuit.getX() + spriteBtnQuit.getWidth()
                    && touchY >= spriteBtnQuit.getY()
                    && touchY <= spriteBtnQuit.getY() + spriteBtnQuit.getHeight()) {
                principal.setScreen(new mx.itesm.wayakkk.PantallaMenu(principal));

            }
        }
    }
}
