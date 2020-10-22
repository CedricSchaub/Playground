package com.playground.net;

public class NetOperations {

	public static byte[] intToByteArray(int number) {
		
		// 32 bit -> 00000000 11111111 22222222 33333333
		// 00000000 11111111 22222222 33333333 & 0xFF = 00000000
		// 00000000 11111111 22222222 33333333 >> 8 & 0xFF = 11111111 
		// 00000000 11111111 22222222 33333333 >> 16 & 0xFF = 22222222
		// 00000000 11111111 22222222 33333333 >> 24 & 0xFF = 33333333
		
		byte b1 = (byte)( number & 0xFF);
		byte b2 = (byte)( (number >> 8)  & 0xFF);
		byte b3 = (byte)( (number >> 16) & 0xFF);
		byte b4 = (byte)( (number >> 24) & 0xFF);
		
		return new byte[] {
				b4,
				b3,
				b2,
				b1
		};
	}
	
	public static int byteArrayToInt(byte[] data) {
		
		return (int)( // NOTE: type cast not necessary for int
	            (0xff & data[0]) << 24  |
	            (0xff & data[1]) << 16  |
	            (0xff & data[2]) << 8   |
	            (0xff & data[3])
	    );
	}
	
}
