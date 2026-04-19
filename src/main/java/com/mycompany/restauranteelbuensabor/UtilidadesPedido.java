package com.mycompany.restauranteelbuensabor;

public class UtilidadesPedido {

    public static boolean hayProductosEnPedido() {

        int contador = 0;
        int indice = 0;

        while (indice < DatosSistema.cantidades.length) {
            if (DatosSistema.cantidades[indice] > 0) {
                contador++;
            }
            indice++;
        }

        if (contador == 0) {
            DatosSistema.totalPedido = 0;
            DatosSistema.temporal = "";
        }

        return contador > 0;
    }

    public static void reiniciarPedido() {

        int indice = 0;

        while (indice < DatosSistema.cantidades.length) {
            DatosSistema.cantidades[indice] = 0;
            indice++;
        }

        DatosSistema.totalPedido = 0;
        DatosSistema.estadoMesa = 0;
        DatosSistema.numeroMesa = 0;
        DatosSistema.temporal = "";
    }
}
