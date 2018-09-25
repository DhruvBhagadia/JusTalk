import java.net.*;
import java.util.*;
import java.io.*;

class Server {

	static Vector<ClientHandler> arrlist = new Vector<ClientHandler>();

	public static void main (String args[]) {

		ServerSocket server_socket = null;
		try{

			server_socket = new ServerSocket(5056);

		}
		catch (Exception e) {
			System.out.println("Something went wrong, I'm quiting");	
			System.exit(0);
		}
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		int i=0;
		while(true) {

			try {

				socket = server_socket.accept();
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				System.out.println("New Client detected! Name assigned: client" + i);
				ClientHandler client = new ClientHandler(socket, "client" + i, dis, dos);
				i++;
				Thread t = new Thread(client);
				arrlist.add(client);
				System.out.println("Client added");
				t.start();

			} 
			catch (Exception e) {

				System.out.println("Something went wrong, I'm quiting");
				System.exit(0);

			}
			

		}

	}

}

class ClientHandler implements Runnable{

	Socket socket = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	String name;
	Boolean isLoggedIn;
	Server server;
	String name_of_img_file; 
	ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {

		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.name = name;
		this.isLoggedIn = true;

	}
	public void changeName (String modified_name) {

		for (ClientHandler client : server.arrlist) {
			if(client.name.equals(this.name)) {
				System.out.println("Changing name of " + this.name + " to " + modified_name + "...");
				client.name = modified_name;
				this.name = modified_name;
				break;
			}
		}

	}
	public String showLoggedInUsers () {
		ArrayList<String> list = new ArrayList<String>();
		for(ClientHandler client : server.arrlist) {
			if(client.isLoggedIn)
				list.add(client.name);
		}
		String[] names = list.toArray(new String[list.size()]);
		return (Arrays.toString(names));
	}
	public void sendHelloImageToRecipient(String recipient, DataOutputStream dos) {
		try {
            FileInputStream fis = new FileInputStream(name_of_img_file);
            byte[] buffer = new byte[4096];
            Boolean foundRecipient = false;
        	
            for(ClientHandler client : server.arrlist) {
					
				if(client.name.equals(recipient) && client.isLoggedIn){
					client.dos.writeUTF(this.name + " sent you Hello image");
					while (fis.read(buffer) > 0) {
                		client.dos.write(buffer);
            		}
            		foundRecipient = true;
					break;

				}

			}
			if(!foundRecipient){
				dos.writeUTF("Recipient does not exist");
			}

            fis.close(); 
        }
        catch(Exception e) {
            System.out.println("Something went wrong, I'm quiting");
            System.exit(0);
        }
	}
	public void saveFile(DataInputStream dis) {

		try{

			Random r = new Random();
			name_of_img_file = "ImageOnServer" + Integer.toString(r.nextInt(100)) + ".png";
			System.out.println("file saved on server as: " + name_of_img_file);
			FileOutputStream fos = new FileOutputStream(name_of_img_file);
			byte[] buffer = new byte[4096];
			
			int filesize = 11718;
			int read = 0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
				totalRead += read;
				remaining -= read;
				fos.write(buffer, 0, read);
			}
			fos.close();
			return;
		}
		catch(Exception e) {
			System.out.println("Something went wrong, I'm quiting");
			System.exit(0);
		}

	}
	@Override
	public void run () {

		try{
			dos.writeUTF("Server: What name would you like yourself to be called with?");
			String modified_name = dis.readUTF();
			changeName(modified_name);
			dos.writeUTF("Server: Some basic instructions");
			dos.writeUTF("1. show@Server: Show all loggedIn users");
			dos.writeUTF("2. quit@Server: Quit to log out");
			dos.writeUTF("3. image@recipient: To send an image saying hello to recipient");
			dos.writeUTF("4. Type anything in the format message@recipient" + 
				"to send the message to a particular recipient");
			dos.writeUTF("--------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println("Something went wrong, I'm quiting");
			System.exit(0);
		}
		while(true) {

			try {
				String input = dis.readUTF();
				if(input.equals("show@Server")) {
					String ppl = showLoggedInUsers();
					dos.writeUTF("loggedIn users: " + ppl);
				}
				else if(input.equals("quit@Server")){

					System.out.println(this.name + ": wants to log out");
					for(ClientHandler client : server.arrlist) {
						if(client.name.equals(this.name))
							client.isLoggedIn = false;
					}
					socket.close();
					dis.close();
					dos.close();
					break;

				}
				else if(input.contains("image@")) {
					String[] str_arr = input.split("@");
					String message = str_arr[0];		
					String recipient = str_arr[1];
					saveFile(this.dis);
					sendHelloImageToRecipient(recipient, this.dos);
				}
				else {
					String[] str_arr = input.split("@");
					String message = str_arr[0];		
					String recipient = str_arr[1];
					int flag = 0;
					for(ClientHandler client : server.arrlist) {
					
						if(client.name.equals(recipient) && client.isLoggedIn){
							System.out.println(this.name + "  --->  " + recipient);
							client.dos.writeUTF(this.name + " sent you \"" + message + "\"");
							flag = 1;
							break;

						}

					}
				if(flag == 0) {
					dos.writeUTF("Couldn't send message :(");
				}
				flag = 0;
				}
				

			}
			catch(Exception e) {

				System.out.println("Something went wrong, I'm quiting");
				System.exit(0);

			}

		}	

	}

}