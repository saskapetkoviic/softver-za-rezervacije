package Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Smena implements ApstraktniDomenskiObjekat {
    private int idSmena;
    private LocalDateTime vremePocetka;
    private LocalDateTime vremeZavrsetka;
    
    public Smena(){
        
    }
    
    public Smena(int idSmena, LocalDateTime vremePocetka, LocalDateTime vremeZavrsetka){
        this.idSmena = idSmena;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalDateTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalDateTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    @Override
    public String toString() {
        return vremePocetka + " - " + vremeZavrsetka;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idSmena;
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
        final Smena other = (Smena) obj;
        return this.idSmena == other.idSmena;
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List <ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idSmena");
            LocalDateTime pocetak = rs.getTimestamp("vremePocetka").toLocalDateTime();
            LocalDateTime zavrsetak = rs.getTimestamp("vremeZavrsetka").toLocalDateTime();
            Smena s = new Smena(id,pocetak,zavrsetak);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "vremePocetka, vremeZavrsetka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + vremePocetka + "', '" + vremeZavrsetka + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idSmena=" + idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "vremePocetka='" + vremePocetka + "', vremeZavrsetka='" + vremeZavrsetka + "'";
    }    
}
