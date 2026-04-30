package biblioteca.service;

import biblioteca.model.Livro;
import biblioteca.repository.LivroRepository;

import java.util.List;
import java.util.Scanner;


public class LivroService {

    private LivroRepository repository;
    Scanner entrada = new Scanner(System.in);

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void cadastrarLivro(Livro livro) {

        if(livro.getQuantidade()<0){
            System.out.println("quantidade invalida");
            return;
        }
        if(livro.getPreco()<0){
            System.out.println("preco invalido");
            return;
        }
        else {
            repository.adicionar(livro);
        }
    }

    public void cadastrarLivroPorInfos(Livro livro) {
        repository.adicionar(livro);
    }

    public Livro buscarLivro(String nome) {
        Livro livro = repository.procurarPorNome(nome);
        if(livro == null) {
            return null;
        }
        return livro;
    }

    public List<Livro> listarLivros() {
        return repository.listarLivros();
    }

}
