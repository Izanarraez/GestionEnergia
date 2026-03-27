import Menus.MenuInicio;

public class Main {
    public static void main(String[] args) {
        boolean salidaMenu = false;

        do {
            MenuInicio.menuInicio();
        }while (!salidaMenu);
    }
}