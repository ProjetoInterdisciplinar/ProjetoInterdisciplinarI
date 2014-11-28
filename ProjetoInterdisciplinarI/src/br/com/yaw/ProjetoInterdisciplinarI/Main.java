package br.com.yaw.ProjetoInterdisciplinarI;

import java.util.Arrays;
import java.util.Scanner;

import javax.naming.LimitExceededException;

public class Main {
	
	//TODO: Preciso verificar se vai ficar desta forma ainda.
	public static int limiteLugares = 25;
	public static Integer[] matrizCorredor = new Integer[limiteLugares];
	public static Integer[] matrizJanela = new Integer[limiteLugares];

	public static void main(String[] args) {
		
		//Utilizo o Try/Cath/Finally 
		try {
			Arrays.fill(matrizCorredor, 0);
			Arrays.fill(matrizJanela, 0);
			IniciaPrograma();
		}  
		catch (Exception ex) {
			System.out.println("Ocorreu um problema: ");
			ex.printStackTrace();
		}  
		finally {
		}				
	}
	
	public static void IniciaPrograma()
	{
		//Criação das variaveis utilizadas no sistema
		Scanner scaMenu = new Scanner(System.in);
		int opcaoMenu = 0;
		Boolean dadoCorreto = false;
		
		try	
		{
			ExibeMenu();
			
			while (!dadoCorreto) {
				scaMenu = new Scanner(System.in);
				
				if (scaMenu.hasNextInt())
					opcaoMenu = scaMenu.nextInt();
				
	            if (opcaoMenu > 0 && opcaoMenu < 4)
	            {
	            	dadoCorreto = true;
	            }
	            
				if (dadoCorreto)
				{
					if (opcaoMenu == 1)
					{
						Opcao1();
					} else if (opcaoMenu == 2)
					{
						Opcao2();
					} else if (opcaoMenu == 3)
					{
						
					}
				} else {
					System.out.println("Por favor escolha uma opção válida");
					ExibeMenu();
				}
			}
		}
		finally {
			if (scaMenu != null)
			{
				scaMenu.close();
				scaMenu = null;
			}
		}
	}
	
	//TODO: Documentar o método
	public static void ExibeMenu()
	{
		String strMenu = "MENU – VENDA DE PASSAGENS \n Escolha a opção: \n 1 - Vender passagem \n 2 - Mostrar mapa de ocupação do ônibus \n 3 - Encerrar";
		System.out.println(strMenu);
	}
	
	//TODO: Documentar o método, nome esta estranho
	public static void Opcao1()
	{
		if (Arrays.asList(matrizCorredor).contains(0) || Arrays.asList(matrizJanela).contains(0))
		{
			Scanner leia2 = new Scanner(System.in);
			int poltrona;
			String opcao;
			Boolean validaDados = false;
			
			System.out.println("Escolha uma poltrona de 1 a " + limiteLugares);
			
			if (leia2.hasNextInt()) {
				poltrona = leia2.nextInt();
				
				if (poltrona > 0 && poltrona <= limiteLugares)
				{
					System.out.println("Escolha janela (J) ou corredor (C)");
					leia2 = new Scanner(System.in);
					opcao = leia2.next().toLowerCase();
					poltrona = poltrona - 1;
					
					if (opcao.equals("j"))
					{
						if (!PoltronaOcupada(matrizJanela, poltrona))
						{
							matrizJanela[poltrona] = 1;
							System.out.println("Venda Efetivada");
							validaDados = true;
						}
					} else if (opcao.equals("c"))
					{
						if (!PoltronaOcupada(matrizCorredor, poltrona))
						{
							matrizCorredor[poltrona] = 1;
							System.out.println("Venda Efetivada");
							validaDados = true;
						}
					}
				}
			}
			
			//TODO: Verificar se desta forma esta coerente
			if (!validaDados)
			{
				Opcao1();
			} else {
				IniciaPrograma();
			}
		} else {
			System.out.println("LOTADO");
		}
	}
	
	//TODO: Documentar o método
	public static void Opcao2()
	{
		System.out.printf("%1s  %-7s   %-7s%n", "Poltrona",  "Janela", "Corredor");
		
		for (int i = 0; i < limiteLugares; i++)
		{
			System.out.printf("%5s  %9s   %7s%n",
							   (i + 1),
							   DetalhesPoltrona(matrizJanela, i),
							   DetalhesPoltrona(matrizCorredor, i)
							 );
		}
		
		System.out.println("\n");
		System.out.println("Poltronas Ocupadas: ");
		System.out.println("Poltronas Livres: ");
		IniciaPrograma();
	}
	
	//TODO: Documentar o método
	public static boolean PoltronaOcupada(Integer[] matriz, int lugar)
	{
		return (matriz[lugar] == 1)? true : false;
	}
	
	public static String DetalhesPoltrona(Integer[] matriz, int lugar)
	{
		return (PoltronaOcupada(matriz, lugar))? "Ocupada" : "Livre";
	}
}
