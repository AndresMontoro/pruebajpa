package es.ca.andresmontoro;

import java.util.List;

public class App {
  public static void main(String[] args) {
    PersonaDAO personaDAO = new PersonaDAO();
    Persona persona = new Persona("Andres", 25);

    System.out.println("Creating a person with JDBC and JPA!!!");
    personaDAO.crearPersona(persona);
    System.out.println("Person created: " + persona);

    System.out.println("Getting a person with JDBC and JPA!!!");
    Persona personaObtenida = personaDAO.obtenerPersona(1L);
    System.out.println("Person obtained: " + personaObtenida.toString());

    System.out.println("Getting all people with JDBC and JPA!!!");
    List<Persona> personas = personaDAO.obtenerPersonas();
    System.out.println("People obtained: " + personas.toString());

    System.out.println("Updating a person with JDBC and JPA!!!");
    personaObtenida.setNombre("Andres Montoro");
    personaDAO.actualizarPersona(personaObtenida);
    System.out.println("Person updated: " + personaObtenida.toString());

    System.out.println("Deleting a person with JDBC and JPA!!!");
    personaDAO.eliminarPersona(personaObtenida.getId());
  }
}
