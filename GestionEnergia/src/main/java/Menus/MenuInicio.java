package Menus;

import Interfaz.IManejoDatos;
import Modelo.ManejoDatos;

import java.util.Scanner;

public class MenuInicio {

    static Scanner sc = new Scanner(System.in);

    public MenuInicio() {}

    public static void menuInicio() {

        boolean salidaMenuInicio = false;

        // Instanciamos la clase que contiene toda la lógica
        IManejoDatos manejoDatos = new ManejoDatos();

        do {
            System.out.println("\n===== GESTIÓN DE ENERGÍA =====");
            System.out.println("1. Crear nuevo archivo Excel");
            System.out.println("2. Insertar Variables");
            System.out.println("3. Eliminar Variables");
            System.out.println("4. Añadir registro (Elemento)");
            System.out.println("5. Mostrar registros (por mes y año)");
            System.out.println("6. Modificar registro (Elemento)");
            System.out.println("7. Borrar registro (Elemento)");
            System.out.println("8. Salir");
            System.out.println("==============================");
            System.out.print("Elige una opción: ");

            try {
                int respuesta = Integer.parseInt(sc.nextLine());

                switch (respuesta) {
                    case 1:
                        manejoDatos.crearTabla();
                        break;
                    case 2:
                        manejoDatos.insertarVariables();
                        break;
                    case 3:
                        manejoDatos.eliminarVariables();
                        break;
                    case 4:
                        manejoDatos.insertarElemento();
                        break;
                    case 5:
                        manejoDatos.mostrarElementos();
                        break;
                    case 6:
                        manejoDatos.modificarElemento();
                        break;
                    case 7:
                        manejoDatos.eliminarElemento();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa... ¡Hasta pronto!");
                        salidaMenuInicio = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige un número del 1 al 9.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }

        } while (!salidaMenuInicio);
    }
}
