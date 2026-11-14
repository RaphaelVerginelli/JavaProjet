package fr.formation.musicstore.mapper;

import fr.formation.musicstore.dto.ClientDTO;
import fr.formation.musicstore.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDTO.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .build();
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        return Client.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .build();
    }
}