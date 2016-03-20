package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p

    public class PantallaJuego implements Screen {

    public static final float ANCHO_MAPA = 1280;
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;


    private SpriteBatch batch;

    public PantallaJuego(Principal principal) {
        this.principal = principal;
        this.camara = camara;
        this.texturaMael = texturaMael;
    }

    // Mael
    private Texture texturaMael;
    private Personaje Mael;
    //public static final int TAM_CELDA = 16;

    private OrthographicCamera camaraHUD;


    // Paletitas
    private int paletas;
    //private Texto texto;
    //private Sound sonidoPaletas;

    // Heladitos
    private int helados;
    private Texto texto;
    //private Sound sonidoHelados;

    // Fin del juego, Gana o Pierde
    //private Texture texturaGana;
    //private Boton btnGana;
    //private Sound sonidoPierde;

    // Estados del juego
    //private EstadosJuego estadoJuego;

    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        cargarTexturasSprites();


        // Cámara para HUD
        camaraHUD = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camaraHUD.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camaraHUD.update();

        //cargarRecursos();
        crearObjetos();

        // Indicar el objeto que atiende los eventos de touch (entrada en general)
        //Gdx.input.setInputProcessor(new ProcesadorEntrada());

        //estadoJuego = EstadosJuego.JUGANDO;

        // Texto
        texto = new Texto();
    }

    private void crearObjetos() {
        AssetManager assetManager = principal.getAssetManager();
        // Cargar frames
        //texturaMael = assetManager.get("SpriteCa.png");
        // Crear el personaje
        //Mael = new Personaje(texturaMael);
        // Posición inicial del personaje
        //Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 10, Principal.ALTO_MUNDO * 0.90f);
    }

    // Efecto Paleta
    //sonidoPaleta = assetManager.get("c.wav");
    //sonidoPierde = assetManager.get("m.wav");

    // Efecto Helado
    //sonidoHelado = assetManager.get("c.wav");
    //sonidoPierde = assetManager.get("m.wav");

    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondoferia.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnPause = new Texture(Gdx.files.internal("RETURN.png"));
        spriteBtnPause = new Sprite(texturaBtnPause);
        spriteBtnPause.setPosition((float) (Principal.ANCHO_MUNDO / 1.2 - spriteBtnPause.getWidth() / 2),
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
        spriteBtnPause.draw(batch);


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
        AssetManager assetManager = principal.getAssetManager();
        assetManager.unload("Sprite-Camiar.png");
    }

    private void leerEntrada() {
        if (Gdx.input.justTouched() == true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if (touchX >= spriteBtnPause.getX() &&
                    touchX < spriteBtnPause.getX() + spriteBtnPause.getWidth()
                    && touchY >= spriteBtnPause.getY()
                    && touchY <= spriteBtnPause.getY() + spriteBtnPause.getHeight())
                principal.setScreen(new PantallaMenu(principal));
        }
    }
}
