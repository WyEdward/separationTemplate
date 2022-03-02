package cn.wyedward.auth.jwt;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;
@Data
public class JwtToken implements AuthenticationToken, Serializable {

    private static final long serialVersionUID = -6276317479540014538L;

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
