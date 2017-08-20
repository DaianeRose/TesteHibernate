package Interface;

import java.util.*;


import Negocio.*;
import Persistencia.*;
import java.sql.*;


public  class Main{
	
	private PedidoDao pedidoDAO;
	private ItemDAO itemDAO;
	public static final int ADICIONAR = 1;
	public static final int CONSULTAR = 2;
	public static final int REMOVER = 3;
	
/**
* Metodo Construtor que gera uma conexao com o Banco de Dados
*/
	public Main() throws Exception{
		 
     pedidoDAO = new PedidoDao();
     itemDAO = new ItemDAO();
		
	}
	
/**
* Metodo que monta o menu principal e 
* obtem uma opcao do usuario
*/
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Pedido");
		System.out.println("2. Consultar Pedido");
		System.out.println("3. Remover Pedido  ");
		System.out.println("4. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
/**
* Metodo que adiciona itens relacionado com o seu pedido
*/	
	public void AdicionarItems(Pedido p) throws Exception {
		String opcao;
		do
		{
			System.out.println("Adicionar Items:");
			System.out.println("-------------------");
			System.out.print("Descricao do Item:");
			String descricao = Console.readLine();
			System.out.print("Quantidade do Item:");
			int quantidade = Console.readInt();
			System.out.print("Valor Unitario:");
			double valorunitario = Console.readDouble();
		    itemDAO.salvar(new Item(descricao,quantidade,valorunitario, p));
			System.out.print("Deseja Adicionar mais um Item? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
	}
	

/**
* 
*/	
	public void AdicionarPedido()throws Exception{
		Pedido p;
		String opcao;
		do {
			System.out.println("Adicionar Pedido:");
			System.out.println("-------------------");
			System.out.print("ID do Pedido:");
			Integer id = new Integer(Console.readInt());
			System.out.print("Descricao do Pedido:");
			String descricao = Console.readLine();
			
						
			p = new Pedido(id, descricao);
			
			// Gravando pedido no  Banco de Dados
			pedidoDAO.salvar(p);
			
			this.AdicionarItems(p);

			
			System.out.print("Deseja Adicionar mais um Pedido? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);

	}
	
	
	
	
	public void RemoverPedido()throws Exception{
		int opcao;
		Pedido pedido;
		System.out.println("Remover Pedido:");
		System.out.println("-------------------");
		System.out.print("Digite o id do Pedido:");
		Integer id = new Integer(Console.readInt());
		
		pedido = pedidoDAO.ConsultarPeloId(id);
		pedidoDAO.Remover(pedido);
	}
	

	
	public void ConsultarPedido() throws Exception{
		Pedido pedido;
		System.out.println("Consultar Pedido:");
		System.out.println("-------------------");
		System.out.print("Digite o id do Pedido:");
		Integer id = new Integer(Console.readInt());
		
		pedido = pedidoDAO.ConsultarPeloId(id);
		
		System.out.println(pedido.getID());
		System.out.println(pedido.getDescricao());
	
	}
	
	public static void main(String args[]){
		
		try {
			Main cp = new Main();
			int opcao;
			
			while((opcao = cp.criaMenuPrincipal()) != 4){
				if(opcao == Main.ADICIONAR)
					cp.AdicionarPedido();
				else if(opcao == Main.CONSULTAR)
					cp.ConsultarPedido();
				else if(opcao == Main.REMOVER)
					cp.RemoverPedido();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}