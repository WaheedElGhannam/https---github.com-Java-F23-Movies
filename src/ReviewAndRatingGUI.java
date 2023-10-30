import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReviewAndRatingGUI extends JFrame {
    private JComboBox<String> movieComboBox;
    private JTextArea reviewTextArea;
    private JSpinner ratingSpinner;
    private ArrayList<Movie> listofmovies;
    User user;
    Testdata start;

    public ReviewAndRatingGUI(ArrayList<Movie> listofmovies, User user, Testdata start) {
        this.listofmovies = listofmovies;
        this.user = user;
        this.start = start;
        setTitle("Leave Review and Rating");
        setSize(400, 600);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel movieLabel = new JLabel("Movie:");
        panel.add(movieLabel);

        String[] movieTitles = listofmovies.stream().map(Movie::getTitle).toArray(String[]::new);
        movieComboBox = new JComboBox<>(movieTitles);
        panel.add(movieComboBox);

        JLabel reviewLabel = new JLabel("Review:");
        panel.add(reviewLabel);

        reviewTextArea = new JTextArea();
        reviewTextArea.setLineWrap(true);
        JScrollPane reviewScrollPane = new JScrollPane(reviewTextArea);
        panel.add(reviewScrollPane);

        JLabel ratingLabel = new JLabel("Rating:");
        panel.add(ratingLabel);

        ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        panel.add(ratingSpinner);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = (String) movieComboBox.getSelectedItem();
                Movie selectedMovie = getMovieByTitle(selectedTitle);

                if (selectedMovie != null) {
                    String review = reviewTextArea.getText();
                    int rating = (int) ratingSpinner.getValue();

                    selectedMovie.addReview(review);
                    selectedMovie.addRating(rating);

                    JOptionPane.showMessageDialog(ReviewAndRatingGUI.this, "Review and Rating submitted.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close the window
                    dispose();
                }
            }
        });

        // Add tooltips
        movieLabel.setToolTipText("Select a movie from the list");
        reviewLabel.setToolTipText("Leave your review here");
        ratingLabel.setToolTipText("Select a rating from 1 to 5");

        panel.add(submitButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private Movie getMovieByTitle(String title) {
        for (Movie movie : listofmovies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
}