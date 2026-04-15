package Interfaz;

/**
 * Interfaz que define las operaciones principales (CRUD) para la gestión
 * de los registros energéticos en un archivo Excel.
 */
public interface IManejoDatos {

    /**
     * Crea un nuevo archivo Excel con la estructura de cabeceras necesaria
     * para almacenar los registros de energía y las variables de configuración.
     */
    void crearTabla();

    /**
     * Solicita al usuario los datos diarios (Aerotermia, Inversor, Compañía Eléctrica)
     * y los inserta como una nueva fila en la hoja de registros del Excel.
     */
    void insertarElemento();

    /**
     * Elimina un registro existente en el Excel basándose en la fecha proporcionada por el usuario.
     */
    void eliminarElemento();

    /**
     * Modifica los datos de un registro existente buscando por su fecha exacta.
     */
    void modificarElemento();

    /**
     * Solicita y guarda en una fila específica (Fila 33) del Excel las variables
     * globales de precios y configuración (tarifas, potencias, IVA, etc.).
     */
    void insertarVariables();

    /**
     * Elimina la configuración de variables guardada en la fila designada del Excel.
     */
    void eliminarVariables();

    /**
     * Busca y muestra por consola los registros almacenados que coincidan
     * con una fecha o periodo específico.
     */
    void mostrarElemento();
}
