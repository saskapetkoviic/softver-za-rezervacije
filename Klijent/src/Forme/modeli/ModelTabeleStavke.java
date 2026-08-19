package Forme.modeli;

import Model.StavkaRezervacije;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleStavke extends AbstractTableModel {
    private List<StavkaRezervacije> lista;
    private String[] kolone = {"RB", "Sto", "Cena", "Popust (%)", "Iznos"};

    public ModelTabeleStavke() {
        this.lista = new ArrayList<>();
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
        StavkaRezervacije stavka = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getSto().getOznaka();
            case 2:
                return stavka.getCena();
            case 3:
                return stavka.getPopust();
            case 4:
                return stavka.getIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavku(StavkaRezervacije stavka) {
        lista.add(stavka);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public List<StavkaRezervacije> getStavke() {
        return lista;
    }

    public double getUkupanIznos() {
        double ukupno = 0;
        for (StavkaRezervacije s : lista) {
            ukupno += s.getIznos();
        }
        return ukupno;
    }
}