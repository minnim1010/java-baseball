package baseball.service;

import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import baseball.model.GameResult;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseballService {

    private static BaseballService INSTANCE;

    private BaseballService() {
    }

    public static BaseballService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BaseballService();
        }
        return INSTANCE;
    }

    public Baseball createAnswerBaseball() {
        Set<Integer> uniqueNumbers = getUniqueNumbers();
        List<BaseballNumber> baseballNumbers = convertUniqueBaseballNumbers(uniqueNumbers);
        return Baseball.from(baseballNumbers);
    }

    private Set<Integer> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        while (uniqueNumbers.size() < Baseball.LENGTH) {
            int uniqueNumber = Randoms.pickNumberInRange(BaseballNumber.MIN_VALUE, BaseballNumber.MAX_VALUE);
            uniqueNumbers.add(uniqueNumber);
        }
        return uniqueNumbers;
    }

    private List<BaseballNumber> convertUniqueBaseballNumbers(Set<Integer> uniqueNumbers) {
        return uniqueNumbers.stream()
                .map(BaseballNumber::new)
                .toList();
    }

    public GameResult calculateResult(final Baseball answer, final Baseball guess) {
        return answer.compareTo(guess);
    }
}
