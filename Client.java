import java.io.*; 
import java.net.*; 
import java.util.*; 
import java.security.*;
import javax.crypto.*;
import java.nio.charset.StandardCharsets;
  
public class Client  {

    static Boolean flag = true;
    static Boolean firstTime = true;
    static int filesize = 0;
    public static void stopRunning(){
    	flag = false;
    }
    static String name_of_img_file = null;

    public static void sendImageToServer (DataOutputStream dos, String file_name) {
        try {
            String name = file_name + ".jpg";
            FileInputStream fis = new FileInputStream(file_name + ".jpg");
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close(); 
        }
        catch(Exception e) {
            System.out.println("Something went wrong, I'm quiting");
            stopRunning();
            System.exit(0);
        }
    }    

    //Whenever a Client is created, public key and Private key of that client
    //are generated.
    public static KeyPair buildKeyPair() throws Exception{
        int size = 2048;
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair keypair = kpg.generateKeyPair();
        return keypair;
    }

    //Encrypted message received from the Server is decrypted using this method
    public static byte[] decrypt(byte[] encrypted, PrivateKey privateKey) throws Exception {
        System.out.println("decrypt");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encrypted);
    }

    //To save an image being sent by some Client through the Server
    public static void saveFile(DataInputStream dis) {
        try{
            Random r = new Random();
            name_of_img_file = "ImageOnClient" + Integer.toString(r.nextInt(100)) + ".jpg";
            System.out.println("file saved as: " + name_of_img_file);
            FileOutputStream fos = new FileOutputStream(name_of_img_file);
            byte[] buffer = new byte[4096];
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buffer, 0, read);
            }
            fos.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong, I'm quiting");
            System.exit(0);
        }
    }  	

    public static void main(String args[]) throws Exception  { 
     
        Scanner sc = new Scanner(System.in);  
        Socket s = new Socket("127.0.0.1", 5056); 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        //RSA algorithm
        KeyPair keypair = buildKeyPair();
        PublicKey publicKey = keypair.getPublic();
        byte[] encodedPublicKey = publicKey.getEncoded();
        String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
        PrivateKey privateKey = keypair.getPrivate();

        //Two separate threads one for sending message and other to receive message
        //so that communication continues and doesn't stop after sending or receiving a single message
        Thread sendMessage = new Thread(new Runnable(){ 
            @Override
            public void run() { 
                while (flag) { 
   
                    String msg = sc.nextLine();
                    if(firstTime)
                        msg = msg + "#"+ b64PublicKey; //Send Public Key to the server when the client is created at first                   
                    try { 
                    	if(!firstTime && !msg.contains("@"))
                    		System.out.println("Server: Something is wrong with format..." + 
                    			" Your message should contain \"@\"");
                        else {
                            if(msg.contains("image@")){ //The client wants to send image
                                System.out.println("Enter anything whose image you want to send");
                                String file_name = sc.nextLine();
                                String target = new String("#Enter the path here#/DownloadImage.sh"); //Script that downloads image
                                ProcessBuilder pb = new ProcessBuilder(target, file_name);
                                Process process = pb.start();
                                System.out.println("Downloading the image... Script running...");
                                try{
                                    process.waitFor();
                                }
                                catch(Exception e) {
                                     System.out.println(e);
                                }
                                System.out.println("Downloaded");
                                dos.writeUTF(msg);
                                File file = new File(file_name + ".jpg");
                                dos.writeUTF(Long.toString(file.length()));
                                sendImageToServer(dos, file_name);
                            }
                            else if(msg.equals("quit@Server")){
                                dos.writeUTF(msg);
                                System.out.println("You are now logged out");
                                stopRunning();
                            }
                            else
                                dos.writeUTF(msg);
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
                        if(firstTime) {
                            String msg = dis.readUTF();
                            System.out.println(msg);
                            if(msg.contains("sent you an image")) {
                                String filesize_str = dis.readUTF();
                                filesize = Integer.parseInt(filesize_str);
                                saveFile(dis);
                            }
                        }
                        else {
                            int len = dis.readInt();
                            if(len>0) {
                                byte[] message = new byte[len];
                                dis.readFully(message);
                                System.out.println(new String(message));
                                byte[] decrypted = decrypt(message, privateKey);
                                System.out.println(new String(decrypted));
                            }
                        }  
                    } catch (Exception e) {
                    	System.out.println(e);
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
