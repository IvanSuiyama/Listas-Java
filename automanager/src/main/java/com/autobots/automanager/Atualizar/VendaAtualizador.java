package com.autobots.automanager.Atualizar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.VendaDto;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.repositorios.MercadoriaRepositorio;
import com.autobots.automanager.repositorios.ServicoRepositorio;
import com.autobots.automanager.repositorios.VeiculoRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;

@Service
public class VendaAtualizador {

	@Autowired
	private VendaRepositorio vendaRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private VeiculoRepositorio veiculoRepositorio;

	@Autowired
	private ServicoRepositorio servicoRepositorio;

	@Autowired
	private MercadoriaRepositorio mercadoriaRepositorio;

	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Venda venda, VendaDto atualizacao) {

		if (atualizacao == null || venda == null) {

			return;
			
		}

		// Update identification if present
		if (!verificador.verificar(atualizacao.getIdentificacao())) {

			venda.setIdentificacao(atualizacao.getIdentificacao());

		}

		// Update cliente
		Optional<Usuario> clienteOpt = usuarioRepositorio.findById(atualizacao.getCliente());

		clienteOpt.ifPresent(venda::setCliente);

		// Update funcionario
		Optional<Usuario> funcionarioOpt = usuarioRepositorio.findById(atualizacao.getFuncionario());

		funcionarioOpt.ifPresent(venda::setFuncionario);

		// Update veiculo
		Optional<Veiculo> veiculoOpt = veiculoRepositorio.findById(atualizacao.getVeiculo());

		veiculoOpt.ifPresent(veiculo -> {

			veiculo.getVendas().add(venda);

			venda.setVeiculo(veiculo);

		});

		// Update servicos
		List<Servico> servicos = new ArrayList<>();

		for (long servicoId : atualizacao.getServicos()) {

			Servico servico = servicoRepositorio.findById(servicoId).orElse(null);

			if (servico != null) {

				servicos.add(servico);

			}
		}

		venda.setServicos(servicos);

		// Update mercadorias
		List<Mercadoria> mercadorias = new ArrayList<>();

		for (long mercadoriaId : atualizacao.getMercadorias()) {

			Mercadoria mercadoria = mercadoriaRepositorio.findById(mercadoriaId).orElse(null);

			if (mercadoria != null) {

				mercadorias.add(mercadoria);

			}
		}
		venda.setMercadorias(mercadorias);

		// Save venda after updates
		vendaRepositorio.save(venda);
	}

	public void atualizar(Set<Venda> vendas, List<VendaDto> atualizacoes) {

		if (vendas == null || atualizacoes == null || vendas.isEmpty() || atualizacoes.isEmpty()) {

			return; // Early return for empty/null collections

		}

		for (VendaDto atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				for (Venda venda : vendas) {

					if (atualizacao.getId().equals(venda.getId())) { // Use equals for object comparison

						atualizar(venda, atualizacao);

						break; // Exit the loop once the venda is updated

					}
				}
			}
		}
	}
}
