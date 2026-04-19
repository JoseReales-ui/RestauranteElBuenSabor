package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {

    public static double calcularTotalFactura() {

        double subtotal = 0;
        double valorIva = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        int productosDiferentes = 0;
        int indice = 0;

        while (indice < DatosSistema.nombresProductos.length) {
            if (DatosSistema.cantidades[indice] > 0) {
                subtotal = subtotal
                        + DatosSistema.precios[indice]
                        * DatosSistema.cantidades[indice];
                productosDiferentes++;
            }
            indice++;
        }

        if (productosDiferentes > 3) {
            subtotalConDescuento = subtotal - (subtotal * 0.05);
        } else {
            subtotalConDescuento = subtotal;
        }

        if (subtotalConDescuento > 50000) {
            valorIva = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + valorIva;
            total = total + (total * 0.10);
        } else {
            valorIva = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + valorIva;
        }

        DatosSistema.estadoMesa = 1;
        DatosSistema.totalPedido = total;

        return total;
    }
}
