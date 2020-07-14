package main.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.data_base.base.load_and_save.StorageFileNames;
import main.data_base.base.load_and_save.load_datas.LoadData;

public class Application extends javafx.application.Application {

    public static void main(String[] args){
        StorageFileNames.initNameFiles();

        LoadData loadData = LoadData.getInstance();
        loadData.loadProperties();

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view_core/enterSystem.fxml"));
        stage.setTitle("Вход в систему");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
