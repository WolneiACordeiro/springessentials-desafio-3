package com.devsuperior.desafio3.service.impl;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.entity.Client;
import com.devsuperior.desafio3.repository.ClientRepository;
import com.devsuperior.desafio3.service.ClientService;
import com.devsuperior.desafio3.service.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional(readOnly = true)
    @Override
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found with id: " + id));
        return modelMapper.map(client, ClientDTO.class);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ClientDTO> findAll(String name, Pageable pageable) {
        Page<Client> result = repository.searchByName(name, pageable);
        return result.map(client -> modelMapper.map(client, ClientDTO.class));
    }
    @Transactional
    @Override
    public ClientDTO insert(ClientDTO dto) {
        Client entity = modelMapper.map(dto, Client.class);
        entity = repository.save(entity);
        return modelMapper.map(entity, ClientDTO.class);
    }
    @Transactional
    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf()) ;
            entity.setIncome(dto.getIncome()) ;
            entity.setBirthDate(dto.getBirthDate());
            entity.setChildren(dto.getChildren());
            entity = repository.save(entity);
            return modelMapper.map(entity, ClientDTO.class);
        } catch (HttpMessageNotReadableException e) {
            throw new HttpMessageNotReadableException("Invalid JSON format");
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential Integrity Failure");
        }
    }
}
