import java.util.ArrayList;

public class Screen {
        public ArrayList<String> names = new ArrayList<>();
        public String name;
        private int capacity;
        private int numberOfTickets = 0;
        public ArrayList<Screen> listofScreens = new ArrayList<>();
        public Screen(String name, int capacity){
            this.name = name;
            this.capacity = capacity;
        }
        public Screen(ArrayList<String> names){
            this.names = names;
        }
        public ArrayList<String> getScreenName(){
            return names;
        }
        public int getCapacity(){
            return capacity;
        }
        public void setScreenCapacity(int capacity){
            this.capacity = capacity;
        }

        public int getNumberOfTickets(){
            return numberOfTickets;
        }
        public void setNumberOfTickets(int numberOfTickets){
            this.numberOfTickets = this.numberOfTickets + numberOfTickets;
        }
        public String getName() {
            return name;
        }
}
