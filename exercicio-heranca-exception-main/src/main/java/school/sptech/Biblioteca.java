package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.ItemIndisponivelException;
import school.sptech.exceptions.ItemNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nome;
    private List<ItemBiblioteca> itens = new ArrayList<>();

    public Biblioteca(String nome) throws DadoInvalidoException {
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException();
        }
        this.nome = nome;
    }

    public void adicionarItem(ItemBiblioteca item) throws DadoInvalidoException{
        if (item == null){
            throw new DadoInvalidoException();
        }

        itens.add(item);

    }

    public ItemBiblioteca buscarPorTitulo(String titulo) throws ItemNaoEncontradoException {
        String novoTitulo = titulo.toLowerCase();
        String procura = "";
        for (ItemBiblioteca item : itens){
            procura = item.getTitulo().toLowerCase();

            if (procura.equals(novoTitulo)) {
                return item;

            }}
        throw new ItemNaoEncontradoException();

    }

    public void emprestarItem(String titulo, Usuario usuario) throws DadoInvalidoException {
        if (titulo == null || usuario == null){
            throw new DadoInvalidoException();
        }

        ItemBiblioteca item =  buscarPorTitulo(titulo);
        if(item.getEmprestadoPara() == null){
            item.setEmprestadoPara(usuario);
            return;
        }
        System.out.println("Esse item já está emprestado para outro usuário."
        );

    }

    public void devolverItem(String titulo) throws DadoInvalidoException, ItemNaoEncontradoException{
        if (titulo == null){
            throw new DadoInvalidoException();
        }
        for (ItemBiblioteca item: itens) {
            if (titulo.equals(item.getTitulo())) {
                item.devolver();
                return;

            }
        }
        throw new ItemNaoEncontradoException();
    }


    public Boolean possuiItemDisponivel(String titulo) throws DadoInvalidoException, ItemNaoEncontradoException {
        if (titulo == null || titulo.isBlank()){
            throw new DadoInvalidoException();
        }
        if (itens.isEmpty()){
            throw new ItemNaoEncontradoException();
        }
        for (ItemBiblioteca item: itens){
          if (item.getTitulo().equalsIgnoreCase(titulo) && item.getEmprestadoPara() != null){
              return false;
          }
           if (item.getTitulo().equalsIgnoreCase(titulo) && item.getEmprestadoPara() == null){
               return true;
           }
        }
        throw new ItemNaoEncontradoException();
    }

    public List<ItemBiblioteca> listarDisponiveis(){
        List<ItemBiblioteca> lista = new ArrayList<>();
        if (itens.isEmpty()){
            return lista;
        }
        for (ItemBiblioteca item : itens){
            if (item.getEmprestadoPara() == null){
                lista.add(item);
            }
        }

        return lista;
    }



    public Integer contarPorTipo(String tipo) throws DadoInvalidoException{
        if (tipo == null || tipo.isBlank() || !tipo.equalsIgnoreCase("DVD") && !tipo.equalsIgnoreCase("Livro")){
             throw new DadoInvalidoException();
        }
        Integer contador = 0;
        for (ItemBiblioteca item: itens) {
            if (item instanceof Dvd dvd && tipo.equalsIgnoreCase("Dvd")) {
                contador++;
            }
            if (item instanceof Livro book && tipo.equalsIgnoreCase("Livro")){
                    contador++;
               }
            }
        if (itens.isEmpty()){
            return 0;
        }
        return contador;
    }


    public Integer calcularPrazoMedioEmprestimo(){
        if (itens.isEmpty()){
            return 0;
        }
        Integer soma = 0;
        for (ItemBiblioteca itens: itens){
            soma += itens.getPrazoEmprestimoDias();
        }
        return soma = soma / itens.size();
    }


    public Integer calcularPrazoMedioPorTipo(String tipo) throws DadoInvalidoException{
        if (tipo == null || tipo.isBlank() || !tipo.equalsIgnoreCase("DVD") && !tipo.equalsIgnoreCase("Livro")){
            throw new DadoInvalidoException();
        }
        Integer soma = 0;
        List<ItemBiblioteca> it = new ArrayList<>();
            for (ItemBiblioteca item: itens){
                if (item instanceof Dvd dvd && tipo.equalsIgnoreCase("Dvd")) {
                    soma += dvd.getPrazoEmprestimoDias();
                    it.add(dvd);
                }
                if (item instanceof Livro book && tipo.equalsIgnoreCase("Livro")) {
                    soma += book.getPrazoEmprestimoDias();
                    it.add(book);
                }
            }
            if(itens.isEmpty()){
                return 0;
            }
            if (it.size() > 0){
                Integer valorFinal = soma / it.size();
                return valorFinal;
            }
            return 0;
    }

    public List<ItemBiblioteca> listarEmprestadosPara(Usuario usuario)throws DadoInvalidoException{
        if (usuario == null){
            throw new DadoInvalidoException();
        }
        List<ItemBiblioteca> lista = new ArrayList<>();
        for (ItemBiblioteca item: itens){
            Usuario emprestadoPara = item.getEmprestadoPara();

            if (emprestadoPara != null && usuario.getNome().equals(emprestadoPara.getNome())){
                lista.add(item);
            }
        }
        return lista;
    }


    public void devolverTodosDoUsuario(Usuario usuario) throws DadoInvalidoException{
        if (usuario == null){
            throw new DadoInvalidoException();
        }
        for (ItemBiblioteca item : itens){
            Usuario emprestadoPara = item.getEmprestadoPara();
            if ( emprestadoPara != null && usuario.getNome().equals(emprestadoPara.getNome())){
                item.devolver();
            }
        }
    }

    public void emprestarVarios(List<String> titulos, Usuario usuario) {
        if (titulos == null || titulos.isEmpty() || usuario == null) {
            throw new DadoInvalidoException();
        }

        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i) == null || titulos.get(i).isBlank()) {
                throw new DadoInvalidoException();
            }

            for (int j = i + 1; j < titulos.size(); j++) {
                if (titulos.get(i).equalsIgnoreCase(titulos.get(j))) {
                    throw new DadoInvalidoException();
                }
            }
        }

        List<ItemBiblioteca> itensEmprestar = new ArrayList<>();

        for (String titulo : titulos) {
            ItemBiblioteca encontrado = null;

            for (ItemBiblioteca item : itens) {
                if (titulo.equalsIgnoreCase(item.getTitulo())) {
                    encontrado = item;
                    break;
                }
            }

            if (encontrado == null) {
                throw new ItemNaoEncontradoException();
            }

            itensEmprestar.add(encontrado);
        }

        for (ItemBiblioteca item : itensEmprestar) {
            if (item.getEmprestadoPara() != null) {
                System.out.println("Não foi possível emprestar o lote: o item '%s' está indisponível.".formatted(item.getTitulo()));
                return;
            }
        }
        for (ItemBiblioteca item : itensEmprestar) {
            try {
                item.emprestar(usuario);
            }catch (ItemIndisponivelException e){
                e.getStackTrace();
            }
        }
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }

    public void setItens(List<ItemBiblioteca> itens) {
        this.itens = itens;
    }
}