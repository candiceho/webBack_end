package neu.edu.project.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.project.dao.UserDao;

public class UserRoleServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    neu.edu.project.domain.User user = userDao.findUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot find user");
		}
		GrantedAuthority grantedAuthority;
		if(user.getRole().equals("VENDER") ){
			grantedAuthority = new SimpleGrantedAuthority("VENDER");
		}else if(user.getRole().equals("BUYER")){
			grantedAuthority = new SimpleGrantedAuthority("BUYER");			
		}else{
			grantedAuthority = new SimpleGrantedAuthority("DELIVERY");	
		}
		List<GrantedAuthority> grantedAuthorities = Arrays.asList(grantedAuthority);		
		return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}
}
