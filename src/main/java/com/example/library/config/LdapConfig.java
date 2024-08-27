package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;


@Configuration
public class LdapConfig {

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldaps://***.univ-amu.fr:636");
        contextSource.setBase("dc=univ-amu,dc=fr");
        contextSource.setUserDn("cn=***,ou=system,dc=univ-amu,dc=fr");
        contextSource.setPassword("***");
        return contextSource;
    }

//    @Bean
//    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
//        CasAuthenticationFilter filter = new CasAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setFilterProcessesUrl("/login/cas"); // Set to CAS login URL
//        return filter;
//    }
}
