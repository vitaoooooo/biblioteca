package biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {

    private LocalDate dataRecebido;
    private LocalDate dataRetorno;
    private String nomeCliente;
    private String nomeLivro;


    public Emprestimo(String nomeCliente, String nomeLivro) {
        this.nomeCliente = nomeCliente;
        this.nomeLivro = nomeLivro;
        this.dataRecebido = LocalDate.now();
        this.dataRetorno = LocalDate.now().plusDays(7);
    }



    public LocalDate getDataRecebido() {
        return dataRecebido;
    }



    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeClietne) {
        this.nomeCliente = nomeClietne;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}
