package aplicacion;

import entidades.Venta;
import utilidades.CsvLoades;

import java.util.List;

public class main {

    static void main() {

        List<Venta> ventas = CsvLoades.cargarVentas();
        ventas.forEach(System.out::println);

    }
}
