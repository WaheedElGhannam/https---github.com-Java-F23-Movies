import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCastMemberGUI extends JFrame implements ActionListener {
    private JComboBox<String> movieSelector;
    private JTextField actorNameField;
    private JButton addButton;
    private ArrayList<Movie> listofmovies;

    public AddCastMemberGUI(ArrayList<Movie> listofmovies) {
        this.listofmovies = listofmovies;

        setTitle("Add Cast Member");

        // Create a list of movie titles
        String[] movieTitles = new String[listofmovies.size()];
        for (int i = 0; i < listofmovies.size(); i++) {
            movieTitles[i] = listofmovies.get(i).getTitle();
        }

        movieSelector = new JComboBox<>(movieTitles);
        actorNameField = new JTextField(20);
        addButton = new JButton("Add Cast Member");
        addButton.addActionListener(this);

        setLayout(new FlowLayout());
        add(new JLabel("Select a Movie:"));
        add(movieSelector);
        add(new JLabel("Actor's Name:"));
        add(actorNameField);
        add(addButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String selectedMovieTitle = (String) movieSelector.getSelectedItem();
            String actorName = actorNameField.getText();

            if (actorName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the actor's name.");
                return;
            }

            for (Movie movie : listofmovies) {
                if (movie.getTitle().equals(selectedMovieTitle)) {
                    if (movie.getCast().contains(actorName)) {
                        JOptionPane.showMessageDialog(this, "This actor is already in the cast.");
                    } else {
                        movie.getCast().add(actorName);
                        JOptionPane.showMessageDialog(this, "Actor added to the cast.");
                    }
                    break;
                }
            }
        }
    }
}