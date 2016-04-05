package mx.itesm.wayakkk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by Stephie Furom on 17/02/2016.
 */
//p
public class Principal extends Game
{
    public static final float ANCHO_MUNDO = 1280;
    public static final float ALTO_MUNDO = 720;

    private final AssetManager assetManager = new AssetManager();


    @Override
    public void create() {
        setScreen(new PantallaMenu(this));
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.clear();
    }

    public void setScreen(PantallaGana pantallaGana) {

    }
}