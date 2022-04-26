package com.escalab.mediappbackend.service;

import java.util.List;

import com.escalab.mediappbackend.model.Menu;

public interface IMenuService extends ICRUD<Menu> {
	
	List<Menu> listarMenuPorUsuario(String nombre);

}
