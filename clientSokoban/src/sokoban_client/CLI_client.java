package sokoban_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CLI_client {
	//private BufferedReader in;
	//private PrintWriter out;
	//String s;


public CLI_client() {


}
	public CLI_client(BufferedReader in , PrintWriter out) {
		//this.in=in;
		//this.out=out;

	}
	//Any source Any destination Protocol dependent


	private void readInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		try {
		String line;

		while(!(line=in.readLine()).equals(exitStr)){
		//System.out.println(line);
		out.println(line);
		out.flush();
		}

		} catch (IOException e) { e.printStackTrace();}
		}

		private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		Thread t=new Thread(new Runnable() {

		public void run() {
			readInputsAndSend(in, out, exitStr); }

		});
		t.start();
		return t;


		}
		public void start(String ip, int port){
			try {
			Socket theServer=new Socket(ip, port);
			System.out.println("connected to server\n");
			//user input
			BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
			//server input
			BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
			PrintWriter outToScreen=new PrintWriter(System.out);
			Thread t1 = aSyncReadInputsAndSend(userInput,outToServer,"exitgame"); // different thread
			Thread t2 = aSyncReadInputsAndSend(serverInput,outToScreen,"bye"); // different thread
			t1.join(); t2.join(); // wait for threads to end
			//t2.join();t1.join();

			userInput.close();
			serverInput.close();
			outToServer.close();
			outToScreen.close();
			theServer.close();


			} catch (UnknownHostException e) {
				System.out.println(" the IP address of a host could not be determined\n ");
				e.printStackTrace();

				}
			catch (IOException e) {
				System.out.println("can not connect to the server");
				e.printStackTrace();
				}
			catch (InterruptedException e) {
				e.printStackTrace();
				}

			}



}



