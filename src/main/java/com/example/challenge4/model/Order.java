package com.example.challenge4.model;

import com.example.challenge4.model.accounts.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_time")
    private LocalDate orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "users_id")
    private Users users;

    private boolean completed;
    private boolean deleted;
}
