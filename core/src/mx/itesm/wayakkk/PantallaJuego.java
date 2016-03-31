package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Random;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p

    public class PantallaJuego implements Screen {


    public static final float ANCHO_MUNDO = 1280;
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    public int puntos;
    public int vidas;
    public int quitavidas;

    private Texto texto;

    private Texture texturaBtnPause;
    private Sprite spriteBtnPause;

    private Texture texturaMael;
    private Personaje Mael;

    private Texture texturaPaleta;
    private Objetos paleta;

    private Texture texturaPayaso;
    private Villano payaso;

    private Texture texturaHelado;
    private Vidas helado;


    private SpriteBatch batch;
    private Object EstadoMovimiento;
    private EstadosJuego estadoJuego;

    private Music efectoAtrapa;
    private Music musicaJuego;



    public PantallaJuego(Principal principal) {
        this.principal = principal;
    }



    @Override
    public void show() {
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        crearObjetos();
        cargarTexturasSprites();
        cargarAudio();

        // Indicar el objeto que atiende los eventos de touch (entrada en general)
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void cargarAudio() {
        efectoAtrapa = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
        efectoAtrapa.setLooping(true);
        efectoAtrapa.play(); // Inicia

         //Musica de fondo
         musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("PrimerNivelMus.mp3"));
         musicaJuego.setLooping(true);
         musicaJuego.play(); // Inicia
    }

    private void crearObjetos() {
        // Texto
        texto = new Texto();
        Random rand = new Random();
        AssetManager assetManager = principal.getAssetManager();
        texturaMael = new Texture(Gdx.files.internal("SpriteCa.png"));
        Mael = new Personaje(texturaMael);
        Mael.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO* 0.10f);

        texturaPaleta = new Texture(Gdx.files.internal("SPRITEPALETA.png"));
        paleta = new Objetos(texturaPaleta);
        paleta.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaPayaso = new Texture(Gdx.files.internal("SpritesMiniPayasos.png"));
        payaso = new Villano(texturaPayaso);
        payaso.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);

        texturaHelado = new Texture(Gdx.files.internal("SPRITESHELADO.png"));
        helado = new Vidas(texturaHelado);
        helado.getSprite().setPosition(rand.nextInt((int) ANCHO_MUNDO), Principal.ALTO_MUNDO);
    }



    private void cargarTexturasSprites() {

        texturaFondo = new Texture(Gdx.files.internal("fondoferia.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnPause = new Texture(Gdx.files.internal("PAUSACHIQUITO.png"));
        spriteBtnPause = new Sprite(texturaBtnPause);
        spriteBtnPause.setPosition((float) (Principal.ANCHO_MUNDO / 1.06 - spriteBtnPause.getWidth() / 2),
                (float) (Principal.ALTO_MUNDO / 1.2));
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        moverPersonaje();

        actualizarMael();
        actualizarCamara();
        probarChoque();

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);

        leerEntrada();

        batch.begin();

        spriteFondo.draw(batch);
        spriteBtnPause.draw(batch);
        Mael.render(batch);
        paleta.render(batch);
        helado.render(batch);
        payaso.render(batch);

        // Mostrar marcador
        //texto.mostrarMensaje(batch, "Paletas: "+marcador,
               // Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO*0.9f);

        // Mostrar el tiempo de golpe
        //texto.mostrarMensaje(batch, "Vidas: " + tiempoSinGolpe,
                //0.1f*Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO*0.9f);

        // Paletas recolectadas
        texto.mostrarMensaje(batch,"Paletas: "+puntos, (float) (Principal.ANCHO_MUNDO/4.2),Principal.ALTO_MUNDO*0.97f);

        // Helados recolectados
        texto.mostrarMensaje(batch,"Helados: "+vidas, (float) (Principal.ANCHO_MUNDO/2 - 0.8),Principal.ALTO_MUNDO*0.97f);

        // Payasos recolectados
        texto.mostrarMensaje(batch,"Payasos: "+quitavidas, (float) (Principal.ANCHO_MUNDO/1.3),Principal.ALTO_MUNDO*0.97f);
        batch.end();
    }

    public void probarChoque(){
        Rectangle a = paleta.getSprite().getBoundingRectangle();
        Rectangle b = Mael.getSprite().getBoundingRectangle();
        if (b.contains(a)) {
            puntos++;
            paleta.getSprite().setY(Principal.ALTO_MUNDO);
            //Gdx.app.log("probarChoque", "ChocaPaleta");
        }
            //sonido,desaparece paleta, contador suma

            Rectangle c = helado.getSprite().getBoundingRectangle();
            if (b.contains(c)) {
                vidas++;
                helado.getSprite().setY(Principal.ALTO_MUNDO);
                //Gdx.app.log("probarChoque", "ChocaHelado");
            }

                Rectangle d = payaso.getSprite().getBoundingRectangle();
                if (b.contains(d)) {
                    puntos=puntos-1;
                    payaso.getSprite().setY(Principal.ALTO_MUNDO);
                    //Gdx.app.log("probarChoque", "ChocaPayaso");
        }
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void actualizarMael() {
    }

    private void actualizarCamara() {
        float posX = Mael.getX();
        if (posX>=Principal.ANCHO_MUNDO/2 && posX<=ANCHO_MUNDO-Principal.ANCHO_MUNDO/2) {
            camara.position.set((int)posX, camara.position.y, 0);
        } else if (posX>ANCHO_MUNDO-Principal.ANCHO_MUNDO/2) {
            camara.position.set(ANCHO_MUNDO-Principal.ANCHO_MUNDO/2, camara.position.y, 0);
        }
        camara.update();
    }

    private void moverPersonaje(){
        switch (Mael.getEstadoMovimiento()) {
            case Inicia:

                break;
            case MovDer:
            case MovIzq:
            break;
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
       if ( musicaJuego.isPlaying() ) {
           musicaJuego.stop();
       }
    }

    @Override
    public void dispose() {
        AssetManager assetManager = principal.getAssetManager();
        assetManager.unload("SpriteCa.png");
        assetManager.unload("SPRITEPALETA.png");
        assetManager.unload("SPRITESHELADO.png");
        assetManager.unload("SpritesMiniPayasos.png");
        texturaFondo.dispose();
        texturaBtnPause.dispose();
        texturaMael.dispose();
        efectoAtrapa.dispose();
        musicaJuego.dispose();
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
                principal.setScreen((Screen) new PantallaPausa(principal));
        }
        //efectoAtrapa.play();

    }
    public class ProcesadorEntrada extends InputAdapter
    {
        private Vector3 coordenadas = new Vector3();
        private float x, y;


        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
           // if (EstadoMovimiento == EstadosJuego.Jugando) {
                if ((x >= 640)) {
                    Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovDer);
                    //Gdx.app.log("touchDown", "CaminaDerecha");
                } else {
                    Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.MovIzq);
                    //Gdx.app.log("touchDown", "CaminaIzquierda");
                    Mael.actualizar();
                }
           // }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Mael.setEstadoMovimiento(Personaje.EstadoMovimiento.Reposo);
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return true;
        }


        private void transformarCoordenadas(int screenX, int screenY) {
            // Transformar las coordenadas de la pantalla física a la cámara HUD
            coordenadas.set(screenX, screenY, 0);
            camara.unproject(coordenadas);
            // Obtiene las coordenadas relativas a la pantalla virtual
            x = coordenadas.x;
            y = coordenadas.y;
        }
    }


    public enum EstadosJuego {
        Gana,
        Jugando,
        Pausado,
        Perdio,
        Caer
    }
}
