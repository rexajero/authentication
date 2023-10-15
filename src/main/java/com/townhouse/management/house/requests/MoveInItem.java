package com.townhouse.management.house.requests;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class MoveInItem {
    @SequenceGenerator(
        name = "item_sequence",
        sequenceName = "item_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "item_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="request_id", nullable=false)
    private Request request;

    private String name;
    private String description;
    private int quantity;
    private String remarks;
    public MoveInItem(Request request, String name, String description, int quantity, String remarks) {
        this.request = request;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.remarks = remarks;
    }
}
