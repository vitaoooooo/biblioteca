package biblioteca.main;

import biblioteca.model.Cliente;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.repository.ClienteRepository;
import biblioteca.repository.EmprestimoRepository;
import biblioteca.repository.LivroRepository;
import biblioteca.service.ClienteService;
import biblioteca.service.EmprestimoService;
import biblioteca.service.LivroService;
import biblioteca.service.ResultadoEmprestimo;

import java.util.List;
import java.util.Scanner;


public class App {

    static LivroService serviceLivro = getLivroService();
    static ClienteService serviceCliente = getClienteService();
    static EmprestimoService emprestimoService = getEmprestimoService();

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Buscar livro");
            System.out.println("2 - Buscar cadastro");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Fazer emprestimo");
            System.out.println("5 - Cadastrar livro");
            System.out.println("6 - Listar livros");
            System.out.println("7 - Remover livros");
            System.out.println("8 - Devolver livro emprestado");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro(entrada, serviceLivro);
                    break;
                case 2:
                    buscarCliente(entrada);
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    fazerEmprestimo(entrada);
                    break;
                case 5:
                    cadastrarLivroPorInfos(entrada);
                    break;
                case 6:
                    listarLivros();
                    break;
                case 7:
                    removerLivros(entrada);
                    break;
                case 8:
                    realizarDevolucaoDeLivro(entrada);
                    break;
            }

        } while(opcao != 0);

        entrada.close();
        
    }


    // usado para mexer no LivroService
    private static LivroService getLivroService() {
        LivroRepository repositoryLivro = new LivroRepository();
        LivroService serviceLivro = new LivroService(repositoryLivro);

        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 39.90, 5);
        Livro livro2 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 99.90, 3);
        Livro livro3 = new Livro("1984", "George Orwell", 29.90, 8);
        Livro livro4 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 24.90, 10);

        serviceLivro.cadastrarLivro(livro1);
        serviceLivro.cadastrarLivro(livro2);
        serviceLivro.cadastrarLivro(livro3);
        serviceLivro.cadastrarLivro(livro4);
        return serviceLivro;
    }

    // usado para mexer no ClienteService
    private static ClienteService getClienteService() {
        ClienteRepository repositoryCliente = new ClienteRepository();
        ClienteService serviceCliente = new ClienteService(repositoryCliente);

        Cliente cliente1 = new Cliente("vitor", "vitor@gmail.com", "35998036785");
        Cliente cliente2 = new Cliente("Vitor Silva", "vitor.silva@email.com", "35998000001");
        Cliente cliente3 = new Cliente("Ana Souza", "ana.souza@email.com", "11987000002");
        Cliente cliente4 = new Cliente("Carlos Pereira", "carlos.pereira@email.com", "21996000003");

        serviceCliente.cadastrarCliente(cliente1);
        serviceCliente.cadastrarCliente(cliente2);
        serviceCliente.cadastrarCliente(cliente3);
        serviceCliente.cadastrarCliente(cliente4);
        return serviceCliente;
    }

    private static EmprestimoService getEmprestimoService() {
        EmprestimoRepository repositoryEmprestimo = new EmprestimoRepository();
        return new EmprestimoService(serviceCliente, serviceLivro, repositoryEmprestimo);
    }


    public static void buscarLivro(Scanner entrada, LivroService service) {

        System.out.println("Digite o nome do livro que deseja procurar: ");
        String nome = entrada.nextLine();

        Livro resultado = service.buscarLivro(nome);
        if(resultado == null){
            System.out.println("Livro nao encontrado");
        } else {
            System.out.println("Livro encontrado");
            System.out.println("Nome: " + resultado.getNome());
            System.out.println("Preço: " + resultado.getPreco());
            System.out.println("Quantidade em estoque: " + resultado.getQuantidade());
        }
    }

    public static void buscarCliente(Scanner entrada){

        System.out.println("Digite o nome do usuario que deseja procurar: ");
        String buscaClienteNome = entrada.nextLine();

        Cliente resultadoBuscaCliente = serviceCliente.buscarCliente(buscaClienteNome);
        if(resultadoBuscaCliente == null){
            System.out.println("Usuario nao encontrado");
        } else {
            System.out.println("Nome: " + resultadoBuscaCliente.getNome());
            System.out.println("Email: " + resultadoBuscaCliente.getEmail());
            System.out.println("Telefone: " + resultadoBuscaCliente.getTelefone());
        }
    }

    public static void listarClientes() {
        List<Cliente> lista = serviceCliente.listarClientes();

        for(Cliente c: lista) {
            System.out.println(c.getNome());
            System.out.println(c.getEmail());
            System.out.println(c.getTelefone());
            System.out.println("--------------------------");
        }
    }

    public static void listarLivros() {
        List<Livro> lista = serviceLivro.listarLivros();

        for(Livro l: lista) {
            System.out.println("Titulo: " + l.getNome());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("Preço: " + l.getPreco());
            System.out.println("Quantidade em estoque: " + l.getQuantidade());
            System.out.println("--------------------------");
        }
    }

    public static void fazerEmprestimo(Scanner entrada) {
        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = entrada.nextLine();


        System.out.println("Digite o nome do livro: ");
        String nomeLivro = entrada.nextLine();

        Emprestimo emprestimo = new Emprestimo(nomeCliente, nomeLivro);

        ResultadoEmprestimo resultado = emprestimoService.realizarEmprestimo(emprestimo);
        System.out.println(resultado.getMensagem());

        if(resultado.isSucesso()){
            System.out.println("Data do emprestimo: " + emprestimo.getDataRecebido());
            System.out.println("Data da devolucao: " + emprestimo.getDataRetorno());
        }

    }

    public static void realizarDevolucaoDeLivro(Scanner entrada) {
        System.out.println("Informe o nome do cliente: ");
        String nomeCliente = entrada.nextLine();

        System.out.println("Digite o nome do livro: ");
        String nomeLivro = entrada.nextLine();

        boolean devolvido = emprestimoService.devolverEmprestimo(nomeCliente, nomeLivro);

        if(devolvido) {
            System.out.println("Livro devolvido com sucesso");
        } else {
            System.out.println("Não existe emprestimo em aberto para esse cliente e/ou livro");
        }

    }

    public static void cadastrarLivroPorInfos(Scanner entrada) {
        System.out.println("Digite o nome do livro: ");
        String nomeLivro = entrada.nextLine();

        System.out.println("Digite o autor do livro: ");
        String nomeAutor = entrada.nextLine();

        System.out.println("Digite o preço do livro: ");
        int precoLivro = entrada.nextInt();

        System.out.println("Digite a quantidade de livro: ");
        int qtdLivro = entrada.nextInt();
        entrada.nextLine();


        Livro livro = new Livro(nomeLivro, nomeAutor, precoLivro, qtdLivro);

        serviceLivro.cadastrarLivroPorInfos(livro);
    }

    public static void removerLivros(Scanner entrada) {
        System.out.println("Digite o nome do livro que deseja remover do estoque: ");
        String nomeLivroRemover = entrada.nextLine();

        boolean removido = serviceLivro.removerLivro(nomeLivroRemover);

        if(removido) {
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado");
        }

    }

}
