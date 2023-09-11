package com.devsuperior.desafio3.service;

import com.devsuperior.desafio3.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    ClientDTO findById(Long id);
    Page<ClientDTO> findAll(String name, Pageable pageable);
    ClientDTO insert(ClientDTO dto);
    ClientDTO update(Long id, ClientDTO dto);
    void delete(Long id);
}
