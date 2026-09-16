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



    public void definirData(Integer dia, Integer mes, Integer ano){

        if (dia == null || dia > 31 || dia < 1){
            return;
        }
        if (ano == null || ano < 0){
            return;
        }
        if ( mes == null ||mes < 1 || mes > 12 ){
            return;
        }
        if (mes == 6 && dia > 30){
            return;
        }

        if (mes == 4 && dia > 30){
            return;
        }
        if ( dia > 28 && (mes == 2 && ano % 2 != 0)){
            return;
        }

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String formatarData(){
        String mensagem;
        int a;
        a = String.valueOf(ano).length();
        if ((dia < 10 || mes < 10) && a == 1){
            mensagem = "0" + dia + "/" + "0"+ mes + "/" + "0" + "0" + "0" +ano;
            return mensagem;
        }
        if (dia < 10 || mes < 10){
            mensagem = "0" + dia + "/" + "0"+ mes + "/" + ano;
            return mensagem;
        }

        if (a == 2){
            mensagem = dia + "/" + mes + "/" + "0" + "0" +ano;
            return mensagem;
        }
        if (a == 3){
            mensagem = dia + "/" + mes + "/" + "0" + ano;
            return mensagem;
        }

        mensagem = dia + "/" + mes + "/" + ano;
        return mensagem;
    }



    public Integer compararDatas(Integer dia, Integer mes, Integer ano){
        if (dia == null || mes == null|| ano== null){
            return null;
        }

      /*  Integer diaA = getDia();
        Integer mesA = getMes();
        Integer anoA = getAno();
        Integer[] obj = {diaA, mesA, anoA};
        Integer[] param = {dia,mes,ano};
        if ((obj[2] > param[2]) || obj[1] > param[1]){
            return -1;
        }
        if ((obj[2] < param[2]) || obj[1] < param[1]){
            return 1;
        }*/
        /*
        for (int i = 0; i < data1.length; i++) {
            objeto += data1[i];
            informada += data2[i];
        }

        if (informada > objeto){
            return 1;
        }
        if (informada < objeto){
            return -1;
        }*/
       /* String objetc = dia.toString() + mes.toString() + ano.toString() + "";
        String info = getAno().toString() + getMes().toString() + getAno().toString() + "";
        Integer objeto = Integer.parseInt(objetc);
        Integer informado = Integer.parseInt(info);
        if (informado > objeto){
            return 1;
        }
        if (informado < objeto){
            return -1;
        }*/
        Integer objeto = 0;
        Integer informada = 0;
        Integer retorno = 0;
        Integer diaA = getDia();
        Integer mesA = getMes();
        Integer anoA = getAno();
        Integer[] obj = {diaA, mesA, anoA};
        Integer[] param = {dia,mes,ano};
        for (int i = 0; i < obj.length; i++) {
        if (obj[i] < param[i]){
            retorno = 1;
        }
        if (obj[i] > param[i]){
            retorno = -1;
        }
            objeto += obj[i];
            informada += param[i];

        }
            if (objeto == informada){
                return 0;
            }


        return retorno;
    }
}
