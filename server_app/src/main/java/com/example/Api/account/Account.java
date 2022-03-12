package com.example.Api.account;

import com.example.Api.wallet.Wallet;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Table(name = "ACCOUNTS")
@Entity(name = "ACCOUNTS")
public class Account {

    @Id
    @Column(
            name = "iban",
            updatable = false,
            nullable = false,
            columnDefinition = "CHAR(16)"
    )
    private String iban;

    @ManyToOne
    @JoinColumn(
            name = "walletID",
            referencedColumnName = "walletID",
            foreignKey = @ForeignKey(
                    name = "ACCOUNTS_WALLETS_walletID_fk"
            )
    )
    private Wallet wallet;

    @Column(
            name = "bic",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(8)"
    )
    private String bic;

    @Column(
            name = "type",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(2)"
    )
    private String type;

    @Column(
            name = "avgBalance",
            nullable = false
    )
    private Float avgBalance;

    @Column(
            name = "localCurr",
            updatable = false,
            nullable = false,
            columnDefinition = "CHAR(3)"
    )
    private String localCurr;

    @Column(
            name = "activity",
            nullable = false
    )
    private Integer activity;
}