package mx.itesm.wayakkk;

import com.badlogic.gdx.Game;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p
public class Principal extends Game
{
    public static final float ANCHO_MUNDO = 1280;
    public static final float ALTO_MUNDO = 720;


    @Override
    public void create () {
        setScreen(new PantallaMenu(this));
    }

}