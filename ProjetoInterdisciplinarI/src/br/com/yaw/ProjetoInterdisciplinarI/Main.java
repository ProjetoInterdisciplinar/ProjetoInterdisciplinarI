package br.com.yaw.ProjetoInterdisciplinarI;

import java.util.Scanner;

import javax.naming.LimitExceededException;

public class Main {
	
	//TODO: Preciso verificar se vai ficar desta forma ainda.
	public static int limiteLugares = 25;
	public int[] matrizCorredor = new int[limiteLugares];
	public int[] matrizJanela = new int[limiteLugares];

	public static void main(String[] args) {
		
		//Criação das variaveis utilizadas no sistema
		Scanner leia = new Scanner(System.in);
		int opcaoMenu;
		Boolean dadoCorreto = true;
		
		//Utilizo o Try/Cath/Finally 
		try {
			ExibeMenu();
			dadoCorreto = (leia.hasNextInt()); //&& (leia.nextInt()  > 0 && leia.nextInt() < 4));
			
			if (dadoCorreto)
			{
				opcaoMenu = leia.nextInt();
				
				if (opcaoMenu == 1)
				{
					Opcao1();
				} else if (opcaoMenu == 2)
				{
					
				} else if (opcaoMenu == 3)
				{
					
				}
			} else {
				System.out.println("Por favor escolha uma opção válida");
				ExibeMenu();
			}
		}  
		catch (Exception ex) {
			System.out.println("Ocorreu um problema: ");
			ex.printStackTrace();
		}  
		finally {
			if (leia != null)
			{
				leia.close();
				leia = null;
			}
		}				
	}
	
	//TODO: Documentar o método
	public static void ExibeMenu()
	{
		String strMenu = "Escolha a opção: \n 1 - xxx \n 2 - yyy \n 3 - pppp";
		System.out.println(strMenu);
	}
	
	//TODO: Documentar o método, nome esta estranho
	public static void Opcao1()
	{
		Scanner leia2 = new Scanner(System.in);
		int poltrona;
		System.out.println("Escolha uma poltrona de 1 a " + limiteLugares);
		
		if (leia2.hasNextInt()) { //&& (leia2.nextInt()  > 0 && leia2.nextInt() <= limiteLugares)) {
			poltrona = leia2.nextInt();
			
			if (poltrona  > 0 && poltrona <= limiteLugares)
			{
				System.out.println("Escolha janela (J) ou corredor (C)");
			}
		}
	}
	
	
	
}
