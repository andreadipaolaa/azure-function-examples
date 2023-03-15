package it.ringmaster.functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerJava {
    /**
     * This function listens at endpoint "/api/HttpTriggerJava". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerJava
     * 2. curl {your host}/api/HttpTriggerJava?name=HTTP%20Query
     */
    @FunctionName("HttpTriggerJava")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Void> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        Map<String, String> map =new HashMap<>();
        map.put("test", "hello");

        return request.createResponseBuilder(HttpStatus.OK).body(map).build();
    }

    @FunctionName("TestGet")
    public HttpResponseMessage getTest(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "get/{name}") HttpRequestMessage<Void> request,
            @BindingName("name") String name,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        Map<String, String> map =new HashMap<>();
        map.put("nome", "ciao " + name);

        return request.createResponseBuilder(HttpStatus.OK).body(map).build();
    }

    @FunctionName("TestGetQuery")
    public HttpResponseMessage getTestQuery(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "getQuery") HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        String name = request.getQueryParameters().getOrDefault("name", "Pasquale");
        Map<String, String> map =new HashMap<>();
        map.put("nome", "ciao " + name);

        return request.createResponseBuilder(HttpStatus.OK).body(map).build();
    }

    @FunctionName("TestPost")
    public HttpResponseMessage postTest(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, route = "post") HttpRequestMessage<Map<String,String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        Map<String, String> map = request.getBody();

        return request.createResponseBuilder(HttpStatus.OK).body(map).build();
    }
}
