package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;

    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_PRODUCTOS_DESCUENTO = 3;

    public static double calcularTotalFactura() {

        double subtotal = calcularSubtotal();
        double subtotalConDescuento = aplicarDescuento(subtotal);
        double iva = calcularIva(subtotalConDescuento);
        double propina = calcularPropina(subtotalConDescuento + iva);

        double total = subtotalConDescuento + iva + propina;

        DatosSistema.totalPedido = total;
        DatosSistema.estadoMesa = 1;

        return total;
    }

    private static double calcularSubtotal() {

        double subtotal = 0;
        int indice = 0;

        while (indice < DatosSistema.nombresProductos.length) {
            subtotal =
                    subtotal
                            + DatosSistema.precios[indice]
                            * DatosSistema.cantidades[indice];
            indice++;
        }

        return subtotal;
    }

    private static double aplicarDescuento(double subtotal) {

        int productosDiferentes = contarProductosDiferentes();

        if (productosDiferentes > MIN_PRODUCTOS_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }

        return subtotal;
    }

    private static double calcularIva(double base) {
        return base * TASA_IVA;
    }

    private static double calcularPropina(double base) {

        if (base > UMBRAL_PROPINA) {
            return base * TASA_PROPINA;
        }

        return 0;
    }

    private static int contarProductosDiferentes() {

        int contador = 0;
        int indice = 0;

        while (indice < DatosSistema.cantidades.length) {
            if (DatosSistema.cantidades[indice] > 0) {
                contador++;
            }
            indice++;
        }

        return contador;
    }
}
