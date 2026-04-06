package Modelo;

import Interfaz.IManejoDatos;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoDatos implements IManejoDatos {

    /*private MaquinaAerotermia maquinaAerotermia;
    private Inversor inversor;
    private CompaniaElectrica companiaElectrica;

    public ManejoDatos(MaquinaAerotermia maquinaAerotermia,Inversor inversor,CompaniaElectrica companiaElectrica) {
        this.maquinaAerotermia = maquinaAerotermia;
        this.inversor = inversor;
        this.companiaElectrica = companiaElectrica;
    }*/

    @Override
    public void crearTabla() {

        boolean salidaCrearTabla = false;

        do {
            try(XSSFWorkbook workbook = new XSSFWorkbook()){
                // Crear libro y hoja
                XSSFSheet sheet = workbook.createSheet();
                // Crear una fila y una celda
                XSSFRow row = sheet.createRow(0);
                XSSFCell cell = row.createCell(0);
                cell.setCellValue("Hola Mundo");
            }catch(Exception e){

            }
        }while(!salidaCrearTabla);

// Guardar el archivo
        FileOutputStream fileOut = new FileOutputStream("libro.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }

    @Override
    public void InsertarElemento() {

        boolean salidaInsertarElemento = false;
        Scanner sc = new Scanner(System.in);

        int fila = 0;
        int columna = 0;

        do {
            try(XSSFWorkbook workbook = new XSSFWorkbook()){

                // Crear libro y hoja
                XSSFSheet sheet = workbook.createSheet();
                // Crear una fila y una celda
                XSSFRow row = sheet.createRow(fila);


                System.out.println("Introduce la fecha de hoy");
                String fecha = sc.nextLine();


                for(int i = 0; i < atributosMaquinaAerotermia(sc).size(); i++){
                    XSSFCell cell = row.createCell(i);

                }


                MaquinaAerotermia maquinaAerotermia = new MaquinaAerotermia("COZYTOUCH",fecha,kwhTotalMAE,aguaKw,kalefKw);

                System.out.println("Inversor");
                linea();

                System.out.println("Introduce Vertida");
                double vertidaInversor = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce Consumo Real");
                double consumoReal = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce Consumo Total");
                double consumoTotal = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce Solar");
                double solar = Double.parseDouble(sc.nextLine());

                Inversor inversor = new Inversor("Victron",fecha,vertidaInversor,consumoReal,consumoTotal,solar);

                System.out.println("Compania Electrica");
                linea();

                System.out.println("Introduce Punta");
                double punta = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce LLano");
                double llano = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce Valle");
                double valle = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce Vertida");
                double vertidaCE = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce KwhTotal");
                double kwhTotalCE = Double.parseDouble(sc.nextLine());

                CompaniaElectrica companiaElectrica = new CompaniaElectrica("ESLUZ",fecha,punta,llano,valle,vertidaCE,kwhTotalCE)


                i = 0;
                j = 0;
            }catch(Exception e){

            }
        }while(!salidaInsertarElemento);
    }

    @Override
    public void EliminarElemento() {

    }

    @Override
    public void ModificarElemento() {

    }

    @Override
    public void ModificarVariables() {

    }

    @Override
    public void MostrarElementos() {

    }

    private void añadirElementoArray() {

        String[] lista = new String[tamanio()];

        Field[] atributos = maquinaAerotermia.getClass().getDeclaredFields();
    }


    private ArrayList<String> atributosMaquinaAerotermia(Scanner sc){

        ArrayList<String> lista = new ArrayList<>();

        System.out.println("Maquina Aerotermia");
        linea();

        System.out.println("Introduce el Kwh Total");
        double kwhTotalMAE = Double.parseDouble(sc.nextLine());
        lista.add(String.valueOf(kwhTotalMAE));

        System.out.println("Introduce Agua Kw");
        double aguaKw = Double.parseDouble(sc.nextLine());
        lista.add(String.valueOf(aguaKw));

        System.out.println("Introduce Kalefaccion Kw");
        double kalefKw = Double.parseDouble(sc.nextLine());
        lista.add(String.valueOf(kalefKw));

        System.out.println("Introduce Euros");
        double eurosMaquinaAerotermia = Double.parseDouble(sc.nextLine());
        lista.add(String.valueOf(eurosMaquinaAerotermia));

        return lista;
    }

    private void linea(){
        System.out.println("-----------------------");
    }
}
