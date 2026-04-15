package Modelo;

import Interfaz.IManejoDatos;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * Implementación de {@link IManejoDatos}.
 * Encargada de leer, escribir, modificar y estructurar los datos
 * en el archivo Excel utilizando la librería Apache POI.
 */
public class ManejoDatos implements IManejoDatos {

    private final String HOJA_REGISTROS = "Registros";

    // En POI el índice empieza en 0. Fila 33 -> Índice 32.
    private final int FILA_CABECERA_VARIABLES = 34; // Fila 32 en Excel
    private final int FILA_VARIABLES = 35;          // Fila 33 en Excel

    /**
     * Implementa la creación de la estructura base del archivo Excel.
     * Genera la hoja "Registros", establece las filas de cabeceras en las posiciones
     * correspondientes (fila 1 para registros, fila 32 para variables) y aplica
     * estilos visuales (negrita y colores) para diferenciar las secciones.
     * Si el archivo ya existe, no lo sobrescribe y avisa al usuario.
     */
    @Override
    public void crearTabla() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);

        File archivo = new File(archivoExcel);

        if (!archivo.exists()) {
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet(HOJA_REGISTROS);

                // --- ESTILOS PARA LAS CABECERAS (Negrita + Colores Claritos) ---
                XSSFCellStyle cabeceraFecha = crearEstilo(workbook, true, null);
                XSSFCellStyle cabeceraMAE = crearEstilo(workbook, true, IndexedColors.ROSE);
                XSSFCellStyle cabeceraINV = crearEstilo(workbook, true, IndexedColors.PALE_BLUE);
                XSSFCellStyle cabeceraCE = crearEstilo(workbook, true, IndexedColors.LIGHT_GREEN);

                // --- Cabeceras Registros (Fila 1 - índice 0) ---
                XSSFRow rowReg = sheet.createRow(0);
                String[] cabecerasReg = {
                        "Fecha", "MAE_KwhTotal", "MAE_AguaKw", "MAE_KalefKw", "MAE_Euros",
                        "INV_Vertida", "INV_ConsumoReal", "INV_ConsumoTotal", "INV_Solar",
                        "CE_Punta", "CE_Llano", "CE_Valle", "CE_Vertida", "CE_KwhTotal", "CE_Euros"
                };
                for (int i = 0; i < cabecerasReg.length; i++) {
                    XSSFCell cell = rowReg.createCell(i);
                    cell.setCellValue(cabecerasReg[i]);
                    // Aplicar estilo según la sección
                    if (i == 0) cell.setCellStyle(cabeceraFecha);
                    else if (i >= 1 && i <= 4) cell.setCellStyle(cabeceraMAE);
                    else if (i >= 5 && i <= 8) cell.setCellStyle(cabeceraINV);
                    else cell.setCellStyle(cabeceraCE);
                }

                // --- Cabeceras Variables (Fila 32 - índice 31) ---
                XSSFRow rowVarsHeader = sheet.createRow(FILA_CABECERA_VARIABLES);
                String[] cabecerasVars = {
                        "NomMAE", "NomINV", "NomCE", "EurosPunta", "EurosLlano", "EurosValle",
                        "PotContratada", "EurosPotPunta", "EurosPotValle", "EurosExcedentes", "IVA"
                };
                for (int i = 0; i < cabecerasVars.length; i++) {
                    XSSFCell cell = rowVarsHeader.createCell(i);
                    cell.setCellValue(cabecerasVars[i]);
                    if (i == 0) cell.setCellStyle(cabeceraMAE);
                    else if (i == 1) cell.setCellStyle(cabeceraINV);
                    else cell.setCellStyle(cabeceraCE);
                }

                try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                    workbook.write(fileOut);
                    System.out.println("Archivo Excel '" + archivoExcel + "' creado exitosamente con colores.");
                }
            } catch (Exception e) {
                System.out.println("Error al crear la tabla: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo Excel '" + archivoExcel + "' ya existe.");
        }
    }

    /**
     * Solicita por consola todos los datos de configuración global (nombres de equipos,
     * tarifas, potencia contratada e IVA) y los escribe exactamente en la fila 33
     * del archivo Excel, aplicando los estilos de color definidos para cada sección.
     */
    @Override
    public void insertarVariables() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);

        System.out.println("--- Inserción de Variables ---");
        System.out.print("Nombre Máquina Aerotermia: ");
        String nomMae = sc.nextLine();
        System.out.print("Nombre Inversor: ");
        String nomInv = sc.nextLine();
        System.out.print("Nombre Compañía Eléctrica: ");
        String nomCe = sc.nextLine();

        System.out.print("Euros Punta: ");
        double ePunta = Double.parseDouble(sc.nextLine());
        System.out.print("Euros Llano: ");
        double eLlano = Double.parseDouble(sc.nextLine());
        System.out.print("Euros Valle: ");
        double eValle = Double.parseDouble(sc.nextLine());

        System.out.print("Potencia Contratada: ");
        double potContratada = Double.parseDouble(sc.nextLine());
        System.out.print("Euros Potencia Punta: ");
        double ePotPunta = Double.parseDouble(sc.nextLine());
        System.out.print("Euros Potencia Valle: ");
        double ePotValle = Double.parseDouble(sc.nextLine());

        System.out.print("Euros Excedentes: ");
        double eExcedentes = Double.parseDouble(sc.nextLine());
        System.out.print("IVA (ej. 0.21): ");
        double iva = Double.parseDouble(sc.nextLine());

        Variables variables = new Variables(nomMae, nomInv, nomCe, ePunta, eLlano, eValle,
                potContratada, ePotPunta, ePotValle, eExcedentes, iva);

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            XSSFRow row = sheet.getRow(FILA_VARIABLES);
            if (row == null) row = sheet.createRow(FILA_VARIABLES);

            // Estilos para los datos de las variables (Sin negrita, solo colores)
            XSSFCellStyle estiloMAE = crearEstilo(workbook, false, IndexedColors.ROSE);
            XSSFCellStyle estiloINV = crearEstilo(workbook, false, IndexedColors.PALE_BLUE);
            XSSFCellStyle estiloCE = crearEstilo(workbook, false, IndexedColors.LIGHT_GREEN);

            // Insertar y aplicar colores
            row.createCell(0).setCellValue(variables.getNombreMaquinaAerotermia());
            row.getCell(0).setCellStyle(estiloMAE);

            row.createCell(1).setCellValue(variables.getNombreInversor());
            row.getCell(1).setCellStyle(estiloINV);

            Object[] datosCE = {
                    variables.getNombreCompaniaElectrica(), variables.getEurosPunta(), variables.getEurosLlano(),
                    variables.getEurosValle(), variables.getPotenciaContratada(), variables.getEurosPotenciaPunta(),
                    variables.getEurosPotenciaValle(), variables.getEurosExcedentes(), variables.getIva()
            };

            for (int i = 0; i < datosCE.length; i++) {
                XSSFCell cell = row.createCell(i + 2);
                if (datosCE[i] instanceof String) cell.setCellValue((String) datosCE[i]);
                else cell.setCellValue((Double) datosCE[i]);
                cell.setCellStyle(estiloCE);
            }

            try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                workbook.write(fileOut);
                System.out.println("Variables guardadas y coloreadas correctamente en la fila 33.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar las variables (Asegúrate de que el archivo existe): " + e.getMessage());
        }
    }

    /**
     * Accede a la fila 33 del archivo Excel, que está reservada para la configuración,
     * y elimina su contenido por completo. Si la fila ya estaba vacía, notifica al usuario.
     */
    @Override
    public void eliminarVariables() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            XSSFRow row = sheet.getRow(FILA_VARIABLES);

            if (row != null) {
                sheet.removeRow(row);
                try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                    workbook.write(fileOut);
                    System.out.println("Variables eliminadas de la fila 33.");
                }
            } else {
                System.out.println("No había variables guardadas.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar variables: " + e.getMessage());
        }
    }

    /**
     * Inserta un nuevo registro de consumo diario en la primera fila vacía disponible
     * antes de la sección de variables. Solicita los datos numéricos por teclado,
     * instancia los objetos del modelo correspondientes para realizar los cálculos
     * automáticos y guarda la información formateada con colores en el Excel.
     * Requiere que las variables globales estén instanciadas previamente.
     */
    @Override
    public void insertarElemento() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);
        Variables variablesActuales = obtenerVariablesDelExcel(archivoExcel);

        if (variablesActuales == null) {
            System.out.println("ERROR: No hay variables en la fila 33 del archivo. Ejecuta 'Insertar Variables' primero.");
            return;
        }

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            int filaDestino = buscarFilaVaciaRegistros(sheet);

            if (filaDestino == -1) {
                System.out.println("ERROR: No hay espacio libre (Límite alcanzado antes de la fila 33).");
                return;
            }

            XSSFRow row = sheet.createRow(filaDestino);

            System.out.println("Introduce la fecha (dd/MM/yyyy): ");
            String fecha = sc.nextLine();

            // Pasamos el workbook para que el método pueda crear y asignar los estilos coloreados
            escribirDatosRegistro(sc, workbook, row, fecha, variablesActuales);

            try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                workbook.write(fileOut);
                System.out.println("Elemento guardado y formateado correctamente en la fila " + (filaDestino + 1) + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar el elemento: " + e.getMessage());
        }
    }

    /**
     * Solicita una fecha al usuario y busca la fila correspondiente en la zona de registros.
     * Si la encuentra, elimina la fila completa y desplaza los registros inferiores
     * hacia arriba para evitar dejar huecos vacíos en la tabla.
     */
    @Override
    public void eliminarElemento() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);
        System.out.println("Introduce la fecha del elemento a eliminar (dd/MM/yyyy): ");
        String fechaEliminar = sc.nextLine();

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            boolean encontrado = false;

            for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    if (row.getCell(0).getStringCellValue().equals(fechaEliminar)) {

                        int ultimaFilaDatos = buscarFilaVaciaRegistros(sheet) - 1;
                        if(ultimaFilaDatos <= 0) ultimaFilaDatos = i;

                        if (i == ultimaFilaDatos) {
                            sheet.removeRow(row);
                        } else {
                            sheet.shiftRows(i + 1, ultimaFilaDatos, -1);
                        }
                        encontrado = true;
                        System.out.println("Elemento eliminado.");
                        break;
                    }
                }
            }

            if (encontrado) {
                try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                    workbook.write(fileOut);
                }
            } else {
                System.out.println("No se ha encontrado elemento con esa fecha.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar elemento: " + e.getMessage());
        }
    }

    /**
     * Solicita la fecha de un registro existente. Si lo encuentra en el Excel,
     * vuelve a pedir todos los datos de consumo diario por consola y sobrescribe
     * la fila encontrada con los nuevos valores, manteniendo los estilos y colores.
     */
    @Override
    public void modificarElemento() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);
        Variables variablesActuales = obtenerVariablesDelExcel(archivoExcel);

        if (variablesActuales == null) {
            System.out.println("ERROR: No hay variables instanciadas. Inserta variables primero.");
            return;
        }

        System.out.println("Introduce la fecha del elemento a modificar (dd/MM/yyyy): ");
        String fechaModificar = sc.nextLine();

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            boolean encontrado = false;

            for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    if (row.getCell(0).getStringCellValue().equals(fechaModificar)) {
                        System.out.println("Elemento encontrado. Introduce los nuevos datos:");
                        escribirDatosRegistro(sc, workbook, row, fechaModificar, variablesActuales);
                        encontrado = true;
                        break;
                    }
                }
            }

            if (encontrado) {
                try (FileOutputStream fileOut = new FileOutputStream(archivoExcel)) {
                    workbook.write(fileOut);
                    System.out.println("Elemento modificado exitosamente.");
                }
            } else {
                System.out.println("No se ha encontrado elemento con esa fecha.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar: " + e.getMessage());
        }
    }

    /**
     * Busca y muestra por consola todos los datos guardados de un registro específico.
     * Solicita una fecha exacta y, si la localiza en el Excel, extrae el valor de
     * las 14 columnas de esa fila para mostrarlas de forma estructurada y legible
     * divididas por secciones (Aerotermia, Inversor, Compañía Eléctrica).
     */
    @Override
    public void mostrarElemento() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);

        System.out.print("Introduce la fecha exacta del registro a consultar (dd/MM/yyyy): ");
        String fechaBuscada = sc.nextLine().trim();

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            boolean encontrado = false;

            System.out.println("\nBuscando datos para la fecha: " + fechaBuscada + "...\n");

            // Recorremos desde la fila 1 hasta la fila donde empiezan las variables
            for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
                XSSFRow row = sheet.getRow(i);

                // Comprobamos que la fila y la primera celda (Fecha) no sean nulas
                if (row != null && row.getCell(0) != null) {
                    String fechaCelda = row.getCell(0).getStringCellValue();

                    // Si coincide la fecha exacta
                    if (fechaCelda.equals(fechaBuscada)) {
                        encontrado = true;

                        System.out.println("==================================================");
                        System.out.println("        DATOS REGISTRADOS EL: " + fechaCelda);
                        System.out.println("==================================================");

                        System.out.println("\n[ MÁQUINA AEROTERMIA ]");
                        System.out.println("  - Kwh Total:       " + row.getCell(1).getNumericCellValue());
                        System.out.println("  - Agua Kw:         " + row.getCell(2).getNumericCellValue());
                        System.out.println("  - Kalefacción Kw:  " + row.getCell(3).getNumericCellValue());
                        System.out.println("  - Coste estimado:  " + row.getCell(4).getNumericCellValue() + " €");

                        System.out.println("\n[ INVERSOR SOLAR ]");
                        System.out.println("  - Energía Vertida: " + row.getCell(5).getNumericCellValue() + " kWh");
                        System.out.println("  - Consumo Real:    " + row.getCell(6).getNumericCellValue() + " kWh");
                        System.out.println("  - Consumo Total:   " + row.getCell(7).getNumericCellValue() + " kWh");
                        System.out.println("  - Energía Solar:   " + row.getCell(8).getNumericCellValue() + " kWh");

                        System.out.println("\n[ COMPAÑÍA ELÉCTRICA ]");
                        System.out.println("  - Consumo Punta:   " + row.getCell(9).getNumericCellValue() + " kWh");
                        System.out.println("  - Consumo Llano:   " + row.getCell(10).getNumericCellValue() + " kWh");
                        System.out.println("  - Consumo Valle:   " + row.getCell(11).getNumericCellValue() + " kWh");
                        System.out.println("  - Energía Vertida: " + row.getCell(12).getNumericCellValue() + " kWh");
                        System.out.println("  - Kwh Total Red:   " + row.getCell(13).getNumericCellValue() + " kWh");
                        System.out.println("  - Coste Total:     " + row.getCell(14).getNumericCellValue() + " €");

                        System.out.println("==================================================\n");

                        // Como ya hemos encontrado el día, rompemos el bucle
                        break;
                    }
                }
            }

            if (!encontrado) {
                System.out.println("❌ No se ha encontrado ningún registro para la fecha proporcionada (" + fechaBuscada + ").");
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo Excel: " + e.getMessage());
        }
    }

    // ==========================================
    //            MÉTODOS AUXILIARES
    // ==========================================

    // (Aquí iría {@inheritDoc} en los métodos implementados de la interfaz si quieres omitir reescribirlos,
    // pero te documento los métodos privados auxiliares que son propios de esta clase:)

    /**
     * Solicita al usuario el nombre del archivo y le añade la extensión correspondiente.
     *
     * @param sc Scanner para leer la entrada por consola.
     * @return El nombre completo del archivo con la extensión .xlsx.
     */
    private String pedirNombreExcel(Scanner sc) {
        System.out.print("\nIntroduce el nombre del archivo Excel a utilizar (sin añadir .xlsx): ");
        String nombre = sc.nextLine().trim();
        return nombre + ".xlsx";
    }

    /**
     * Lee el Excel especificado y extrae las variables de configuración guardadas en la fila 33.
     *
     * @param archivoExcel Ruta o nombre del archivo Excel a leer.
     * @return Objeto {@link Variables} con los datos recuperados, o null si no se encuentran.
     */
    private Variables obtenerVariablesDelExcel(String archivoExcel) {
        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            XSSFRow row = sheet.getRow(FILA_VARIABLES);

            if (row == null || row.getCell(0) == null) {
                return null;
            }

            return new Variables(
                    row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getNumericCellValue(),
                    row.getCell(4).getNumericCellValue(),
                    row.getCell(5).getNumericCellValue(),
                    row.getCell(6).getNumericCellValue(),
                    row.getCell(7).getNumericCellValue(),
                    row.getCell(8).getNumericCellValue(),
                    row.getCell(9).getNumericCellValue(),
                    row.getCell(10).getNumericCellValue()
            );
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca la primera fila vacía en la sección de registros (antes de la zona de variables)
     * para saber dónde insertar el nuevo dato diario.
     *
     * @param sheet La hoja de cálculo donde buscar.
     * @return El índice de la primera fila libre, o -1 si no hay espacio.
     */
    private int buscarFilaVaciaRegistros(XSSFSheet sheet) {
        for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().trim().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pide al usuario todos los datos numéricos de los equipos, crea los objetos correspondientes
     * y los escribe aplicando estilos en la fila del Excel indicada.
     *
     * @param sc Scanner para la entrada de datos.
     * @param workbook El libro de Excel actual (para generar estilos).
     * @param row La fila específica donde se van a escribir los datos.
     * @param fecha La fecha del registro que se está insertando o modificando.
     * @param vars Las variables de configuración vigentes.
     */
    private void escribirDatosRegistro(Scanner sc, XSSFWorkbook workbook, XSSFRow row, String fecha, Variables vars) {
        // --- PREPARAR LOS ESTILOS DE COLORES Y NEGRITA ---
        XSSFCellStyle estiloFecha = crearEstilo(workbook, true, null); // Fecha en negrita
        XSSFCellStyle estiloMAE = crearEstilo(workbook, false, IndexedColors.ROSE); // Rosa
        XSSFCellStyle estiloINV = crearEstilo(workbook, false, IndexedColors.PALE_BLUE); // Azul Clarito
        XSSFCellStyle estiloCE = crearEstilo(workbook, false, IndexedColors.LIGHT_GREEN); // Verde Clarito

        System.out.println("--- Máquina Aerotermia (" + vars.getNombreMaquinaAerotermia() + ") ---");
        System.out.print("Kwh Total: "); double kwhMAE = Double.parseDouble(sc.nextLine());
        System.out.print("Agua Kw: "); double aguaKw = Double.parseDouble(sc.nextLine());
        System.out.print("Kalefaccion Kw: "); double kalefKw = Double.parseDouble(sc.nextLine());
        System.out.print("Euros: "); double eurosMAE = Double.parseDouble(sc.nextLine());

        MaquinaAerotermia mae = new MaquinaAerotermia(vars.getNombreMaquinaAerotermia(), fecha, kwhMAE, kalefKw, eurosMAE);
        mae.setAguaKwh(aguaKw);

        System.out.println("--- Inversor (" + vars.getNombreInversor() + ") ---");
        System.out.print("Vertida: "); double vertidaInv = Double.parseDouble(sc.nextLine());
        System.out.print("Consumo Real: "); double consReal = Double.parseDouble(sc.nextLine());
        System.out.print("Consumo Total: "); double consTot = Double.parseDouble(sc.nextLine());
        System.out.print("Solar: "); double solar = Double.parseDouble(sc.nextLine());

        Inversor inv = new Inversor(vars.getNombreInversor(), fecha, vertidaInv, consReal, consTot, solar);

        System.out.println("--- Compañía Eléctrica (" + vars.getNombreCompaniaElectrica() + ") ---");
        System.out.print("Punta: "); double punta = Double.parseDouble(sc.nextLine());
        System.out.print("Llano: "); double llano = Double.parseDouble(sc.nextLine());
        System.out.print("Valle: "); double valle = Double.parseDouble(sc.nextLine());
        System.out.print("Vertida: "); double vertidaCE = Double.parseDouble(sc.nextLine());
        System.out.print("KwhTotal: "); double kwhCE = Double.parseDouble(sc.nextLine());

        CompaniaElectrica ce = new CompaniaElectrica(vars.getNombreCompaniaElectrica(), fecha, punta, llano, valle, vertidaCE, kwhCE, vars);

        // --- INSERCIÓN EN EXCEL Y APLICACIÓN DE COLORES ---

        // 0. Fecha (Negrita)
        XSSFCell c0 = row.createCell(0); c0.setCellValue(fecha); c0.setCellStyle(estiloFecha);

        // 1-4. Aerotermia (Rosa)
        XSSFCell c1 = row.createCell(1); c1.setCellValue(mae.getKwhTotal()); c1.setCellStyle(estiloMAE);
        XSSFCell c2 = row.createCell(2); c2.setCellValue(mae.getAguaKwh()); c2.setCellStyle(estiloMAE);
        XSSFCell c3 = row.createCell(3); c3.setCellValue(mae.getKalefKw()); c3.setCellStyle(estiloMAE);
        XSSFCell c4 = row.createCell(4); c4.setCellValue(mae.getEuros()); c4.setCellStyle(estiloMAE);

        // 5-8. Inversor (Azul)
        XSSFCell c5 = row.createCell(5); c5.setCellValue(inv.getVertida()); c5.setCellStyle(estiloINV);
        XSSFCell c6 = row.createCell(6); c6.setCellValue(inv.getConsumoReal()); c6.setCellStyle(estiloINV);
        XSSFCell c7 = row.createCell(7); c7.setCellValue(inv.getConsumoTotal()); c7.setCellStyle(estiloINV);
        XSSFCell c8 = row.createCell(8); c8.setCellValue(inv.getSolar()); c8.setCellStyle(estiloINV);

        // 9-14. Compañía Eléctrica (Verde)
        XSSFCell c9 = row.createCell(9); c9.setCellValue(ce.getPunta()); c9.setCellStyle(estiloCE);
        XSSFCell c10 = row.createCell(10); c10.setCellValue(ce.getLlano()); c10.setCellStyle(estiloCE);
        XSSFCell c11 = row.createCell(11); c11.setCellValue(ce.getValle()); c11.setCellStyle(estiloCE);
        XSSFCell c12 = row.createCell(12); c12.setCellValue(ce.getVertida()); c12.setCellStyle(estiloCE);
        XSSFCell c13 = row.createCell(13); c13.setCellValue(ce.getKwhTotal()); c13.setCellStyle(estiloCE);
        XSSFCell c14 = row.createCell(14); c14.setCellValue(ce.getEuros()); c14.setCellStyle(estiloCE);
    }

    /**
     * Método auxiliar que crea y devuelve un estilo para las celdas de Excel.
     * Permite activar texto en negrita y aplicar un color de fondo.
     *
     * @param workbook El libro de Excel sobre el que se aplica el estilo.
     * @param isBold True si se desea fuente en negrita, False en caso contrario.
     * @param color El color indexado de POI ({@link IndexedColors}) para el fondo, o null para sin color.
     * @return El estilo de celda configurado ({@link XSSFCellStyle}).
     */
    private XSSFCellStyle crearEstilo(XSSFWorkbook workbook, boolean isBold, IndexedColors color) {
        XSSFCellStyle style = workbook.createCellStyle();

        // Aplicar Negrita
        if (isBold) {
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
        }

        // Aplicar Color de Fondo
        if (color != null) {
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        return style;
    }
}