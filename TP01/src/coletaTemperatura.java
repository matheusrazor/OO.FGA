/* Programa feito pelo aluno:
 * 
 * Matheus Raphael Soares de Oliveira
 * 
 * Matrícula: 190058587
 * 
 */

import java.util.Scanner;

public class coletaTemperatura {

	public static int MES = 12;
	public static int ANO = 10;
	public static int DIA = 31;
	
	public static Scanner leitor = new Scanner(System.in);
	
	public static double[][][] temperaturas = new double[ANO][MES][DIA];
	
	public static void main(String args[]) {
		//Colocando valores quaisquer em Janeiro de 2020
		for(int i = 0; i < DIA; i++) {
			int tempRandom = DIA - i;
			temperaturas[9][0][i] = tempRandom;
		}
		//Comeco do main

		int mes = 0, ano = 0;
		int selecao = 0;
		do {
			menuOpcoes();
			
			selecao = leitor.nextInt();
		
			switch(selecao) {
				case 1:
					//entrada das temperaturas
					System.out.printf("\nVocê escolheu a opção 1: Entrada dos dados de Temperatura.\n");
					entradaTemperaturas();
					break;
				case 2:
					//calculo temperaturas medias
					System.out.printf("\nVocê escolheu a opção 2: Calculo da média das temperaturas do mês.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					mediaTemperaturas(mes, ano);
					break;
				case 3:
					//temperatura minima
					System.out.printf("\nVocê escolheu a opção 3: Temperatura mínima do mês.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					minimaTemperatura(mes, ano);
					break;
				case 4:
					//temperatura maxima
					System.out.printf("\nVocê escolheu a opção 4: Temperatura máxima do mês.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					maximaTemperatura(mes, ano);
					break;
				case 5:
					//relatorio meteorologico (medias de cada dia, media geral, minima e maxima)
					System.out.printf("\nVocê escolheu a opção 5: Relatório Meteorológico.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					relatorioMeteorologico(mes, ano);
					break;
				case 6:
					System.out.println("Fechando o programa.");
					break;
				default:
					System.out.println("Seleção inválida. Digite de novo: ");
			}
		
		} while (selecao != 6);

	}
	
	public static void entradaTemperaturas() {
		
		int auxMes = 0;
		int mes = 0, ano = 0;
		
		do {
			System.out.println("Digite o mês dos dados que quer cadastrar: ");			
			mes = leitor.nextInt();
			if (checarMes(mes) == 1) auxMes = 1; else {
				System.out.println("Mês inválido, digite de novo: ");
			}
		} while (auxMes == 0);
		
		int auxAno = 0;
		do {
			System.out.println("Digite o ano dos dados que quer cadastrar: ");
			ano = leitor.nextInt();
			if (checarAno(ano) != 0) auxAno = 1; else {
				System.out.println("Ano inválido, digite de novo: ");
			}
		} while (auxAno == 0);
		
		int dia = checarDia(mes, ano);
		
		for (int i = 0; i < dia; i++) {
			int diaPrint = i + 1;
			System.out.println("Digite a temperatura do dia: " + diaPrint + " / " + mes + " / " + ano);
			temperaturas[ano-2011][mes-1][i] = leitor.nextDouble();
		}
		
	}
	
	public static void mediaTemperaturas(int mes, int ano) {
		
		int dia = checarDia(mes, ano);
		
		double media = 0;
		for (int i = 0; i < dia; i++) {
			media += temperaturas[ano-2011][mes-1][i];
		}
		media /= dia;
		
		System.out.printf("A temperatura média desse mês é: %.2f C°\n", media);
	}
	
	public static void minimaTemperatura(int mes, int ano) {

		double tempMinima = 10000;
		
		int dia = checarDia(mes, ano);
		
		for (int i = 0; i < dia; i++) {
			if(temperaturas[ano-2011][mes-1][i] < tempMinima) {
				tempMinima = temperaturas[ano-2011][mes-1][i];
			}
		}
		System.out.printf("A temperatura mínima do mês foi: %.2f C°\n", tempMinima);
		for (int i = 0; i < dia; i++) {
			int auxDia = 0;
			if(temperaturas[ano-2011][mes-1][i] == tempMinima) {
				auxDia = i + 1;
				System.out.println("Essa temperatura foi atingida no(s) dia(s): ");
				System.out.println(auxDia + " / "  + mes + " / " + ano);
			}
		}
		
	}
	
	public static void maximaTemperatura(int mes, int ano) {

		double tempMaxima = -10000;
		
		int dia = checarDia(mes, ano);
		
		for (int i = 0; i < dia; i++) {
			if(temperaturas[ano-2011][mes-1][i] > tempMaxima) {
				tempMaxima = temperaturas[ano-2011][mes-1][i];
			}
		}
		System.out.printf("A temperatura máxima do mês foi: %.2f C°\n", tempMaxima);
		for (int i = 0; i < dia; i++) {
			int auxDia = 0;
			if(temperaturas[ano-2011][mes-1][i] == tempMaxima) {
				auxDia = i + 1;
				System.out.println("Essa temperatura foi atingida no(s) dia(s): ");
				System.out.println(auxDia + " / "  + mes + " / " + ano);
			}
		}
		
	}
	
	public static void relatorioMeteorologico(int mes, int ano) {
		//O codigo ja começa mostrando a média de cada dia
		System.out.printf("Mostrando a média de temperatura de cada dia do mes: %d de %d.\n", mes, ano);
		
		int dia = checarDia(mes, ano);
		int auxDia = 0;
		
		for(int i = 0; i < dia; i++) {
			auxDia = i + 1;
			System.out.printf("A média de temperatura do dia %d / %d / %d foi %.2f C°\n", auxDia, mes, ano, temperaturas[ano-2011][mes-1][i]);
		}
		//O resto dessa funcao é feita por outras funcoes
		mediaTemperaturas(mes, ano);
		minimaTemperatura(mes, ano);
		maximaTemperatura(mes, ano);
	}
	
	private static int auxiliarConsultaMes() {
		//Consulta se o mes é valido e retorna o valor se for
		int mes = 0;
		
		int auxMes = 0;
		do {
			System.out.println("Digite o mês que deseja consultar: ");			
			mes = leitor.nextInt();
			if (checarMes(mes) == 1) auxMes = 1; else {
				System.out.println("Mês inválido, digite de novo: ");
			}
		} while (auxMes == 0);
		
		return mes;
	}
	
	private static int auxiliarConsultaAno() {
		//Consulta se o ano é valido e retorna o valor se for
		int ano = 0;
		
		int auxAno = 0;
		do {
			System.out.println("Digite o ano que deseja consultar: ");
			ano = leitor.nextInt();
			if (checarAno(ano) != 0) auxAno = 1; else {
				System.out.println("Ano inválido, digite de novo: ");
			}
		} while (auxAno == 0);
		
		return ano;
	}
	
	private static void menuOpcoes() {
		System.out.printf("\n\nQual operação deseja realizar? Digite o número correspondente e aperte ENTER.\n");
		System.out.printf("\n");
		System.out.println("1: Entrada das temperaturas. (Cadastrar as temperaturas de um mês da última década)");
		System.out.println("2: Cálculo da temperatura média. (Mostra a temperatura média de certo mês)");
		System.out.println("3: Cálculo da temperatura mínima. (Mostra o dia, ou dias, que tiveram a menor temperatura do mês)");
		System.out.println("4: Cálculo da temperatura máxima. (Mostra os dias com a maior temperatura)");
		System.out.println("5: Relatório meteorológico. (Mostra as médias da temperatura de cada um dos dias daquele mês, a média geral, assim como a mínima e a máxima do mês)");
		System.out.println("6: Aperte 6 para fechar o programa.");
		System.out.printf("\n");
	}
	
	private static int checarMes(int mes) {
		//funcao simples para checar o mes
		int aux = 0;
		if(mes >= 1 && mes <= 12) aux = 1;
		return aux;
	}
	
	private static int checarAno(int ano) {
		//funcao para checar se o input do ano está dentro da década válida e se o ano é bissexto ou não
		int aux = 0;
		if(ano >= 2011 && ano <= 2020) {
			aux = 1;
			if(ano % 4 == 0 && ano % 100 != 0) aux = 2;
		}
		return aux;
	}
	
	private static int checarDia(int mes, int ano) {
		//funcao para calcular quantos dias tem no mes selecionado, em fevereiro essa funcao retorna o numero certo de dias caso o ano seja bissexto
		int aux = 0;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			aux = 31;
		} else if(mes != 2) { aux = 30; } else {
			if(checarAno(ano) == 2) {
				aux = 29;
			} else aux = 28;
		}
		return aux;
	}
}
