import java.sql.*;

public class Database {

    private static Database instance;
    private Connection connection;

    private Database() throws Exception {
        String url = "jdbc:sqlite:database/sushi.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to database");
    }

    public static Database getInstance() throws Exception {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public String selectAll() {
        String result = "";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Connection is null or invalid");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Connection is null or invalid");
            return null;
        }

        String query = "SELECT * FROM menu";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result += rs.getString("id") + "\t";
                result += rs.getString("piatto") + "\t";
                result += rs.getString("prezzo") + "\t";
                result += rs.getString("quantita") + "\n";
            }


        } catch (SQLException e) {
            System.err.println("Error in query: " + query);
            return null;
        }

        return result;
    }


    public boolean insert(String piatto, double prezzo, int quantita) {
        //Controlliamo che il database sia ancora collegato
        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Connection is null or invalid");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Connection is null or invalid");
            return false;
        }



        String query = "INSERT INTO menu(piatto, prezzo, quantita) VALUES ( "+piatto+", "+prezzo+", "+quantita+")";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Insert failed");
            return false;
        }

        return true;
    }
    public boolean delete(String piatto) {
        //Controlliamo che il database sia ancora collegato
        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Connection is null or invalid");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Connection is null or invalid");
            return false;
        }



        String query = "DELETE FROM menu WHERE piatto = '"+piatto+"'";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Delete failed");
            return false;
        }

        return true;
    }
    public boolean Update(String piatto, double prezzo, int quantita) {
        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Connection is null or invalid");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Connection is null or invalid");
            return false;
        }



        String query = "UPDATE menu SET prezzo='"+prezzo+"', quantita='"+quantita+"'";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Update failed");
            return false;
        }

        return true;
    }
}
