package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.request.RabbitRequest;
import com.trademarket.model.response.RabbitResponse;
import com.trademarket.services.mapper.CommonMapper;
import com.trademarket.services.service.AbstractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


@Log4j2
public abstract class AbstractListener<D, R extends JpaRepository<E, UUID>,
        E, M extends CommonMapper<D, E>, S extends AbstractService<R ,D, E, M>> {

    protected abstract S getService();
    protected abstract ObjectMapper getObjectMapper();

    public String commonListen(String request) throws JsonProcessingException {
        RabbitRequest rabbitRequest = getObjectMapper().readValue(request, RabbitRequest.class);
        log.info(rabbitRequest.getTypeOperation());
        if("save".equals(rabbitRequest.getTypeOperation())) {
            D body = getObjectMapper().readValue(rabbitRequest.getBody(), new TypeReference<D>() {});
            return getObjectMapper().writeValueAsString(getService().save(body));
        } else if ("getById".equals(rabbitRequest.getTypeOperation())){
            UUID body = UUID.fromString(rabbitRequest.getBody());
            return getObjectMapper().writeValueAsString(getService().getById(body));
        } else if ("getAll".equals(rabbitRequest.getTypeOperation())) {
            return getObjectMapper().writeValueAsString(getService().getAll());
        } else if ("edit".equals(rabbitRequest.getTypeOperation())) {
            D body = getObjectMapper().readValue(rabbitRequest.getBody(), new TypeReference<D>() {
            });
            return  getObjectMapper().writeValueAsString(getService().edit(body));
        }
        return new RabbitResponse<D>("метод не найден", null).toString();
    }

    public abstract String listen(String request) throws JsonProcessingException;
}
