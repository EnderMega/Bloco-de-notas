package br.edu.ifsp.teste;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class ControladorCenas
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void novoGrupo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("folder-create.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Criar novo Grupo");
            stage.setScene(new Scene(loader.load()));
    
            stage.setResizable(false); 
    
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void irNota(ActionEvent event) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("note-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irMenu(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TreeView<String> treeView;

    // This method loads files into the TreeView
    public void initialize() {
        File rootDirectory = new File("src/main/resources/notas"); // Specify your folder path here
        TreeItem<String> rootItem = new TreeItem<>(rootDirectory.getName());
        rootItem.setExpanded(true);  // Optionally expand the root item

        loadFiles(rootDirectory, rootItem);

        treeView.setRoot(rootItem);
    }
    // Recursively load files into the TreeView
    private void loadFiles(File folder, TreeItem<String> parentItem) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> fileItem = new TreeItem<>(file.getName());
                parentItem.getChildren().add(fileItem);
                if (file.isDirectory()) {
                    loadFiles(file, fileItem);  // Recurse if directory
                }
            }
        }
    }
}