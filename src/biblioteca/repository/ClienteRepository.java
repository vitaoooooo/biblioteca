package biblioteca.repository;

import biblioteca.model.Cliente;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public String normalizar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD);
        semAcento = semAcento.replaceAll("[^\\p{ASCII}]", "");
        return semAcento.toLowerCase();
    }

    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente procurarClientePorNome(String nomeCliente) {
        for(Cliente clienteProcura: clientes){
            if(normalizar(clienteProcura.getNome()).equals(normalizar(nomeCliente))){
                return clienteProcura;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }


}
