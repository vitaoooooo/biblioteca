package biblioteca.service;

import biblioteca.model.Cliente;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.repository.EmprestimoRepository;


public class EmprestimoService {

    private ClienteService clienteService;
    private LivroService livroService;
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(ClienteService clienteService, LivroService livroService, EmprestimoRepository emprestimoRepository){
        this.clienteService = clienteService;
        this.livroService = livroService;
        this.emprestimoRepository = emprestimoRepository;
    }

    public ResultadoEmprestimo realizarEmprestimo(Emprestimo emprestimo) {

        Cliente cliente = clienteService.buscarCliente(emprestimo.getNomeCliente());
        if(cliente == null){
            return new ResultadoEmprestimo(false, "Cliente nao cadastrado!");
        }

        Livro livro = livroService.buscarLivro(emprestimo.getNomeLivro());

        if(livro == null){
            return new ResultadoEmprestimo(false, "Livro nao cadastrado!");
        }

        if(livro.getQuantidade()<=0){
            return new ResultadoEmprestimo(false, "Quantidade de livro insuficiente!");
        }

        livro.setQuantidade(livro.getQuantidade()-1);
        emprestimoRepository.adicionarNaListaEmprestimos(emprestimo);

        return new ResultadoEmprestimo(true, "Emprestimo realizado com sucesso!");
    }


    public boolean devolverEmprestimo(String nomeCliente, String nomeLivro) {
        Emprestimo emprestimo = emprestimoRepository.procurarEmprestimoAberto(nomeCliente, nomeLivro);

        if (emprestimo == null) {
            return false;
        }

        Livro livro = livroService.buscarLivro(nomeLivro);

        if (livro == null) {
            return false;
        }

        emprestimo.devolver();
        livro.setQuantidade(livro.getQuantidade() + 1);

        return true;
    }

}
