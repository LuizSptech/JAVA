package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores = new ArrayList<>();

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
    }


    public void contratar(Desenvolvedor desenvolvedor){
        if (desenvolvedores.size() < vagas){
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor){
        if (desenvolvedores.size() < vagas){
        if (desenvolvedor instanceof DesenvolvedorWeb web){
            if (web.isFullstack()) {
                desenvolvedores.add(desenvolvedor);
            }
        }}
    }


    public Double getTotalSalarios(){
        Double salario =0.0;
        if (desenvolvedores.isEmpty()){
            return 0.0;
        }

        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (desenvolvedore instanceof Desenvolvedor ju){
                 salario += ju.calcularSalario();
            }

            else if (desenvolvedore instanceof DesenvolvedorWeb web){
                 salario += web.calcularSalario();


            } else if (desenvolvedore instanceof DesenvolvedorMobile mob){
                salario += mob.calcularSalario();}


        }

        return salario;
    }


    public Integer qtdDesenvolvedoresMobile(){
        Integer qtdMob = 0;
        if (desenvolvedores.isEmpty()){
            return 0;
        }
        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (desenvolvedore instanceof DesenvolvedorMobile mob){
                qtdMob++;
            }
        }
        return qtdMob;
    }


    public List<DesenvolvedorWeb> getDesenvolvedoresWeb(){
        List<DesenvolvedorWeb> dev = new ArrayList<>() ;
        if (desenvolvedores.isEmpty()) {
        return dev;
        }
        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (desenvolvedore instanceof DesenvolvedorWeb web){
                dev.add(web);
            }
        }
        return dev;

    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        List<Desenvolvedor> dev = new ArrayList<>();
        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (desenvolvedore instanceof Desenvolvedor jun){
                if (jun.calcularSalario() >=  salario){
                    dev.add(jun);
                }

            }
        }
        return dev;
    }

     public Desenvolvedor buscarMenorSalario(){
         if (desenvolvedores.isEmpty()) {
             return null;
         }

        Double salarioDev = Double.MAX_VALUE;


         Desenvolvedor menor = null;

         for (Desenvolvedor dev: desenvolvedores){
             Double salario = dev.calcularSalario();
             if (salario < salarioDev){
                 salarioDev = salario;
                 menor = dev;
             }

         }

         return menor;
     }


     public Double calcularMediaSalarial(){
        if (desenvolvedores.isEmpty()){
            return 0.0;
        }
        Double media = 0.0;

        for (Desenvolvedor dev: desenvolvedores){
            System.out.println(dev.calcularSalario());
            media += dev.calcularSalario();
        }
        media = media / desenvolvedores.size();
        return media;
     }




     public List<Desenvolvedor> buscarPorNome(String nome){
        List<Desenvolvedor> desenvolvedor = new ArrayList<>();
        String nomeRecebido = nome.toLowerCase();
        for (Desenvolvedor dev: desenvolvedores){
            String nomeComparado = dev.getNome().toLowerCase();
            if (nomeComparado.contains(nomeRecebido)){
                desenvolvedor.add(dev);
            }
        }
        return desenvolvedor;
    }


    public Double calcularMediaSalarialPorTipo(String tipo) {
        Double salario = 0.0;
        Double media = 0.0;

        List<Desenvolvedor> teste = new ArrayList<>();

        if (desenvolvedores.isEmpty()) {
            return media;
        }

        if (!tipo.equals("comum") &&
                !tipo.equals("web") &&
                !tipo.equals("mobile")) {
            return null;
        }

        for (Desenvolvedor desen : desenvolvedores) {

            if (tipo.equals("comum") && desen.getClass() == Desenvolvedor.class) {
                teste.add(desen);
                salario += desen.calcularSalario();

            } else if (tipo.equals("web") && desen instanceof DesenvolvedorWeb web) {
                teste.add(web);
                salario += web.calcularSalario();

            } else if (tipo.equals("mobile") && desen instanceof DesenvolvedorMobile mob) {
                teste.add(mob);
                salario += mob.calcularSalario();
            }
        }

        if (teste.isEmpty()) {
            return media;
        }

        media = salario / teste.size();

        return media;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }


}
