package Forme.modeli;

import Model.ApstraktniDomenskiObjekat;
import Model.Rezervacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleRezervacije extends AbstractTableModel {
    private List<ApstraktniDomenskiObjekat> lista;
    private String[] kolone = {"ID", "Datum dolaska", "Status", "Ukupan iznos", "Zaposleni", "Gost"};

    public ModelTabeleRezervacije(List<ApstraktniDomenskiObjekat> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = (Rezervacija) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRezervacija();
            case 1:
                return r.getDatumVremeDolaska();
            case 2:
                return r.getStatus();
            case 3:
                return r.getUkupanIznos();
            case 4:
                return r.getZaposleni();
            case 5:
                return r.getGost();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Rezervacija vratiRezervaciju(int row) {
        return (Rezervacija) lista.get(row);
    }
}