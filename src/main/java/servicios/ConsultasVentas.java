package servicios;

import entidades.Venta;
import lombok.Data;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ConsultasVentas {

    private List<Venta> ventas;

    public ConsultasVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    //1. Ventas con importe total > 100€
    public List<Venta> getVentasMayor100() {
        return ventas.stream()
                .filter(venta -> venta.getTotalLinea()> 100)
                .toList();
    }

    //2. Ventas de la categoría "Electrónica"
    public List<Venta> getVentasByCategoriaElectronica() {
        return ventas.stream()
                .filter(v ->v.getCategoria().equals("Electronics"))
                .toList();
    }
    //3. Productos distintos vendidos
    public List<String> getProductosVendidosOrder() {
        return ventas.stream()
                .map(Venta::getProducto)
                .distinct()
                .sorted()
                .toList();
    }
    //4. Primera venta registrada de España
    public Optional<Venta> getPrimeraVentaSpain() {
        return ventas.stream()
                .filter(v -> v.getPais().equals("Spain"))
                .findFirst();
    }
    //5. Top 10 ventas por importe total
    public List<Venta> get10VentasImporteTotal() {
        return ventas.stream()
                .sorted(Comparator.comparing(Venta::getTotalLinea).reversed())
                .limit(10)
                .toList();
    }
    //6. Facturación total
    public double getFacturacionTotal() {
        return ventas.stream()
                .mapToDouble(Venta::getTotalLinea)
                .sum();
    }
    //7. Estadísticas del precio unitario
    public DoubleSummaryStatistics getEstadisticasPrecioUnitario() {
        return ventas.stream()
                .mapToDouble(Venta::getPrecioUnitario)
                .summaryStatistics();
    }
    //8. Número de ventas por categoría
    public Map<String, Long> getNUmeroDeVentasPorCategoria() {
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getCategoria,
                        Collectors.counting()));
    }
    //9. Facturación total por país
    public Map<String, Double> getFacturacionTotalPorPais() {
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getPais,
                        Collectors.summingDouble(Venta::getTotalLinea)));
    }
    //10. Número de ventas por método de pago
    public Map<String, Long> getNUmeroDeVentasPorMetodoDePago() {
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getMetodoPago,
                        Collectors.counting()));
    }
    //11. Facturación por categoría
    public Map<String, Double> getFacturacionTotalPorCategoria() {
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getCategoria,
                        Collectors.summingDouble(Venta::getTotalLinea)));
    }

    //12. Ventas agrupadas por mes (1–12)
    public Map<Month, List<Venta>> getVentasAgrupadasPorMes() {
        return ventas.stream()
                .collect(Collectors.groupingBy(v ->
                        v.getFecha().getMonth()));
    }
    //13. Categoría con más unidades vendidas
    public void getCategoriaMasUnidadesVendidas() {
        Map<String, Integer> unidadesVendidasPorCategoria = ventas.stream()
                .collect(Collectors.groupingBy(Venta::getCategoria,
                        Collectors.summingInt(Venta::getCantidad)));
    }
    //14. ¿Todas las ventas con BIZUM son < 200€?
    public boolean todasVentasBizumMenor200() {
        return ventas.stream()
                .allMatch(v -> v.getMetodoPago().equals("BIZUM")
                        && v.getTotalLinea() < 200);
    }
    //15. Porcentaje de ventas pagadas con TARJETA
    public double getPorcentajeVentasTarjeta() {
        long totalTarjeta = ventas.stream()
                .filter(v -> v.getMetodoPago().equals("TARJETA"))
                .count();
        return ((double) totalTarjeta / ventas.size()) * 100.0;
    }

}
