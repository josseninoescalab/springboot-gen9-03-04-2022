package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Archivo;

public interface IArchivoService {
	
	int guardar(Archivo archivo);
	byte[] leerArchivo(Integer idArchivo);

}
