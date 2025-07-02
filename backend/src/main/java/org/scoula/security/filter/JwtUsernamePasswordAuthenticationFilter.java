package org.scoula.security.filter;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandelr;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Log4j2
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandelr loginFailureHandelr){
        super(authenticationManager);
        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(loginFailureHandelr);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException {
        //요청BODY의JSON에서username,passwordLoginDTO
        LoginDTO login= LoginDTO.of(request);
        //인증토큰(UsernamePasswordAuthenticationToken)구성
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        //AuthenticationManager에게인증요청
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
