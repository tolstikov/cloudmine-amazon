package com.cloudaware.cloudmine.amazon;

import com.google.api.server.spi.config.Transformer;

import java.nio.ByteBuffer;

public final class ByteBufferTransformer implements Transformer<ByteBuffer, String> {
    @Override
    public String transformTo(final ByteBuffer buffer) {
        final byte[] bytes;
        if (buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes);
    }

    @Override
    public ByteBuffer transformFrom(final String s) {
        return ByteBuffer.wrap(s.getBytes());
    }
}
