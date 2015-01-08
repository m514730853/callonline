package com.netviom.test;


import com.nvm.common.response.vo.User;
import com.nvm.common.util.DbUtil;


public class Test {
   public static void main(String[] args)
   {
	   User user = new User();
	   user.setUsername("aaaa");
	   user.setPassword("213123");
	   System.out.println( DbUtil.creatTable(User.class));
	//   DbUtil.update(user, new String[]{"username","password"}, new String[]{"123","213123"});
   }
}
