package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Usuario;

public interface ILoginService {
	
	Usuario verificarNombreUsuario(String usuario) throws Exception;
	int cambiarClave(String clave, String nombre) throws Exception;
}
