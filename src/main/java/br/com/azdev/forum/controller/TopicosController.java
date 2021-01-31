package br.com.azdev.forum.controller;

import java.net.URI;
import java.util.List;

import br.com.azdev.forum.dto.TopicoDetalhadoDto;
import br.com.azdev.forum.form.AtualizacaoTopicoForm;
import br.com.azdev.forum.form.TopicoForm;
import br.com.azdev.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.azdev.forum.dto.TopicoDto;
import br.com.azdev.forum.modelo.Topico;
import br.com.azdev.forum.repository.TopicoRepository;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}

	@PostMapping
	public ResponseEntity<TopicoDto> cadastra(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri =   uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return  ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	@GetMapping("/{id}")
	public TopicoDetalhadoDto detalhar(@PathVariable Long id){
		TopicoDetalhadoDto topico = new TopicoDetalhadoDto(topicoRepository.getOne(id));
		return topico;
	}
	@PutMapping("/{id}")
	@Transactional //Dispara o commit no banco de dados
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Topico topico = form.atualizar(id, topicoRepository);

		return ResponseEntity.ok(new TopicoDto(topico));
	}

}
