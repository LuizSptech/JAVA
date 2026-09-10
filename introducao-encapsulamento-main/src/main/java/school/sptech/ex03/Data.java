package school.sptech.ex03;

public class Data {

    private Integer dia;
    private Integer mes;
    private Integer ano;

    public Integer getDia() {
        return dia;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }



    public void definitData(Integer dia, Integer mes, Integer ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        if (ano == null){
            return;
        }
        if ( mes < 1 || mes > 12 ){
            return;
        }
        if (dia < 1 || dia > 31){
            return;
        }

        

    }
}
