package pl.dk.transactiontrouble.processing;

import org.springframework.context.ApplicationEvent;

public class FileProcessedEvent extends ApplicationEvent {

    private String fileName;

    private Long linesProcessed;

    public FileProcessedEvent(Object source, String fileName, Long linesProcessed) {
        super(source);
        this.fileName = fileName;
        this.linesProcessed = linesProcessed;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getLinesProcessed() {
        return linesProcessed;
    }

}
