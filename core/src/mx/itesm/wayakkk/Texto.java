package mx.itesm.wayakkk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import PeasMisterGiggles.PeasMisterGiggles;

/**
 * Created by Stephie Furom on 15/03/2016.
 */
public class Texto {

    private PeasMisterGiggles font;

    public Texto() {
        font = new PeasMisterGiggles(Gdx.files.internal("LetraJuego.fnt"));
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y) {
        GlyphLayout glyp = new GlyphLayout();
        glyp.setText(font, mensaje);
        float anchoTexto = glyp.width;
        font.draw(batch,glyp,x-anchoTexto/2,y);
    }
}
