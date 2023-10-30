import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeatSelectionGUI extends JFrame implements ActionListener {
    private ArrayList<Movie> listofmovies;
    private JComboBox<String> movieSelector;
    private JComboBox<String> screenSelector;
    private JTextField ticketsField;
    private JButton purchaseButton;
    Testdata start;
    User user;

    public SeatSelectionGUI(ArrayList<Movie> listofmovies, Testdata start, User user) {
        this.listofmovies = listofmovies;
        this.start = start;
        this.user = user;

        setTitle("Purchase Tickets");

        // Create a list of movie titles
        String[] movieTitles = new String[listofmovies.size()];
        for (int i = 0; i < listofmovies.size(); i++) {
            movieTitles[i] = listofmovies.get(i).getTitle();
        }
        String[] Screennames = new String[start.screens.size()];
        for (int i = 0; i < start.screens.size(); i++){
            Screennames[i] = start.getlistofmovies().get(i).getScreenNames().get(i);
        }


        movieSelector = new JComboBox<>(movieTitles);
        screenSelector = new JComboBox<>(Screennames);
        ticketsField = new JTextField(5);
        purchaseButton = new JButton("Purchase Tickets");
        purchaseButton.addActionListener(this);

        setLayout(new FlowLayout());
        add(new JLabel("Select a Movie:"));
        add(movieSelector);
        add(new JLabel("Select a Screen:"));
        add(screenSelector);
        add(new JLabel("Number of Tickets:"));
        add(ticketsField);
        add(purchaseButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == purchaseButton) {
            String selectedMovieTitle = (String) movieSelector.getSelectedItem();
            String selectedScreenName = (String) screenSelector.getSelectedItem();
            int numTickets;

            try {
                numTickets = Integer.parseInt(ticketsField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of tickets");
                return;
            }

            if (numTickets <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive number of tickets.");
                return;
            }

            Movie selectedMovie = null;
            Screen selectedScreen = null;

            for (Movie movie : listofmovies) {
                if (movie.getTitle().equals(selectedMovieTitle)) {
                    selectedMovie = movie;
                    for (Screen screen : movie.getScreens()) {
                        if (screen.getName().equals(selectedScreenName)) {
                            selectedScreen = screen;
                            break;
                        }
                    }
                    break;
                }
            }

            if (selectedMovie != null && selectedScreen != null) {
                if (numTickets <= selectedScreen.getCapacity() - selectedScreen.getNumberOfTickets()) {
                    selectedScreen.setNumberOfTickets(selectedScreen.getNumberOfTickets() + numTickets);
                    JOptionPane.showMessageDialog(this, "Tickets purchased successfully! Seats available " + (selectedScreen.getCapacity() - selectedScreen.getNumberOfTickets()));
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough available seats, Number of seats available: " + (selectedScreen.getCapacity() - selectedScreen.getNumberOfTickets()));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid selection.");
            }
        }
    }

    private void updateScreenSelector(String selectedMovieTitle) {
        screenSelector.removeAllItems();

        for (Movie movie : listofmovies) {
            if (movie.getTitle().equals(selectedMovieTitle)) {
                for (Screen screen : movie.getScreens()) {
                    screenSelector.addItem(screen.getName());
                }
                break;
            }
        }
    }
}