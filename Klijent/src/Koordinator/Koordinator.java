package Koordinator;

import Forme.GlavnaForma;
import Forme.LoginForma;
import Kontroleri.DetaljiGostController;
import Kontroleri.GlavnaFormaController;
import Kontroleri.IzmeniRezervacijuController;
import Kontroleri.KreirajRezervacijuController;
import Kontroleri.LoginController;
import Kontroleri.PrikazGostijuController;
import Kontroleri.PrikazRezervacijaController;
import Kontroleri.PrikazSmenaController;
import Kontroleri.UbaciSmenuController;
import Model.Zaposleni;

public class Koordinator {
    private static Koordinator instance;
    private PrikazGostijuController prikazGostijuController;
    private PrikazRezervacijaController prikazRezervacijaController;
    private Zaposleni ulogovani;

    private Koordinator() {
    }

    public static Koordinator getInstance() {
        if (instance == null)
            instance = new Koordinator();
        return instance;
    }
    
    public void otvoriLoginFormu() {
        LoginController lc = new LoginController(new LoginForma());
        lc.otvoriFormu();
    }
    
   public void otvoriGlavnuFormu() {
        GlavnaFormaController gfc = new GlavnaFormaController(new GlavnaForma());
        gfc.otvoriFormu();
    }
   
    public void otvoriPrikazGostiju() {
        prikazGostijuController = new PrikazGostijuController(new Forme.PrikazGostijuForma());
        prikazGostijuController.otvoriFormu();
    }

    public void osveziPrikazGostiju() {
        if (prikazGostijuController != null) {
            prikazGostijuController.napuniTabelu();
        }
    }

  public void otvoriDetaljiGost(Model.Gost gost) {
        DetaljiGostController dgc = new DetaljiGostController(new Forme.DetaljiGostForma(), gost);
        dgc.otvoriFormu();
    }

    public void otvoriPrikazRezervacija() {
        prikazRezervacijaController = new PrikazRezervacijaController(new Forme.PrikazRezervacijeForma());
        prikazRezervacijaController.otvoriFormu();
    }

    public void osveziPrikazRezervacija() {
        if (prikazRezervacijaController != null) {
            prikazRezervacijaController.napuniTabelu();
        }
    }

    public void otvoriIzmeniRezervaciju(Model.Rezervacija rezervacija) {
        IzmeniRezervacijuController irc = new IzmeniRezervacijuController(new Forme.IzmeniRezervacijuForma(), rezervacija);
        irc.otvoriFormu();
    }
   
   public void otvoriKreirajRezervaciju() {
        KreirajRezervacijuController krc = new KreirajRezervacijuController(new Forme.IzmeniRezervacijuForma());
        krc.otvoriFormu();
    }

    public void otvoriUbaciSmenu() {
        UbaciSmenuController usc = new UbaciSmenuController(new Forme.UbaciSmenuForma());
        usc.otvoriFormu();
    }
    
    public void otvoriPrikazSmene() {
        PrikazSmenaController psc = new PrikazSmenaController(new Forme.PrikazSmeneForma());
        psc.otvoriFormu();
    }
    
    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public static class getInstance {

        public getInstance() {
        }
    }
}