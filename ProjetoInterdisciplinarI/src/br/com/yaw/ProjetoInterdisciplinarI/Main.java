package br.com.yaw.ProjetoInterdisciplinarI;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	//Criação das variaveis globais para ser utilizadas em todos os métodos
	public static final int limiteLugares = 25;
	public static Integer[] matrizCorredor = new Integer[limiteLugares];
	public static Integer[] matrizJanela = new Integer[limiteLugares];

	public static void main(String[] args) {
		
		//Utilizo o Try/Cath 
		try {
			
			//Defino os valores da matriz para 0
			Arrays.fill(matrizCorredor, 0);
			Arrays.fill(matrizJanela, 0);
			
			//Início o programa
			IniciaPrograma();
		}  
		catch (Exception ex) {
			System.out.println("Ocorreu um problema: ");
			ex.printStackTrace();
		}  
	}
	
	/**
	 * Método responsável por iniciar o programa e exibir o menu com as 3 opções
	 * O método contém a validação de entrada da informação. 
	 */
	public static void IniciaPrograma()
	{
		//Variaveis utilizadas
		Scanner scaMenu = new Scanner(System.in);
		int opcaoMenu = 0;
		Boolean dadoCorreto = false;
		
		try	
		{
			//Executo o método para exibir o menu
			ExibeMenu();
			
			//Enquanto a informação de entrada não estiver correta, continua aparecendo o menu.
			while (!dadoCorreto) {
				scaMenu = new Scanner(System.in);
				
				//Verifico se a informação de entrada é um inteiro
				if (scaMenu.hasNextInt())
					opcaoMenu = scaMenu.nextInt();
				
				//Verifico se a informação de entrada é maior que zero e menor que 4
	            if (opcaoMenu > 0 && opcaoMenu < 4)
	            	dadoCorreto = true;
	            
	            //Caso a informação de entrada é correta, verifico qual a opção desejada
				if (dadoCorreto)
				{
					if (opcaoMenu == 1)
						Opcao1();
					else if (opcaoMenu == 2)
						Opcao2();
					else if (opcaoMenu == 3)
						Opcao3();
				} else {
					System.out.println("Por favor escolha uma opção válida");
					ExibeMenu();
				}
			}
		}
		finally {
			//Realizo um dispose dos objetos necessarios para garantir a performance de mémoria
			if (scaMenu != null)
			{
				scaMenu.close();
				scaMenu = null;
			}
		}
	}
	
	
	/**
	 * Exibe o menu de opções  
	 */
	public static void ExibeMenu()
	{
		String strMenu = "MENU – VENDA DE PASSAGENS \n Escolha a opção: \n 1 - Vender passagem \n 2 - Mostrar mapa de ocupação do ônibus \n 3 - Encerrar";
		System.out.println(strMenu);
	}
	
	/**
	 * Método responsável pela seleção de poltronas.
	 * É necessário informar duas entradas, número da poltrona, se é corredor ou janela.
	 * Verifico se as entradas são válidas, caso estejam válidas verifico se a poltrona está dísponivel.
	 */
	public static void Opcao1()
	{
		Scanner scaInfo = new Scanner(System.in);
		
		try
		{
			if (Arrays.asList(matrizCorredor).contains(0) || Arrays.asList(matrizJanela).contains(0))
			{
				int poltrona;
				String opcao;
				Boolean validaDados = false;
				
				System.out.println("Escolha uma poltrona de 1 a " + limiteLugares);
				
				if (scaInfo.hasNextInt()) {
					poltrona = scaInfo.nextInt();
					
					if (poltrona > 0 && poltrona <= limiteLugares)
					{
						System.out.println("Escolha janela (J) ou corredor (C)");
						scaInfo = new Scanner(System.in);
						opcao = scaInfo.next().toLowerCase();
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
				
				if (!validaDados)
				{
					Opcao1();
				} else {
					IniciaPrograma();
				}
			} else {
				System.out.println("LOTADO");
				IniciaPrograma();
			}
		} 
		finally
		{
			//Realizo um dispose dos objetos necessarios para garantir a performance de mémoria
			if (scaInfo != null)
			{
				scaInfo.close();
				scaInfo = null;
			}
			
		}
	}
	
	/**
	 * Método responsável pela exibição do mapa de poltronas.
	 * Com o For percorro todas as poltronas da Janela e do Corredor, verificando um a um se a poltrona está ocupada ou não
	 */
	public static void Opcao2()
	{
		System.out.printf("%1s  %-7s   %-7s%n", "Poltrona",  "Janela", "Corredor");
		int poltronasOcupadas = 0;
		int poltronasLivres = 0;
		
		for (int i = 0; i < limiteLugares; i++)
		{
			System.out.printf("%5s  %9s   %7s%n",
							   (i + 1),
							   DetalhesPoltrona(matrizJanela, i),
							   DetalhesPoltrona(matrizCorredor, i)
							 );
			
			if (matrizCorredor[i] == 1)
				poltronasOcupadas++;
			else
				poltronasLivres++;
			
			if (matrizJanela[i] == 1)
				poltronasOcupadas++;
			else
				poltronasLivres++;
		}
		
		System.out.println("\n");
		System.out.println("Poltronas Ocupadas: " + poltronasOcupadas);
		System.out.println("Poltronas Livres: " + poltronasLivres);
		System.out.println("\n");
		IniciaPrograma();
	}
	
	/**
	 * Método responsável por sair do sitema
	 */
	public static void Opcao3()
	{
		System.exit(0);
	}
	
	/**
	 * Funcionalidade que verifica se a poltrona está ocupada ou não
	 * @param matriz - Matriz da Janela ou Corredor
	 * @param lugar - Número da poltrona
	 * @return o Retorno do método um boolean True ou False
	 */
	public static boolean PoltronaOcupada(Integer[] matriz, int lugar)
	{
		return (matriz[lugar] == 1)? true : false;
	}
	
	/**
	 * Funcionalidade que exibe os detalhes da poltrona 
	 * @param matriz - Matriz da Janela ou Corredor
	 * @param lugar - Número da poltrona
	 * @return o Retorno será Ocupada ou Livre em formato de string
	 */
	public static String DetalhesPoltrona(Integer[] matriz, int lugar)
	{
		return (PoltronaOcupada(matriz, lugar))? "Ocupada" : "Livre";
	}
}
