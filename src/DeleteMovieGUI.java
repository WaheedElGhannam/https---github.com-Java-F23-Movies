import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteMovieGUI extends JFrame implements ActionListener {
    private ArrayList<Movie> listofmovies;
    private JList<String> movieList;
    private JButton deleteButton;

    public DeleteMovieGUI(ArrayList<Movie> listofmovies) {
        this.listofmovies = listofmovies;

        setTitle("Delete Movie");

        // Create a list of movie titles
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Movie movie : listofmovies) {
            model.addElement(movie.getTitle());
        }

        movieList = new JList<>(model);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        deleteButton = new JButton("Delete Movie");
        deleteButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(new JScrollPane(movieList), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String selectedTitle = movieList.getSelectedValue();
            if (selectedTitle != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this movie?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Movie selectedMovie = null;
                    for (Movie movie : listofmovies) {
                        if (movie.getTitle().equals(selectedTitle)) {
                            selectedMovie = movie;
                            break;
                        }
                    }
                    if (selectedMovie != null) {
                        listofmovies.remove(selectedMovie);
                        DefaultListModel<String> model = (DefaultListModel<String>) movieList.getModel();
                        model.removeElement(selectedTitle);
                    }
                }
            }
        }
    }
}