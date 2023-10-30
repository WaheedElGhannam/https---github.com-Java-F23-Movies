import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditMovieGUI extends JFrame implements ActionListener {
    private JTable table;
    private JButton editButton, submitButton;
    private ArrayList<Movie> movies;
    User user;
    Testdata start;

    public EditMovieGUI(ArrayList<Movie> movies, User user, Testdata start) {
        this.movies = movies;
        this.user = user;
        this.start = start;
        setTitle("Movie List");

        // Create a table model using a custom implementation (EditableTableModel)
        EditableTableModel tableModel = new EditableTableModel(movies);
        table = new JTable(tableModel);

        // Create buttons
        editButton = new JButton("Edit");
        editButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Set up the layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            // Allow editing of the table
            table.setEnabled(true);
        } else if (e.getSource() == submitButton) {
            // Save changes
            EditableTableModel model = (EditableTableModel) table.getModel();
            ArrayList<Movie> updatedMovies = model.getMovies();
            // Now 'updatedMovies' contains the edited data
            // You can choose what to do with it, like saving to a file or updating your ArrayList<Movie>
            table.setEnabled(false); // Disable table editing after submitting changes
        }
    }
}