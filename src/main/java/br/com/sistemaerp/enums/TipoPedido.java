package br.com.sistemaerp.enums;

import br.com.sistemaerp.service.exceptions.ObjectNotFoundException;

public enum TipoPedido {
	ATACADO("A"),
	VAREJO("V"),
	SERVICO("S"),
	ORCAMENTO("O");
	
	String codigo;

	private TipoPedido(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public static TipoPedido valueOfCodigo(String codigo) {
		for(TipoPedido obj : TipoPedido.values()) {
			if(obj.getCodigo().equals(codigo)) {
				return obj;
			}
		}
		
		throw new ObjectNotFoundException("Tipo de Venda errado..");
	}
}
