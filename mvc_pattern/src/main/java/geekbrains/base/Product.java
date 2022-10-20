package geekbrains.base;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private double cost;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}' + "\n";
    }
}
