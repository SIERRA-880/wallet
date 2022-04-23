
package com.example.Api.History;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * History entity class. Defines the entity in which the history of transactions is stored
 */
@Entity(name = "TRX_HISTORY")
@Table(name = "TRX_HISTORY")
public class History {


    @Id
    @Column(
            name = "trxID",
            updatable = false,
            nullable = false
    )
    private Long trxID;

    @Column(
            name = "balanceIdViewer",
            updatable = false,
            nullable = false
    )
    private Long balanceIdViewer;

    @Column(
            name = "ibanSender",
            columnDefinition = "CHAR(16)",
            updatable = false
    )
    private String ibanSender;

    @Column(
            name = "insIDReceiver",
            updatable = false
    )
    private Long insIDReceiver;

    @Column(
            name = "ibanReceiver",
            updatable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanReceiver;

    @Column(
            name = "amount",
            updatable = false,
            nullable = false
    )
    private Float amount;

    @Column(
            name = "prevBalance",
            updatable = false,
            nullable = false
    )
    private Float prevBalance;

    @Column(
            name = "nextBalance",
            updatable = false,
            nullable = false
    )
    private Float nextBalance;

    @Column(
            name = "dateTime",
            updatable = false,
            nullable = false
    )
    private LocalDateTime dateTime;

    @Column(
            name = "comments",
            updatable = false,
            columnDefinition = "VARCHAR(255) default ''",
            nullable = false
    )
    private String comments;

    public History(Long trxID, Long balanceIdViewer, String ibanReceiver, Float amount, Float prevBalance, Float nextBalance, LocalDateTime dateTime, String comments) {
        this.trxID = trxID;
        this.balanceIdViewer = balanceIdViewer;
        this.ibanReceiver = ibanReceiver;
        this.amount = amount;
        this.prevBalance = prevBalance;
        this.nextBalance = nextBalance;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public History(Long trxID, Long balanceIdViewer, String ibanSender, Long insIDReceiver, Float amount, Float prevBalance, Float nextBalance, LocalDateTime dateTime, String comments) {
        this.trxID = trxID;
        this.balanceIdViewer = balanceIdViewer;
        this.ibanSender = ibanSender;
        this.insIDReceiver = insIDReceiver;
        this.amount = amount;
        this.prevBalance = prevBalance;
        this.nextBalance = nextBalance;
        this.dateTime = dateTime;
        this.comments = comments;
    }
}


