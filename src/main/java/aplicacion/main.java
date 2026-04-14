package aplicacion;

import entidades.Venta;
import servicios.ConsultasVentas;
import utilidades.CsvLoades;

import java.util.List;

public class main {

    static void main() {

        List<Venta> ventas = CsvLoades.cargarVentas();

        ConsultasVentas consVentas = new ConsultasVentas(ventas);

        IO.println();
        // --- 1 ---
        consVentas.getVentasMayor100()
                .forEach(System.out::println);
        // --- 2 ---
        consVentas.getVentasByCategoriaElectronica()
                .forEach(System.out::println);
        // --- 3 ---
        consVentas.getProductosVendidosOrder()
                .forEach(System.out::println);
        // --- 4 ---
        consVentas.getPrimeraVentaSpain()
                .ifPresent(System.out::println);
        // --- 5 ---
        consVentas.get10VentasImporteTotal()
                .forEach(System.out::println);
        // --- 6 ---
        IO.println(consVentas.getFacturacionTotal());
        // --- 7 ---
        IO.println(consVentas.getEstadisticasPrecioUnitario());
        // --- 8 ---
        consVentas.getFacturacionTotalPorCategoria()
                .forEach((k,v) -> IO.println(k + ": " + v));
        // --- 9 ---
        consVentas.getFacturacionTotalPorPais()
                .forEach((k,v) -> IO.println(k + ": " + v + "€"));
        // --- 10 ---
        consVentas.getNUmeroDeVentasPorMetodoDePago()
                .forEach((k,v) -> IO.println(k + ": " + v));
        // --- 11 ---
        consVentas.getFacturacionTotalPorCategoria()
                .forEach((k,v) -> IO.println(k + ": " + v + "€"));
        // --- 12 ---
        consVentas.getVentasAgrupadasPorMes()
                .forEach((k,v) -> IO.println(k + ": " + v.size() + " ventas"));
        // --- 13 ---
        consVentas.getCategoriaMasUnidadesVendidas();
        // --- 14 ---
        IO.println(consVentas.todasVentasBizumMenor200());
        // --- 15 ---
        IO.println(consVentas.getPorcentajeVentasTarjeta() + "%");
    }
}
