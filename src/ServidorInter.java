import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInter extends Remote {
	public void conectar(ClienteInter jogador) throws RemoteException;
	
	public int getConexoes() throws RemoteException;
	
	public void setTamanho(int tamanho) throws RemoteException;
	
	//public String fimDeJogo(ClienteInter clienteImpl);
	
	public void atacar(ClienteInter jogador, int l, int c) throws RemoteException;
}
