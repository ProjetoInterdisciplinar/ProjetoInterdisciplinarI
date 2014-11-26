package br.com.yaw.ProjetoInterdisciplinarI;

import java.util.Scanner;

import javax.naming.LimitExceededException;

public class Main {
	
	//TODO: Preciso verificar se vai ficar desta forma ainda.
	public static int limiteLugares = 25;
	public static int[] matrizCorredor = new int[limiteLugares];
	public static int[] matrizJanela = new int[limiteLugares];

	public static void main(String[] args) {
		
		//Criação das variaveis utilizadas no sistema
		Scanner scaMenu = new Scanner(System.in);
		int opcaoMenu = 0;
		Boolean dadoCorreto = false;
		
		//Utilizo o Try/Cath/Finally 
		try {
			
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
						
					} else if (opcaoMenu == 3)
					{
						
					}
				} else {
					System.out.println("Por favor escolha uma opção válida");
					ExibeMenu();
				}
	            
			}
		}  
		catch (Exception ex) {
			System.out.println("Ocorreu um problema: ");
			ex.printStackTrace();
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
		//TODO: Preciso analisar se tem alguma matriz disponivel
		Scanner leia2 = new Scanner(System.in);
		int poltrona;
		String opcao;
		
		System.out.println("Escolha uma poltrona de 1 a " + limiteLugares);
		
		if (leia2.hasNextInt()) { //&& (leia2.nextInt()  > 0 && leia2.nextInt() <= limiteLugares)) {
			poltrona = leia2.nextInt();
			
			//TODO: Desenvolver o else
			if (poltrona > 0 && poltrona <= limiteLugares)
			{
				System.out.println("Escolha janela (J) ou corredor (C)");
				leia2 = new Scanner(System.in);
				opcao = leia2.next().toLowerCase();
				poltrona = poltrona - 1;
				
				//TODO: Desenvolver o else
				if (opcao.equals("j"))
				{
					//TODO: Desenvolver o else
					if (!PoltronaOcupada(matrizJanela, poltrona))
					{
						matrizJanela[poltrona] = 1;
						System.out.println("Venda Efetivada");
					}
				} else if (opcao.equals("c"))
				{
					//TODO: Desenvolver o else
					if (!PoltronaOcupada(matrizCorredor, poltrona))
					{
						matrizCorredor[poltrona] = 1;
						System.out.println("Venda Efetivada");
					}
				}
			}
		}
	}
	
	public static boolean PoltronaOcupada(int[] matriz, int lugar)
	{
		return (matriz[lugar] == 1)? true : false;
	}
	
	
	
}
