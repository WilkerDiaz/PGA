package com.abstracta.webstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abstracta.bean.SessionHelper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
                
        		SessionHelper sh = new SessionHelper();
        	
                com.abstracta.bean.Customer domainUser = sh.findUserByUserName(login);
                
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                
                if(domainUser==null)
                	return null;
                
                return new User(
                                domainUser.getUsername(), 
                                domainUser.getPassword(), 
                                enabled, 
                                accountNonExpired, 
                                credentialsNonExpired, 
                                accountNonLocked,
                                getAuthorities(1)
                );
        }
        
        public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
                List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
                return authList;
        }
        
        public List<String> getRoles(Integer role) {

                List<String> roles = new ArrayList<String>();

                if (role.intValue() == 1) {
                        roles.add("user");
                        roles.add("user");
                } else if (role.intValue() == 2) {
                        roles.add("user");
                }
                return roles;
        }
        
        public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                
                for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                }
                return authorities;
        }

}