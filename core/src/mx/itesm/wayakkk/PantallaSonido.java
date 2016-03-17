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
 * Created by Stephie Furom on 15/03/2016.
 */
public class PantallaSonido implements Screen {


    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;


    private Texture texturaFondo;
    private Sprite spriteFondo;


    private Texture texturaBtnSi;
    private Sprite spriteBtnSi;


    private Texture texturaBtnNo;
    private Sprite spriteBtnNo;

    private Texture texturaBtnReturn;
    private Sprite spriteBtnReturn;


    private SpriteBatch batch;

    public PantallaSonido(Principal principal) {
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

        texturaFondo = new Texture(Gdx.files.internal("PANTALLAfonbn.png"));
        spriteFondo = new Sprite(texturaFondo);


        texturaBtnSi = new Texture(Gdx.files.internal("sonsi.png"));
        spriteBtnSi = new Sprite(texturaBtnSi);
        spriteBtnSi.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnSi.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.75));

        texturaBtnNo = new Texture(Gdx.files.internal("sonno.png"));
        spriteBtnNo = new Sprite(texturaBtnNo);
        spriteBtnNo.setPosition((float) (Principal.ANCHO_MUNDO / 2 - spriteBtnNo.getWidth() / 2),
                Principal.ALTO_MUNDO / 3);

        texturaBtnReturn = new Texture(Gdx.files.internal("RETURN.png"));
        spriteBtnReturn = new Sprite(texturaBtnReturn);
        spriteBtnReturn.setPosition((float) (Principal.ANCHO_MUNDO / 1.35 - spriteBtnReturn.getWidth() / 2),
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
        spriteBtnSi.draw(batch);
        spriteBtnNo.draw(batch);
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

            if (touchX >= spriteBtnSi.getX() &&
                    touchX < spriteBtnSi.getX() + spriteBtnSi.getWidth()
                    && touchY >= spriteBtnSi.getY()
                    && touchY <= spriteBtnSi.getY() + spriteBtnSi.getHeight()) {
                principal.setScreen(new PantallaSonido(principal));
            }

            if (touchX >= spriteBtnNo.getX() &&
                    touchX <= spriteBtnNo.getX() + spriteBtnNo.getWidth()
                    && touchY >= spriteBtnNo.getY()
                    && touchY <= spriteBtnNo.getY() + spriteBtnNo.getHeight()) {
                principal.setScreen((Screen) new PantallaSonido(principal));

            }
            if (touchX >= spriteBtnReturn.getX() &&
                    touchX <= spriteBtnReturn.getX() + spriteBtnReturn.getWidth()
                    && touchY >= spriteBtnReturn.getY()
                    && touchY <= spriteBtnReturn.getY() + spriteBtnReturn.getHeight())
                principal.setScreen((Screen) new PantallaAjustes(principal));
        }
    }
}