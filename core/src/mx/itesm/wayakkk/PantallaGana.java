package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 05/04/2016.
 */
public class PantallaGana implements Screen {

    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;


    private Texture texturaFondo;
    private Sprite spriteFondo;


    private Texture texturaBtnRetry;
    private Sprite spriteBtnRetry;


    private Texture texturaBtnQuit;
    private Sprite spriteBtnQuit;


    private SpriteBatch batch;

    public PantallaGana(Principal principal) {
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
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("WINNERPANTALLA2.png"));
        spriteFondo = new Sprite(texturaFondo);


        texturaBtnRetry = new Texture(Gdx.files.internal("RETRY.png"));
        spriteBtnRetry = new Sprite(texturaBtnRetry);
        spriteBtnRetry.setPosition(Principal.ANCHO_MUNDO / 3 - spriteBtnRetry.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 5.5));

        texturaBtnQuit = new Texture(Gdx.files.internal("QUIT.png"));
        spriteBtnQuit = new Sprite(texturaBtnQuit);
        spriteBtnQuit.setPosition((float) (Principal.ANCHO_MUNDO / 1.5 - spriteBtnQuit.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 5.5));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnRetry.draw(batch);
        spriteBtnQuit.draw(batch);

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

    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnRetry.getX() &&
                    touchX < spriteBtnRetry.getX() + spriteBtnRetry.getWidth()
                    && touchY >= spriteBtnRetry.getY()
                    && touchY <= spriteBtnRetry.getY() + spriteBtnRetry.getHeight()) {
                principal.setScreen(new PantallaJuego(principal));
            }

            if (touchX >= spriteBtnQuit.getX() &&
                    touchX <= spriteBtnQuit.getX() + spriteBtnQuit.getWidth()
                    && touchY >= spriteBtnQuit.getY()
                    && touchY <= spriteBtnQuit.getY() + spriteBtnQuit.getHeight()) {
                principal.setScreen((Screen) new PantallaMenu(principal));


            }
        }
    }
}
