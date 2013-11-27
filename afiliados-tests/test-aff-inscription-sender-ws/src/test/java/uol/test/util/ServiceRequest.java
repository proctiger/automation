package uol.test.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequest.class);

    private Map<String, String> map;

    private Map<String, String> mapBody;

    private Object body;

    public <T> T doGet(String url,
                       Class<T> clazz) {
        return doGet(url, clazz, MediaType.APPLICATION_JSON_TYPE);
    }

    public <T> T doPost(String url,
                        Class<T> clazz) {
        return doPost(url, clazz, MediaType.APPLICATION_JSON_TYPE);
    }

    public Status doGet(String url,
                        MediaType mediaType) throws Exception {
        ClientRequest request = createClientRequest(url);
        ClientResponse<String> result = null;
        try {
            if (mediaType != null) {
                request.accept(mediaType);
            }
            addParams(request);
            result = request.get(String.class);
            LOGGER.info("O servico '{}' respondeu com a seguinte mensagem: {}.", url, result.getEntity());
            return result.getResponseStatus();
        } catch (Exception e) {
            LOGGER.warn("Erro durante chamada ao metodo GET da URL: {}.", url, e);
            throw e;
        } finally {
            releaseConnection(result);
        }
    }

    public Status doGet(String url) throws Exception {
        return doGet(url, (MediaType) null);
    }

    public Status doPost(String url,
                         MediaType mediaType) throws Exception {
        ClientRequest request = createClientRequest(url);
        ClientResponse<String> result = null;
        try {
            String type;
            if (mediaType != null) {
                LOGGER.info(String.format("Content-Type:%s", type = mediaType.toString()));
            } else {
                LOGGER.info(String.format("Content-Type:%s", type = "text/plain"));
            }
            addParams(request);
            addBody(request, type);
            result = request.post(String.class);
            LOGGER.info("O servico '{}' respondeu com a seguinte mensagem: {}.", url, result.getEntity());
            return result.getResponseStatus();
        } catch (Exception e) {
            LOGGER.warn("Erro durante chamada ao metodo POST da URL: {}.", url, e);
            throw e;
        } finally {
            releaseConnection(result);
        }
    }

    public Status doPost(String url) throws Exception {
        return doPost(url, (MediaType) null);
    }

    public Status doPut(String url) throws Exception {
        return doPut(url, (MediaType) null);
    }

    public Status doPut(String url,
                        MediaType mediaType) throws Exception {
        ClientRequest request = createClientRequest(url);
        ClientResponse<String> result = null;
        try {
            addParams(request);
            String type;
            if (mediaType != null) {
                LOGGER.info(String.format("Content-Type:%s", type = mediaType.toString()));
            } else {
                LOGGER.info(String.format("Content-Type:%s", type = "text/plain"));
            }
            addBody(request, type);
            result = request.put(String.class);
            LOGGER.info("O servico '{}' respondeu com a seguinte mensagem: {}.", url, result.getEntity());
            return result.getResponseStatus();
        } catch (Exception e) {
            LOGGER.warn("Erro durante chamada ao metodo PUT da URL: {}.", url, e);
            throw e;
        } finally {
            releaseConnection(result);
        }
    }

    private ClientRequest createClientRequest(String url) {
        ClientRequest request = new ClientRequest(url);
        return request;
    }

    private void addParams(ClientRequest request) {
        if (map != null && !map.isEmpty()) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key;
                request.queryParameter(key = it.next().toString(), map.get(key));
            }
        }
    }

    private void addBody(ClientRequest request,
                         String type) {
        Object mapped;
        if ((mapped = mapParams()) != null) {
            request.body(type, mapped);
        }
    }

    private Object mapParams() {
        StringBuilder builder = new StringBuilder();
        if (mapBody != null && !mapBody.isEmpty()) {
            Iterator<String> it = mapBody.keySet().iterator();
            while (it.hasNext()) {
                String key;
                builder.append(String.format("&%s=%s", key = it.next().toString(), mapBody.get(key)));
            }
            builder = new StringBuilder(builder.substring(1));
            return builder.toString();
        }
        return body;
    }

    private <T> void releaseConnection(ClientResponse<T> response) {
        try {
            if (response != null) {
                response.releaseConnection();
            }
        } catch (Exception e) {
            LOGGER.error("erro ao liberar conexao do response", e);
        }
    }

    private <T> T doGet(String url,
                        Class<T> clazz,
                        MediaType accept) {
        HTTPCommand<T> command = new ServiceRequest.HTTPCommand<T>() {

            @Override
            public ClientResponse<T> execute(ClientRequest request,
                                             Class<T> clazz) throws Exception {
                return request.get(clazz);
            }
        };
        return doHttpMethod(url, clazz, accept, command);
    }

    private <T> T doPost(String url,
                         Class<T> clazz,
                         MediaType accept) {
        HTTPCommand<T> command = new ServiceRequest.HTTPCommand<T>() {

            @Override
            public ClientResponse<T> execute(ClientRequest request,
                                             Class<T> clazz) throws Exception {
                return request.post(clazz);
            }
        };
        return doHttpMethod(url, clazz, accept, command);
    }

    private <T> T doHttpMethod(String url,
                               Class<T> clazz,
                               MediaType accept,
                               HTTPCommand<T> command) {
        ClientRequest request = createClientRequest(url);
        request.accept(accept);
        ClientResponse<T> result = null;
        try {
            addParams(request);
            result = command.execute(request, clazz);
            if (result.getResponseStatus() == Response.Status.OK || result.getResponseStatus() == Response.Status.ACCEPTED) {
                T entity;
                LOGGER.info("O servico '{}' respondeu com a seguinte mensagem: {}", url, (entity = result.getEntity(clazz)).toString());
                return entity;
            }
        } catch (Exception e) {
            LOGGER.warn("Erro durante chamada na URL: {}.", url, e);
        } finally {
            releaseConnection(result);
        }
        return null;
    }

    public void setParameters(HashMap<String, String> map) {
        this.map = map;
    }

    public void setBody(HashMap<String, String> mapBody) {
        this.mapBody = mapBody;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    private static interface HTTPCommand<T> {

        public ClientResponse<T> execute(ClientRequest request,
                                         Class<T> clazz) throws Exception;
    }
}