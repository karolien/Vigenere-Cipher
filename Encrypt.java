//Karolien Koorts

import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

public class Encrypt extends SwingWorker<Void, String> {

	String key, message;
    JTextArea result;
    int keyLength, messageLength;
    boolean encrypt;

    public Encrypt(JTextArea result, String key, String message, boolean encrypt) {
        this.result = result;
        this.key = key.toUpperCase();
        this.message = message.toUpperCase();
        this.keyLength = key.length();
        this.messageLength = message.length();
        this.encrypt = encrypt;
    }

    @Override
    public Void doInBackground() throws Exception {
    	
    	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	char[] alphabetChar = alphabet.toCharArray();
    	//Set up alphabet HashMap
    	HashMap<Character, Integer> hm1 = new HashMap<Character, Integer>();
    	for (int i=0; i<26; i++){
    		hm1.put(alphabet.charAt(i), i);
    	}
    	//Set up Vigenère table HashMap
    	HashMap<Character, String> hm2 = new HashMap<Character, String>();
    	for (int k=0; k<26; k++){
    		String derangedAlpha = alphabet.substring(k);
    		derangedAlpha += alphabet.substring(0, k);
    		String value = derangedAlpha;
    		hm2.put(alphabet.charAt(k), value);
    	}
    	
    	Character currentChar;
    	int keyCount = 0;
    	
    	if (encrypt){
	    	//Encrypt message
	    	for (int x=0; x<messageLength; x++){
	    		currentChar = message.charAt(x);
	    		if (!Character.isLetter(currentChar)){
	    			result.append(currentChar.toString());
	    		}
	    		else{
	    			Integer letterValue = hm1.get(currentChar);
	    			String alphaUsed = hm2.get(key.charAt(keyCount));
	    			Character encryptedLetter = alphaUsed.charAt(letterValue);
	    			result.append(encryptedLetter.toString());
	    			if(keyCount==keyLength-1){
	    				keyCount = 0;
	    			}
	    			else{
	    				keyCount++;
	    			}
	    		}
	    	}
    	}
    	else{
    		//Decrypt message
    		for (int y=0; y<messageLength;y++){
    			currentChar = message.charAt(y);
    			if (!Character.isLetter(currentChar)){
	    			result.append(currentChar.toString());
	    		}
    			else{
    				String decodeAlpha = hm2.get(key.charAt(keyCount));
    				Integer letterPos = decodeAlpha.indexOf(currentChar);
    				Character finalLetter = alphabetChar[letterPos];
    				result.append(finalLetter.toString());
    				if(keyCount==keyLength-1){
	    				keyCount = 0;
	    			}
	    			else{
	    				keyCount++;
	    			}
    			}
    		}
    	}
		return null;

    }
}
