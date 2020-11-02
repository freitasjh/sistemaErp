package br.com.sistemaerp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.querydsl.core.annotations.QueryEntity;

import br.com.sistemaerp.enums.TipoProduto;

/**
 * 
 * @author JOAO HENRIQUE FREITAS
 *
 */
@Entity(name = "produto")
@QueryEntity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "codigo_interno")
	private String codigoInterno;
	@Column(name = "descricao")
	@NotEmpty(message = "Informe a descrição do produto")
	private String descricao;
	@Column(name = "valor_atacado")
	private BigDecimal valorAtacado;
	@Column(name = "valor_varejo")
	private BigDecimal valorVarejo;
	@Column(name = "ativado")
	private boolean ativado;
	@Column(name = "tipo_produto")
	@Enumerated(EnumType.ORDINAL)
	private TipoProduto tipoProduto;
	@Column(name = "codigo_ean")
	private String codigoEan;
	@Column(name = "codigo_fabricante")
	private String codigoFabricante;

	public Produto() {
		super();
		this.ativado = false;
		this.tipoProduto = TipoProduto.PRODUTO;
	}

	public Produto(UUID id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getValorAtacado() {
		return valorAtacado;
	}

	public void setValorAtacado(BigDecimal valorAtacado) {
		this.valorAtacado = valorAtacado;
	}

	public BigDecimal getValorVarejo() {
		return valorVarejo;
	}

	public void setValorVarejo(BigDecimal valorVarejo) {
		this.valorVarejo = valorVarejo;
	}

	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String codigo) {
		this.tipoProduto = TipoProduto.valueOfCodigo(codigo);
	}
	
	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoEan() {
		return codigoEan;
	}

	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}

	public String getCodigoFabricante() {
		return codigoFabricante;
	}

	public void setCodigoFabricante(String codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
