import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMovieGUI extends JFrame implements ActionListener {
    private JLabel nameLabel, yearLabel, plotLabel, genreLabel, castLabel, timingsLabel, directorLabel;
    private JTextField nameField, yearField, plotField, genreField, castField, timingsField, directorField;
    private ArrayList<String> cast;
    private ArrayList<String> timings;
    User user;
    Testdata start;

    public AddMovieGUI(ArrayList<Movie> movies, User user, Testdata start) {
        this.start = start;
        this.user = user;
        setTitle("Add Movie");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nameLabel = new JLabel("Name of Movie:");
        yearLabel = new JLabel("Year:");
        plotLabel = new JLabel("Plot:");
        genreLabel = new JLabel("Genre:");
        castLabel = new JLabel("Cast Member:");
        timingsLabel = new JLabel("Timings:");
        directorLabel = new JLabel("Director:");

        nameField = new JTextField(20);
        yearField = new JTextField(20);
        plotField = new JTextField(20);
        genreField = new JTextField(20);
        castField = new JTextField(20);
        timingsField = new JTextField(20);
        directorField = new JTextField(20);

        cast = new ArrayList<>();
        timings = new ArrayList<>();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JButton addTimingButton = new JButton("Add Timings");
        addTimingButton.addActionListener(this);

        JButton addCastButton = new JButton("Add Cast Member");
        addCastButton.addActionListener(this);

        // Add tooltips
        nameField.setToolTipText("Enter the name of the movie");
        yearField.setToolTipText("Enter the release year (as a number)");
        plotField.setToolTipText("Enter the plot of the movie");
        genreField.setToolTipText("Enter the genre of the movie");
        castField.setToolTipText("Enter a cast member name");
        timingsField.setToolTipText("Enter a timing");
        directorField.setToolTipText("Enter the name of the director");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridy = 1;
        add(yearLabel, gbc);

        gbc.gridy = 2;
        add(plotLabel, gbc);

        gbc.gridy = 3;
        add(genreLabel, gbc);

        gbc.gridy = 4;
        add(castLabel, gbc);

        gbc.gridy = 5;
        add(timingsLabel, gbc);

        gbc.gridy = 6;
        add(directorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        gbc.gridy = 1;
        add(yearField, gbc);

        gbc.gridy = 2;
        add(plotField, gbc);

        gbc.gridy = 3;
        add(genreField, gbc);

        gbc.gridy = 4;
        add(castField, gbc);

        gbc.gridy = 5;
        add(timingsField, gbc);

        gbc.gridy = 6;
        add(directorField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(addCastButton, gbc);

        gbc.gridy = 5;
        add(addTimingButton, gbc);

        gbc.gridy = 7;
        add(submitButton, gbc);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Cast Member")) {
            String castMember = castField.getText().trim();
            if (!castMember.isEmpty() && !cast.contains(castMember)) {
                cast.add(castMember);
                castField.setText("");
                JOptionPane.showMessageDialog(this, "Cast Member Added");
            }else{
                JOptionPane.showMessageDialog(this, "Try Again");
            }
        } else if (e.getActionCommand().equals("Add Timings")) {
            String timing = timingsField.getText().trim();
            if (!timing.isEmpty() && !timings.contains(timing)) {
                timings.add(timing);
                timingsField.setText("");
                JOptionPane.showMessageDialog(this,"Timing added");
            }else{
                JOptionPane.showMessageDialog(this,"Try Again");
            }
        } else if (e.getActionCommand().equals("Submit")) {
            String name = nameField.getText().trim();
            String yearString = yearField.getText().trim();
            String plot = plotField.getText().trim();
            String genre = genreField.getText().trim();
            String director = directorField.getText().trim();

            if (name.isEmpty() || yearString.isEmpty() || plot.isEmpty() || genre.isEmpty() || director.isEmpty() || cast.isEmpty() || timings.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields and add at least one cast member and one timing.");
            } else {
                int year = Integer.parseInt(yearString);
                String movieDetails = "Name: " + name + "\nYear: " + year + "\nPlot: " + plot + "\nGenre: " + genre + "\nDirector: " + director;
                JOptionPane.showMessageDialog(this, "Movie Added!\n\n" + movieDetails);
                Movie movie = new Movie(name, year, genre, cast, plot, timings, start.screens, director);
                start.addMovie(movie);
            }
        }
    }
}