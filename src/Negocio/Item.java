package Negocio;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
	@JoinColumn(name = "Pedido_idPedido")
	private Pedido pedido;
	
	@Column
	private String descricao;
	@Column
	private int quantidade;
	@Column
	private double valorUnitario;
	
	public Item(String newDescricao, int newQuantidade, double newValor, Pedido pedido){
		this.descricao = newDescricao;
		this.quantidade = newQuantidade;
		this.valorUnitario = newValor;
		this.pedido = pedido;
	}
	
	public Item(String newDescricao, int newQuantidade, double newValor){
		this.descricao = newDescricao;
		this.quantidade = newQuantidade;
		this.valorUnitario = newValor;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public double getValorTotalItem(){
		return this.quantidade * this.valorUnitario;
	}
	
	public String toString(){
		return "Descricao: " + this.descricao + " - " + "Qtd:" +
		this.quantidade + " - " + "Valor: " + this.valorUnitario + "\n";
	}
	
	public int getQuantidade(){
		return this.quantidade;
	}

	public double getValorUnitario(){
		return this.valorUnitario;
	}	
}