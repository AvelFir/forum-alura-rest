package br.com.azdev.forum.form;

import br.com.azdev.forum.modelo.Curso;
import br.com.azdev.forum.modelo.Topico;
import br.com.azdev.forum.repository.CursoRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoForm {

    @NotNull @NotBlank @Size(min = 5)
    private String titulo;
    @NotNull @NotBlank @Size(min = 10)
    private String mensagem;
    @NotNull @NotBlank
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public  Topico converter(CursoRepository cursoRepository){
        Curso curso = cursoRepository.findByNome(getNomeCurso());
        return  new Topico(getTitulo(),getMensagem(),curso);
    }
}
