package baseball.model;

import baseball.constants.Message;
import java.util.Arrays;
import java.util.EnumMap;

public class BaseballGameResult {

    private final EnumMap<BaseballGameResultType, Integer> resultTypeCounts;

    public BaseballGameResult() {
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

    public boolean isNothing() {
        int ballCount = resultTypeCounts.get(BaseballGameResultType.BALL);
        int strikeCount = resultTypeCounts.get(BaseballGameResultType.STRIKE);

        return ballCount == 0 && strikeCount == 0;
    }

    public String getResultMessage() {
        int strikeCount = resultTypeCounts.get(BaseballGameResultType.STRIKE);
        int ballCount = resultTypeCounts.get(BaseballGameResultType.BALL);

        if (isNothing()) {
            return Message.NOTHING;
        }

        return createResultMessage(ballCount, strikeCount);
    }

    private String createResultMessage(int ballCount, int strikeCount) {
        StringBuilder sb = new StringBuilder();

        if (ballCount > 0) {
            sb.append(String.format("%d%s ", ballCount, Message.BALL));
        }
        if (strikeCount > 0) {
            sb.append(String.format("%d%s", strikeCount, Message.STRIKE));
        }

        return sb.toString()
                .trim();
    }
}
