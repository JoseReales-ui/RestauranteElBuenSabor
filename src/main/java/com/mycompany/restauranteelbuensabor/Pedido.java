package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        // Si el producto ya existe en el pedido, se suma la cantidad
        for (ItemPedido item : items) {
            if (item.getProducto().equals(producto)) {
                item.agregarCantidad(cantidad);
                return;
            }
        }

        items.add(new ItemPedido(producto, cantidad));
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public int contarProductosDiferentes() {
        return items.size();
    }

    public double calcularSubtotal() {
        double subtotal = 0;

        for (ItemPedido item : items) {
            subtotal += item.calcularSubtotal();
        }

        return subtotal;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void limpiar() {
        items.clear();
    }
}
