package biblioteca.service;

import biblioteca.model.Cliente;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.repository.EmprestimoRepository;
import biblioteca.repository.LivroRepository;

import java.time.LocalDate;


public class EmprestimoService {

    private ClienteService clienteService;
    private LivroService livroService;
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(ClienteService clienteService, LivroService livroService, EmprestimoRepository emprestimoRepository){
        this.clienteService = clienteService;
        this.livroService = livroService;
        this.emprestimoRepository = emprestimoRepository;
    }

    public boolean realizarEmprestimo(Emprestimo emprestimo) {
        Cliente cliente = clienteService.buscarCliente(emprestimo.getNomeCliente());
        if(cliente == null){
            return false;
        }

        Livro livro = livroService.buscarLivro(emprestimo.getNomeLivro());

        if(livro == null){
            return false;
        }

        if(livro.getQuantidade()<=0){
            return false;
        }

        livro.setQuantidade(livro.getQuantidade()-1);

        emprestimoRepository.adicionarNaListaEmprestimos(emprestimo);
        return true;
    }



}
