package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Zaposleni;
import java.util.List;

public class PrijaviZaposleniSO extends ApstraktnaGenerickaOperacija{

    private Zaposleni zaposleni;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Zaposleni)){
            throw new Exception("Objekat nije tipa Zaposleni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Zaposleni z = (Zaposleni)obj;
        String uslov = " WHERE korisnickoIme='" + z.getKorisnickoIme() + "' AND sifra='" + z.getSifra() + "'";
        List<ApstraktniDomenskiObjekat> lista = broker.getAll(z,uslov);
        
        if(lista.isEmpty()){
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
        
        zaposleni = (Zaposleni) lista.get(0);
    }
    
    public Zaposleni getZaposleni(){
        return zaposleni;
    }
    
}
