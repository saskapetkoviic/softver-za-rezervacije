package Controller;

import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import Model.Mesto;
import Model.Rezervacija;
import Model.Smena;
import Model.Sto;
import Model.Zaposleni;
import Operacije.*;
import java.util.List;

public class Controller {
    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public Zaposleni prijaviZaposleni(Zaposleni z) throws Exception {
        PrijaviZaposleniSO so = new PrijaviZaposleniSO();
        so.izvrsi(z, null);
        return so.getZaposleni();
    }

    public List<ApstraktniDomenskiObjekat> ucitajMesta() throws Exception {
        UcitajMestaSO so = new UcitajMestaSO();
        so.izvrsi(new Mesto(), null);
        return so.getLista();
    }

    public List<ApstraktniDomenskiObjekat> ucitajStolove() throws Exception {
        UcitajStoloveSO so = new UcitajStoloveSO();
        so.izvrsi(new Sto(), null);
        return so.getLista();
    }

    public List<ApstraktniDomenskiObjekat> ucitajZaposlene() throws Exception {
        UcitajZaposleneSO so = new UcitajZaposleneSO();
        so.izvrsi(new Zaposleni(), null);
        return so.getLista();
    }

    public void kreirajGosta(Gost g) throws Exception {
        KreirajGostaSO so = new KreirajGostaSO();
        so.izvrsi(g, null);
    }

    public void promeniGosta(Gost g) throws Exception {
        PromeniGostaSO so = new PromeniGostaSO();
        so.izvrsi(g, null);
    }

    public void obrisiGosta(Gost g) throws Exception {
        ObrisiGostaSO so = new ObrisiGostaSO();
        so.izvrsi(g, null);
    }

    public List<ApstraktniDomenskiObjekat> ucitajGoste() throws Exception {
        UcitajGosteSO so = new UcitajGosteSO();
        so.izvrsi(new Gost(), null);
        return so.getLista();
    }

    public List<ApstraktniDomenskiObjekat> pretraziGosta(Gost g) throws Exception {
        PretraziGostaSO so = new PretraziGostaSO();
        so.izvrsi(g, null);
        return so.getLista();
    }

    public void ubaciSmenu(Smena s) throws Exception {
        UbaciSmenuSO so = new UbaciSmenuSO();
        so.izvrsi(s, null);
    }
    
    public List<ApstraktniDomenskiObjekat> pretraziSmenu() throws Exception {
        PretraziSmenuSO so = new PretraziSmenuSO();
        so.izvrsi(new Smena(), null);
        return so.getLista();
    }

    public void kreirajRezervaciju(Rezervacija r) throws Exception {
        KreirajRezervacijuSO so = new KreirajRezervacijuSO();
        so.izvrsi(r, null);
    }

    public void promeniRezervaciju(Rezervacija r) throws Exception {
        PromeniRezervacijuSO so = new PromeniRezervacijuSO();
        so.izvrsi(r, null);
    }

    public List<ApstraktniDomenskiObjekat> pretraziRezervaciju(Rezervacija r) throws Exception {
        PretraziRezervacijuSO so = new PretraziRezervacijuSO();
        so.izvrsi(r, null);
        return so.getLista();
    }
}