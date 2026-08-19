package Kontroleri;

import Forme.UbaciSmenuForma;
import Komunikacija.Komunikacija;
import Model.Smena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class UbaciSmenuController {
    private UbaciSmenuForma forma;

    public UbaciSmenuController(UbaciSmenuForma forma) {
        this.forma = forma;
        addActionsListener();
    }

    public void otvoriFormu() {
        forma.setVisible(true);
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
            String pocetakStr = forma.getTxtPocetak().getText();
            String zavrsetakStr = forma.getTxtZavrsetak().getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime pocetak = LocalDateTime.parse(pocetakStr, formatter);
            LocalDateTime zavrsetak = LocalDateTime.parse(zavrsetakStr, formatter);

            Smena smena = new Smena(0, pocetak, zavrsetak);
            Komunikacija.getInstance().ubaciSmenu(smena);

            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio smenu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti smenu.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}