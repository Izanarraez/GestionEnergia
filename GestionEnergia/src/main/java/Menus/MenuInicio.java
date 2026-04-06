package Menus;

import Modelo.ManejoDatos;

import java.util.Scanner;

public class MenuInicio {

    static Scanner sc = new Scanner(System.in);

    public MenuInicio() {}

    public static void menuInicio() {

        boolean salidaMenuInicio = false;

        do {
            System.out.println("1.Crear excel");
            System.out.println("2.Añadir elemento");
            System.out.println("3.Borrar elemento");
            System.out.println("4.Modificar elemento");
            System.out.println("4.Modificar variables");
            System.out.println("6.Mostrar elemento (mes y año)");
            System.out.println("7.Salir");

            int respuesta = Integer.parseInt(sc.nextLine());

            switch (respuesta) {
                case 1:
                    break;
                case 2:




                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    salidaMenuInicio = true;
                    break;
            }
        }
        while (!salidaMenuInicio);
    }
}
