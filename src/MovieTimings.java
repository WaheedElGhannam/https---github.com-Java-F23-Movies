import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MovieTimings extends JFrame {
    public MovieTimings(Movie movie) {
        setTitle(movie.getTitle() + " - Movie Info");
        setSize(6000, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create labels or components to display the movie information here
        JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
        JLabel screenings = new JLabel("Screenings: " + movie.getScreens());
        JLabel timings = new JLabel("Timings " + movie.getTimings());
        // Add more labels as needed

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(titleLabel);
        panel.add(screenings);
        panel.add(timings);
        // Add more components as needed

        add(panel);
    }
}