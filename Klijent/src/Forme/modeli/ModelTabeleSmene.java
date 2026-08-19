package Forme.modeli;

import Model.ApstraktniDomenskiObjekat;
import Model.Smena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleSmene extends AbstractTableModel {
    private List<ApstraktniDomenskiObjekat> lista;
    private String[] kolone = {"ID", "Vreme početka", "Vreme završetka"};

    public ModelTabeleSmene(List<ApstraktniDomenskiObjekat> lista) {
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
        Smena s = (Smena) lista.get(rowIndex);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch (columnIndex) {
            case 0: return s.getIdSmena();
            case 1: return s.getVremePocetka().format(formatter);
            case 2: return s.getVremeZavrsetka().format(formatter);
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Smena vratiSmenu(int row) {
        return (Smena) lista.get(row);
    }
}