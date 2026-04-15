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

public class ManejoDatos implements IManejoDatos {

    private final String HOJA_REGISTROS = "Registros";

    // En POI el índice empieza en 0. Fila 33 -> Índice 32.
    private final int FILA_CABECERA_VARIABLES = 34; // Fila 32 en Excel
    private final int FILA_VARIABLES = 35;          // Fila 33 en Excel

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

    @Override
    public void mostrarElementos() {
        Scanner sc = new Scanner(System.in);
        String archivoExcel = pedirNombreExcel(sc);
        System.out.println("Introduce el mes y año a consultar (MM/yyyy): ");
        String filtroFecha = sc.nextLine();

        try (FileInputStream fileIn = new FileInputStream(archivoExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            XSSFSheet sheet = workbook.getSheet(HOJA_REGISTROS);
            boolean hayDatos = false;

            System.out.println("\n--- Registros para " + filtroFecha + " ---");
            for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    String fechaCelda = row.getCell(0).getStringCellValue();

                    if (fechaCelda.endsWith(filtroFecha)) {
                        hayDatos = true;
                        System.out.println("Fecha: " + fechaCelda);
                        System.out.println("  MAE -> Kwh Total: " + row.getCell(1).getNumericCellValue() + " | Euros: " + row.getCell(4).getNumericCellValue());
                        System.out.println("  INV -> Solar: " + row.getCell(8).getNumericCellValue() + " | Consumo Total: " + row.getCell(7).getNumericCellValue());
                        System.out.println("  CE  -> Kwh Total: " + row.getCell(13).getNumericCellValue() + " | Coste (€): " + row.getCell(14).getNumericCellValue());
                        System.out.println("-----------------------");
                    }
                }
            }

            if (!hayDatos) {
                System.out.println("No hay registros para ese mes/año en este archivo.");
            }

        } catch (Exception e) {
            System.out.println("Error al leer elementos: " + e.getMessage());
        }
    }

    // ==========================================
    //            MÉTODOS AUXILIARES
    // ==========================================

    private String pedirNombreExcel(Scanner sc) {
        System.out.print("\nIntroduce el nombre del archivo Excel a utilizar (sin añadir .xlsx): ");
        String nombre = sc.nextLine().trim();
        return nombre + ".xlsx";
    }

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

    private int buscarFilaVaciaRegistros(XSSFSheet sheet) {
        for (int i = 1; i < FILA_CABECERA_VARIABLES; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().trim().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

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
     * Permite activar negrita y aplicar un color de fondo fácilmente.
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