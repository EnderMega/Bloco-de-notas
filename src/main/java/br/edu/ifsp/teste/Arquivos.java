package br.edu.ifsp.teste;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Arquivos
{
	public void CriarArq(String nome, String texto) throws Exception
	{
		File arquivo = new File(nome + ".txt");
		try {
			if (arquivo.createNewFile())
			{
				System.out.println("Arquivo criado: " + arquivo.getName());

				FileWriter aaa = new FileWriter(nome + ".txt");
				aaa.write(texto);
				aaa.close();
			}
			else
			{
				//TODO: aaa
				System.out.println("Arquivo já existe, imbecíl >:(");
			}
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro, desculpa :(");
			e.printStackTrace();
		}

	}

	public void LerArq(String nome, String texto) throws Exception
	{
		try {
			File arquivo = new File(nome + ".txt");
			texto = new Scanner(new File("filename")).useDelimiter("\\Z").next();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro, desculpa :(");
			e.printStackTrace();
		}
	}

    public void CriarPasta(String)
    {
        File pasta = new File("/path/directory").mkdirs();
    }
}