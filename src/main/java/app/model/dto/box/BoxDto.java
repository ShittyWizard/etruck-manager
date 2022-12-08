package app.model.dto.box;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxDto {
    private float weight;
    private String code;
    private String imageUrl;
}
