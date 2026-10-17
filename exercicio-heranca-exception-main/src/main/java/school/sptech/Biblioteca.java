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

    public void emprestarItem(String titulo, Usuario usuario) throws DadoInvalidoException, ItemIndisponivelException {
        if (titulo == null || usuario == null){
            throw new DadoInvalidoException();
        }

        ItemBiblioteca item =  buscarPorTitulo(titulo);
        item.emprestar(usuario);
        Usuario pessoa = item.getEmprestadoPara();
        if (pessoa.getNome() != null){
            throw new ItemIndisponivelException( "Esse item já está emprestado para outro usuário.");
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