package Pruebas;

import Modelo.ManejoDatos;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * Clase orientada a la ejecución de pruebas automatizadas y de integración.
 * Simula la entrada de usuario por teclado para probar la creación, inserción
 * y lectura en el Excel de forma desatendida.
 */
public class BateriaDePruebas {

    /**
     * Método de ejecución de las pruebas automatizadas.
     * Borra el archivo previo (si existe) y ejecuta las pruebas de creación,
     * variables, inserción de elementos y lectura secuencialmente.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("🚀 INICIANDO BATERÍA DE PRUEBAS AUTOMATIZADA...\n");

        String nombreExcelPrueba = "TestGestionEnergia";

        // 0. Preparación: Borramos el archivo si existía de una prueba anterior
        File archivo = new File(nombreExcelPrueba + ".xlsx");
        if (archivo.exists()) {
            archivo.delete();
        }

        ManejoDatos manejo = new ManejoDatos();

        // ==========================================
        // PRUEBA 1: Crear la tabla
        // ==========================================
        System.out.println("▶ PRUEBA 1: Creación de la tabla Excel");
        simularTeclado(nombreExcelPrueba + "\n");
        manejo.crearTabla();
        System.out.println("✅ Prueba 1 superada.\n");


        // ==========================================
        // PRUEBA 2: Insertar Variables
        // ==========================================
        System.out.println("▶ PRUEBA 2: Inserción de Variables en la Fila 33");
        // Preparamos todas las respuestas que el Scanner va a pedir
        String inputVariables = nombreExcelPrueba + "\n" + // Archivo
                "AerotermiaTest\n" + "InversorTest\n" + "CompañiaTest\n" + // Nombres
                "0.15\n" + "0.10\n" + "0.05\n" + // Precios Luz (Punta, Llano, Valle)
                "4.6\n" + "0.08\n" + "0.02\n" +  // Potencias
                "0.06\n" + "0.21\n";             // Excedentes e IVA

        simularTeclado(inputVariables);
        manejo.insertarVariables();
        System.out.println("✅ Prueba 2 superada.\n");


        // ==========================================
        // PRUEBA 3: Insertar un Elemento (Registro Diario)
        // ==========================================
        System.out.println("▶ PRUEBA 3: Inserción de un registro diario");
        String inputElemento = nombreExcelPrueba + "\n" + // Archivo
                "15/04/2026\n" + // Fecha
                "10.5\n" + "2.0\n" + "8.5\n" + "1.5\n" + // Aerotermia: KwhTotal, AguaKw, KalefKw, Euros
                "4.0\n" + "12.0\n" + "16.0\n" + "6.0\n" + // Inversor: Vertida, ConsReal, ConsTot, Solar
                "5.0\n" + "3.0\n" + "2.0\n" + "3.5\n" + "10.0\n"; // Compañía: Punta, Llano, Valle, Vertida, KwhTotal

        simularTeclado(inputElemento);
        manejo.insertarElemento();
        System.out.println("✅ Prueba 3 superada.\n");


        // ==========================================
        // PRUEBA 4: Mostrar los Elementos
        // ==========================================
        System.out.println("▶ PRUEBA 4: Lectura y cálculo del registro insertado");
        String inputMostrar = nombreExcelPrueba + "\n" + "04/2026\n"; // Archivo y Filtro

        simularTeclado(inputMostrar);
        manejo.mostrarElemento();
        System.out.println("✅ Prueba 4 superada.\n");


        System.out.println("🎉 BATERÍA DE PRUEBAS COMPLETADA CON ÉXITO.");
        System.out.println("Puedes abrir el archivo '" + nombreExcelPrueba + ".xlsx' para comprobar visualmente los datos generados.");
    }

    /**
     * Método auxiliar que engaña a la consola (System.in) inyectando
     * un texto predefinido como si el usuario lo hubiera escrito físicamente en el teclado.
     *
     * @param datosSimulados Cadena de texto con todas las respuestas separadas por saltos de línea (\n).
     */
    private static void simularTeclado(String datosSimulados) {
        System.setIn(new ByteArrayInputStream(datosSimulados.getBytes()));
    }
}
