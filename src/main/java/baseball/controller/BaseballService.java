package baseball.controller;

import baseball.common.random.Random;
import baseball.dto.input.BaseballDto;
import baseball.dto.output.GameResultDto;
import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseballService {

    private BaseballService() {
        throw new AssertionError();
    }

    public static Baseball createAnswerBaseball() {
        Set<Integer> uniqueNumbers = getUniqueNumbers();
        List<BaseballNumber> baseballNumbers = convertUniqueBaseballNumbers(uniqueNumbers);
        return Baseball.from(baseballNumbers);
    }

    private static Set<Integer> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        while (uniqueNumbers.size() < Baseball.LENGTH) {
            int uniqueNumber = Random.getNumberInRange(BaseballNumber.MIN_VALUE, BaseballNumber.MAX_VALUE);
            uniqueNumbers.add(uniqueNumber);
        }
        return uniqueNumbers;
    }

    private static List<BaseballNumber> convertUniqueBaseballNumbers(Set<Integer> uniqueNumbers) {
        return uniqueNumbers.stream()
                .map(BaseballNumber::from)
                .toList();
    }

    public static GameResultDto calculateResult(final Baseball answer, final BaseballDto baseballDto) {
        Baseball guess = baseballDto.toBaseball();

        int strikeCount = answer.getStrikeCount(guess);
        int ballCount = answer.getBallCount(guess);
        return new GameResultDto(strikeCount, ballCount);
    }
}
