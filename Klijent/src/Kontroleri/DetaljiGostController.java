package Kontroleri;

import Forme.DetaljiGostForma;
import Komunikacija.Komunikacija;
import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import Model.Mesto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class DetaljiGostController {
    private DetaljiGostForma forma;
    private Gost gost;

    public DetaljiGostController(DetaljiGostForma forma, Gost gost) {
        this.forma = forma;
        this.gost = gost;
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniComboMesta();
        if (gost != null) {
            popuniPolja();
        }
        forma.setVisible(true);
    }

    private void napuniComboMesta() {
        try {
            List<ApstraktniDomenskiObjekat> mesta = Komunikacija.getInstance().ucitajMesta();
            DefaultComboBoxModel model = new DefaultComboBoxModel(mesta.toArray());
            forma.getCmbMesto().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
        }
    }

    private void popuniPolja() {
        forma.getTxtIme().setText(gost.getIme());
        forma.getTxtPrezime().setText(gost.getPrezime());
        forma.getTxtTelefon().setText(gost.getTelefon());
        forma.getCmbMesto().setSelectedItem(gost.getMesto());
    }

    private void addActionsListener() {
        forma.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }
        });
    }

    private void sacuvaj() {
        try {
            String ime = forma.getTxtIme().getText();
            String prezime = forma.getTxtPrezime().getText();
            String telefon = forma.getTxtTelefon().getText();
            Mesto mesto = (Mesto) forma.getCmbMesto().getSelectedItem();

            if (gost == null) {
                Gost noviGost = new Gost(0, ime, prezime, telefon, mesto);
                Komunikacija.getInstance().kreirajGosta(noviGost);
                JOptionPane.showMessageDialog(forma, "Sistem je zapamtio gosta.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                gost.setIme(ime);
                gost.setPrezime(prezime);
                gost.setTelefon(telefon);
                gost.setMesto(mesto);
                Komunikacija.getInstance().promeniGosta(gost);
                JOptionPane.showMessageDialog(forma, "Sistem je zapamtio gosta.", "Obaveštenje" , JOptionPane.INFORMATION_MESSAGE);
            }
            Koordinator.Koordinator.getInstance().osveziPrikazGostiju();
            forma.dispose();
      } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti gosta.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}