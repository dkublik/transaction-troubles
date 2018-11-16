package pl.dk.transactiontrouble.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void publish(ApplicationEvent event) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            publishAfterCommit(event);
        } else {
            publishNonTransactional(event);
        }
    }

    private void publishAfterCommit(final ApplicationEvent event) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
            public void afterCommit() {
                publishNonTransactional(event);
            }
        });
    }

    private void publishNonTransactional(ApplicationEvent e) {
        applicationEventPublisher.publishEvent(e);
    }
}
