import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OptionsPanel implements ActionListener{
    JPanel optionsPanel;
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

    public void run(){
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
