package pl.dk.transactiontrouble.summary;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Summary {

    @Id
    @SequenceGenerator(name = "s_summary", sequenceName = "s_summary")
    @GeneratedValue(strategy = SEQUENCE, generator = "s_summary")
    private long id;

    @Version
    private Long version;

    private String fileName;

    private Long linesProcessed;
    
    public Summary() {
    }

    public Summary(String fileName, Long linesProcessed) {
        this.fileName = fileName;
        this.linesProcessed = linesProcessed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getLinesProcessed() {
        return linesProcessed;
    }

    public void setLinesProcessed(Long linesProcessed) {
        this.linesProcessed = linesProcessed;
    }

}
