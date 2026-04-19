package com.mycompany.restauranteelbuensabor;

public class ImpresionFactura {

    public static void mostrarCarta() {

        System.out.println("========================================");
        System.out.println("    " + DatosSistema.nombreRestaurante);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println("========================================");

        int indice = 0;

        while (indice < DatosSistema.nombresProductos.length) {
            System.out.printf(
                    "%d. %-22s $%,.0f%n",
                    indice + 1,
                    DatosSistema.nombresProductos[indice],
                    DatosSistema.precios[indice]
            );
            indice++;
        }

        System.out.println("========================================");
    }

    public static void mostrarPedido() {

        double subtotal = 0;
        int indice = 0;

        System.out.println("--- PEDIDO ACTUAL ---");

        while (indice < DatosSistema.nombresProductos.length) {
            if (DatosSistema.cantidades[indice] > 0) {
                System.out.printf(
                        "%-20s x%-6d $%,.0f%n",
                        DatosSistema.nombresProductos[indice],
                        DatosSistema.cantidades[indice],
                        DatosSistema.precios[indice]
                                * DatosSistema.cantidades[indice]
                );

                subtotal =
                        subtotal
                                + DatosSistema.precios[indice]
                                * DatosSistema.cantidades[indice];
            }
            indice++;
        }

        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {

        double total = DatosSistema.totalPedido;

        String separador = "========================================";

        System.out.println(separador);
        System.out.println("    " + DatosSistema.nombreRestaurante);
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(separador);

        System.out.printf(
                "FACTURA No. %03d%n",
                DatosSistema.numeroFactura
        );

        System.out.println("----------------------------------------");

        int indice = 0;

        while (indice < DatosSistema.nombresProductos.length) {
            if (DatosSistema.cantidades[indice] > 0) {
                System.out.printf(
                        "%-20s x%-6d $%,.0f%n",
                        DatosSistema.nombresProductos[indice],
                        DatosSistema.cantidades[indice],
                        DatosSistema.precios[indice]
                                * DatosSistema.cantidades[indice]
                );
            }
            indice++;
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(separador);
        System.out.println("Gracias por su visita!");
        System.out.println(DatosSistema.nombreRestaurante + " - Valledupar");
        System.out.println(separador);

        DatosSistema.numeroFactura++;
        DatosSistema.estadoMesa = 0;
    }
}