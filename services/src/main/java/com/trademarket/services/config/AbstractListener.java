package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        E, M extends CommonMapper<D, E>, S extends AbstractService<R, D, E, M>> {

    protected abstract S getService();

    protected abstract ObjectMapper getObjectMapper();

    protected abstract Class<D> getDtoClass();

    public String commonListen(String request) {
        RabbitRequest rabbitRequest;
        RabbitResponse response;
        try {
            rabbitRequest = getObjectMapper().readValue(request, RabbitRequest.class);

            log.info(rabbitRequest.getTypeOperation());
            if ("save".equals(rabbitRequest.getTypeOperation())) {
                response = save(rabbitRequest);
            } else if ("getById".equals(rabbitRequest.getTypeOperation())) {
                response = getById(rabbitRequest);
            } else if ("getAll".equals(rabbitRequest.getTypeOperation())) {
                response = getAll(rabbitRequest);
            } else if ("delete".equals(rabbitRequest.getTypeOperation())) {
                response = delete(rabbitRequest);
            } else {
                response = customListen(rabbitRequest);
            }
        } catch (JsonProcessingException e) {
            log.error("Некорректный запрос ", e);
            response = new RabbitResponse("Некорректный запрос ", request);
        }
        try {
            return getObjectMapper().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Некорректное тело запроса ", e);
            return "{\"errorMessage\" : \"ошибка записи\"}";
        }

    }

    protected RabbitResponse customListen(RabbitRequest rabbitRequest) {
        log.error("метод не найден. " + rabbitRequest.getTypeOperation());
        return new RabbitResponse("метод не найден " + rabbitRequest.getTypeOperation(), null);

    }

    private RabbitResponse save(RabbitRequest rabbitRequest) {
        D body;
        try {
            body = getObjectMapper().readValue(rabbitRequest.getBody(), getDtoClass());
            return new RabbitResponse(getObjectMapper().writeValueAsString(getService().save(body)));
        } catch (JsonProcessingException e) {
            log.error("Некорректное тело запроса ", e);
            return new RabbitResponse("Некорректное тело запроса ", rabbitRequest.getBody());
        } catch (Exception e) {
            log.error("Ошибка hibernate ", e);
            return new RabbitResponse("Ошибка hibernate ", rabbitRequest.getBody());
        }
    }

    private RabbitResponse getById(RabbitRequest rabbitRequest) {
        UUID body = UUID.fromString(rabbitRequest.getBody());
        try {
            return new RabbitResponse(getObjectMapper().writeValueAsString(getService().getById(body)));
        } catch (JsonProcessingException e) {
            log.error("Некорректное тело запроса ", e);
            return new RabbitResponse("Некорректное тело запроса ", rabbitRequest.getBody());
        } catch (Exception e) {
            log.error("Ошибка hibernate ", e);
            return new RabbitResponse("Ошибка hibernate ", rabbitRequest.getBody());
        }
    }

    private RabbitResponse getAll(RabbitRequest rabbitRequest) {
        try {
            return new RabbitResponse(getObjectMapper().writeValueAsString(getService().getAll()));
        } catch (JsonProcessingException e) {
            log.error("Некорректное тело запроса ", e);
            return new RabbitResponse("Некорректное тело запроса ", rabbitRequest.getBody());
        } catch (Exception e) {
            log.error("Ошибка hibernate ", e);
            return new RabbitResponse("Ошибка hibernate ", rabbitRequest.getBody());
        }
    }

    private RabbitResponse delete(RabbitRequest rabbitRequest) {
        UUID body = UUID.fromString(rabbitRequest.getBody());
        try {
            getService().delete(body);
            return new RabbitResponse("объект c id: " + body + " удалён");
        } catch (Exception e) {
            log.error("Ошибка hibernate ", e);
            return new RabbitResponse("Ошибка hibernate ", rabbitRequest.getBody());
        }
    }

    public abstract String listen(String request);
}
