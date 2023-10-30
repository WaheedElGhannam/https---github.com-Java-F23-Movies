import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewGUI extends JFrame implements ActionListener{
    private JPanel optionsPanel;
    private JTable movieTable;
    private static User user;
    Testdata start;
    JButton viewlist;

    public ViewGUI(ArrayList<Movie> movieList, User user, Testdata start) {
        this.user = user;
        this.start = start;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        // Create options panel (same as in MainGUI, excluding admin options)
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        viewlist = new JButton("View List of Available Movies");
        viewlist.addActionListener(this);
        JButton searchgenre = new JButton("Search for movies by Genre");
        searchgenre.addActionListener(this);
        JButton searchdate = new JButton("Search Movies by release date");
        searchdate.addActionListener(this);
        JButton info = new JButton("View Information about a Movie");
        info.addActionListener(this);
        JButton showtimes = new JButton("View Movie Showtimes");
        showtimes.addActionListener(this);
        JButton favmovie = new JButton("Mark Favorite Movies");
        favmovie.addActionListener(this);
        JButton viewfav = new JButton("View Favorites");
        viewfav.addActionListener(this);
        JButton removefav = new JButton("Remove Movie from Favorites");
        removefav.addActionListener(this);
        JButton ratemovie = new JButton("Rate A Movie");
        ratemovie.addActionListener(this);
        JButton purchasetickets = new JButton("Purchase Tickets");
        purchasetickets.addActionListener(this);
        JButton recomend = new JButton("Recommendations");
        recomend.addActionListener(this);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        JButton exitButton = new JButton("Exit");
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
        optionsPanel.add(recomend);
        optionsPanel.add(logoutButton);
        optionsPanel.add(exitButton);

        JScrollPane scrollPane1 = new JScrollPane(optionsPanel);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Create data for the table
        String[] columnNames = {"Title", "Genre", "Year"};
        Object[][] data = new Object[movieList.size()][3];

        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            data[i][0] = movie.getTitle();
            data[i][1] = movie.getGenre();
            data[i][2] = movie.getYear();
        }

        movieTable = new JTable(data, columnNames);
        movieTable.setEnabled(false);
        movieTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        setLayout(new BorderLayout());
        add(scrollPane1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addOption(String option) {
        JButton button = new JButton(option);
        optionsPanel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            // ... (previous cases remain the same)
            case "Logout":
                dispose(); // Close the MainGUI
                LoginGUI loginGUI = new LoginGUI(start);
                loginGUI.setVisible(true);
                break;
            case "Exit":
                System.exit(0);
                break;
            case "View List of Available Movies":
             dispose();
             ViewGUI viewGUI = new ViewGUI(start.getlistofmovies(), user, start);
             viewGUI.setVisible(true);
             break;
            case "Search for movies by Genre":
             dispose();
             SearchByGenreGUI Search1 = new SearchByGenreGUI(start.getlistofmovies(), user, start);
             Search1.setVisible(true);
             break;
            case "Search Movies by release date":
             dispose();
             SearchByYearGUI Search2 = new SearchByYearGUI(start.getlistofmovies(), user, start);
             Search2.setVisible(true);
             break;
            case "View Information about a Movie":
             dispose();
             MovieListGUI movieListGUI = new MovieListGUI(start.getlistofmovies(), user, start);
             movieListGUI.setVisible(true);
             break;
            case "View Movie Showtimes":
             dispose();
             MovieListGUITimings movieListGUI1 = new MovieListGUITimings(start.getlistofmovies(), user, start);
             movieListGUI1.setVisible(true);
             break;
            case "Mark Favorite Movies":
             dispose();
             AddFavoriteGUI movieSelectionGUI = new AddFavoriteGUI(user, start.getlistofmovies(), start);
             movieSelectionGUI.setVisible(true);
             break;
            case "View Favorites":
             dispose();
             ViewFav viewFav = new ViewFav(user, start);
             viewFav.setVisible(true);
             break;
            case "Rate A Movie":
             ReviewAndRatingGUI reviewAndRatingGUI = new ReviewAndRatingGUI(start.getlistofmovies(), user, start);
             reviewAndRatingGUI.setVisible(true);
             break;
            case "Purchase Tickets":
             SeatSelectionGUI seatSelectionGUI = new SeatSelectionGUI(start.getlistofmovies(), start, user);
             seatSelectionGUI.setVisible(true);
             break;
            case "Remove Movie from Favorites":
             dispose();
             RemoveFavoriteGUI removeFavoriteGUI = new RemoveFavoriteGUI(user.getFavoriteMovies(), user, start);
             removeFavoriteGUI.setVisible(true);
             break;
            case "Recommendations":
             RecommendationsGUI recommendationsGUI = new RecommendationsGUI(user.getFavoriteMovies(), start.getlistofmovies());
             recommendationsGUI.setVisible(true);
             break;
    }
}
}
