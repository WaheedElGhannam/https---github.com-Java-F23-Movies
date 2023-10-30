import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchByGenreGUIAdmin extends JFrame implements ActionListener {
    Testdata start;
    private JLabel searchLabel;
    private JTextField genreField;
    private JButton searchButton;
    private JTable resultsTable;
    private User user;
    private ArrayList<Movie> movieList;
    private JPanel optionsPanel;
    JButton addmovie;
    JButton editmovie;
    JButton genremovie;
    JButton logoutButton;
    JButton exitButton;
    JButton searchgenre;
    JButton deletemovie;
    JButton removecastmember;
    JButton removecrew;

    public SearchByGenreGUIAdmin(ArrayList<Movie> movieList, User user, Testdata start) {
        this.movieList = movieList;
        this.user = user;
        this.start = start;
        setTitle("Search Movies by Genre");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        searchLabel = new JLabel("Search Genre:");
        genreField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setBackground(getBackground().BLACK);
        searchButton.setForeground(getForeground().white);
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addmovie = new JButton("Add Movie to Database");
        addmovie.addActionListener(this);
        searchgenre = new JButton("Search for movies by Genre");
        searchgenre.addActionListener(this);
        deletemovie = new JButton("Delete Movie From Database");
        deletemovie.addActionListener(this);
        removecastmember = new JButton("Remove Cast Members");
        removecastmember.addActionListener(this);
        removecrew = new JButton("Remove Crew Member");
        removecrew.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        optionsPanel.add(addmovie);
        optionsPanel.add(searchgenre);
        optionsPanel.add(deletemovie);
        optionsPanel.add(removecastmember);
        optionsPanel.add(logoutButton);
        optionsPanel.add(exitButton);
        JScrollPane scrollPane1 = new JScrollPane(optionsPanel);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(genreField);
        searchPanel.add(searchButton);

        setLayout(new BorderLayout());

        add(searchPanel, BorderLayout.SOUTH);
        add(scrollPane1, BorderLayout.NORTH);

        String[] columnNames = {"Title", "Genre", "Year"};
        Object[][] data = new Object[0][3]; // Initialize with empty data

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        resultsTable = new JTable(model);
        resultsTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsTable.getTableHeader().setReorderingAllowed(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String genreToSearch = genreField.getText();
            if (genreToSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a genre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Filter movies by genre
            ArrayList<Movie> filteredMovies = movieList.stream()
                    .filter(movie -> movie.getGenre().equalsIgnoreCase(genreToSearch))
                    .collect(Collectors.toCollection(ArrayList::new));
                    if (filteredMovies.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No movies in genre " + genreToSearch, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
            // Update the table with search results
            DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
            model.setRowCount(0);

            for (Movie movie : filteredMovies) {
                model.addRow(new Object[]{movie.getTitle(), movie.getGenre(), movie.getYear()});
            }
        }if(e.getSource() == addmovie){
            dispose();
            ViewGUI viewGUI = new ViewGUI(start.getlistofmovies(), user, start);
            viewGUI.setVisible(true);
        }else if(e.getSource() == logoutButton){
            dispose(); // Close the MainGUI
            LoginGUI loginGUI = new LoginGUI(start);
            loginGUI.setVisible(true);
        }else if(e.getSource() == exitButton){
            System.exit(0);
        }else if(e.getSource() == searchgenre){
            dispose();
            SearchByGenreGUI Search1 = new SearchByGenreGUI(start.getlistofmovies(), user, start);
            Search1.setVisible(true);
        } else if(e.getSource() == removecastmember){
            dispose();
            AddFavoriteGUI addFavoriteGUI = new AddFavoriteGUI(user, start.getlistofmovies(), start);
            addFavoriteGUI.setVisible(true);
        } else if(e.getSource() == removecrew){
            dispose();
            RemoveFavoriteGUI removeFavoriteGUI = new RemoveFavoriteGUI(user.getFavoriteMovies(), user, start);
            removeFavoriteGUI.setVisible(true);
        } else if(e.getSource() == deletemovie){
            dispose();
            MovieListGUI movieListGUI = new MovieListGUI(start.getlistofmovies(), user, start);
            movieListGUI.setVisible(true);
        }
    }
}