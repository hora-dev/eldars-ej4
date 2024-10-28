package com.eldars.fraud;

import java.util.*;

public class FraudulentActivityNotifications {

    // Método principal para contar las notificaciones
    public static int countNotifications(int[] gastos, int d) {
        int notificaciones = 0;

        // ArrayList para mantener la ventana de `d` días
        List<Integer> ventana = new ArrayList<>();

        // Inicializa la ventana con los primeros `d` días de gastos
        for (int i = 0; i < d; i++) {
            ventana.add(gastos[i]);
        }

        // Procesa cada día desde el día `d` en adelante
        for (int i = d; i < gastos.length; i++) {
            // Calcula la mediana de la ventana
            double mediana = calcularMediana(ventana);

            // Verifica si se cumple la condición para una notificación
            if (gastos[i] >= 2 * mediana) {
                notificaciones++;
            }

            // Actualiza la ventana: elimina el gasto más antiguo y agrega el nuevo gasto
            ventana.remove(0);
            ventana.add(gastos[i]);
        }

        return notificaciones;
    }

    // Método para calcular la mediana de una lista de enteros
    private static double calcularMediana(List<Integer> lista) {
        Collections.sort(lista);
        int n = lista.size();

        if (n % 2 == 1) {
            return lista.get(n / 2); // Caso impar
        } else {
            return (lista.get(n / 2 - 1) + lista.get(n / 2)) / 2.0; // Caso par
        }
    }

    // Método principal para probar el código
    public static void main(String[] args) {
        int[] gastos = {10, 20, 30, 40, 60};
        int d = 3;

        int notificaciones = countNotifications(gastos, d);
        System.out.println("Número de notificaciones: " + notificaciones);
    }
}
