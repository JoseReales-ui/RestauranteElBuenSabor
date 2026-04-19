package com.mycompany.restauranteelbuensabor;

public class Factura {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;

    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_PRODUCTOS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numeroFactura;

    public Factura(Pedido pedido, int numeroFactura) {
        this.pedido = pedido;
        this.numeroFactura = numeroFactura;
    }

    public double calcularSubtotalConDescuento() {
        double subtotal = pedido.calcularSubtotal();

        if (pedido.contarProductosDiferentes() > MIN_PRODUCTOS_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }

        return subtotal;
    }

    public double calcularIva() {
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    public double calcularPropina() {
        double base = calcularSubtotalConDescuento() + calcularIva();

        if (base > UMBRAL_PROPINA) {
            return base * TASA_PROPINA;
        }

        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotalConDescuento()
                + calcularIva()
                + calcularPropina();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }
}