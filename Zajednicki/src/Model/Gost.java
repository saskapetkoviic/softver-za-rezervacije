package Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gost implements ApstraktniDomenskiObjekat {
    private int idGost;
    private String ime;
    private String prezime;
    private String telefon;
    private Mesto mesto;
    
    public Gost(){
        
    }
    
    public Gost(int idGost, String ime, String prezime, String telefon, Mesto mesto){
        this.idGost = idGost;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public int getIdGost() {
        return idGost;
    }

    public void setIdGost(int idGost) {
        this.idGost = idGost;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idGost;
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
        final Gost other = (Gost) obj;
        return this.idGost == other.idGost;
    }

    @Override
    public String vratiNazivTabele() {
        return "gost";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List <ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idGost");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String telefon = rs.getString("telefon");
            
            int idMesto = rs.getInt("idMesto");
            String nazivMesta = rs.getString("naziv");
            String pb = rs.getString("postanskiBroj");
            Mesto m = new Mesto(idMesto, nazivMesta,pb);
            
            Gost g = new Gost(id,ime,prezime,telefon,m);
            lista.add(g);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, telefon, idMesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + telefon + "', " + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idGost=" + idGost;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', telefon='" + telefon + "', idMesto=" + mesto.getIdMesto();
    } 
}
