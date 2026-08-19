package Model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

public interface ApstraktniDomenskiObjekat extends Serializable {
    public String vratiNazivTabele();
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    public String vratiKoloneZaUbacivanje();
    public String vratiVrednostiZaUbacivanje();
    public String vratiPrimarniKljuc();
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    public String vratiVrednostiZaIzmenu();
}