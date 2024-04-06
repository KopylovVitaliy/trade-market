package com.trademarket.model.dto;

import com.trademarket.model.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDto implements Serializable {
    private UUID id;
    private String email;
    private String firstName;
    private String secondName;
    private String phone;

    private ImageDto image;

    private Role role;

    private List<UUID> advertisements;

    private List<UUID> comments;
}
