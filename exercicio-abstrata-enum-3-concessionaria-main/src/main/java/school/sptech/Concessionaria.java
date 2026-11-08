package school.sptech;

import school.sptech.exceptions.DocumentoInvalidoException;
import school.sptech.exceptions.RequisicaoDeFinanciamentoInvalidaException;
import school.sptech.exceptions.ValorInexistenteException;
import school.sptech.financiamento.Financiamento;
import school.sptech.financiamento.FinanciamentoPF;
import school.sptech.financiamento.FinanciamentoPJ;

import java.util.ArrayList;
import java.util.List;

public class Concessionaria {
private String nome;
private List<Financiamento> financiamentos;


    public Concessionaria(String nome, List<Financiamento> financiamentos) {
        this.nome = nome;
        this.financiamentos = new ArrayList<>();
    }

    public void financiarVeiculo(Pessoa pessoa, Veiculo veiculo, Integer parcelas) throws ValorInexistenteException, RequisicaoDeFinanciamentoInvalidaException, DocumentoInvalidoException{
        if (pessoa == null || veiculo == null || parcelas == null){
            throw new ValorInexistenteException();
        }
        if (parcelas <= 0 || parcelas > 600){
            throw new RequisicaoDeFinanciamentoInvalidaException();
        }
        if (pessoa.getCpf() == null || pessoa.getCpf().length() != 11) {
            throw new DocumentoInvalidoException();
        }
        FinanciamentoPF people = new FinanciamentoPF(veiculo, parcelas, 0, pessoa);
        financiamentos.add(people);

    }

    public void financiarVeiculo(Empresa empresa, Veiculo veiculo, Integer parcelas){
        if (empresa == null || veiculo == null || parcelas == null){
            throw new ValorInexistenteException();
        }
        if (parcelas <= 0 || parcelas > 600){
            throw new RequisicaoDeFinanciamentoInvalidaException();
        }
        if (empresa.getCnpj() == null || empresa.getCnpj().length() != 14) {
            throw new DocumentoInvalidoException();
        }
        FinanciamentoPJ people = new FinanciamentoPJ(veiculo, parcelas, 0, empresa);
        financiamentos.add(people);
    }


    public List<Financiamento> buscarPorCpf(String busca){
        List<Financiamento> lista = new ArrayList<>();
        for (Financiamento fi: financiamentos){
            if (fi instanceof FinanciamentoPF f){
                if (f.getPessoa().getCpf().equals(busca)){
                    lista.add(f);
                }
            }
        }
        return lista;
    }


    public List<Financiamento> buscarPorNomeOuRazaoSocial(String busca){
        List<Financiamento> lista = new ArrayList<>();
        String nome = busca.toLowerCase();
        for (Financiamento fi: financiamentos){
            if (fi instanceof FinanciamentoPF f){
                String name = f.getPessoa().getNome().toLowerCase();
                if (name.contains(nome)){
                    lista.add(f);
                }
            }
            if (fi instanceof FinanciamentoPJ j){
                String raz = j.getEmpresa().getRazaoSocial().toLowerCase();
                if (raz.contains(nome)){
                    lista.add(j);
                }
            }
        }
        return lista;
    }

    public void pagarParcela(Double valor, String placa){
        if (valor == null || placa == null){
            throw new ValorInexistenteException();
        }
        for (Financiamento fid : financiamentos){
          if (fid.getVeiculo().getPlaca().equalsIgnoreCase(placa)){
              fid.pagarParcela(valor);
              return;
          }
        }
        throw new ValorInexistenteException();
    }

    public Double totalValorPago(){
        Double valorTotal = 0.0;
        for (Financiamento fid: financiamentos){
            valorTotal += fid.calcularValorPago();
        }
        return valorTotal;
    }

    public Double totalValorAPagar(){
        Double valorTotal = 0.0;
        for (Financiamento fid:  financiamentos){
            valorTotal += fid.calcularValorAPagar();
        }
        return valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public List<Financiamento> getFinanciamentos() {
        return financiamentos;
    }
}
