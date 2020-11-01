package br.com.sistemaerp.enums;

import br.com.sistemaerp.service.exceptions.ObjectNotFoundException;

public enum SituacaoVenda {
	ABERTO("A"), FINALIZADO("F");
	
	private String codigo;

	private SituacaoVenda(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public static SituacaoVenda valueOfCodigo(String codigo) {
		for(SituacaoVenda obj : SituacaoVenda.values()) {
			if(obj.getCodigo().equals(codigo)) {
				return obj;
			}
		}
		
		throw new ObjectNotFoundException("Situação não cadastrada...");
	}
}
