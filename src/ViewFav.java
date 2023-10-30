import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewFav extends JFrame implements ActionListener{
    private User user;
    private JTable favoritesTable;
    private JPanel optionsPanel;
    Testdata start;
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

    public ViewFav(User user, Testdata start) {
        this.user = user;
        this.start = start;

        setTitle("Favorite Movies");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Create a table model with columns: Title, Year, Genre
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Title"}, 0);

        // Populate the table with user's favorite movies
        for (Movie movie : user.getFavoriteMovies()) {
            model.addRow(new Object[]{movie.getTitle()});
        }

        // Create the JTable
        favoritesTable = new JTable(model);
        favoritesTable.setEnabled(false);
        favoritesTable.getTableHeader().setReorderingAllowed(false);

        // Add the table to a scrollable pane
        JScrollPane scrollPane = new JScrollPane(favoritesTable);
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane1, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewlist){
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