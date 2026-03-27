package Menus;

import java.util.Scanner;

public class MenuInicio {

    static Scanner sc = new Scanner(System.in);

    public MenuInicio() {}

    public static void menuInicio() {

        boolean salidaMenuInicio = false;

        do {
            System.out.println("1.Mantenimiento");
            System.out.println("2.Listar elementos");
            System.out.println("3.Salir");

            int respuesta = Integer.parseInt(sc.nextLine());

            switch (respuesta) {
                case 1:
                    MenuMantenimiento.MenuMantenimiento();
                    break;
                case 2:
                    break;
                case 3:
                    salidaMenuInicio = true;
                    break;
            }
        }
        while (!salidaMenuInicio);
    }
}
