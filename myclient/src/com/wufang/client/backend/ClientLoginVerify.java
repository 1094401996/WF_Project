package com.wufang.client.backend;

import com.wufang.common.User;

/**用户端 后台验证
 * 
 */
public class ClientLoginVerify {

	
	public static void main(String[] args) {
		
	}
	public boolean verifyUser(User u){
		return new ClientBackend().sendLoginInfo(u);
	}

}
