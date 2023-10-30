import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    ArrayList<Movie> fav = new ArrayList<>();
    // Add any other fields you want to associate with a user

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Add getter and setter methods for additional fields if needed

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void addFavorite(Movie selectedMovie) {
        fav.add(selectedMovie);
    }
    public ArrayList<Movie> getFavoriteMovies() {
        return fav;
    }
    public void removeFav(Movie movie){
        fav.remove(movie);
    }

    public ArrayList<String> getTitlesofFavMovie(){
        ArrayList<String> titles = new ArrayList<>();
        for(Movie movie: fav){
            titles.add((movie.getTitle()));
        }
        return titles;
    }
}
