import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddFavoriteGUI extends JFrame implements ActionListener {
    private User user;
    private JPanel optionsPanel;
    private JTable moviesTable;
    private JScrollPane tableScrollPane;
    private Testdata start;
    private ArrayList<Movie> favoriteMovies;
    JButton viewlist;
    JButton searchdate;
    JButton exitButton;
    JButton logoutButton;
    JButton showtimes;
    JButton info;
    JButton favmovie;
    JButton viewfav;
    JButton removefav;
    JButton purchasetickets;
    JButton searchgenre;
    JButton recommend;
    JButton ratemovie;

    public AddFavoriteGUI(User user, ArrayList<Movie> listofmovies, Testdata start) {
        this.user = user;
        this.start = start;
        this.favoriteMovies = new ArrayList<>();

        setTitle("Movie Selection");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        String[] columnNames = {"Select", "Title", "Year", "Genre"};

        Object[][] data = new Object[listofmovies.size()][4];
        for (int i = 0; i < listofmovies.size(); i++) {
            data[i][0] = false;
            data[i][1] = listofmovies.get(i).getTitle();
            data[i][2] = listofmovies.get(i).getYear();
            data[i][3] = listofmovies.get(i).getGenre();
        }

        CustomTableModel tableModel = new CustomTableModel(data, columnNames);

        moviesTable = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }
        };

        TableColumn checkBoxColumn = moviesTable.getColumnModel().getColumn(0);
        checkBoxColumn.setCellEditor(moviesTable.getDefaultEditor(Boolean.class));
        checkBoxColumn.setCellRenderer(moviesTable.getDefaultRenderer(Boolean.class));

        tableScrollPane = new JScrollPane(moviesTable);

        optionsPanel = new JPanel();
    optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    viewlist = new JButton("View List of Available Movies");
    viewlist.addActionListener(this);
    searchgenre = new JButton("Search for movies by Genre");
    searchgenre.addActionListener(this);
    searchdate = new JButton("Search Movies by release date");
    searchdate.addActionListener(this);
    info = new JButton("View Information about a Movie");
    info.addActionListener(this);
    showtimes = new JButton("View Movie Showtimes");
    showtimes.addActionListener(this);
    favmovie = new JButton("Mark Favorite Movies");
    favmovie.addActionListener(this);
    viewfav = new JButton("View Favorites");
    viewfav.addActionListener(this);
    removefav = new JButton("Remove Movie from Favorites");
    removefav.addActionListener(this);
    ratemovie = new JButton("Rate A Movie");
    ratemovie.addActionListener(this);
    purchasetickets = new JButton("Purchase Tickets");
    purchasetickets.addActionListener(this);
    recommend = new JButton("Recommendations");
    recommend.addActionListener(this);
    logoutButton = new JButton("Logout");
    logoutButton.addActionListener(this);
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    optionsPanel.add(viewlist);
    optionsPanel.add(searchgenre);
    optionsPanel.add(searchdate);
    optionsPanel.add(info);
    optionsPanel.add(showtimes);
    optionsPanel.add(favmovie);
    optionsPanel.add(viewfav);
    optionsPanel.add(removefav);
    optionsPanel.add(ratemovie);
    optionsPanel.add(purchasetickets);
    optionsPanel.add(recommend);
    optionsPanel.add(logoutButton);
    optionsPanel.add(exitButton);
    JScrollPane scrollPane1 = new JScrollPane(optionsPanel);
    scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        moviesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = moviesTable.rowAtPoint(evt.getPoint());
                int col = moviesTable.columnAtPoint(evt.getPoint());
                if (col == 0) {
                    handleMovieSelection(row);
                }
            }
        });

        add(optionsPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    // Helper method to check if a movie is already in favorites
    private boolean isMovieInFavorites(Movie movie) {
        for (Movie favoriteMovie : favoriteMovies) {
            if (favoriteMovie.equals(movie)) {
                return true;
            }
        }
        return false;
    }

    // Method to handle movie selection
    private void handleMovieSelection(int rowIndex) {
        boolean selected = (boolean) moviesTable.getValueAt(rowIndex, 0);
        Movie selectedMovie = start.getlistofmovies().get(rowIndex);

        if (selected && !isMovieInFavorites(selectedMovie)) {
            user.addFavorite(selectedMovie);
        } else if (!selected) {
            user.removeFav(selectedMovie);
        } else {
            JOptionPane.showMessageDialog(this, "Movie is already in favorites.");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedMovieTitle = ((JButton) e.getSource()).getText();
        Movie selectedMovie = start.getMovieByTitle(selectedMovieTitle);

        if (selectedMovie != null && !user.getTitlesofFavMovie().contains(selectedMovieTitle)) {
            user.addFavorite(selectedMovie);
            JOptionPane.showMessageDialog(this, "Added to Favorites: " + selectedMovie.getTitle());
        } else if (user.getTitlesofFavMovie().contains(selectedMovieTitle)) {
            JOptionPane.showMessageDialog(this, selectedMovie.getTitle() + " already in favorites.");
        } if(e.getSource() == viewlist){
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
        } else if(e.getSource() == favmovie){
            dispose();
            AddFavoriteGUI addFavoriteGUI = new AddFavoriteGUI(user, start.getlistofmovies(), start);
            addFavoriteGUI.setVisible(true);
        } else if(e.getSource() == removefav){
            dispose();
            RemoveFavoriteGUI removeFavoriteGUI = new RemoveFavoriteGUI(user.getFavoriteMovies(), user, start);
            removeFavoriteGUI.setVisible(true);
        } else if(e.getSource() == info){
            dispose();
            MovieListGUI movieListGUI = new MovieListGUI(start.getlistofmovies(), user, start);
            movieListGUI.setVisible(true);
        } else if(e.getSource() == showtimes){
            dispose();
            MovieListGUITimings movieListGUITimings = new MovieListGUITimings(start.getlistofmovies(), user, start);
            movieListGUITimings.setVisible(true);
        } else if(e.getSource() == ratemovie){
            ReviewAndRatingGUI reviewAndRatingGUI = new ReviewAndRatingGUI(start.getlistofmovies(), user, start);
            reviewAndRatingGUI.setVisible(true);
        } else if(e.getSource() == viewfav){
            dispose();
            ViewFav viewFav = new ViewFav(user, start);
            viewFav.setVisible(true);
        } else if(e.getSource() == recommend){
            RecommendationsGUI recommendationsGUI = new RecommendationsGUI(user.getFavoriteMovies(), start.getlistofmovies());
            recommendationsGUI.setVisible(true);
        } else if(e.getSource() == purchasetickets){
            SeatSelectionGUI seatSelectionGUI = new SeatSelectionGUI(start.getlistofmovies(), start, user);
            seatSelectionGUI.setVisible(true);
        } else if(e.getSource() == searchdate){
            dispose();
            SearchByYearGUI searchByYearGUI = new SearchByYearGUI(start.getlistofmovies(), user, start);
            searchByYearGUI.setVisible(true);
        }
    }
}
