package biblioteca.service;

public class ResultadoEmprestimo {
    private boolean sucesso;
    private String mensagem;

    public ResultadoEmprestimo(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }
}
