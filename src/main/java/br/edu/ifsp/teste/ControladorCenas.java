package br.edu.ifsp.teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

public class ControladorCenas
{
    private Stage stage;
    private Scene scene;
    private Parent root;
	
	@FXML
	public void initialize() {
		if (listView != null) {
			listarArquivosTxt();
		} else {
			System.out.println("listView ainda não foi carregada.");

		}
	}

    public void irNota(ActionEvent event) throws IOException
    {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("note-view.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    // public void irMenu(ActionEvent event) throws IOException 
    // {
    //     Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
    //     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

	public void irMenu(ActionEvent event) throws IOException 
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
		Parent root = loader.load();
		
		ControladorCenas novoControlador = loader.getController();
		novoControlador.listarArquivosTxt();
		
		this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	@FXML
    private TextField pasta;

	@FXML
    private TextArea textoId;

	@FXML
	public void CriarArq(ActionEvent event) throws Exception
	{
		File arquivo = new File(pasta.getText() + ".txt");
		try {
			// if (arquivo.createNewFile())
			// {
				System.out.println("Arquivo criado: " + arquivo.getName());

				FileWriter aaa = new FileWriter(pasta.getText() + ".txt");
				aaa.write(textoId.getText());
				aaa.close();
			// }
			// else
			// {
			// 	//TODO: aaa
			// 	System.out.println("Arquivo já existe, imbecíl >:(");
			// }
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro, desculpa :(");
			e.printStackTrace();
		}
	}

	@FXML
	private TextField abrirArquivo;

	@FXML
	public void LerArq(ActionEvent event) throws Exception
	{
		try {
			if (abrirArquivo.getText() == null)
				return;
	
			Path path = Paths.get(abrirArquivo.getText() + ".txt");
			String arquivo = Files.readString(path);
			System.out.println(arquivo);
	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("note-view.fxml"));
			Parent root = loader.load();
	
			ControladorCenas novoControlador = loader.getController();
			novoControlador.textoId.setText(arquivo);
			novoControlador.pasta.setText(abrirArquivo.getText());
	
			this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			this.scene = new Scene(root);
			this.stage.setScene(this.scene);
			this.stage.show();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro, desculpa :(");
			e.printStackTrace();
		}
	}
	

	// @FXML
	// public void LerArq(ActionEvent event) throws Exception
	// {
	// 	try {
	// 		if (abrirArquivo.getText() == null)
	// 			return;
				
	// 		Path path = Paths.get(abrirArquivo.getText() + ".txt");
    //         String arquivo = Files.readString(path);
    //         System.out.println(arquivo);
	// 		Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("note-view.fxml"));

	// 		this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	// 		this.scene = new Scene(root);
	// 		this.stage.setScene(this.scene);
	// 		this.stage.show();
			
	// 		textoId.setText(arquivo);
	// 	}
	// 	catch (IOException e)
	// 	{
	// 		System.out.println("Ocorreu um erro, desculpa :(");
	// 		e.printStackTrace();
	// 	}
	// }

	@FXML
	public void deletar(ActionEvent event) throws Exception
	{
		File arquivo = new File(abrirArquivo.getText() + ".txt");

		if (arquivo.delete())
		{
			listarArquivosTxt();
			System.out.println("Arquivo deletado com sucesso.");
		}
		else
		{
			System.out.println("Falha ao deletar o arquivo.");
		}
	}

	@FXML
	private ListView<String> listView;

	@FXML
	public void listarArquivosTxt() {
		File pastaAtual = new File("."); // Diretório atual
		File[] arquivos = pastaAtual.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

		if (arquivos != null) {
			List<String> nomesArquivos = Arrays.stream(arquivos)
					.map(File::getName)
					.collect(Collectors.toList());

			ObservableList<String> listaObservavel = FXCollections.observableArrayList(nomesArquivos);
			listView.setItems(listaObservavel);
		} else {
			System.out.println("Nenhum arquivo .txt encontrado.");
		}
	}

	// public void initialize()
	// {
	// 	listarArquivosTxt();
	// }
}