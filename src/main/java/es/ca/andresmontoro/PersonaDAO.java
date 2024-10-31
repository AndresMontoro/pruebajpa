package es.ca.andresmontoro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
  public void crearPersona(Persona persona) {
    String sql = "INSERT INTO persona (nombre, edad) VALUES (?, ?)";
    try (
      Connection connection = DatabaseConnection.getConnection();
      PreparedStatement pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
    ) {  
      pStatement.setString(1, persona.getNombre());
      pStatement.setInt(2, persona.getEdad());
      pStatement.executeUpdate();

      try(ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          persona.setId(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      } 
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  public Persona obtenerPersona(Long id) {
    Persona persona = null;
    String sql = "SELECT * FROM persona WHERE id = ?";
    try (
      Connection connection = DatabaseConnection.getConnection();
      PreparedStatement pStatement = connection.prepareStatement(sql)
    ) {
      pStatement.setLong(1, id);
      try(ResultSet resultSet = pStatement.executeQuery()) {
        if (resultSet.next()) {
          persona = new Persona();
          persona.setId(resultSet.getLong("id"));
          persona.setNombre(resultSet.getString("nombre"));
          persona.setEdad(resultSet.getInt("edad"));
        }
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return persona;
  }

  public List<Persona> obtenerPersonas() {
    String sql = "SELECT * FROM persona";
    List<Persona> personas = new ArrayList<>();
    try (
      Connection connection = DatabaseConnection.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql)
    ) {
      while (resultSet.next()) {
        Persona persona = new Persona();
        persona.setId(resultSet.getLong("id"));
        persona.setNombre(resultSet.getString("nombre"));
        persona.setEdad(resultSet.getInt("edad"));
        personas.add(persona);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return personas;
  }

  public void actualizarPersona(Persona persona) {
    String sql = "UPDATE persona SET nombre = ?, edad = ? WHERE id = ?";
    try (
      Connection connection = DatabaseConnection.getConnection();
      PreparedStatement pStatement = connection.prepareStatement(sql)
    ) {
      pStatement.setString(1, persona.getNombre());
      pStatement.setInt(2, persona.getEdad());
      pStatement.setLong(3, persona.getId());
      pStatement.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
    } 
  }

  public void eliminarPersona(Long id) {
    String sql = "DELETE FROM persona WHERE id = ?";
    try (
      Connection conn = DatabaseConnection.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setLong(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
