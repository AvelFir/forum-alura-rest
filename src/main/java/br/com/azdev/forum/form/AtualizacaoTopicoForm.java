package br.com.azdev.forum.form;

import br.com.azdev.forum.modelo.Curso;
import br.com.azdev.forum.modelo.Topico;
import br.com.azdev.forum.repository.CursoRepository;
import br.com.azdev.forum.repository.TopicoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtualizacaoTopicoForm {
    @NotNull
    @NotBlank
    @Size(min = 5)
    private String titulo;
    @NotNull @NotBlank @Size(min = 10)
    private String mensagem;

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

    public Topico atualizar(long id, TopicoRepository topicoRepository){
        Topico topico = topicoRepository.getOne(id);
        topico.setId(id);
        topico.setTitulo(getTitulo());
        topico.setMensagem(getMensagem());
        return topico;
    }
}
