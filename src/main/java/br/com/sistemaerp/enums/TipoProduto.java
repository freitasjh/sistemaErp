package br.com.sistemaerp.enums;

public enum TipoProduto {
	PRODUTO("PROD"), 
	SERVICO("SERV");
	
	String codigo;

	private TipoProduto(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	
	public static TipoProduto valueOfCodigo(String codigo) {
		for(TipoProduto obj : TipoProduto.values()) {
			if(obj.getCodigo().equalsIgnoreCase(codigo)) {
				return obj;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return codigo;
	}
	
}
