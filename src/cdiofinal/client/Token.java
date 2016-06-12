package cdiofinal.client;

public class Token {
	static String token;
	
	public Token(String token){
		Token.token = token;
	}
	
	public static String getToken(){
		return token;
	}
	
	public static void setToken(String token){
		Token.token = token;
	}
}
