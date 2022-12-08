package app.model.mapper.box;

import app.model.entity.box.Box;
import app.model.dto.box.BoxDto;

public class BoxMapper {
    public static Box toEntity(BoxDto box) {
        if (box == null) {
            return null;
        }
        return new Box(
                box.getWeight(),
                box.getCode(),
                box.getImageUrl(),
                null
        );
    }

    public static BoxDto toDto(Box boxEntity) {
        if (boxEntity == null) {
            return null;
        }
        return new BoxDto(
                boxEntity.getWeight(),
                boxEntity.getCode(),
                boxEntity.getImageUrl()
        );
    }
}
