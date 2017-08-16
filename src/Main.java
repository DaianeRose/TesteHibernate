

public class Main {

	public static void main(String[] args) {
		
    Pedido pedido = new Pedido(1,"testando descrição");
    PedidoDao pedidoDao = new PedidoDao();
    pedidoDao.salvar(pedido);
 
		
		
	}

}
