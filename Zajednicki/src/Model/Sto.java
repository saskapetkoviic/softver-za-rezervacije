package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sto implements ApstraktniDomenskiObjekat {
    private int idSto;
    private String oznaka;
    private int kapacitet;
    private double cenaRezervacije;
    
    public Sto(){

   }

    public Sto(int idSto, String oznaka, int kapacitet, double cenaRezervacije){
    this.idSto = idSto;
    this.oznaka = oznaka;
    this.kapacitet = kapacitet;
    this.cenaRezervacije = cenaRezervacije;
   }

    public int getIdSto() {
        return idSto;
    }

    public void setIdSto(int idSto) {
        this.idSto = idSto;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public double getCenaRezervacije() {
        return cenaRezervacije;
    }

    public void setCenaRezervacije(double cenaRezervacije) {
        this.cenaRezervacije = cenaRezervacije;
    }

    @Override
    public String toString() {
        return oznaka;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.idSto;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sto other = (Sto) obj;
        return this.idSto == other.idSto;
    }

    @Override
    public String vratiNazivTabele() {
        return "sto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List <ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idSto");
            String oznaka = rs.getString("oznaka");
            int kapacitet = rs.getInt("kapacitet");
            double cenaRezervacije = rs.getDouble("cenaRezervacije");
            Sto s = new Sto(id, oznaka, kapacitet, cenaRezervacije);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "oznaka,kapacitet,cenaRezervacije"; 
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + oznaka + "'" + kapacitet + ", " + cenaRezervacije;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idSto=" + idSto;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "oznaka='" + oznaka + "', kapacitet=" + kapacitet + ", cenaRezervacije=" + cenaRezervacije;
    }
}



