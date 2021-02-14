/* Programa feito pelo aluno:
 * 
 * Matheus Raphael Soares de Oliveira
 * 
 * Matr�cula: 190058587
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
					System.out.printf("\nVoc� escolheu a op��o 1: Entrada dos dados de Temperatura.\n");
					entradaTemperaturas();
					break;
				case 2:
					//calculo temperaturas medias
					System.out.printf("\nVoc� escolheu a op��o 2: Calculo da m�dia das temperaturas do m�s.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					mediaTemperaturas(mes, ano);
					break;
				case 3:
					//temperatura minima
					System.out.printf("\nVoc� escolheu a op��o 3: Temperatura m�nima do m�s.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					minimaTemperatura(mes, ano);
					break;
				case 4:
					//temperatura maxima
					System.out.printf("\nVoc� escolheu a op��o 4: Temperatura m�xima do m�s.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					maximaTemperatura(mes, ano);
					break;
				case 5:
					//relatorio meteorologico (medias de cada dia, media geral, minima e maxima)
					System.out.printf("\nVoc� escolheu a op��o 5: Relat�rio Meteorol�gico.\n");
					mes = auxiliarConsultaMes();
					ano = auxiliarConsultaAno();
					relatorioMeteorologico(mes, ano);
					break;
				case 6:
					System.out.println("Fechando o programa.");
					break;
				default:
					System.out.println("Sele��o inv�lida. Digite de novo: ");
			}
		
		} while (selecao != 6);

	}
	
	public static void entradaTemperaturas() {
		
		int auxMes = 0;
		int mes = 0, ano = 0;
		
		do {
			System.out.println("Digite o m�s dos dados que quer cadastrar: ");			
			mes = leitor.nextInt();
			if (checarMes(mes) == 1) auxMes = 1; else {
				System.out.println("M�s inv�lido, digite de novo: ");
			}
		} while (auxMes == 0);
		
		int auxAno = 0;
		do {
			System.out.println("Digite o ano dos dados que quer cadastrar: ");
			ano = leitor.nextInt();
			if (checarAno(ano) != 0) auxAno = 1; else {
				System.out.println("Ano inv�lido, digite de novo: ");
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
		
		System.out.printf("A temperatura m�dia desse m�s �: %.2f C�\n", media);
	}
	
	public static void minimaTemperatura(int mes, int ano) {

		double tempMinima = 10000;
		
		int dia = checarDia(mes, ano);
		
		for (int i = 0; i < dia; i++) {
			if(temperaturas[ano-2011][mes-1][i] < tempMinima) {
				tempMinima = temperaturas[ano-2011][mes-1][i];
			}
		}
		System.out.printf("A temperatura m�nima do m�s foi: %.2f C�\n", tempMinima);
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
		System.out.printf("A temperatura m�xima do m�s foi: %.2f C�\n", tempMaxima);
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
		//O codigo ja come�a mostrando a m�dia de cada dia
		System.out.printf("Mostrando a m�dia de temperatura de cada dia do mes: %d de %d.\n", mes, ano);
		
		int dia = checarDia(mes, ano);
		int auxDia = 0;
		
		for(int i = 0; i < dia; i++) {
			auxDia = i + 1;
			System.out.printf("A m�dia de temperatura do dia %d / %d / %d foi %.2f C�\n", auxDia, mes, ano, temperaturas[ano-2011][mes-1][i]);
		}
		//O resto dessa funcao � feita por outras funcoes
		mediaTemperaturas(mes, ano);
		minimaTemperatura(mes, ano);
		maximaTemperatura(mes, ano);
	}
	
	private static int auxiliarConsultaMes() {
		//Consulta se o mes � valido e retorna o valor se for
		int mes = 0;
		
		int auxMes = 0;
		do {
			System.out.println("Digite o m�s que deseja consultar: ");			
			mes = leitor.nextInt();
			if (checarMes(mes) == 1) auxMes = 1; else {
				System.out.println("M�s inv�lido, digite de novo: ");
			}
		} while (auxMes == 0);
		
		return mes;
	}
	
	private static int auxiliarConsultaAno() {
		//Consulta se o ano � valido e retorna o valor se for
		int ano = 0;
		
		int auxAno = 0;
		do {
			System.out.println("Digite o ano que deseja consultar: ");
			ano = leitor.nextInt();
			if (checarAno(ano) != 0) auxAno = 1; else {
				System.out.println("Ano inv�lido, digite de novo: ");
			}
		} while (auxAno == 0);
		
		return ano;
	}
	
	private static void menuOpcoes() {
		System.out.printf("\n\nQual opera��o deseja realizar? Digite o n�mero correspondente e aperte ENTER.\n");
		System.out.printf("\n");
		System.out.println("1: Entrada das temperaturas. (Cadastrar as temperaturas de um m�s da �ltima d�cada)");
		System.out.println("2: C�lculo da temperatura m�dia. (Mostra a temperatura m�dia de certo m�s)");
		System.out.println("3: C�lculo da temperatura m�nima. (Mostra o dia, ou dias, que tiveram a menor temperatura do m�s)");
		System.out.println("4: C�lculo da temperatura m�xima. (Mostra os dias com a maior temperatura)");
		System.out.println("5: Relat�rio meteorol�gico. (Mostra as m�dias da temperatura de cada um dos dias daquele m�s, a m�dia geral, assim como a m�nima e a m�xima do m�s)");
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
		//funcao para checar se o input do ano est� dentro da d�cada v�lida e se o ano � bissexto ou n�o
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
