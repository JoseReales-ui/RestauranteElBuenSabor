package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcionMenu = 0;
        boolean ejecutarMenu = true;
        int intentosInvalidos = 0;

        String mensajeAuxiliar = "";
        int valorTemporal = 0;
        double montoAuxiliar = 0;
        boolean continuar = true;

        System.out.println("========================================");
        System.out.println("    " + DatosSistema.nombreRestaurante);
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");

        while (ejecutarMenu) {

            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            opcionMenu = scanner.nextInt();

            if (opcionMenu == 1) {

                ImpresionFactura.mostrarCarta();
                System.out.println();

            } else if (opcionMenu == 2) {

                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print(
                        "Numero de producto (1-"
                                + DatosSistema.nombresProductos.length
                                + "): "
                );

                int numeroProducto = scanner.nextInt();

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (
                        numeroProducto > 0
                                && numeroProducto <= DatosSistema.nombresProductos.length
                ) {

                    if (cantidad > 0) {

                        if (DatosSistema.estadoMesa == 0) {

                            System.out.print("Ingrese numero de mesa: ");
                            DatosSistema.numeroMesa = scanner.nextInt();

                            if (DatosSistema.numeroMesa > 0) {
                                DatosSistema.estadoMesa = 1;
                                mensajeAuxiliar =
                                        String.valueOf(DatosSistema.numeroMesa);
                                valorTemporal = DatosSistema.numeroMesa;
                                intentosInvalidos = valorTemporal + 1;
                            } else {
                                DatosSistema.numeroMesa = 1;
                                DatosSistema.estadoMesa = 1;
                                mensajeAuxiliar = "1";
                                valorTemporal = 1;
                                intentosInvalidos = 2;
                            }
                        }

                        DatosSistema.cantidades[numeroProducto - 1] =
                                DatosSistema.cantidades[numeroProducto - 1]
                                        + cantidad;

                        System.out.println("Producto agregado al pedido.");
                        System.out.println(
                                "  -> "
                                        + DatosSistema.nombresProductos[numeroProducto - 1]
                                        + " x"
                                        + cantidad
                        );

                        montoAuxiliar =
                                DatosSistema.precios[numeroProducto - 1]
                                        * cantidad;

                    } else {

                        if (cantidad == 0) {
                            System.out.println(
                                    "La cantidad no puede ser cero."
                            );
                        } else {
                            System.out.println(
                                    "Cantidad invalida. Ingrese un valor positivo."
                            );
                        }
                    }

                } else {

                    if (numeroProducto <= 0) {
                        System.out.println(
                                "El numero debe ser mayor a cero."
                        );
                    } else {
                        System.out.println(
                                "Producto no existe. La carta tiene "
                                        + DatosSistema.nombresProductos.length
                                        + " productos."
                        );
                    }
                }

                System.out.println();

            } else if (opcionMenu == 3) {

                System.out.println();

                if (UtilidadesPedido.hayProductosEnPedido()) {
                    ImpresionFactura.mostrarPedido();
                } else {
                    System.out.println(
                            "No hay productos en el pedido actual."
                    );
                    System.out.println(
                            "Use la opcion 2 para agregar productos."
                    );
                    continuar = true;
                }

                System.out.println();

            } else if (opcionMenu == 4) {

                System.out.println();

                if (UtilidadesPedido.hayProductosEnPedido()) {

                    double totalFactura =
                            CalculadorFactura.calcularTotalFactura();

                    valorTemporal = (int) totalFactura;
                    mensajeAuxiliar =
                            "Total calculado: $" + valorTemporal;
                    montoAuxiliar = totalFactura;

                    ImpresionFactura.imprimirFacturaCompleta();
                    System.out.println();

                } else {

                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println(
                            "Use la opcion 2 para agregar productos primero."
                    );

                    valorTemporal = 0;
                    mensajeAuxiliar = "";
                    montoAuxiliar = 0;
                    continuar = true;
                }

            } else if (opcionMenu == 5) {

                System.out.println();

                UtilidadesPedido.reiniciarPedido();

                intentosInvalidos = 0;
                valorTemporal = 0;
                mensajeAuxiliar = "";
                montoAuxiliar = 0;
                continuar = true;

                System.out.println(
                        "Mesa reiniciada. Lista para nuevo cliente."
                );
                System.out.println();

            } else if (opcionMenu == 0) {

                ejecutarMenu = false;
                System.out.println("Hasta luego!");

            } else {

                System.out.println(
                        "Opcion no valida. Seleccione entre 0 y 5."
                );

                intentosInvalidos++;

                if (intentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    intentosInvalidos = 0;
                }
            }
        }

        scanner.close();
    }
}
