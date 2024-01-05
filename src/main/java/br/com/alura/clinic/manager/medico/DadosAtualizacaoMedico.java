package br.com.alura.clinic.manager.medico;

import br.com.alura.clinic.manager.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco dadosEndereco) {
}
