package Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StavkaRezervacije implements ApstraktniDomenskiObjekat{
    private int idRezervacija;
    private int rb;
    private double cena;
    private double popust;
    private double iznos;
    private String napomena;
    private Sto sto;
    
    public StavkaRezervacije(){
        
    }
    
    public StavkaRezervacije(int idRezervacija, int rb, double cena, double popust, double iznos, String napomena, Sto sto){
        this.idRezervacija = idRezervacija;
        this.rb = rb;
        this.cena = cena;
        this.popust = popust;
        this.iznos = iznos;
        this.napomena = napomena;
        this.sto = sto;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    @Override
    public String toString() {
        return "Sto " + sto + " - " + iznos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idRezervacija;
        hash = 53 * hash + this.rb;
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.idRezervacija != other.idRezervacija) {
            return false;
        }
        return this.rb == other.rb;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List <ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idRezervacija");
            int rb = rs.getInt("rb");
            double cena = rs.getDouble("cena");
            double popust = rs.getDouble("popust");
            double iznos = rs.getDouble("iznos");
            String napomena = rs.getString("napomena");
            
            int idSto = rs.getInt("idSto");
            String oznaka = rs.getString("oznaka");
            int kapacitet = rs.getInt("kapacitet");
            double cenaRezervacije = rs.getDouble("cenaRezervacije");
            Sto s = new Sto(idSto,oznaka,kapacitet,cenaRezervacije);
            
            StavkaRezervacije sr = new StavkaRezervacije(id, rb, cena, popust, iznos, napomena, s);
            lista.add(sr);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRezervacija, rb, cena, popust, iznos, napomena, idSto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return idRezervacija + ", " + rb + ", " + cena + ", " + popust + ", " + iznos + ", '" + napomena + "', " + sto.getIdSto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRezervacija=" + idRezervacija + " AND rb=" + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idRezervacija=" + idRezervacija + ", rb=" + rb + ", cena=" + cena + ", popust=" + popust + ", iznos=" + iznos + ", napomena='" + napomena + "', idSto=" + sto.getIdSto();
    }   
}
