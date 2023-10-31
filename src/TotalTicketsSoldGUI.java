import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TotalTicketsSoldGUI extends JFrame implements ActionListener {
    private ArrayList<Movie> listofmovies;
    private JComboBox<String> screenSelector;
    private JTextArea ticketInfoArea;

    public TotalTicketsSoldGUI(ArrayList<Movie> listofmovies) {
        this.listofmovies = listofmovies;

        setTitle("Total Tickets Sold");

        // Create a list of unique screen names
        ArrayList<String> screenNames = new ArrayList<>();
        for (Movie movie : listofmovies) {
            for (Screen screen : movie.getScreens()) {
                String screenName = screen.getName();
                if (!screenNames.contains(screenName)) {
                    screenNames.add(screenName);
                }
            }
        }

        screenSelector = new JComboBox<>(screenNames.toArray(new String[0]));
        screenSelector.addActionListener(this);

        ticketInfoArea = new JTextArea(10, 30);
        ticketInfoArea.setEditable(false);

        setLayout(new FlowLayout());
        add(new JLabel("Select a Screen:"));
        add(screenSelector);
        add(new JLabel("Total Tickets Sold:"));
        add(new JScrollPane(ticketInfoArea));

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void updateTicketInfo() {
        String selectedScreenName = (String) screenSelector.getSelectedItem();
        if (selectedScreenName != null) {
            int totalTicketsSold = 0;
            for (Movie movie : listofmovies) {
                for (Screen screen : movie.getScreens()) {
                    if (screen.getName().equals(selectedScreenName)) {
                        totalTicketsSold += screen.getNumberOfTickets();
                    }
                }
            }
            ticketInfoArea.setText(String.valueOf(totalTicketsSold));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == screenSelector) {
            updateTicketInfo();
        }
    }
}