package org.alf.filuname.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("hsqlDataSource")
	private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/", "/logout").permitAll()
					.antMatchers("/servlet/**", "/report/**").hasAnyAuthority("org.alf.filuname.report")
					.antMatchers("/shutdown").hasAnyAuthority("org.alf.filuname.admin")
				.and().formLogin()
					.permitAll()
				.and().logout()
                	.logoutUrl("/logout")
					.permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		.usersByUsernameQuery("select username, password, true as enabled from Users where username = ?")
    		.groupAuthoritiesByUsername(
				"select g.id, g.name, auth.authority " + 
				"from Users u, UserGroupAssn n, UserGroup g, UserGroupAuthority auth " + 
				"where u.username = ? and u.id = n.user_id and g.id = n.group_id and g.id = auth.group_id"
			).getUserDetailsService().setEnableAuthorities(false);
    }
}