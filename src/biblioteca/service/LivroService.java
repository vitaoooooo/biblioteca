package biblioteca.service;

import biblioteca.model.Livro;
import biblioteca.repository.LivroRepository;


public class LivroService {

    private LivroRepository repository;

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

    public Livro buscarLivro(String nome) {
        Livro livro = repository.procurarPorNome(nome);
        if(livro == null) {
            return null;
        }
        return livro;
    }

}
