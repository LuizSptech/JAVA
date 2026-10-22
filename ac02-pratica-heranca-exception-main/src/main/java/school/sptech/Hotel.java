package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.HospedeNaoEncontradoException;
import school.sptech.exceptions.QuartoIndisponivelException;
import school.sptech.hospede.Hospede;
import school.sptech.hospede.HospedeVip;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private List<Hospede> hospedes;


    public Hotel(String nome) throws DadoInvalidoException {
        if ( nome == null || nome.isBlank() ){
            throw new DadoInvalidoException();
        }
        this.nome = nome;
        this.hospedes = new ArrayList<>();
    }
    public Hospede buscarPorCpf(String cpf) throws HospedeNaoEncontradoException {
        for (Hospede hos : hospedes){
            if (hos.getCpf().equalsIgnoreCase(cpf)){
                return hos;
            }
        }
        throw new HospedeNaoEncontradoException();
    }

    public Double calcularFaturamentoTotal(){
        Double faturamento = 0.0;
        for(Hospede hos : hospedes){
        if (hos instanceof HospedeVip vip){
           Double so = vip.calcularTotalGasto();
           Double desconto =  so * (vip.getPercentualDesconto()/ 100.0);
           so -= desconto;
            faturamento += so;
        }
        if (hos instanceof Hospede normal){
            faturamento += normal.calcularTotalGasto();
        }
        }
        return faturamento;
    }


    public Hospede buscarHospedeQueMaisGastou(){
        if (hospedes.isEmpty()){
            return null;
        }
        Hospede gasto = hospedes.getFirst();
        for (Hospede hos : hospedes){
            if (hos.calcularTotalGasto() > gasto.calcularTotalGasto()){
                gasto = hos;
            }
        }
        return gasto;
    }


    public List<HospedeVip> buscarHospedesVip(){
        List<HospedeVip> lista = new ArrayList<>();
        for (Hospede hops : hospedes){
            if (hops instanceof HospedeVip vip){
                lista.add(vip);
            }
        }
        return lista;
    }


    public void cadastrarHospede(Hospede hospede) {
                for (Hospede hops: hospedes){
                   if (hospede.getCpf() == hops.getCpf()){
                       return;
                   }
                }
                hospedes.add(hospede);
    }


    public List<Hospede> buscarPorTipo(String tipo){
        List<Hospede> lista = new ArrayList<>();
        if (!tipo.equalsIgnoreCase("vip") && !tipo.equalsIgnoreCase("classico")){
            return lista;
        }
        if (tipo.equalsIgnoreCase("vip")){
            for (Hospede hops : hospedes){
                if (hops instanceof HospedeVip vip){
                    lista.add(vip);
                }
            }
            return lista;
        }
        if (tipo.equalsIgnoreCase("classico")){
            for (Hospede hops : hospedes){
                if (hops instanceof Hospede normal){
                    lista.add(normal);
                }
            }
            return lista;
        }
        return lista;
    }

    public void verificarDisponibilidade(Integer numeroQuarto) throws DadoInvalidoException, QuartoIndisponivelException {
        if (numeroQuarto == null || numeroQuarto <= 0){
            throw new DadoInvalidoException();
        }
        for (Hospede hosp: hospedes){
            hosp.getReservas();
// Parei aqui por conta do tempo

        }
    }


    public String getNome() {
        return nome;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }
}
