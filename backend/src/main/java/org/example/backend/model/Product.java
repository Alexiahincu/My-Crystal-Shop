package org.example.backend.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data // Lombok generates getters, setters, toString, equals, and hashCode methods automatically
@NoArgsConstructor // Lombok generates a no-argument constructor
@AllArgsConstructor // Lombok generates a constructor with arguments for all fields
@Builder
public class Product {

    @Id
    private String id; // MongoDB will generate an ObjectId if this is left as null

    private ProductType productType;
    private String name;
    private String description;
    private Integer quantity;
    private Integer price;

}
