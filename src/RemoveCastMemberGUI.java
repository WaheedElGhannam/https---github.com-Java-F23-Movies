import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveCastMemberGUI extends JFrame implements ActionListener {
    private JComboBox<String> movieSelector;
    private JComboBox<String> castSelector;
    private JButton removeButton;
    private ArrayList<Movie> listofmovies;

    public RemoveCastMemberGUI(ArrayList<Movie> listofmovies) {
        this.listofmovies = listofmovies;

        setTitle("Remove Cast Member");

        // Create a list of movie titles
        String[] movieTitles = new String[listofmovies.size()];
        for (int i = 0; i < listofmovies.size(); i++) {
            movieTitles[i] = listofmovies.get(i).getTitle();
        }

        movieSelector = new JComboBox<>(movieTitles);
        castSelector = new JComboBox<>();
        updateCastSelector(); // Update the cast list based on the selected movie
        movieSelector.addActionListener(this);

        removeButton = new JButton("Remove Cast Member");
        removeButton.addActionListener(this);

        setLayout(new FlowLayout());
        add(new JLabel("Select a Movie:"));
        add(movieSelector);
        add(new JLabel("Select a Cast Member:"));
        add(castSelector);
        add(removeButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == movieSelector) {
            updateCastSelector();
        } else if (e.getSource() == removeButton) {
            String selectedMovieTitle = (String) movieSelector.getSelectedItem();
            String selectedCastMember = (String) castSelector.getSelectedItem();

            if (selectedCastMember == null) {
                JOptionPane.showMessageDialog(this, "Please select a cast member to remove.");
                return;
            }

            for (Movie movie : listofmovies) {
                if (movie.getTitle().equals(selectedMovieTitle)) {
                    movie.getCast().remove(selectedCastMember);
                    JOptionPane.showMessageDialog(this, "Cast member removed.");
                    updateCastSelector(); // Update the cast list after removal
                    break;
                }
            }
        }
    }

    private void updateCastSelector() {
        String selectedMovieTitle = (String) movieSelector.getSelectedItem();
        castSelector.removeAllItems();

        if (selectedMovieTitle != null) {
            for (Movie movie : listofmovies) {
                if (movie.getTitle().equals(selectedMovieTitle)) {
                    for (String castMember : movie.getCast()) {
                        castSelector.addItem(castMember);
                    }
                    break;
                }
            }
        }
    }
}