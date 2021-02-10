package br.com.zup.beta.microServico.core.erros.tipos;

public class ColunaErro extends RuntimeException{

    private String coluna;

    public ColunaErro(String mensagem, String coluna){
        super(mensagem);
        this.coluna = coluna;
    }

    public String getColuna() {
        return coluna;
    }
}

