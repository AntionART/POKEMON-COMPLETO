package com.pokemonapp;

import com.pokemonapp.entities.Trainer;
import com.pokemonapp.entities.Pokemon;
import com.pokemonapp.entities.Type;
import com.pokemonapp.entities.Move;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AplicacionPokemon {
    private static Scanner scanner = new Scanner(System.in);
    private static final Connection connection;

    // Inicializar la conexión a la base de datos
    static {
        Connection conn = null;
        try {
            // Cambia los parámetros de conexión según tu configuración
            String url = "jdbc:mysql://localhost:3306/pokemonapp";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            System.exit(1); // Salir si no se puede conectar a la base de datos
        }
        connection = conn;
    }

    private static final TrainerDAO trainerDAO = new TrainerDAO(connection);
    private static final PokemonDAO pokemonDAO = new PokemonDAO(connection);
    private static final TypeDAO typeDAO = new TypeDAO(connection);
    private static final MoveDAO moveDAO = new MoveDAO(connection);

    public static void main(String[] args) {
        while (true) {
            System.out.println("________________________________________________________");
            System.out.println("|               Aplicacion Pokemon                     |");
            System.out.println("|_______________________________________________________");
            System.out.println("| 1. Agregar Entrenador                                |");
            System.out.println("| 2. Obtener Entrenador                                |");
            System.out.println("| 3. Agregar Pokemon                                   |");
            System.out.println("| 4. Obtener Pokemon                                   |");
            System.out.println("| 5. Agregar Tipo                                      |");
            System.out.println("| 6. Obtener Tipo                                      |");
            System.out.println("| 7. Agregar Movimiento                                |");
            System.out.println("| 8. Obtener Movimiento                                |");
            System.out.println("| 9. Salir                                             |");
            System.out.println("________________________________________________________");
            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            try {
                switch (opcion) {
                    case 1:
                        agregarEntrenador();
                        break;
                    case 2:
                        obtenerEntrenador();
                        break;
                    case 3:
                        agregarPokemon();
                        break;
                    case 4:
                        obtenerPokemon();
                        break;
                    case 5:
                        agregarTipo();
                        break;
                    case 6:
                        obtenerTipo();
                        break;
                    case 7:
                        agregarMovimiento();
                        break;
                    case 8:
                        obtenerMovimiento();
                        break;
                    case 9:
                        System.exit(0);
                    default:
                        System.out.println("Opcion no válida. Intente nuevamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error de base de datos: " + e.getMessage());
            }
        }
    }

    private static void agregarEntrenador() throws SQLException {
        System.out.print("Ingrese el nombre del entrenador: ");
        String nombre = scanner.nextLine();
        // Ahora necesitamos crear una lista de pokemones vacía
        List<Pokemon> pokemones = new ArrayList<>();
        Trainer entrenador = new Trainer(0, nombre, pokemones); // El ID se generará automáticamente
        trainerDAO.addTrainer(entrenador);
        System.out.println("Entrenador agregado.");
    }

    private static void obtenerEntrenador() throws SQLException {
        System.out.print("Ingrese el ID del entrenador: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Trainer entrenador = trainerDAO.getTrainer(id);
        if (entrenador != null) {
            System.out.println("ID del entrenador: " + entrenador.getId() + ", Nombre: " + entrenador.getName());
            System.out.println("Pokemon:");
            for (Pokemon pokemon : entrenador.getPokemons()) {
                System.out.println(" - ID del Pokemon: " + pokemon.getId() + ", Nombre: " + pokemon.getName());
            }
        } else {
            System.out.println("Entrenador no encontrado.");
        }
    }

    private static void agregarPokemon() throws SQLException {
        System.out.print("Ingrese el nombre del Pokemon: ");
        String nombre = scanner.nextLine();
        Pokemon pokemon = new Pokemon(0, nombre); // El ID se generará automáticamente
        pokemonDAO.addPokemon(pokemon);
        System.out.println("Pokemon agregado.");
    }

    private static void obtenerPokemon() throws SQLException {
        System.out.print("Ingrese el ID del Pokemon: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Pokemon pokemon = pokemonDAO.getPokemon(id);
        if (pokemon != null) {
            System.out.println("ID del Pokemon: " + pokemon.getId() + ", Nombre: " + pokemon.getName());
            System.out.println("Tipos:");
            for (Type tipo : pokemon.getTypes()) {
                System.out.println(" - ID del Tipo: " + tipo.getId() + ", Nombre: " + tipo.getName());
            }
            System.out.println("Movimientos:");
            for (Move move : pokemon.getMoves()) {
                System.out.println(" - ID del Movimiento: " + move.getId() + ", Nombre: " + move.getName());
            }
        } else {
            System.out.println("Pokemon no encontrado.");
        }
    }

    private static void agregarTipo() throws SQLException {
        System.out.print("Ingrese el nombre del Tipo: ");
        String nombre = scanner.nextLine();
        Type tipo = new Type(0, nombre); // El ID se generará automáticamente
        typeDAO.addType(tipo);
        System.out.println("Tipo agregado.");
    }

    private static void obtenerTipo() throws SQLException {
        System.out.print("Ingrese el ID del Tipo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Type tipo = typeDAO.getType(id);
        if (tipo != null) {
            System.out.println("ID del Tipo: " + tipo.getId() + ", Nombre: " + tipo.getName());
            System.out.println("Movimientos:");
            for (Move move : tipo.getMoves()) {
                System.out.println(" - ID del Movimiento: " + move.getId() + ", Nombre: " + move.getName());
            }
        } else {
            System.out.println("Tipo no encontrado.");
        }
    }

    private static void agregarMovimiento() throws SQLException {
        System.out.print("Ingrese el nombre del Movimiento: ");
        String nombre = scanner.nextLine();
        Move movimiento = new Move(0, nombre); // El ID se generará automáticamente
        moveDAO.addMove(movimiento);
        System.out.println("Movimiento agregado.");
    }

    private static void obtenerMovimiento() throws SQLException {
        System.out.print("Ingrese el ID del Movimiento: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Move movimiento = moveDAO.getMove(id);
        if (movimiento != null) {
            System.out.println("ID del Movimiento: " + movimiento.getId() + ", Nombre: " + movimiento.getName());
        } else {
            System.out.println("Movimiento no encontrado.");
        }
    }
}
