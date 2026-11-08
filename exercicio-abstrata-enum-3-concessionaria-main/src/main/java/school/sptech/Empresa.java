package school.sptech;

public class Empresa {
private String razaoSocial;
private String cnpj;
private TipoEmpresa tipo;

    public Empresa(String razaoSocial, String cnpj, TipoEmpresa tipo) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.tipo = tipo;
    }


    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }
}
