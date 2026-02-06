package com.kamilly.meson.config.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RestauranteInativoException extends AuthenticationException {
    public RestauranteInativoException() {
        super("O restaurante em que você está cadastrado está inativo. Entre em contato com seu administrador ou com o SAC do Meson para mais informações.");
    }
}
