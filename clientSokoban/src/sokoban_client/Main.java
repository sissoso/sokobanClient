package sokoban_client;

public class Main {
	public static void main(String[] args) {

		String ip=args[0];
		int port = Integer.parseInt(args[1]);
		CLI_client client=new CLI_client();
		client.start(ip, port); 
	}


}