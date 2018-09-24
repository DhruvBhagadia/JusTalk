import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
public class Client  {

    static Boolean flag = true;
    static Boolean firstTime = true;
    public static void stopRunning(){
    	flag = false;
    }        	
    public static void main(String args[]) throws UnknownHostException, IOException  { 
     
        Scanner sc = new Scanner(System.in);  
        Socket s = new Socket("127.0.0.1", 5056); 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Thread sendMessage = new Thread(new Runnable(){ 
            @Override
            public void run() { 
                while (flag) { 
   
                   String msg = sc.nextLine();                      
                    try { 
                    	if(!firstTime && !msg.contains("@"))
                    		System.out.println("Server: Something is wrong with format..." + 
                    			" Your message should contain \"@\"");
                    	else {
                    		dos.writeUTF(msg);
                        	if(msg.equals("quit@Server")){
                        		System.out.println("You are now logged out");
                        		stopRunning();
                        	}
                        	firstTime = false;	
                    	} 
                    } catch (IOException e) { 
                    	System.out.println("Something went wrong, I'm quiting");
                    	stopRunning();
                    	System.exit(0);
                    } 
                } 
            } 
        }); 
        Thread readMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
  
                while (flag) { 
                    try {  
                        String msg = dis.readUTF(); 
                        System.out.println(msg); 
                    } catch (IOException e) { 
                    	System.out.println("Something went wrong, I'm quiting");
                    	stopRunning();
                    	System.exit(0);
                    } 
                } 
            } 
        }); 
        sendMessage.start(); 
        readMessage.start(); 
  
    } 
} 
