package biblioteca.repository;

import biblioteca.model.Cliente;
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

    public Emprestimo procurarEmprestimos(Emprestimo emprestimo){
        for(Emprestimo procuraEmprestimos: emprestimos){
            return procuraEmprestimos;
        }
        return null;
    }


}
