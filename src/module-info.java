module arraypizz.module {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    opens com.bluejaygm.arraypizz.login;
    exports com.bluejaygm.arraypizz.login;

    opens com.bluejaygm.arraypizz.cadastro;
    exports com.bluejaygm.arraypizz.cadastro;

    opens com.bluejaygm.arraypizz.telaprincipal;
    exports com.bluejaygm.arraypizz.telaprincipal;

    opens com.bluejaygm.arraypizz.teladoadm;
    exports com.bluejaygm.arraypizz.teladoadm;

    opens com.bluejaygm.arraypizz.telaprincipal.cadastrarclientes;
    exports com.bluejaygm.arraypizz.telaprincipal.cadastrarclientes;
}