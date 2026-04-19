package com.mycompany.restauranteelbuensabor;

public class FacturaImpresor {

    public void imprimir(Factura factura) {

        System.out.println("========================================");
        System.out.println("RESTAURANTE EL BUEN SABOR");
        System.out.println("Calle 15 #8-32, Valledupar");
        System.out.println("NIT: 900.123.456-7");
        System.out.println("========================================");

        System.out.printf(
                "FACTURA No. %03d%n",
                factura.getNumeroFactura()
        );

        System.out.println("----------------------------------------");

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal()
            );
        }

        System.out.println("----------------------------------------");
        System.out.printf(
                "%-27s $%,.0f%n",
                "TOTAL:",
                factura.calcularTotal()
        );
        System.out.println("========================================");
    }
}
