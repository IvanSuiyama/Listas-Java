package com.autobots.automanager.modelos;

import com.autobots.automanager.entitades.*;

import lombok.Data;

@Data
public class EmpresaDto {

	private String razaoSocial;

	private String nomeFantasia;

	private Endereco endereco;
	
}