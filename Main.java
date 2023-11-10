import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) {
        Gui gui = new Gui();       
        try {
            gui.createAndShowGUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

