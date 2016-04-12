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

/**p
 * Created by Stephie Furom on 17/02/2016.
 */
    public class PantallaAcercaDe implements Screen {

     private final Principal principal;
     private OrthographicCamera camara;
     private Viewport vista;

     private Texture texturaFondo;
        private Sprite spriteFondo;

        private Texture texturaBtnReturn;
        private Sprite spriteBtnReturn;

    private Music musicaMenu;


    private SpriteBatch batch;

    public PantallaAcercaDe(Principal principal) {
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
        cargarAudio();
    }

    private void cargarAudio() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("MenuMus.mp3"));
        musicaMenu.setLooping(true);
        if (PantallaMenu.musica==true)
            musicaMenu.play();
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("ACERCADEBIEN.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnReturn = new Texture(Gdx.files.internal("RETURN.png"));
        spriteBtnReturn = new Sprite(texturaBtnReturn);
        spriteBtnReturn.setPosition((float) (Principal.ANCHO_MUNDO / 1.2 - spriteBtnReturn.getWidth() / 2),
                Principal.ALTO_MUNDO / 20);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
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

            if ( touchX>=spriteBtnReturn.getX() &&
                    touchX<spriteBtnReturn.getX()+spriteBtnReturn.getWidth()
                    && touchY>=spriteBtnReturn.getY()
                    && touchY<=spriteBtnReturn.getY()+spriteBtnReturn.getHeight() )
                principal.setScreen(new PantallaMenu(principal));
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
    }

    @Override
    public void dispose() {
        musicaMenu.dispose();
    }
}