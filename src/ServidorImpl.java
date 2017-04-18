import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ServidorImpl extends UnicastRemoteObject implements ServidorInter {
	private static final long serialVersionUID = 1L;
	private static Scanner teclado = new Scanner(System.in);
	private static ClienteInter[] jogadores = new ClienteInter[2];
	private static int conexoes = 0, tamanho;
	
	protected ServidorImpl() throws RemoteException {
		super();
		System.out.println("Aguardando jogadores... ");
	}
	
	@Override
	public void conectar(ClienteInter jogador) throws RemoteException {
		jogadores[conexoes++] = jogador;
		if(conexoes==2) {
			iniciar();
		}
	}
	
	public void iniciar() throws RemoteException {
		for(ClienteInter j : jogadores)
			j.gerarTabuleiro(tamanho);
		batalha();
	}

	public void batalha() throws RemoteException {
		ClienteInter vencedor = null;
		do {
			for(ClienteInter j : jogadores) {
				j.atacar();
				vencedor = j.vencedor();
			}
		} while(vencedor==null);
		System.out.println(vencedor.getNome()+ " venceu!");
	}

	@Override
	public void atacar(ClienteInter jogador, int l, int c) throws RemoteException {
		for(ClienteInter j : jogadores)
			if(j.equals(jogador))
				j.ataque(l-1, c-1);
	}
	
	public String fimDeJogo(ClienteInter jogador) {
		return "Fim de Jogo!";
	}

	@Override
	public void setTamanho(int tamanho) throws RemoteException {
		ServidorImpl.tamanho = tamanho;
	}
	
	public int getConexoes() {
		return conexoes;
	}
}
