import java.util.Arrays;

//import javax.swing.JOptionPane;

public class Aviao extends Aeronave {
	public Passageiro[][] lugares;
	private int fileira;
	private int assento;

	public Aviao(String modelo, int fileira, int assento) {
		super(modelo);// passa a assinatura para o contrutor da classe mae
		setModelo(modelo);// seta modelo
		setFileira(fileira);
		setAssento(assento);
		this.lugares = new Passageiro[fileira][assento];
	}

	// Construt caso seja preenchido com string
	public Aviao(String modelo, String fileira, String assento) {
		super(modelo);
		try {
			this.lugares = new Passageiro[Integer.parseInt(fileira)][Integer.parseInt(assento)];
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public boolean verificaLugarOcupado(int fileira, int assento) {
		if (this.lugares[fileira][assento] == null) {
			return true;
		} else {
			return false;
		}
	}

	// seta o passageiro e passa a referência do obj
	public void setPassageiro(Passageiro passageiro, int fileira, int assento) {
		this.lugares[fileira][assento] = passageiro;
	}

	public Passageiro getPassageiro(int fileira, int assento) {
		return lugares[fileira][assento];
	}

	@Override
	public void setModelo(String modelo) {
		this.modelo = modelo;

	}

	@Override
	public String getModelo() {
		// TODO Auto-generated method stub
		return this.modelo;
	}

	public int getFileira() {
		return fileira;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public int getAssento() {
		return assento;
	}

	public void setAssento(int assento) {
		this.assento = assento;
	}

	@Override
	public String toString() {
//		return "Aviao [lugares=" + Arrays.toString(lugares). + "]\n";
		return "Modelo : " + getModelo() + "Lugares" + Arrays.toString(lugares);
	}

}
