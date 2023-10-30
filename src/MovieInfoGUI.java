import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MovieInfoGUI extends JFrame {
    public MovieInfoGUI(Movie movie) {
        setTitle(movie.getTitle() + " - Movie Info");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);

        // Create labels or components to display the movie information here
        JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
        JLabel yearLabel = new JLabel("Year: " + movie.getYear());
        JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
        JLabel castLabel = new JLabel("Cast: " + movie.getCast());
        JTextArea plot = new JTextArea("Plot: " + movie.getPlot());
        JLabel director = new JLabel("Director: " + movie.getcrew());
        JLabel timings = new JLabel("Timings: " + movie.getTimings());
        JLabel screenings = new JLabel("Screenings: " + movie.getScreenNames());
        JLabel review = new JLabel("Reviews: " + movie.getreviews());
        JLabel avgrating = new JLabel("Avg Rating: " + movie.averageratings());
        plot.setLineWrap(true);
        plot.setWrapStyleWord(true);
        Font customFont = new Font("Arial", Font.BOLD, 12);
        plot.setFont(customFont);
        plot.setEditable(false);
        // Add more labels as needed

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(titleLabel);
        panel.add(genreLabel);
        panel.add(castLabel);
        panel.add(director);
        panel.add(timings);
        panel.add(screenings);
        panel.add(yearLabel);
        panel.add(plot);
        panel.add(review);
        panel.add(avgrating);
        // Add more components as needed

        pack();
        add(panel);
    }
}