
/*
 * PROJETO RESERVA : LINGUAGEM DE PROGRAMAÇÃO / PROFº ANDRE EVANDRO LOURENCO
 * NOME : FELIPPE MARQUES DA SILVA DE ALMEIDA / IFSP
 * LINGUAGEM : JAVA / 20/07/2021
 * 
 * */
import javax.swing.JOptionPane;
import java.util.*;

public class Teste {
	static ArrayList<Aviao> avioes = new ArrayList<Aviao>();
	static ArrayList<Voo> voos = new ArrayList<Voo>();
	static ArrayList<Passageiro> reservas = new ArrayList<Passageiro>();

	static String str = "";
	public static void main(String[] args) {
		try {
			menu();// ACESSANDO O MENU PRINCIPAL

		} catch (Exception e) {
			// EXCEPTION DE ERROS CONVENCIONAIS
			JOptionPane.showMessageDialog(null, "HOUVE UM ERRO : " + e);
			menu();
		} finally {
			System.out.println("APLICAÇÃO FINALIZADA");
		}

	}

	public static void menu() {
		int opt = 0;

		do {
			opt = Integer.parseInt(JOptionPane.showInputDialog("***RESERVAS DE PASSAGENS AÉREAS***"
					+ "\n\n1 - PARÂMETROS DO SISTEMA\n2 - RESERVAS DE PASSAGENS\n3 - SAIR\n"));

			switch (opt) {
			case 1:
				pDoSistema();
				break;
			case 2:
				rDePassagens();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
				menu();
			}
//			opt = 3;
			/*
			 * OPT =3, pois quando você seleciona uma opção do case, ele irá se manter na
			 * comparação dentro do while(OPT!=3). Logo, após acessar o 1, depois voltar ao
			 * menu principal, e tentar acessar o 3, ele irá manter o opt=1 e comparar 1!=3:
			 * sim, logo ele irá repetir erroneamento. Por esse motivo, quando sair do
			 * switch, o valor de será opt=3 para não ocasionar repetição do menu
			 * 
			 */
		} while (opt != 3);

	}

	private static void rDePassagens() {
		// CADASTRAR PASSAGEIRO
		int opt = 0;

		opt = Integer.parseInt(JOptionPane.showInputDialog("***RESERVA DE PASSAGENS***"
				+ "\n\n1 - FAZER RESERVA\n2 - CONSULTAR LUGARES VAZIOS\n3 - CONSULTAR RESERVAS JÁ REALIZADAS\n4 - VOLTAR\n"));

		switch (opt) {
		case 1:
			try {
				int i = 0;
				int lVazio = 0;

				if (!Teste.voos.isEmpty()) {
					Passageiro passageiro = new Passageiro(JOptionPane.showInputDialog("DIGITE O NOME DO PASSAGEIRO"),
							JOptionPane.showInputDialog("DIGITE O CPF"));

					str = "[AERONAVES CADASTRADAS]\n\n";
					int c = 0;
					for (Aviao av : avioes) {
						str += "Nº " + c + " Modelo : " + av.getModelo() + " Assento : " + (av.getAssento() - 1)
								+ " Fileira : " + (av.getFileira() - 1) + "\n \n";
						c++;
					}

					int nVoo = Integer.parseInt(JOptionPane.showInputDialog(str + "DIGITE O Nº DO VOO"));
					if (nVoo > Teste.voos.size()) {
						JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA. TENTE NOVAMENTE");
						rDePassagens();
					}

					Aviao av = (Aviao) Teste.avioes.get(nVoo);
					JOptionPane.showMessageDialog(null, "CASTING: " + av.modelo);

					// MOSTRA OS ASSENTOS DISPONÍVEIS NO AVIÃO
					for (i = 0; i < av.getAssento(); i++) {
						System.out.println();
						for (int j = 0; j < av.getAssento(); j++) {
							if (av.verificaLugarOcupado(i, j)) {
								lVazio++;// contador
							}
						}
					}

					JOptionPane.showMessageDialog(null, "HÁ LUGARES " + lVazio + " DISPONÍVEIS");

					int fileira = Integer.parseInt(JOptionPane.showInputDialog("DIGITE A FILEIRA"));
					int assento = Integer.parseInt(JOptionPane.showInputDialog("DIGITE O ASSENTO"));

					if (fileira + 1 > av.getFileira() || assento + 1 > av.getAssento()) {
						JOptionPane.showMessageDialog(null, " ASSENTO/FILEIRA INVÁLIDO. TENTE NOVAMENTE");
						rDePassagens();
					} else if (av.verificaLugarOcupado(fileira, assento)) {
//						JOptionPane.showMessageDialog(null,
//								"O RETORNO É  : " + av.verificaLugarOcupado(fileira, assento));
						JOptionPane.showMessageDialog(null,
								passageiro.getNome() + ", SEU ASSENTO FOI RESERVADO COM SUCESSO");
						av.setPassageiro(passageiro, fileira, assento);
						Teste.reservas.add(passageiro);
						rDePassagens();

					} else {
						JOptionPane.showMessageDialog(null,
								"ESCOLHA JÁ PREENCHIDA PELO USUÁRIO " + av.getPassageiro(fileira, assento).getNome());
						rDePassagens();
					}

//				JOptionPane.showMessageDialog(null, aviao);				
				} else {
					JOptionPane.showMessageDialog(null, "NÃO HÁ VOOS CADASTRADOS. CONSULTE O ADMINISTRADOR");
					rDePassagens();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		case 2:
			// CONSULTAR LUGARES VAZIOS
			if (!Teste.voos.isEmpty()) {
				int c = 0;
				str = "[CONSULTAR LUGARES VAZIOS]\n\n";
				for (Aviao v : Teste.avioes) {
					str += c + ") AVIÃO " + v.getModelo() + "\n";
					c++;
				}

				int op = Integer.parseInt(JOptionPane.showInputDialog(str + "\nDIGITE O AVIÃO DESEJADO"));

				Aviao av = (Aviao) Teste.avioes.get(op);

				// TABELA DE ASSENTOS VAGOS
				int i = 0;
				String str1 = "[ASSENTOS VAGOS]\n\n";
				for (i = 0; i < av.getFileira(); i++) {
					System.out.println();
					for (int j = 0; j < av.getAssento(); j++) {
						if (av.verificaLugarOcupado(i, j)) {
							str1 += "[" + i + "]" + "[" + j + "]" + " (  ) ";
						}
					}
					str1 += "\n\n";
				}
				JOptionPane.showMessageDialog(null, str1);
				rDePassagens();

			} else {
				JOptionPane.showMessageDialog(null, "NÃO HÁ VOOS CADASTRADOS. CONSULTE O ADMINISTRADOR");
				rDePassagens();
			}
			break;
		case 3:
			// CONSULTAR RESERVAS REALIZADAS
			if (!Teste.reservas.isEmpty()) {
				str = "[RESERVAS JÁ REALIZADAS]\n\n";
				int cont = 0;
				for (Passageiro p : Teste.reservas) {
					str += cont + 1 + "º Nome : " + p.getNome() + " CPF : " + p.getCpf() + "\n";
					cont++;
				}

				JOptionPane.showMessageDialog(null, str);
				rDePassagens();

			} else {
				JOptionPane.showMessageDialog(null, "NÃO HÁ PASSAGEIROS REGISTRADOS. CONSULTE O ADMINISTRADOR");
				rDePassagens();
			}
			break;
		case 4:

			return;
		default:
			JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
			rDePassagens();
		}

	}

	private static void pDoSistema() {
		int opt = 0;
		opt = Integer.parseInt(JOptionPane.showInputDialog("***PARÂMETROS DO SISTEMA***"
				+ "\n\n1 - CADASTRAR AERONAVE\n2 - CADASTRAR VOOS\n3 - LISTAR AERONAVES\n4 - LISTAR VOOS\n5 - VOLTAR\n"));

		switch (opt) {
		case 1:
			// CADASTRAR AERONAVE

			Aviao aero = new Aviao(JOptionPane.showInputDialog("NOME DA AERONAVE:"),
					Integer.parseInt(JOptionPane.showInputDialog("Nº DE FILEIRAS")),
					Integer.parseInt(JOptionPane.showInputDialog("Nº DE ASSENTOS")));

			Teste.avioes.add(aero);
			JOptionPane.showMessageDialog(null, "AERONAVE CADASTRADA COM SUCESSO");
			pDoSistema();
			break;
		case 2:
			// CADASTRAR VOOS
			int cont = 0;
			str = "[CADASTRAR VOOS]\n\n";
			// EXIBE AERONAVES CADASTRADAS
			if (!Teste.avioes.isEmpty()) {
				for (Aviao av : avioes) {
					str += "Nº " + cont + " Modelo : " + av.getModelo() + " Assento : " + av.getAssento()
							+ " Fileira : " + av.getFileira() + "\n";
					cont++;
				}
				int nvoo = Integer.parseInt(JOptionPane.showInputDialog(str + "\nDIGITE O Nº DA AERONAVE"));

//			PULO DO GADO ABAIXO : CASTING 
				Aviao castingObj = (Aviao) Teste.avioes.get(nvoo);// PEGA A AERONAVE COM BASE NO NUMERO SELECIONADO E
																	// TRANSFORMA EM OBJ
//				JOptionPane.showMessageDialog(null, "OBJETO COM NOME : " + castingObj.getModelo());

				// CRIAR UM OBJETO DE VOO
				Voo v = new Voo(castingObj, nvoo, JOptionPane.showInputDialog("DIGITE A DATA DO VOO"),
						JOptionPane.showInputDialog("DIGITE A HORA"));
				Teste.voos.add(v);// O objeto criado é transferido para o Arraylist do tipo voo

				JOptionPane.showMessageDialog(null, "VOO CADASTRADO COM SUCESSO : " + v.getAeronave().modelo);
				pDoSistema();
			} else {
				JOptionPane.showMessageDialog(null, "NÃO HÁ AERONAVES CADASTRADAS");
				pDoSistema();
			}
			break;
		case 3:
			/// TESTE QUE EXIBE AVIÕES CADASTRADOS / MODELO / ASSENTOS / FILEIRAS

			str = "";
			int c = 0;
			if (!Teste.avioes.isEmpty()) {
				str = "[AERONAVES CADASTRADAS]\n\n";
				for (Aviao av : avioes) {
					str += "Nº " + c + " Modelo : " + av.getModelo() + " Assento : " + av.getAssento() + " Fileira : "
							+ av.getFileira() + "\n";
					c++;
				}
				JOptionPane.showMessageDialog(null, str);

			} else {
				JOptionPane.showMessageDialog(null, "NÃO HÁ AVIÕES CADASTRADOS");
			}
			pDoSistema();
			break;
		case 4:
			/// TESTE QUE EXIBE VOOS CADASTRADOS / HORA / DATA / N
			str = "";
			int n = 0;
			if (!Teste.voos.isEmpty()) {
				str = "[VOOS CADASTRADOS]\n \n";
				for (Voo listaVoo : voos) {
					str += "NOME DA AERONAVE " + ": " + listaVoo.getAeronave().modelo + "\nNº)" + n + " DO VOO : ["
							+ listaVoo.getNro() + "] HORA DO VOO :" + listaVoo.getHora() + " DATA DO VOO : "
							+ listaVoo.getData() + "\n\n";
					n++;
				}
				JOptionPane.showMessageDialog(null, str);
			} else
				JOptionPane.showMessageDialog(null, "NÃO HÁ VOOS CADASTRADOS");
			pDoSistema();
			break;

		case 5:
			return;
		default:
			JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
			pDoSistema();
		}
	}

}
