package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

public class PantallaAjustes implements Screen {
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;


    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaBtni;
    private Sprite spriteBtni;

    private Texture texturaBtno;
    private Sprite spriteBtno;

    private Texture texturaBtnVolumen;
    private Sprite spriteBtnVolumen;

    private Texture texturaBtnReturn;
    private Sprite spriteBtnReturn;

    private Music musicaMenu;

    private SpriteBatch batch;

    public PantallaAjustes(Principal principal) {
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

        texturaFondo = new Texture(Gdx.files.internal("PANTALLAfonbn.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnVolumen = new Texture(Gdx.files.internal("SOUND.png"));
        spriteBtnVolumen = new Sprite(texturaBtnVolumen);
        spriteBtnVolumen.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnVolumen.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.7));

        texturaBtno = new Texture(Gdx.files.internal("sonno.png"));
        spriteBtno = new Sprite(texturaBtno);
        spriteBtno.setPosition((float) (Principal.ANCHO_MUNDO / 1.75 - spriteBtno.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 2.75));

        texturaBtni = new Texture(Gdx.files.internal("sonsi.png"));
        spriteBtni = new Sprite(texturaBtni);
        spriteBtni.setPosition((float) (Principal.ANCHO_MUNDO / 2.4 - spriteBtni.getWidth() / 2),
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
        spriteBtnVolumen.draw(batch);
        spriteBtno.draw(batch);
        spriteBtni.draw(batch);
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

            if (touchX >= spriteBtno.getX() &&
                    touchX < spriteBtno.getX() + spriteBtno.getWidth()
                    && touchY >= spriteBtno.getY()
                    && touchY <= spriteBtno.getY() + spriteBtno.getHeight()) {
                Gdx.app.log("touchX", "SinMusica");
                PantallaMenu.musica = false;
                musicaMenu.stop();

                Preferences prefs = Gdx.app.getPreferences("Preferencias");
                prefs.putBoolean("SonidoNo", false);
                prefs.flush();
                //principal.setScreen(new PantallaVolumen(principal));
            }

            if (touchX >= spriteBtni.getX() &&
                    touchX <= spriteBtni.getX() + spriteBtni.getWidth()
                    && touchY >= spriteBtni.getY()
                    && touchY <= spriteBtni.getY() + spriteBtni.getHeight()) {
                Gdx.app.log("touchX", "ConMusica");
                PantallaMenu.musica = true;
                musicaMenu.play();
                Preferences prefs = Gdx.app.getPreferences("My Preferences");
                prefs.putBoolean("SonidoNo", true);
                prefs.flush();
                //principal.setScreen((Screen) new PantallaVolumen(principal));
            }

                    if (touchX >= spriteBtnReturn.getX() &&
                            touchX <= spriteBtnReturn.getX() + spriteBtnReturn.getWidth()
                            && touchY >= spriteBtnReturn.getY()
                            && touchY <= spriteBtnReturn.getY() + spriteBtnReturn.getHeight()) {
                        principal.setScreen((Screen) new PantallaMenu(principal));
                    }
                }
            }
        }
