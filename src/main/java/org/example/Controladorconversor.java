package org.example;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controladorconversor {
    @FXML
    private TextField candidad,eu, ey, resultadoEU, resultadoEY;


    @FXML
    public void initialize(){
    SimpleIntegerProperty cantidad = new SimpleIntegerProperty(10);
    /**
     * TASA DE DOLAR Y YENES
     * */
    SimpleDoubleProperty ED = new SimpleDoubleProperty(1.1256);
    SimpleDoubleProperty EY = new SimpleDoubleProperty(163.50);

    NumberBinding resultadoED = cantidad.multiply(ED);
    System.out.println(resultadoED.getValue());
}





}
