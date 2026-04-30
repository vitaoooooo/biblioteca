package biblioteca.repository;

import biblioteca.model.Cliente;
import biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class LivroRepository {

    private List<Livro> livros = new ArrayList<>();

    public String normalizar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD);
        semAcento = semAcento.replaceAll("[^\\p{ASCII}]", "");
        return semAcento.toLowerCase();
    }

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public void remover(Livro livro) {
        livros.remove(livro);
    }

    public Livro procurarPorNome(String nome) {
        for(Livro livrosProcura: livros){
            if(normalizar(livrosProcura.getNome()).equals(normalizar(nome))){
                return livrosProcura;
            }
        }
        return null;
    }

    public List<Livro> listarLivros() {
        return livros;
    }
}
