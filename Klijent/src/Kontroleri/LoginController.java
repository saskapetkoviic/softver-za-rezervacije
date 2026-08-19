package Kontroleri;

import Forme.LoginForma;
import Komunikacija.Komunikacija;
import Koordinator.Koordinator;
import Model.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginForma loginForma;

    public LoginController(LoginForma loginForma) {
        this.loginForma = loginForma;
        addActionsListener();
    }

    public void otvoriFormu() {
        loginForma.setVisible(true);
    }

    private void addActionsListener() {
        loginForma.getBtnPrijava().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava();
            }
        });
    }
    

    private void prijava() {
        try {
            String korisnickoIme = loginForma.getTxtKorisnickoIme().getText();
            String sifra = loginForma.getSifra();

            Komunikacija.getInstance().konekcija();
            Zaposleni z = Komunikacija.getInstance().login(korisnickoIme, sifra);

            Koordinator.getInstance().setUlogovani(z);
            JOptionPane.showMessageDialog(loginForma, "Korisničko ime i šifra su ispravni.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.getInstance().otvoriGlavnuFormu();
            loginForma.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginForma, "Korisničko ime i šifra nisu ispravni.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}