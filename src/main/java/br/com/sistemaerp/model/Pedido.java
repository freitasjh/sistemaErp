package br.com.sistemaerp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.querydsl.core.annotations.QueryEntity;

import br.com.sistemaerp.enums.SituacaoVenda;
import br.com.sistemaerp.enums.TipoPedido;

/**
 * 
 * @author JOAO HENRIQUE FREITAS
 */
@Entity(name = "pedido")
@QueryEntity
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "tipo_venda")
	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = "Informe o tipo de venda")
	private TipoPedido tipoVenda;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	@Column(name = "sub_total")
	private BigDecimal subTotal;
	@Column(name = "desconto_percentual")
	private BigDecimal descontoPercentual;
	@Column(name = "desconto_valor")
	private BigDecimal descontoValor;
	@Column(name = "quantidade_total")
	private BigDecimal quantidadeTotal;
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	@Column(name = "data_finalizado")
	private Date dataFinalizado;
	@Column(name = "situacao_venda")
	@NotNull(message = "informe a situação de venda")
	private SituacaoVenda situacaoVenda;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> listaDeItem;
	
	public Pedido() {
		super();
		this.listaDeItem = new ArrayList<>();
		this.situacaoVenda = SituacaoVenda.ABERTO;
		this.tipoVenda = TipoPedido.VAREJO;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoPedido getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = TipoPedido.valueOfCodigo(tipoVenda);
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataFinalizado() {
		return dataFinalizado;
	}

	public void setDataFinalizado(Date dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDescontoPercentual() {
		return descontoPercentual;
	}

	public void setDescontoPercentual(BigDecimal descontoPercentual) {
		this.descontoPercentual = descontoPercentual;
	}

	public BigDecimal getDescontoValor() {
		return descontoValor;
	}

	public void setDescontoValor(BigDecimal descontoValor) {
		this.descontoValor = descontoValor;
	}

	public BigDecimal getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(BigDecimal quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public SituacaoVenda getSituacaoVenda() {
		return situacaoVenda;
	}

	public void setSituacaoVenda(String situacaoVenda) {
		this.situacaoVenda = SituacaoVenda.valueOfCodigo(situacaoVenda);
	}

	public List<ItemPedido> getListaDeItem() {
		return listaDeItem;
	}

	public void setListaDeItem(List<ItemPedido> listaDeItem) {
		this.listaDeItem = listaDeItem;
	}
}
