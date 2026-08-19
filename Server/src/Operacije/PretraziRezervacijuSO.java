package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Rezervacija;
import java.util.List;

public class PretraziRezervacijuSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

  @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) obj;
        String uslov = " JOIN zaposleni ON rezervacija.idZaposleni = zaposleni.idZaposleni"
                     + " JOIN gost ON rezervacija.idGost = gost.idGost"
                     + " WHERE rezervacija.status LIKE '%" + r.getStatus() + "%'";
        lista = broker.getAll(r, uslov);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }
    
}
