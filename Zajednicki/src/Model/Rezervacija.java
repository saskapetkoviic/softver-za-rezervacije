package Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rezervacija implements ApstraktniDomenskiObjekat{
    private int idRezervacija;
    private LocalDateTime datumRezervacije;
    private LocalDateTime datumVremeDolaska;
    private String status;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Gost gost;
    private List<StavkaRezervacije> stavke;
    
    public Rezervacija(){
        stavke = new ArrayList<>();
    }
    
    public Rezervacija(int idRezervacija, LocalDateTime datumRezervacije, LocalDateTime datumVremeDolaska, String status, double ukupanIznos, Zaposleni zaposleni, Gost gost){
        this.idRezervacija = idRezervacija;
        this.datumRezervacije = datumRezervacije;
        this.datumVremeDolaska = datumVremeDolaska;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.gost = gost;
        this.stavke = new ArrayList<>();
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public LocalDateTime getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDateTime datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public LocalDateTime getDatumVremeDolaska() {
        return datumVremeDolaska;
    }

    public void setDatumVremeDolaska(LocalDateTime datumVremeDolaska) {
        this.datumVremeDolaska = datumVremeDolaska;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Rezervacija " + idRezervacija + " - " + gost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.idRezervacija;
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
        final Rezervacija other = (Rezervacija) obj;
        return this.idRezervacija == other.idRezervacija;
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idRezervacija");
            LocalDateTime datumRez = rs.getTimestamp("datumRezervacije").toLocalDateTime();
            LocalDateTime datumDol = rs.getTimestamp("datumVremeDolaska").toLocalDateTime();
            String status = rs.getString("status");
            double ukupanIznos = rs.getDouble("ukupanIznos");

            int idZaposleni = rs.getInt("idZaposleni");
            String imeZ = rs.getString(9);
            String prezimeZ = rs.getString(10);
            Zaposleni z = new Zaposleni(idZaposleni, imeZ, prezimeZ, null, null);

            int idGost = rs.getInt("idGost");
            String imeG = rs.getString(14);
            String prezimeG = rs.getString(15);
            Gost g = new Gost(idGost, imeG, prezimeG, null, null);

            Rezervacija r = new Rezervacija(id, datumRez, datumDol, status, ukupanIznos, z, g);
            lista.add(r);
        }
        return lista;
    }
    
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumRezervacije, datumVremeDolaska, status, ukupanIznos, idZaposleni, idGost";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datumRezervacije + "', '" + datumVremeDolaska + "', '" + status + "', " + ukupanIznos + ", " + zaposleni.getIdZaposleni() + ", " + gost.getIdGost();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRezervacija=" + idRezervacija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumRezervacije='" + datumRezervacije + "', datumVremeDolaska='" + datumVremeDolaska + "', status='" + status + "', ukupanIznos=" + ukupanIznos + ", idZaposleni=" + zaposleni.getIdZaposleni() + ", idGost=" + gost.getIdGost();
    }
}
