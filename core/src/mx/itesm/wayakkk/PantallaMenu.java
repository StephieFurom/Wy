package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

public class PantallaMenu implements Screen {
    private final Principal principal;

    private OrthographicCamera camara;
    private Viewport vista;

    public static boolean musica = true;

    private SpriteBatch batch;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    // Botón Jugar
    private Texture texturaBtnJugar;
    private Sprite spriteBtnJugar;

    // Botón Acerca De
    private Texture texturaBtnAcercaDe;
    private Sprite spriteBtnAcercaDe;

    // Botón Ajustes
    private Texture texturaBtnAjustes;
    private Sprite spriteBtnAjustes;

    // Botón Instrucciones
    private Texture texturaBtnInstrucciones;
    private Sprite spriteBtnInstrucciones;


    public static Music musicaMenu;


    public PantallaMenu(Principal principal) {
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

        Preferences prefs = Gdx.app.getPreferences("Preferencias");
        musica = prefs.getBoolean("SonidoNo", true);

        cargarAudio();
        Gdx.input.setCatchBackKey(false);
    }

    private void cargarAudio() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("MenuMus.mp3"));
        musicaMenu.setLooping(true);
        if (PantallaMenu.musica == true)
            musicaMenu.play();
    }

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondo2.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnJugar = new Texture(Gdx.files.internal("PLAY.png"));
        spriteBtnJugar = new Sprite(texturaBtnJugar);
        spriteBtnJugar.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnJugar.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 1.9));

        texturaBtnAcercaDe = new Texture(Gdx.files.internal("ABOUT.png"));
        spriteBtnAcercaDe = new Sprite(texturaBtnAcercaDe);
        spriteBtnAcercaDe.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnAcercaDe.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 2.65));

        texturaBtnAjustes = new Texture(Gdx.files.internal("SETTINGS.png"));
        spriteBtnAjustes = new Sprite(texturaBtnAjustes);
        spriteBtnAjustes.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnAjustes.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 4.8));

        texturaBtnInstrucciones = new Texture(Gdx.files.internal("help2.png"));
        spriteBtnInstrucciones = new Sprite(texturaBtnInstrucciones);
        spriteBtnInstrucciones.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInstrucciones.getWidth() / 2,
                (float) (Principal.ALTO_MUNDO / 23));
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnJugar.draw(batch);
        spriteBtnAcercaDe.draw(batch);
        spriteBtnAjustes.draw(batch);
        spriteBtnInstrucciones.draw(batch);
        batch.end();
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Gdx.app.log("leer entrada", "procesando");
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnAcercaDe.getX() &&
                    touchX < spriteBtnAcercaDe.getX() + spriteBtnAcercaDe.getWidth()
                    && touchY >= spriteBtnAcercaDe.getY()
                    && touchY <= spriteBtnAcercaDe.getY() + spriteBtnAcercaDe.getHeight()) {
                principal.setScreen(new PantallaAcercaDe(principal));

            }
            if (touchX >= spriteBtnJugar.getX() &&
                    touchX <= spriteBtnJugar.getX() + spriteBtnJugar.getWidth()
                    && touchY >= spriteBtnJugar.getY()
                    && touchY <= spriteBtnJugar.getY() + spriteBtnJugar.getHeight()) {
                principal.setScreen(new PantallaGana(principal));

            }
            if (touchX >= spriteBtnAjustes.getX() &&
                    touchX <= spriteBtnAjustes.getX() + spriteBtnAjustes.getWidth()
                    && touchY >= spriteBtnAjustes.getY()
                    && touchY <= spriteBtnAjustes.getY() + spriteBtnAjustes.getHeight())
                principal.setScreen((Screen) new PantallaAjustes(principal));

            if (touchX >= spriteBtnInstrucciones.getX() &&
                    touchX <= spriteBtnInstrucciones.getX() + spriteBtnInstrucciones.getWidth()
                    && touchY >= spriteBtnInstrucciones.getY()
                    && touchY <= spriteBtnInstrucciones.getY() + spriteBtnInstrucciones.getHeight())
                principal.setScreen((Screen) new PantallaInstrucciones(principal));
        }
        }

    @Override
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
        if (musicaMenu.isPlaying()) {
            musicaMenu.stop();
        }
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        texturaBtnAjustes.dispose();
        texturaBtnAcercaDe.dispose();
        texturaBtnJugar.dispose();
        texturaBtnInstrucciones.dispose();
        musicaMenu.dispose();
    }
}