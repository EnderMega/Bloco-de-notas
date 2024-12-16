module br.edu.ifsp.teste {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsp.teste to javafx.fxml;
    exports br.edu.ifsp.teste;
}