package com.wufang.client.backend;

import com.wufang.common.Info;

/**
 * the verification module on the client part... You can understand it as the medium of Frontend and Backend...
 * this kind of design make the project more flexible.... 
 */
public class ClientLoginVerify {

	public boolean verifyUser(Info out){
		return new ClientBackend().sendInfo(out);
	}

}
