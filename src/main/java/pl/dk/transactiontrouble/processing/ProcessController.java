package pl.dk.transactiontrouble.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.transactiontrouble.base.EventPublisher;

@RestController
@RequestMapping(value = "/")
class ProcessController {

    private static final Long LINES_PROCESSED = 36l;
    
    private static final String FILE_NAME_PREFIX = "file-to-test_"; 
    
    @Autowired
    private EventPublisher eventPublisher;
    
    @GetMapping
    @Transactional
    public String processFile() {
        // do some processing
        String fileName = getFileNameToProcess();
        eventPublisher.publish(new FileProcessedEvent(this, fileName, LINES_PROCESSED));
        return "processed: " + fileName;
    }

    private String getFileNameToProcess() {
        return FILE_NAME_PREFIX + System.currentTimeMillis();
    }
    
}