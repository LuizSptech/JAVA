package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

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
        Double menorSalario = 0.0;
        Double salarioDev = 0.0;
         Double salarioWeb = 0.0;
         Double salarioMob = 0.0;
        Desenvolvedor menordev = desenvolvedores.get(0);
         if (desenvolvedores.isEmpty()){
             return null;
         }
         for (Desenvolvedor desenvolvedore : desenvolvedores) {
             if (desenvolvedore instanceof Desenvolvedor dev){
                 salarioDev = dev.calcularSalario();
             } else if (desenvolvedore instanceof DesenvolvedorWeb web) {
                 salarioWeb = web.calcularSalario();
             } else if (desenvolvedore instanceof ) {
                 
             }

         }
         return null;
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
