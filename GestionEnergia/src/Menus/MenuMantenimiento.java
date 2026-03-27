package Menus;

import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MenuMantenimiento {

    static Scanner sc = new Scanner(System.in);

    public MenuMantenimiento() {}

    public static void MenuMantenimiento() {

        boolean salidaMenuMantenimiento = false;

        do {
            System.out.println("1.Crear nuevo excel");
            System.out.println("2.Insertar elemento en un excel");
            System.out.println("3.Borrar elemento de un excel");
            System.out.println("4.Modificar elemento en un excel");
            System.out.println("5.Salir");

            int respuesta = Integer.parseInt(sc.nextLine()); //NumberrFormatException

            switch (respuesta) {
                case 1:
                    Workbook workbook = new XSSFWorkbook(); // Para archivos .xlsx

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    salidaMenuMantenimiento = true;
                    break;
            }
        }
        while (!salidaMenuMantenimiento);
    }
}
