package pl.dk.transactiontrouble.summary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.dk.transactiontrouble.processing.FileProcessedEvent;

@Component
public class SummaryMaker {

    @Autowired
    private SummaryRepository summaryRepository;
    
    @EventListener
    // TODO uncommit below to make it work
    @Transactional/*(propagation = Propagation.REQUIRES_NEW)*/
    public void createSummary(FileProcessedEvent event) {
        System.out.println("--- creating summary");
        summaryRepository.save(
                new Summary(event.getFileName(), event.getLinesProcessed())
        );
    }
    
}
