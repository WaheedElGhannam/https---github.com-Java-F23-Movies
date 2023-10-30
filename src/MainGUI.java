import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
    private JLabel welcomeLabel;
    private JPanel optionsPanel;
    private User user;
    Testdata start;

    public MainGUI(User user, Testdata start) {
        this.user = user;
        this.start = start;
        setTitle("Main GUI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        welcomeLabel = new JLabel("Hello " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.BLUE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (user instanceof Admin) {
            addOption("Add Movie to Database");
            addOption("Edit and Update Movie");
            addOption("Browse By Genre");
            addOption("Delete Movie from Database");
            addOption("Remove Cast Members");
            addOption("Add Cast Member");
            addOption("Remove Crew Member");
            addOption("Generate Reports");
            addOption("Manage Theater Schedules and Seating");
            addOption("Historical Showtimes and Theater Performance");
        } else {
            addOption("View List of Available Movies");
            addOption("Search for movies by Genre");
            addOption("Search Movies by release date");
            addOption("View Information about a Movie");
            addOption("View Movie Showtimes");
            addOption("Mark Favorite Movies");
            addOption("View Favorites");
            addOption("Remove Movie from Favorites");
            addOption("Rate A Movie");
            addOption("Purchase Tickets");
            addOption("Recommendations");
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        optionsPanel.add(logoutButton);
        optionsPanel.add(exitButton);

        JScrollPane scrollPane = new JScrollPane(optionsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel optionsPanelContainer = new JPanel(new BorderLayout());
        optionsPanelContainer.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(optionsPanelContainer, BorderLayout.NORTH);
    }

    private void addOption(String option) {
        JButton button = new JButton(option);
        button.addActionListener(this);
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
            case "Add Movie to Database":
             AddMovieGUI addMovieGUI = new AddMovieGUI(start.getlistofmovies(), user, start);
             addMovieGUI.setVisible(true);
             break;
            case "Edit and Update Movie":
             EditMovieGUI editMovieGUI = new EditMovieGUI(start.getlistofmovies(), user, start);
             editMovieGUI.setVisible(true);
             break;
            case "Browse By Genre":
             dispose();
             SearchByGenreGUIAdmin searchByGenreGUIAdmin = new SearchByGenreGUIAdmin(start.getlistofmovies(), user, start);
             searchByGenreGUIAdmin.setVisible(true);
             break;
            case "Delete Movie from Database":
             DeleteMovieGUI deleteMovieGUI = new DeleteMovieGUI(start.getlistofmovies());
             deleteMovieGUI.setVisible(true);
             break;
            case "Add Cast Member":
             AddCastMemberGUI addCastMemberGUI = new AddCastMemberGUI(start.getlistofmovies());
             addCastMemberGUI.setVisible(true);
             break;
            case "Remove Cast Members":
             RemoveCastMemberGUI removeCastMemberGUI = new RemoveCastMemberGUI(start.getlistofmovies());
             removeCastMemberGUI.setVisible(true);
             break;

        }
    }
}
