package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long transactionId;

    private Date transactionDate;

    private TransactionType type;

    private TransactionStatus status;

    @JoinColumn(name = "clientId")
    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;

    @Column(name = "clientId", insertable = false, updatable = false)
    private Long clientId;

    @JoinColumn(name = "vendorId")
    @ManyToOne(targetEntity = Vendor.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Vendor vendor;

    @Column(name = "vendorId", insertable = false, updatable = false)
    private Long vendorId;

    @UpdateTimestamp
    private Timestamp lastModified;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Set<TransactionProduct> products;

    public Transaction(Date transactionDate, TransactionType type, TransactionStatus status, Client client, Vendor vendor) {
        this.transactionDate = transactionDate;
        this.type = type;
        this.status = status;
        this.client = client;
        this.vendor = vendor;
//        this.client_id = client.getClient_id();
    }
}
