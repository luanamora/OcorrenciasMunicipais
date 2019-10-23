package projeto.fag.com.ocorrenciasmunicipais.task;

import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class Result{
    private String Content; //Conteudo
    private Boolean Error; //Se deu erro ou não

    public Result(){

    }

    public Result(String content, Boolean error) {
        Content = content;
        Error = error;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Boolean getError() {
        return Error;
    }

    public void setError(Boolean error) {
        Error = error;
    }
}
