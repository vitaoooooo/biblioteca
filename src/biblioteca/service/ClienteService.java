package biblioteca.service;

import biblioteca.model.Cliente;
import biblioteca.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(Cliente cliente) {
        if(!cliente.getEmail().contains("@")){
            System.out.println("email invalido");
            return;
        }

        if(cliente.getTelefone().length()!=11){
            System.out.println("telefone invalido");
            return;
        }
            repository.cadastrarCliente(cliente);
    }

    public Cliente buscarCliente(String nomeCliente) {
        Cliente cliente = repository.procurarClientePorNome(nomeCliente);
        if(cliente == null){
            return null;
        } else {
            return cliente;
        }
    }

    public List<Cliente> listarClientes() {
        return repository.listarClientes();
    }
}
