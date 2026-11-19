
package org.example;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        enlaces(eu);
        enlaces(ey);

    }


    /**
     * Leertexfiel permite leer los textfield que pase por parametros para hacer una llamada del evento  y poner los valores
     *
     * @param  textfield pasa como parametro el id del textfield que tengamos
     * @param  propiedad pasa como parametro  la propieda de tipo double
     * @exception  NumberFormatException excepcion  de que se debe poner un numero y no  un valor String
     * */

   public  void leerTexfiel(TextField textfield, DoubleProperty propiedad){

        textfield.textProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue == null || newValue.trim().isEmpty()) {
                propiedad.set(0);
                return;
            }

            try {


                if(newValue.matches(".*[a-zA-Z].*")){
                    throw  new NumberFormatException();

                }
                propiedad.set(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                textfield.setText(oldValue);
                mostrarAlerta("Erro", "Error: no puede introducir letras");
                propiedad.set(0);
            }
        });

    }

    /**
     *
     * Enlace que  esta desastivada al no sé qué haya cantidad
     * */
    public void enlaces(TextField textField){
        textField.disableProperty().bind(
                Bindings.when(cantidadEuros.lessThanOrEqualTo(0))
                        .then(true)
                        .otherwise(false)
        );
    }

    /**
     * mostrarAlerta  llama a la clase aleta para mostrar la altera
     * */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
