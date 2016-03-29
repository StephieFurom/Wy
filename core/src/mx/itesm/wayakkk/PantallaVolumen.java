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

        public class PantallaVolumen implements Screen
        {
            private final Principal principal;
            private OrthographicCamera camara;
            private Viewport vista;


            private Texture texturaFondo;
            private Sprite spriteFondo;


            private Texture texturaBtnMas;
            private Sprite spriteBtnMas;


            private Texture texturaBtnMenos;
            private Sprite spriteBtnMenos;

            private Texture texturaBtnReturn;
            private Sprite spriteBtnReturn;


            private SpriteBatch batch;

            public PantallaVolumen(Principal principal) {
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

                texturaFondo = new Texture(Gdx.files.internal("PANTALLAfonbn.png"));
                spriteFondo = new Sprite(texturaFondo);


                texturaBtnMas = new Texture(Gdx.files.internal("MASSON.png"));
                spriteBtnMas = new Sprite(texturaBtnMas);
                spriteBtnMas.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMas.getWidth() / 2,
                        (float) (Principal.ALTO_MUNDO / 1.75));

                texturaBtnMenos = new Texture(Gdx.files.internal("MENOSSON.png"));
                spriteBtnMenos = new Sprite(texturaBtnMenos);
                spriteBtnMenos.setPosition((float) (Principal.ANCHO_MUNDO / 2 - spriteBtnMenos.getWidth() / 2),
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
                spriteBtnMas.draw(batch);
                spriteBtnMenos.draw(batch);
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

                    if ( touchX>=spriteBtnMas.getX() &&
                            touchX<spriteBtnMas.getX()+spriteBtnMas.getWidth()
                            && touchY>=spriteBtnMas.getY()
                            && touchY<=spriteBtnMas.getY()+spriteBtnMas.getHeight() ) {
                        principal.setScreen(new PantallaVolumen(principal));
                    }

                    if ( touchX>=spriteBtnMenos.getX() &&
                            touchX<=spriteBtnMenos.getX()+spriteBtnMenos.getWidth()
                            && touchY>=spriteBtnMenos.getY()
                            && touchY<=spriteBtnMenos.getY()+spriteBtnMenos.getHeight() ) {
                        principal.setScreen((Screen) new PantallaVolumen(principal));

                    }
                    if ( touchX>=spriteBtnReturn.getX() &&
                            touchX<=spriteBtnReturn.getX()+spriteBtnReturn.getWidth()
                            && touchY>=spriteBtnReturn.getY()
                            && touchY<=spriteBtnReturn.getY()+spriteBtnReturn.getHeight() )
                        principal.setScreen((Screen) new PantallaAjustes(principal));
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

            }

            @Override
            public void dispose() {

                texturaFondo.dispose();
            }
        }
