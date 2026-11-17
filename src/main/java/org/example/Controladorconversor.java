
package org.example;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Controladorconversor {

    @FXML
    private TextField cantidad, eu, ey, resultadoEU, resultadoEY;

    @FXML
    private Button limpiar;

    @FXML
    public void initialize() {

        DoubleProperty ED = new SimpleDoubleProperty();//1.1256
        DoubleProperty EY = new SimpleDoubleProperty();//163.50

        DoubleProperty cantidadEuros = new SimpleDoubleProperty();

        // Leer la cantidad introducida en euros

        cantidad.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                cantidadEuros.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                cantidadEuros.set(0);
            }
        });

        eu.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                ED.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                ED.set(0);
            }
        });
        ey.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                EY.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                EY.set(0);
            }
        });

        // Realizar las conversiones
        NumberBinding resultadoDolar = cantidadEuros.multiply(ED);
        NumberBinding resultadoYen = cantidadEuros.multiply(EY);

        // Mostrar los resultados
        resultadoEU.textProperty().bind(resultadoDolar.asString());
        resultadoEY.textProperty().bind(resultadoYen.asString());

        // Botón limpiar
        limpiar.setOnAction(event -> {
            cantidad.clear();
            eu.clear();
            ey.clear();
        });
    }


   /*public  void leerTexfiel(TextField textfield, DoubleProperty propiedad){

        textfield.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                propiedad.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                propiedad.set(0);
            }
        });

    }*/
}
