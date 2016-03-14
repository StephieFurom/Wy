import mx.itesm.wayakkk.PantallaAcercaDe;
import mx.itesm.wayakkk.PantallaJuego;
import mx.itesm.wayakkk.PantallaMenu;

/**
 * Created by Stephie Furom on 14/03/2016.
 */
public class AdministradorDeEscenas {

    private static final AdministradorDeEscenas INSTANCE = new AdministradorDeEscenas();

    protected ControlJuego actividadJuego;

    private PantallaMenu PantallaMenu;
    private PantallaMenu PantallaAcercaDe;
    private PantallaMenu PantallaJuego;
    private PantallaMenu PantallaGameOver;


    private TipoEscena tipoEscenaActual = TipoEscena.ESCENA_SPLASH;
    private EscenaBase escenaActual;
    public Engine engine;

    public static void inicializarAdministrador(ControlJuego Actividad, Engine engine) {
        getInstance().Actividad = Actividad;
        getInstance().engine = engine;
    }

    public static AdministradorDeEscenas getInstance() {
        return INSTANCE;
    }

    public TipoEscena getTipoEscenaActual() {
        return tipoEscenaActual;
    }

    public PantallaMenu getEscenaActual() {
        return escenaActual;
    }


    private void setPantallaMenu(PantallaMenu nueva) {
        engine.setScene(nueva);

        escenaActual = nueva;
        tipoEscenaActual = nueva.getTipoPantalla();
    }

    public void setPantalla(TipoPantalla nuevoTipo) {
        switch (nuevoTipo) {
            case PANTALLA_MENU:
                setPantallaMenu(PantallaMenu);
                break;
            case PANTALLA_ACERCA_DE:
                setPantallaAcercaDe(PantallaAcercaDe);
                break;
            case PANTALLA_JUEGO:
                setPantallaJuego(PantallaJuego);
                break;
            case GAMEOVER:
                setPantallaGameOver(PantallaGameOver);
                break;
        }
    }


    public void crearPantallaMenu() {
        PantallaMenu = new PantallaMenu();
    }

    public void liberarPantallaMenu() {
        PantallaMenu.liberarEscena();
        PantallaMenu = null;
    }

    public void crearPantallaAcercaDe() {
        PantallaAcercaDe = new PantallaAcercaDe();
    }

    public void liberarPantallaAcercaDe() {
        PantallaAcercaDe.liberarEscena();
        PantallaAcercaDe = null;
    }

    public void crearPantallaJuego() {
        PantallaJuego = new PantallaJuego();
    }

    public void liberarPantallaJuego() {
        PantallaJuego.liberarEscena();
        PantallaJuego = null;

    public void crearGameover() {
        PantallaGameOver= new PantallaGameOver();
    }

    public void liberarGameOver() {
        PantallaGameOver.liberarEscena();
        PantallaGameOver = null;
}
}
