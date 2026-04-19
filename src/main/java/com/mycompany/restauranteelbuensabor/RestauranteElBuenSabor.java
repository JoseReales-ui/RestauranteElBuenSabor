package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestauranteElBuenSabor {

    private static final List<Producto> carta = new ArrayList<>();
    private static Pedido pedidoActual = new Pedido();
    private static int numeroFactura = 1;

    public static void main(String[] args) {

        inicializarCarta();

        Scanner scanner = new Scanner(System.in);
        boolean ejecutarMenu = true;

        while (ejecutarMenu) {

            System.out.println("========================================");
            System.out.println("RESTAURANTE EL BUEN SABOR");
            System.out.println("========================================");
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();

            if (opcion == 1) {
                mostrarCarta();
            }
            else if (opcion == 2) {
                agregarProducto(scanner);
            }
            else if (opcion == 3) {
                mostrarPedido();
            }
            else if (opcion == 4) {
                generarFactura();
            }
            else if (opcion == 5) {
                pedidoActual.limpiar();
                System.out.println("Mesa reiniciada.");
            }
            else if (opcion == 0) {
                ejecutarMenu = false;
                System.out.println("Hasta luego!");
            }
            else {
                System.out.println("Opcion invalida.");
            }
        }

        scanner.close();
    }

    private static void inicializarCarta() {
        carta.add(new Producto("Bandeja Paisa", 32000));
        carta.add(new Producto("Sancocho de Gallina", 28000));
        carta.add(new Producto("Arepa con Huevo", 8000));
        carta.add(new Producto("Jugo Natural", 7000));
        carta.add(new Producto("Gaseosa", 4500));
        carta.add(new Producto("Cerveza Poker", 6000));
        carta.add(new Producto("Agua Panela", 3500));
        carta.add(new Producto("Arroz con Pollo", 25000));
    }

    private static void mostrarCarta() {
        int i = 1;
        for (Producto producto : carta) {
            System.out.printf(
                    "%d. %-22s $%,.0f%n",
                    i++,
                    producto.getNombre(),
                    producto.getPrecio()
            );
        }
    }

    private static void agregarProducto(Scanner scanner) {
        mostrarCarta();
        System.out.print("Numero de producto: ");
        int indice = scanner.nextInt();

        if (indice < 1 || indice > carta.size()) {
            System.out.println("Producto invalido.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        pedidoActual.agregarProducto(carta.get(indice - 1), cantidad);
        System.out.println("Producto agregado.");
    }

    private static void mostrarPedido() {
        if (pedidoActual.estaVacio()) {
            System.out.println("No hay productos en el pedido.");
            return;
        }

        for (ItemPedido item : pedidoActual.getItems()) {
            System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal()
            );
        }
    }

    private static void generarFactura() {
        if (pedidoActual.estaVacio()) {
            System.out.println("No hay productos para facturar.");
            return;
        }

        Factura factura = new Factura(pedidoActual, numeroFactura++);
        FacturaImpresor impresor = new FacturaImpresor();
        impresor.imprimir(factura);
    }
}
