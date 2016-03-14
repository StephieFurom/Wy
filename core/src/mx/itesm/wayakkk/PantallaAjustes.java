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

/**p
 * Created by Stephie Furom on 17/02/2016.
 */
    public class PantallaAjustes {
    public class PantallaMenu implements Screen
    {
        private final Principal principal;
        private OrthographicCamera camara;
        private Viewport vista;


        private Texture texturaFondo;
        private Sprite spriteFondo;


        private Texture texturaMusica;
        private Sprite spriteBtnMusica;


        private Texture texturaBtnVolumen;
        private Sprite spriteBtnVolumen;


        private Texture texturaBtnReturn;
        private Sprite spriteBtnReturn;


        private SpriteBatch batch;

        public PantallaMenu(Principal principal) {
        this.principal = principal;
    }


        @Override
        public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();

        cargarTexturasSprites();
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondomenustodos.png"));
        spriteFondo = new Sprite(texturaFondo);

        Texture texturaBtnMusica = new Texture(Gdx.files.internal("SOUND.png"));
        spriteBtnMusica = new Sprite(texturaBtnMusica);
        spriteBtnMusica.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getWidth() / 2,
                Principal.ALTO_MUNDO / 2);

        texturaBtnVolumen = new Texture(Gdx.files.internal("VOLUME.png"));
        spriteBtnVolumen = new Sprite(texturaBtnVolumen);
        spriteBtnVolumen.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnVolumen.getWidth() / 2,
                Principal.ALTO_MUNDO / 3);

        texturaBtnReturn = new Texture(Gdx.files.internal("RETURN.png"));
        spriteBtnReturn = new Sprite(texturaBtnReturn);
        spriteBtnReturn.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnReturn.getWidth() / 2,
                Principal.ALTO_MUNDO / 6);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnMusica.draw(batch);
        spriteBtnVolumen.draw(batch);
        spriteBtnReturn.draw(batch);

        batch.end();
    }

    private void leerEntrada() {
        if (Gdx.input.justTouched()==true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if ( touchX>=spriteBtnMusica.getX() &&
                    touchX<spriteBtnMusica.getX()+spriteBtnMusica.getWidth()
                    && touchY>=spriteBtnMusica.getY()
                    && touchY<=spriteBtnMusica.getY()+spriteBtnMusica.getHeight() ) {
                principal.setScreen(new PantallaAcercaDe(principal));

            }
            if ( touchX>=spriteBtnVolumen.getX() &&
                    touchX<=spriteBtnVolumen.getX()+spriteBtnVolumen.getWidth()
                    && touchY>=spriteBtnVolumen.getY()
                    && touchY<=spriteBtnVolumen.getY()+spriteBtnVolumen.getHeight() ) {
                principal.setScreen(new PantallaJuego(principal));

            }
            if ( touchX>=spriteBtnReturn.getX() &&
                    touchX<=spriteBtnReturn.getX()+spriteBtnReturn.getWidth()
                    && touchY>=spriteBtnReturn.getY()
                    && touchY<=spriteBtnReturn.getY()+spriteBtnReturn.getHeight() ) {
                principal.setScreen((Screen) new PantallaAjustes());
            }
        }
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

        texturaFondo.dispose();
    }
}}
