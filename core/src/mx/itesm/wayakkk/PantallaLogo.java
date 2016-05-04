package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 03/05/2016.
 */
public class PantallaLogo implements Screen {
    private final Principal principal;

    private float tiempo=0;

    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    private SpriteBatch batch;

    public PantallaLogo(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);
        batch = new SpriteBatch();
        cargarTeturasSprites();
        Gdx.input.setCatchBackKey(true);
    }

    private void cargarTeturasSprites() {
        texturaFondo = new Texture(Gdx.files.internal("logotec.png"));
        spriteFondo = new Sprite(texturaFondo);
    }


    @Override
    public void render(float delta) {
        tiempo = Gdx.graphics.getRawDeltaTime() + tiempo;
        if(tiempo > 1){
            principal.setScreen(new PantallaMenu(principal));
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        spriteFondo.draw(batch);

        batch.end();}

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
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
    }
}
