
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

    DoubleProperty EDolar = new SimpleDoubleProperty();//1.1256
    DoubleProperty EYuan = new SimpleDoubleProperty();//163.50

    DoubleProperty cantidadEuros = new SimpleDoubleProperty();
    @FXML
    public void initialize() {



        // Leer la cantidad introducida en euros

        leerTexfiel(cantidad, cantidadEuros);
        leerTexfiel(eu, EDolar);
        leerTexfiel(ey, EYuan);


        // Realizar las conversiones
        NumberBinding resultadoDolar = cantidadEuros.multiply(EDolar);
        NumberBinding resultadoYen = cantidadEuros.multiply(EYuan);

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


   public  void leerTexfiel(TextField textfield, DoubleProperty propiedad){

        textfield.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                propiedad.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                propiedad.set(0);
            }
        });

    }
}
