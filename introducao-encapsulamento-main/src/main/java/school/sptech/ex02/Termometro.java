package school.sptech.ex02;

public class Termometro {

    private  Double temperaturaAtual;
    private Double temperaturaMaxRegistrada;
    private Double temperaturaMinRegistrada;




    public Termometro(Double temperaturaInicial){
       this.temperaturaAtual = temperaturaInicial;
       this.temperaturaMinRegistrada = temperaturaInicial;
       this.temperaturaMaxRegistrada = temperaturaInicial;
    }

    public void alterarTemperatura(Double novaTemperatura){
        if (novaTemperatura == null){
            return;
        }
        temperaturaAtual = novaTemperatura;

        if (temperaturaAtual > temperaturaMaxRegistrada){
            temperaturaMaxRegistrada = temperaturaAtual;
        }
        if (temperaturaAtual < temperaturaMinRegistrada){
            temperaturaMinRegistrada = temperaturaAtual;
        }
    }

    public Double converterParaKelvin(){
         temperaturaAtual += 273.15;
        return temperaturaAtual;
    }


    public Double converterParaFahrenheit(){
        temperaturaAtual *= 1.8;
        temperaturaAtual  += 32;
        return temperaturaAtual;
    }


    public Double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public Double getTemperaturaMaxRegistrada() {
        return temperaturaMaxRegistrada;
    }

    public Double getTemperaturaMinRegistrada() {
        return temperaturaMinRegistrada;
    }
}
