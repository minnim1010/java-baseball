package baseball.model;

import java.util.Arrays;
import java.util.EnumMap;

public class GameResult {

    private final EnumMap<BaseballGameResultType, Integer> resultTypeCounts;

    public GameResult() {
        resultTypeCounts = new EnumMap<>(BaseballGameResultType.class);
        Arrays.stream(BaseballGameResultType.values())
                .forEach(type -> resultTypeCounts.put(type, 0));
    }

    public int getCount(BaseballGameResultType type) {
        return resultTypeCounts.get(type);
    }

    @Override
    public String toString() {
        return resultTypeCounts.toString();
    }

    public void add(BaseballGameResultType type) {
        resultTypeCounts.put(type, resultTypeCounts.get(type) + 1);
    }

    public boolean isClear() {
        int strikeCount = resultTypeCounts.get(BaseballGameResultType.STRIKE);

        return strikeCount == Baseball.LENGTH;
    }
}
