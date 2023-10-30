import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EditableTableModel extends AbstractTableModel {
    private ArrayList<Movie> movies;
    private String[] columnNames = {"Title", "Year", "Genre", "Cast", "Plot", "Crew"};

    public EditableTableModel(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class; // All columns are treated as String data
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // All cells are editable
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return movie.getTitle();
            case 1:
                return Integer.toString(movie.getYear());
            case 2:
                return movie.getGenre();
            case 3:
                return movie.getCast().toString();
            case 4:
                return movie.getPlot();
            case 5:
                return movie.getcrew();
            default:
                return null; // This should not happen
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0:
                movie.setTitle((String) value);
                break;
            case 1:
                movie.setYear(Integer.parseInt((String) value));
                break;
            case 2:
                movie.setGenre((String) value);
                break;
            case 3:
                // Handle cast
                // Note: You need to implement methods in the Movie class to handle cast manipulation
                break;
            case 4:
                movie.setPlot((String) value);
                break;
            case 5:
                movie.setcrew((String) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}