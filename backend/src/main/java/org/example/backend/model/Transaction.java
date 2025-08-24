package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.model.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data // Lombok generates getters, setters, toString, equals, and hashCode methods automatically
@NoArgsConstructor // Lombok generates a no-argument constructor
@AllArgsConstructor // Lombok generates a constructor with arguments for all fields
@Builder
public class Transaction {

    @Id
    private String id; // MongoDB will generate an ObjectId if this is left as null

    private TransactionType transactionType;
    private String time;
    private Integer productId;
    private Integer quantity;

}
