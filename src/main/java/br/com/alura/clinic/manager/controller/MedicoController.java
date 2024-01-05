package br.com.alura.clinic.manager.controller;

import br.com.alura.clinic.manager.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired // Injeção de dependência
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional // Transação ativa com banco de dados
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
		medicoRepository.save(new Medico(dadosCadastroMedico));
	}

	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable pageable) {
		// Para sobrescrever o padrão da paginação, pode-se decorar o tipo do parâmetro com
		// @PageableDefault, definindo as propriedades desejadas (page, size, sort...)
		return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
		medico.atualizar(dadosAtualizacaoMedico);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();
	}

}
