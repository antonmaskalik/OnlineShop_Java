package api;

import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestAssuredLogger extends PrintStream {

    public RestAssuredLogger() {
        super(new OutputStream() {
            @Override
            public void write(int b) {
            }

            @Override
            public void write(byte[] b, int off, int len) {
                String message = new String(b, off, len, StandardCharsets.UTF_8).trim();
                log.info(message);
            }
        });
    }
}
