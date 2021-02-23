package br.com.zup.beta.microServico.model;

public enum Status {

    NAO_LEGIVEL, LEGIVEL;

    public static Status resultado(String resultadoSolicitacao) {
        if(resultadoSolicitacao.equals("COM_RESTRICAO")){
            return NAO_LEGIVEL;
        } else {
            return LEGIVEL;
        }
    }
}
