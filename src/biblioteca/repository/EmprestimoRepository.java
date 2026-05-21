package biblioteca.repository;

import biblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarNaListaEmprestimos(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void removerDaListaEmprestimos(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }

    public Emprestimo procurarEmprestimos(String nomeClienteEmprestimo){
        for(Emprestimo procuraEmprestimos: emprestimos){
            return procuraEmprestimos;
        }
        return null;
    }

    public Emprestimo procurarEmprestimoAberto(String nomeCliente, String nomeLivro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getNomeCliente().equalsIgnoreCase(nomeCliente)
                    && emprestimo.getNomeLivro().equalsIgnoreCase(nomeLivro)
                    && !emprestimo.isDevolvido()) {
                return emprestimo;
            }
        }

        return null;
    }


}
