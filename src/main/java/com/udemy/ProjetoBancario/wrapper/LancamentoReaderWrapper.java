package com.udemy.ProjetoBancario.wrapper;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;
import com.udemy.ProjetoBancario.processor.LancamentoBancarioProcessor;

public class LancamentoReaderWrapper implements ResourceAwareItemReaderItemStream<LancamentoBancarioDto> {

    private final FlatFileItemReader<LancamentoBancarioDto> delegate;
    private final LancamentoBancarioProcessor processor;

    public LancamentoReaderWrapper(FlatFileItemReader<LancamentoBancarioDto> delegate,
                                   LancamentoBancarioProcessor processor) {
        this.delegate = delegate;
        this.processor = processor;
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    	processor.setResource(resource);
        
    }

    @Override
    public LancamentoBancarioDto read() throws Exception {
        return delegate.read();
    }

    @Override
    public void open(ExecutionContext executionContext) {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) {
        delegate.update(executionContext);
    }

    @Override
    public void close() {
        delegate.close();
    }
}

