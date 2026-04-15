package Modelo;

import Interfaz.IManejoDatos;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ManejoDatos implements IManejoDatos {

    private final String ARCHIVO_EXCEL = "GestionEnergia.xlsx";
    private final String HOJA_NOMBRE = "Registros";


    @Override
    public void crearTabla() {
        
    }

    @Override
    public void insertarElemento() {

    }

    @Override
    public void eliminarElemento() {

    }

    @Override
    public void dodificarElemento() {

    }

    @Override
    public void insertarVariables() {

    }

    @Override
    public void eliminarVariables() {

    }

    @Override
    public void dodificarVariables() {

    }

    @Override
    public void mostrarElementos() {

    }
}