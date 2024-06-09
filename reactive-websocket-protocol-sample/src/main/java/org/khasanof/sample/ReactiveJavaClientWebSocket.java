package org.khasanof.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.model.ws.WsRequest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Nurislom
 * @see org.khasanof.sample
 * @since 5/29/2024 2:08 PM
 */
@Slf4j
public class ReactiveJavaClientWebSocket {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, URISyntaxException {
//        WebSocketClient client = new ReactorNettyWebSocketClient();
//        int count = 100;
//        Flux<String> input = Flux.interval(Duration.ofSeconds(3)).map(index -> "msg-" + index);
//        ReplayProcessor<Object> output = ReplayProcessor.create(count);
//
//        client
//            .execute(
//                new URI("ws://localhost:8080/reactive"),
//                session -> {
//                    log.debug("Starting to send messages");
//                    return session
//                        .send(input.doOnNext(s -> log.debug("outbound " + s))
//                            .map(session::textMessage))
//                        .thenMany(session.receive()
//                            .take(count)
//                            .map(WebSocketMessage::getPayloadAsText))
//                        .subscribeWith(output)
//                        .doOnNext(s -> log.debug("inbound " + s))
//                        .then();
//                }
//            )
//            .block();

        WebSocketClient client = new ReactorNettyWebSocketClient();

        WsRequest wsRequest = new WsRequest();
        wsRequest.setId("32895643543543");
        wsRequest.setMethod("handle");
        wsRequest.setData(new Message("hello", "john"));

        String textMessage = objectMapper.writeValueAsString(wsRequest);

        ReplayProcessor<String> output = ReplayProcessor.create();

        client.execute(
            new URI("ws://localhost:8080/reactive"),
            session -> {
                // Send a single message
                return session.send(Mono.just(session.textMessage(textMessage))
                        .doOnNext(msg -> System.out.println("Sent: " + msg.getPayloadAsText())))
                    .thenMany(session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .doOnNext(msg -> System.out.println("Received: " + msg))
                        .subscribeWith(output)
                        .next())
                    .then();
            }
        ).block();

        // Retrieve the response
        String response = output.blockFirst();
        if (response != null) {
            System.out.println("Final received message: " + response);
        } else {
            System.out.println("No message received within the timeout period.");
        }
    }
}
