import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecommendationsGUI extends JFrame {
    private JTable movieTable;
    public ArrayList<Movie> Recommedation = new ArrayList<>();

    public RecommendationsGUI(ArrayList<Movie> favoriteMovies, ArrayList<Movie> allMovies) {
        setTitle("Recommended Movies");
        setSize(600, 400);
        for(int i = 0; i < allMovies.size(); i++){
            for(int j = 0; j < favoriteMovies.size(); j++){
                if(allMovies.get(i).getGenre() == favoriteMovies.get(j).getGenre()){
                    Recommedation.add(allMovies.get(i));
                }
            }
        }
        String [] columnnames = {"Title" , "Genre"};
        String [][] data = new String[Recommedation.size()][2];
        for (int i = 0; i < Recommedation.size(); i++) {
            Movie movie = Recommedation.get(i);
            data[i][0] = movie.getTitle();
            data[i][1] = movie.getGenre();
        }
        movieTable = new JTable(data, columnnames);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        getContentPane().add(scrollPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}