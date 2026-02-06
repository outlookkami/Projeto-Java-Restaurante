package com.kamilly.meson.config.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UsuarioInativoException extends AuthenticationException {
    public UsuarioInativoException() {
        super("Seu usuário está inativo. Para mais informações entre em contato com o administrador.");
    }
}
