import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DevelopersImplementation implements Developers {

    @Override
    public ResultSet loadDevelopers() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developer", "root", "Mariamtinu96");

            Statement statement = connection.createStatement();
            String CreateTableDevelopers = "CREATE TABLE IF NOT EXISTS developers(id Integer not null auto_increment, name Text, age Integer, location Text, skill Text, PRIMARY KEY(id))";
            statement.execute(CreateTableDevelopers);

            File file = new File("C:\\Users\\HP\\IdeaProjects\\ClassProject1\\src\\project.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String insertSQL = "INSERT INTO developers (name, age, location, skill) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                        preparedStatement.setString(1, data[0].trim());
                        preparedStatement.setInt(2, Integer.parseInt(data[1].trim()));
                        preparedStatement.setString(3, data[2].trim());
                        preparedStatement.setString(4, data[3].trim());
                        preparedStatement.executeUpdate();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String selectAll = "SELECT * FROM developers";
            ResultSet resultSet = statement.executeQuery(selectAll);
            return resultSet;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        DevelopersImplementation developersImpl = new DevelopersImplementation();
        ResultSet resultSet = developersImpl.loadDevelopers();

        if (resultSet != null) {
            try {
                System.out.println("Name, \tage, \tSkill, \tskill");
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String location = resultSet.getString("location");
                    String skill = resultSet.getString("skill");
                    System.out.printf("%s \t%d \t%s \t%s\n", name, age, location, skill);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }
}
