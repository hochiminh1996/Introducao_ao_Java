
//COMPLETO

public class Passageiro {
	private String nome;
	private String cpf;

	public Passageiro(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Passageiro [nome=" + nome + ", cpf=" + cpf + "]";
	}

}
