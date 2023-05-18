public class Banco extends Pessoa{

    public Pessoa cliente;
    public String numConta;
    private double saldo;

    public Banco(Pessoa cliente, String numConta, double saldo) {
        super(cliente.getNome(), cliente.getCpf());
        this.cliente = cliente;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public String getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void depositar(double valor) {
        saldo += valor;
    }

}
